package com.study.android.team_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.helper.Base64;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    NetworkStatsManager networkStatsManager;

    //********* 앱 로그인 ***************
    Button loginBtn;
    EditText idet, pwet;
    String result;


    private static final int RC_SIGN_IN = 1001;

    private Object mMyData;



    //Firebase - Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    private static String userName;

    // Views
    private SignInButton mBtnGoogleSignIn; //로그인버튼
    private Button mBtnGoogleSignOut; //로그아웃 버튼
    private TextView mTxtProfileInfo;  //사용자 정보 표시


    //****** 카카오 로그인******
    private static final String TAG = "";
    private SessionCallback sessionCallback;

    //****** 네이버 로그인******

    private static String OAUTH_CLIENT_ID = "zksx9n376OoJyp6OYMaO";
    private static String OAUTH_CLIENT_SECRET = "JMaJ_cm9Lv";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";

    public static OAuthLoginButton mOAuthLoginButton;
    public static OAuthLogin mOAuthLoginInstance;

    public static Context mContext;


    String kaUserId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loginBtn = findViewById(R.id.login);
        idet = findViewById(R.id.id);
        pwet = findViewById(R.id.pwd);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    String id = idet.getText().toString();
                    String pwd = pwet.getText().toString();

                    LoginAsyncTask task = new LoginAsyncTask();
                    result = task.execute(id, pwd).get();
                    Log.d(TAG, "통신 결과 :  " + result);

                    if (result.equals( "로그인 성공" )){

                        Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                        Toast.makeText( getApplication(), "로그인 성공. "+ id + " 님 환영합니다.", Toast.LENGTH_SHORT ).show();
                        intent.putExtra( "userId", id );
                        startActivity( intent );

                    }else{
                        Toast.makeText( getApplication(), "로그인 실패. 아이디나 비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e) {
                    Log.d(TAG, "ERROR!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
        });

        //***** 구글 로그인
        initViews();
        initFirebaseAuth();
        //***************

        //****** 카카오 로그인******

        try {
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("aaaa", Base64.encodeBase64URLSafeString(messageDigest.digest()));
            }

        } catch (Exception e) {
            Log.d("error", "PackageInfo error is : " + e.toString());
        }

        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
        // requestMe();

        //*****네이버 로그인******
        mContext = LoginActivity.this;

        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.showDevelopersLog(true);
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);


        mOAuthLoginButton = findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void forceLogin(){
        mOAuthLoginInstance.startOauthLoginActivity((Activity)mContext,mOAuthLoginHandler);
    }

    //로그 아웃 처리(토큰 함께 삭제)
    public void forceLogout(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                mOAuthLoginInstance.logoutAndDeleteToken(mContext);
            }
        }).start();
    }

    //로그인 처리 핸들러
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            //로그인 인증 성공


            if(success){
                //사용자 정보 가져오기
                String accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                String refreshToken = mOAuthLoginInstance.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                String tokenType = mOAuthLoginInstance.getTokenType(mContext);


                //new RequestApiTask(accessToken).execute();

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);


            }else {
                //로그인 인증 실패
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode : " + errorCode + ", errorDesc : " + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };


    //************* 네이버 로그인 끝 **********************



    //*********구글 로그인 ************
    private void initViews() {
        mBtnGoogleSignIn = findViewById(R.id.btn_google_signin);
        mBtnGoogleSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mTxtProfileInfo = findViewById(R.id.txt_profile_info);
        mBtnGoogleSignOut = findViewById(R.id.btn_google_signout);
    }

    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gsio = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gsio)
                .build();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateProfile();
            }
        };
    }

    private void updateProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // 비 로그인 상태
            mBtnGoogleSignIn.setVisibility(View.VISIBLE);
            mBtnGoogleSignOut.setVisibility(View.GONE);
            mTxtProfileInfo.setVisibility(View.GONE);
        } else {
            // 로그인 상태
            mBtnGoogleSignIn.setVisibility(View.GONE);
            mBtnGoogleSignOut.setVisibility(View.VISIBLE);
            mTxtProfileInfo.setVisibility(View.VISIBLE);

            userName = user.getDisplayName(); // 채팅에 사용 될 닉네임 설정
            mTxtProfileInfo.setText(userName+"님");
            Log.d(TAG,userName);
        }
    }

    public void signIn() {
        Intent singIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(singIntent, RC_SIGN_IN);
    }

    public void signOut(View v){
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateProfile();
                        userName = null;
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {

            return;
        }
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();


                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                super.onActivityResult(requestCode, resultCode, data);

            }
        }
    }
    //********** 구글 로그인 끝***************

    // *************카카오 로그인 ****************
    public void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {

            @Override
            public void onSessionClosed(ErrorResult errorResult){
                Log.d("error", "Session Closed Error is : " + errorResult.toString());
            }

            @Override
            public void onNotSignedUp(){

            }

            @Override
            public void onSuccess(UserProfile result){
                Toast.makeText(LoginActivity.this, "사용자 이름은 " + result.getNickname(), Toast.LENGTH_SHORT).show();

                kaUserId = result.getNickname();

                Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                intent.putExtra( "kaUserId", kaUserId);
                startActivity( intent );

                return;

            }

        });


    }



    private class SessionCallback implements ISessionCallback {


        @Override
        public void onSessionOpened() {
            requestMe();


        }

        @Override
        public void onSessionOpenFailed(KakaoException exception){
            Log.d("error", "Session Fail Error is : " + exception.getMessage().toString());
        }

    }

    //********* 카카오 로그인 끝*****************

    //*********비밀번호 암호화*******************
    public static String encryptSHA512(String target){
        try{
            MessageDigest sh = MessageDigest.getInstance( "SHA-512" );
            sh.update( (target.getBytes()) );

            StringBuffer sb = new StringBuffer(  );

            for(Byte b : sh.digest())
                sb.append( Integer.toHexString( 0xff & b ) );

            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException( e );
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Session.getCurrentSession().removeCallback( sessionCallback );
    }

}