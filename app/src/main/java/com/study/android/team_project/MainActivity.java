package com.study.android.team_project;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "qqqq";
    private static final int PERMISSON_REQUEST_CODE = 200;


    Button button1;  // 서비스안내 버튼
    Button button2;  // 이용요금 버튼


    TabLayout tabLayout; //탭바
    ViewPager viewPager;
    FrameLayout frameLayout;
    Toolbar myToolbar;



    static String userId ;
    static String result;
    static String petId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        myToolbar = findViewById( R.id.my_toolbar );
        setSupportActionBar( myToolbar );

        Intent intent1 = getIntent();
        userId = intent1.getStringExtra( "userId" );


        Intent intent2 = getIntent();
        result = intent2.getStringExtra( "result" );
        petId = intent2.getStringExtra( "petId" );




        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale( this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION )) {

            } else {
                ActivityCompat.requestPermissions( this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
            }

        }
        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission( this, READ_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED ){
            if (ActivityCompat.shouldShowRequestPermissionRationale( this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE )
                    && ActivityCompat.shouldShowRequestPermissionRationale( this, READ_EXTERNAL_STORAGE )){

            }else{
                ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE } ,PERMISSON_REQUEST_CODE);
            }
        }


        // *****************서비스안내 페이지로 이동*************************
        button1 = findViewById( R.id.button1 );
        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "이용시간은 09:00-18:00 입니다.", Toast.LENGTH_LONG ).show();

                // 액티비티 전환 코드
                Intent intent = new Intent( getApplicationContext(), Service.class );
                startActivity( intent );
            }
        } );
        // ****************서비스안내 페이지이동 끝***************************


        // *****************이용요금 페이지 이동********************************

        button2 = findViewById( R.id.button2 );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 액티비티 전환 코드
                Intent intent = new Intent( getApplicationContext(), Charge.class );
                startActivity( intent );
            }
        } );
        //***************이용요금 페이지 이동 끝******************************



        //***************탭바 이동 페이지*************************************

        viewPager= findViewById( R.id.container );
        tabLayout = findViewById( R.id.tabMenu );

        PageAdapter adapter= new PageAdapter(
                getSupportFragmentManager(),tabLayout.getTabCount() );

        viewPager.setAdapter( adapter);
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );


        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );

        //********************탭바 이동 끝********************************

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (userId == null) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate( R.menu.menu, menu );
        } else if (userId != null) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate( R.menu.menu2, menu );
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.action_settings_login) {
            loginOptionDialog();
        } else if (id == R.id.action_settings_logout) {
            logoutOptionDialog();
        } else {
            finish();
        }
         return super.onOptionsItemSelected( item );

    }


    private void loginOptionDialog(){
        new AlertDialog.Builder( this )
                .setTitle( "로그인" ).setMessage( "로그인 하시겠습니까?" )
                .setPositiveButton( "로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent( MainActivity.this, LoginActivity.class );
                        startActivity( intent );
                    }
                } )
                .setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        return;
                    }
                } )
                .show();
    }

    private void logoutOptionDialog(){
            new AlertDialog.Builder( this )
                .setTitle( "로그아웃" ).setMessage( "로그아웃 하시겠습니까?" )
                .setPositiveButton( "로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent( MainActivity.this, LoginActivity.class );
                        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
                        startActivity( intent );


                    }
                } )
                .setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                        return;

                    }
                } )
                .show();
    }


    public static class PageAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;

        private MyPageFragment myPageFragment = null;
        private CommentFragment commentFragment = null;

        public PageAdapter(FragmentManager fragmentManager, int NumOfTabs) {
            super( fragmentManager );
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

                switch (position) {


                    case 0:
                        return new PetSitterFragment();
                    case 1:

                        commentFragment = CommentFragment.newInstance(userId);

                        return commentFragment;

                    case 2:
                        return new QnAFragment();
                    case 3:
                            myPageFragment = MyPageFragment.newInstance( userId, result, petId);
                            return myPageFragment;
                    default:
                        return null;
                }


        }


        @Override
        public int getCount(){
            return mNumOfTabs;
        }
    }

}
