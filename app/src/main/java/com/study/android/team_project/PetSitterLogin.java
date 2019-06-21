package com.study.android.team_project;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;




public class PetSitterLogin extends AppCompatActivity {

    private static final String TAG = "lecture";
    Button loginBtn;
    EditText idet, pwet;


    public static String result;
    public static String petId;

    public static Context mContext;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pet_sitter_login );

        FragmentManager manager = getSupportFragmentManager();
        final android.support.v4.app.FragmentTransaction t= manager.beginTransaction();
        final MyPageFragment myPageFragment = new MyPageFragment();

        loginBtn = findViewById( R.id.btn_pet_sit_login );
        idet = findViewById(R.id.pet_sit_id);
        pwet = findViewById(R.id.pet_sit_pwd);

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loginBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                try {
                    String id = idet.getText().toString();
                    String pwd = pwet.getText().toString();
                    PetSitterLoginAsyncTask task = new PetSitterLoginAsyncTask();
                    result = task.execute(id, pwd).get();
                    Log.d(TAG, "통신 결과 :  " + result);


                    if (result.equals( "로그인 성공" )){

                        petId = id;


                        Intent intent = new Intent(PetSitterLogin.this, MainActivity.class);
                        Toast.makeText( getApplication(),"로그인 성공. 팻시터"+petId+"님 환영합니다.", Toast.LENGTH_LONG ).show();
                        intent.putExtra( "result", result );
                        intent.putExtra( "petId", petId );
                        startActivity( intent );




                    }
                    else if(id == null || pwd == null){
                        Toast.makeText( mContext, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT ).show();
                    }
                    else{
                        Toast.makeText(mContext, result + " 아이디나 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        startActivityForResult(intent ,0);
                    }




                } catch (Exception e) {
                    Log.d(TAG, "ERROR!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
        });
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
}
