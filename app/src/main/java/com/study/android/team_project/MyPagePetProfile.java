package com.study.android.team_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


public class MyPagePetProfile extends AppCompatActivity {

    private static final String TAG = "lecture";

    EditText etName;
    EditText etKind;
    EditText etBirth;
    EditText etWeight;
    EditText etSex;
    String id = "";
    ImageView imageView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    StorageReference mStorageRef;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_page_pet_profile );

        etName = findViewById( R.id.nameText );
        etKind = findViewById( R.id.kindText );
        etBirth = findViewById( R.id.birthText );
        etWeight = findViewById( R.id.weightText );
        etSex = findViewById( R.id.sexText );

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        checkPermissions();

        Button dwData = findViewById( R.id.btnDwData );
        dwData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataRead();
                downloadImage();
                initProgram();
                printImageList();
            }
        } );
    }


    public void initProgram() {

        imageView = findViewById(R.id.imageView);

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

        final StorageReference pathReference = storageRef.child("Photo/"+ id + "/" + id + ".png");


        Log.d( TAG,storageRef +"@get");

        String fileName = "20190623_1838.png";
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
                            imageView.setImageBitmap(bitmapImage);
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
        imageView = findViewById( R.id.iv_view );

        GlideApp.with( MyPagePetProfile.this )
                .load( pathReference )
                .into( imageView );
    }

    public void printImageList(){

    }
    public void dataRead() {

        Intent intent = getIntent();

        id = intent.getStringExtra( "userId" );

        String newCount = String.format( id );

        DocumentReference docRef = db.collection( "PetProfile" ).document( newCount );
        docRef.get().addOnSuccessListener( new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                PetDTO petDTO = documentSnapshot.toObject( PetDTO.class );
                etName.setText( petDTO.getName() );
                etBirth.setText( petDTO.getBirth() );
                etKind.setText( petDTO.getKind() );
                etSex.setText( petDTO.getSex() );
                etWeight.setText( petDTO.getWeight() );
            }
        } );
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

