package com.study.android.team_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

// 서비스 안내 페이지
public class Service extends AppCompatActivity {
    ImageButton imageButton1;   // 뒤로가기 이미지버튼
    ImageButton imser1;
    ScrollView scr_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_service);


        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // 스크롤위로
        scr_service = findViewById (R.id.scr_service);
        imser1 = findViewById (R.id.img_ser1);
        imser1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                scr_service.fullScroll (ScrollView.FOCUS_UP);
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
