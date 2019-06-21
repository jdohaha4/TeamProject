package com.study.android.team_project;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

public class MyPageSignPet extends AppCompatActivity {
    private static final String TAG = "lecture";

    private Button btChoose;
    private Button btUpload;
    private ImageView ivPreview;
    private Uri filePath;

    private Button btDate;
    final int DIALOG_DATE = 1;
    String id;

    FirebaseFirestore db;

    EditText etName;
    EditText etKind;
    EditText etBirth;
    EditText etWeight;


    RadioButton btMale;
    RadioButton btFemale;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child( "PetName, Sex, Kind, Birth, Weight" );
    //    CollectionReference petProfile = db.collection("PetProfile");
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set( Calendar.YEAR, year );
            myCalendar.set( Calendar.MONTH, month );
            myCalendar.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            updateLabe();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_page_sign_pet );

        //******** 이미지 업로드 ****************
        btChoose = findViewById( R.id.bt_choose );
        btUpload = findViewById( R.id.bt_upload );
        ivPreview = findViewById( R.id.iv_view );

        //************** 데이터 업로드***********
        etName = findViewById( R.id.nameText );
        etKind = findViewById( R.id.kindText );
        etBirth = findViewById( R.id.birthText );
        etWeight = findViewById( R.id.weightText );

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        db = FirebaseFirestore.getInstance();

        dataQuery();

        //버튼 클릭 이벤트
        btChoose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이미지를 선택
                Intent intent = new Intent();
                intent.setType( "image/*" );
                intent.setAction( Intent.ACTION_GET_CONTENT );
                startActivityForResult( Intent.createChooser( intent, "이미지를 선택하세요." ), 0 );
            }
        } );

        btUpload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //업로드
                uploadFile();
                dataInsert();
            }
        } );

        etBirth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog( MyPageSignPet.this, myDatePicker, myCalendar.get( Calendar.YEAR ),
                        myCalendar.get( Calendar.MONTH ),myCalendar.get( Calendar.DAY_OF_MONTH ) ).show();
            }
        } );
    }

    private void updateLabe(){
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat( myFormat, Locale.KOREA );

        etBirth = findViewById( R.id.birthText );
        etBirth.setText( sdf.format( myCalendar.getTime() ) );
    }

    public void dataQuery(){
        // 서버에서 일단 가져온 후에도 서버에서 데이터가 변경되면
        // 폰의 화면에서도 실시간으로 자동 갱신 된다.

        int LIMIT = 30;
        db.collection( "PetProfile" )
                .addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {

                        if (e != null){
                            Log.d( TAG, "Listen failed.", e );
                            return;
                        }

                        String sMax = "0";
                        StringBuilder sb = new StringBuilder(  );
                        sb.append( "" );
                        for (QueryDocumentSnapshot doc : value){
                            if(doc.get( "name" ) != null){
                                sMax = doc.getId();
                                sb.append( sMax + " / " +
                                        doc.getString( "name" ) + " / " +
                                        doc.getString( "kind" ) + " / " +
                                        doc.getString( "sex" ) + " / " +
                                        doc.getString( "birth" ) + " / " +
                                        doc.getString( "weight" ) + "\n");
                            }
                        }
                    }

                } );

        Intent intent = getIntent();
        id = intent.getStringExtra( "userId" );

    }

    public void dataInsert(){

        //데이터 준비
        Map<String, Object> quote = new HashMap<>(  );
        quote.put("name", etName.getText().toString());
        quote.put("kind", etKind.getText().toString());
        quote.put("birth", etBirth.getText().toString());
        quote.put("weight", etWeight.getText().toString());
        quote.put( "sex",   Checked(  ));

        //petProfile.document(newCount).set( quote );


        Intent intent = getIntent();
        id = intent.getStringExtra( "userId" );

        String newCount = String.format( id );

        db.collection( "PetProfile" )
                .document(newCount)
                .set( quote )
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d( TAG, "DocumentSnapshot successfully written!" );
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w( TAG, "Error writing document", e );
                    }
                } );
    }

    public String Checked(){

        btMale = findViewById( R.id.btnMale ) ;

        btFemale = findViewById( R.id.btnFemale );

        String resultText = "";

        if(btMale.isChecked()){
            resultText = "Male";

        }
        if (btFemale.isChecked()){
            resultText = "Female";
        }

        return resultText;
    }

    //결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if(requestCode == 0 && resultCode == RESULT_OK){
            filePath = data.getData();
            Log.d(TAG, "uri:" + String.valueOf(filePath));
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivPreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //upload the file
    private void uploadFile() {

        Intent intent = getIntent();

        id = intent.getStringExtra( "userId" );


        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
//            String filename = formatter.format(now) + ".png";
            String filename = id + ".png";

            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://projectteam-a209a.appspot.com/Photo").child( id +"/" + filename);
            //올라가거라...
            final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = storageRef.putFile( filePath )
                    //성공시
                    .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText( getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT ).show();
                            finish();
                        }
                    } )
                    //실패시
                    .addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText( getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT ).show();
                        }
                    } )
                    //진행중
                    .addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage( "Uploaded " + ((int) progress) + "% ..." );
                        }
                    } );
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
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


