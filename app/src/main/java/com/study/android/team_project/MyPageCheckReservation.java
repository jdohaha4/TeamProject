package com.study.android.team_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyPageCheckReservation extends AppCompatActivity {
    private static final String TAG = "lecture";
    ListView Rcheck_listview; // 리스트뷰
    TextView Rcheck_id; // 아이디
    TextView Rcheck_t1; // 그냥 텍스트
    Button Rcheck_btn1; // 예약 하기
    WebView Rcheck_web1; // 믿고맡견 사이트 이동
//    Button Rcheck_btn3; // 전화

    MyPageCheckReservationAdapter adapter;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_check_reservation);

        Rcheck_id = findViewById (R.id.Rcheck_id); // 아이디
        Rcheck_t1 = findViewById (R.id.Rcheck_t1); //
        Rcheck_listview = findViewById (R.id.Rcheck_listview); // 리스트뷰
        Rcheck_btn1 = findViewById (R.id.Rcheck_btn3); // 예약하러가기 (웹뷰 버튼)
        Rcheck_web1 = findViewById (R.id.Rcheck_web1); // 믿고맡견 사이트 이동
//        Rcheck_btn3 = findViewById (R.id.Rcheck_btn3); // 전화
        // 아이디 받기
        final String userId = getIntent ().getStringExtra ("userId");

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new MyPageCheckReservationAdapter(this);
        try {
            String result;

            MyPageCheckReservationDB task = new MyPageCheckReservationDB ();
            result = task.execute(userId).get();

            JSONObject jObject = new JSONObject (result);
            JSONArray arr = jObject.getJSONArray("returns");

            for (int i =0;i<arr.length();i++) {
                String b_visit_pet_sitter = arr.getJSONObject(i).getString("b_visit_pet_sitter");// 펫시터 이름
                String b_visit_type = arr.getJSONObject(i).getString("b_visit_type");           // 방문 유형
                String b_count = arr.getJSONObject(i).getString("b_count");                     // 방문 횟수
                String b_reserve_day = arr.getJSONObject(i).getString("b_reserve_day");         // 예약 요일
                String b_use_time = arr.getJSONObject(i).getString("b_use_time");               // 이용 시간
                String b_visit_startdate = arr.getJSONObject(i).getString("b_visit_startdate"); // 방문 시작일
                String b_visit_time = arr.getJSONObject(i).getString("b_visit_time");           // 방문 시간
                String b_visit_endDate = arr.getJSONObject(i).getString("b_visit_endDate");     // 방문 종료일
                String b_sysdate = arr.getJSONObject(i).getString("b_sysdate");                 // 결제 일시
                String b_totalcount = arr.getJSONObject(i).getString("b_totalcount");           // 결제 금액
                String b_sitter_type = arr.getJSONObject(i).getString("b_sitter_type");         // 산책 / 돌봄
                String b_request = arr.getJSONObject(i).getString("b_request");                 // 요구사항
                String c_id = arr.getJSONObject(i).getString("c_id");                           // 예약자 id

                MyPageCheckReservationDTO dto = new MyPageCheckReservationDTO(b_visit_pet_sitter,b_visit_type,b_count,b_reserve_day,
                        b_use_time,b_visit_startdate,b_visit_time,b_visit_endDate,b_sysdate,b_totalcount,b_sitter_type,b_request,c_id);


                if (userId.equals (c_id)) {
                    Rcheck_id.setText (userId+"고객님의 예약내역입니다.");
                    Rcheck_listview.setVisibility (View.VISIBLE);
                    Rcheck_t1.setVisibility (View.GONE);
                    Rcheck_btn1.setVisibility (View.GONE);
                }



                adapter.addItem (dto);

            }

        } catch (Exception e) {
            e.printStackTrace ();

        }
        Rcheck_btn1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Rcheck_web1.setVisibility (View.VISIBLE);
                Toast.makeText (getApplicationContext (),"믿고맡견의 웹 사이트입니다!",Toast.LENGTH_SHORT).show ();
                Rcheck_web1.loadUrl("http://192.168.219.108:8081");
            }
        });

        Rcheck_listview.setAdapter (adapter);

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
