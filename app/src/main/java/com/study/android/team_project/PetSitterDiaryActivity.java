package com.study.android.team_project;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PetSitterDiaryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener,
                    GoogleApiClient.ConnectionCallbacks,
                    ActivityCompat.OnRequestPermissionsResultCallback,
                    LocationListener {

    private static final String TAG = "lecture";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSON_REQUEST_CODE = 200;
    SupportMapFragment mapFragment;
    GoogleMap map;
    Location mCurrentLocation;
    MarkerOptions myLocationMarker;
    LatLng startLatLng = new LatLng( 0, 0 );
    LatLng endLatLng = new LatLng( 0, 0 );
    boolean walkState = false;
    GoogleApiClient mGoogleApiClient;
    Marker mCurrentMarker;
    LocationRequest mLocationRequest;
    Context context;
    Button choose, upload;
    FrameLayout container;
    private Uri filePath;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pet_sitter_diary );

        choose = findViewById( R.id.btnChoose );
        upload = findViewById( R.id.btnUpload );

        choose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType( "image/*" );
                intent.setAction( Intent.ACTION_GET_CONTENT );
                startActivityForResult( Intent.createChooser( intent, "이미지를 선택하세요." ), 0 );           }
        });


        upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadFile();
            }
        } );



        final FloatingActionButton start = findViewById( R.id.btnStart );


        start.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                changeWalkState();

            }

        } );

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder( this )
                    .addApi( LocationServices.API )
                    .addConnectionCallbacks( this )
                    .addOnConnectionFailedListener( this )
                    .build();
        }
        createLocationRequest();
    }

    private void changeWalkState() {
        if (!walkState) {
            Toast.makeText( getApplicationContext(), "산책 시작", Toast.LENGTH_SHORT ).show();
            walkState = true;
            startLatLng = new LatLng( mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude() );

        } else {
            Toast.makeText( getApplicationContext(), "산책 종료", Toast.LENGTH_SHORT ).show();
            walkState = false;


        }
    }

    private void drawPath() {
        PolylineOptions options = new PolylineOptions().add( startLatLng )
                .add( endLatLng ).width( 15 ).color( Color.BLACK ).geodesic( true );
        map.addPolyline( options );
        map.moveCamera( CameraUpdateFactory.newLatLngZoom( startLatLng, 18 ) );

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }


    public void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation( location );
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

            mCurrentLocation = manager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
            if (mCurrentLocation != null) {
                showCurrentLocation( mCurrentLocation );
            }

            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation( location );
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng( location.getLatitude(), location.getLongitude() );

        map.animateCamera( CameraUpdateFactory.newLatLngZoom( curPoint, 15 ) );

        showMyLocationMarker( location );
    }

    private void showMyLocationMarker(Location location) {

        double latitude = location.getLatitude(), longtitude = location.getLongitude();

        if (mCurrentMarker != null) mCurrentMarker.remove();
        mCurrentLocation = location;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position( new LatLng( latitude, longtitude ) );
        mCurrentMarker = map.addMarker( markerOptions );


        map.animateCamera( CameraUpdateFactory.newLatLngZoom(
                new LatLng( mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude() ), 18 ) );
        if (walkState) {                        //걸음 시작 버튼이 눌렸을 때
            endLatLng = new LatLng( latitude, longtitude );        //현재 위치를 끝점으로 설정
            drawPath();                                            //polyline 그리기
            startLatLng = new LatLng( latitude, longtitude );        //시작점을 끝점으로 다시 설정
        }
}
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if (map != null) {
//            map.setMyLocationEnabled( false );
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (map != null) {
//            map.setMyLocationEnabled( true );
//        }
//    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        enableMyLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        requestMyLocation();
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude(), longtitude = location.getLongitude();

        if (mCurrentMarker != null) mCurrentMarker.remove();
        mCurrentLocation = location;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position( new LatLng( latitude, longtitude ) );
        mCurrentMarker = map.addMarker( markerOptions );


        map.animateCamera( CameraUpdateFactory.newLatLngZoom(
                new LatLng( mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude() ), 18 ) );
        if (walkState) {                        //걸음 시작 버튼이 눌렸을 때
            endLatLng = new LatLng( latitude, longtitude );        //현재 위치를 끝점으로 설정
            drawPath();                                            //polyline 그리기
            startLatLng = new LatLng( latitude, longtitude );        //시작점을 끝점으로 다시 설정
        }
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission to access the location is missing.
                PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                        Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (map != null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            // Start location updates.
//            LocationServices.FusedLocationApi.requestLocationUpdates(
//                    mGoogleApiClient, mLocationRequest, this );

            if (mCurrentLocation != null) {
                Log.i("Location", "Latitude: " + mCurrentLocation.getLatitude()
                        + ", Longitude: " + mCurrentLocation.getLongitude());
                }
            }
        }



    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public File ScreenShot(View view){
        view.setDrawingCacheEnabled( true );

        Bitmap screenBitmap = view.getDrawingCache(  );

        String filename = "screenshot.png";
        File file = new File(Environment.getExternalStorageDirectory() + "/Pictures", filename);
        FileOutputStream os = null;

        try{
            os = new FileOutputStream( file );
            screenBitmap.compress( Bitmap.CompressFormat.PNG, 90, os );
            os.close();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        view.setDrawingCacheEnabled( false );
        return file;
    }

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //upload the file
    private void uploadFile() {
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
            String filename = formatter.format(now) + ".png";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://projectteam-a209a.appspot.com/PetSitterDiary").child("images/" + filename);
            //올라가거라...
            final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = storageRef.putFile( filePath )
                    //성공시
                    .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText( getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT ).show();
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
}
