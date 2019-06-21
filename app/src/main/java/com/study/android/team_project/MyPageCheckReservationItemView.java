package com.study.android.team_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyPageCheckReservationItemView extends LinearLayout  {

    ImageView Rcheck_img1; // 펫시터 이미지

    TextView Rcheck_txt1; // 펫시터 이름
    TextView Rcheck_txt2; // 방문 유형
    TextView Rcheck_txt3; // 방문 횟수
    TextView Rcheck_txt4; // 예약 요일
    TextView Rcheck_txt5; // 이용 시간
    TextView Rcheck_txt6; // 방문 시작일
    TextView Rcheck_txt7; // 방문 종료일
    TextView Rcheck_txt8; // 결제 일시
    TextView Rcheck_txt9; // 결제 금액
    TextView Rcheck_txt10; // 산책 / 돌봄
    TextView Rcheck_txt11; // 요구사항
//    TextView Rcheck_txt12; // 예약자 ID
    TextView Rcheck_txt13; // 방문 시간


    public MyPageCheckReservationItemView(Context context){
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate( R.layout.activity_my_page_check_reservation_item_view,this,true);

        Rcheck_img1 = findViewById ( R.id.Rcheck_img1);

        Rcheck_txt1 = findViewById ( R.id.Rcheck_txt1);
        Rcheck_txt2 = findViewById ( R.id.Rcheck_txt2);
        Rcheck_txt3 = findViewById ( R.id.Rcheck_txt3);
        Rcheck_txt4 = findViewById ( R.id.Rcheck_txt4);
        Rcheck_txt5 = findViewById ( R.id.Rcheck_txt5);
        Rcheck_txt6 = findViewById ( R.id.Rcheck_txt6);
        Rcheck_txt7 = findViewById ( R.id.Rcheck_txt7);
        Rcheck_txt8 = findViewById ( R.id.Rcheck_txt8);
        Rcheck_txt9 = findViewById ( R.id.Rcheck_txt9);
        Rcheck_txt10 = findViewById ( R.id.Rcheck_txt10);
        Rcheck_txt11 = findViewById ( R.id.Rcheck_txt11);
//        Rcheck_txt12 = findViewById (R.id.Rcheck_txt12);
        Rcheck_txt13 = findViewById ( R.id.Rcheck_txt13);

    }
    public void setB_visit_pet_sitter(String b_visit_pet_sitter) {
        if (b_visit_pet_sitter.equals ("정의만")) {
            Rcheck_txt1.setText (b_visit_pet_sitter);

            Rcheck_img1.setImageResource ( R.drawable.sittera);

        } else if (b_visit_pet_sitter.equals ("윤영로")) {
            Rcheck_txt1.setText (b_visit_pet_sitter);

            Rcheck_img1.setImageResource ( R.drawable.sitterb);

        } else if (b_visit_pet_sitter.equals ("박상원")) {
            Rcheck_txt1.setText (b_visit_pet_sitter);

            Rcheck_img1.setImageResource ( R.drawable.sitterc);

        } else if (b_visit_pet_sitter.equals ("이병헌")) {
            Rcheck_txt1.setText (b_visit_pet_sitter);

            Rcheck_img1.setImageResource ( R.drawable.sitterd);

        } else if (b_visit_pet_sitter.equals ("지동원")) {
            Rcheck_txt1.setText (b_visit_pet_sitter);

            Rcheck_img1.setImageResource ( R.drawable.sittere);

        } else if (b_visit_pet_sitter.equals ("최소라")) {
            Rcheck_txt1.setText (b_visit_pet_sitter);

            Rcheck_img1.setImageResource ( R.drawable.sitterf);
        }
    }
    public void setB_visit_type(String b_visit_type ) {
        Rcheck_txt2.setText (b_visit_type );

    }
    public void setB_count(String b_count ) {
        Rcheck_txt3.setText (b_count+"회" );

    }
    public void setB_reserve_day(String b_reserve_day ) {
        Rcheck_txt4.setText (b_reserve_day );

    }
    public void setB_use_time(String b_use_time ) {
        Rcheck_txt5.setText (b_use_time );

    }
    public void setB_visit_time(String b_visit_time ) {
        Rcheck_txt13.setText (b_visit_time );

    }

    public void setB_visit_startdate(String b_visit_startdate ) {
        Rcheck_txt6.setText (b_visit_startdate);

    }
    public void setB_visit_endDate(String b_visit_endDate ) {
        Rcheck_txt7.setText (b_visit_endDate );

    }

    public void setB_sysdate(String b_sysdate ) {
        Rcheck_txt8.setText (b_sysdate );

    }
    public void setB_totalcount(String b_totalcount ) {
        Rcheck_txt9.setText (b_totalcount+"원");

    }
    public void setB_sitter_type(String b_sitter_type ) {
        Rcheck_txt10.setText (b_sitter_type );

    }
    public void setB_request(String b_request ) {
        Rcheck_txt11.setText (b_request );

    }
    public void setC_id(String c_id ) {
//        Rcheck_txt12.setText (c_id );

    }
}
