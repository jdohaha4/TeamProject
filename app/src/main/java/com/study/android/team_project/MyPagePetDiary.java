package com.study.android.team_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MyPagePetDiary extends AppCompatActivity {

    private static final String TAG = "lecture";

    String id = "";
    ImageView diaryView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_pet_diary);

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button diaryData = findViewById( R.id.btn_diary_data);
        diaryData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                downloadImage();
                initProgram();
                printImageList();
            }
        } );
    }


    public void initProgram() {

        diaryView = findViewById(R.id.diary_view);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        printImageList();
        downloadImage();
    }

    public void downloadImage() {


        String folderName = "images";
        String imageName = "20190623_1838.png";

        Intent intent = getIntent();

        id = intent.getStringExtra( "userId" );

        String newCount = String.format( id );

        //Firebasestorage 인스턴스 만들기
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        long size;
        //point to the images's root reference
        StorageReference storageRef = storage.getReference();
        //예제라서 업로드파일이름을 넣어 보았다. 시작시 파일명이 없으므로 임시로 하나를 넣었다.
        final StorageReference pathReference = storageRef.child("PetSitterDiary/images/20190619_5151.png");


        Log.d( TAG,storageRef +"@get");

        String fileName = "20190618_0135.png";
        pathReference.getMetadata()
                .addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                    @Override
                    public void onSuccess(StorageMetadata storageMetadata) {
                        String size = String.valueOf(storageMetadata.getSizeBytes());
                        String type = storageMetadata.getContentType();
                        Log.d(TAG, "조회 성공!");


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "조회 실패!");
                    }
                });

        // Storage 이미지 다운로드 경로
        String storagePath = folderName + "/" + imageName;

//        StorageReference imageRef = mStorageRef.child(storagePath);

        try {
            // Storage 에서 다운받아 저장시킬 임시파일
            final File imageFile = File.createTempFile("images", "jpg");
            pathReference.getFile(imageFile).addOnSuccessListener(
                    new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Success Case
                            Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getPath());
                            diaryView.setImageBitmap(bitmapImage);
                            Toast.makeText(getApplicationContext(), "Success !!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail Case
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Fail !!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        diaryView = findViewById( R.id.diary_view );

        GlideApp.with( MyPagePetDiary.this )
                .load( pathReference )
                .into( diaryView );
    }

    public void printImageList(){

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
