package com.study.android.team_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyPageCheckReservationAdapter extends BaseAdapter {
    private static final String TAG = "lecture";
    TextView Rcheck_id;
    Context context;
    ArrayList<MyPageCheckReservationDTO> items = new ArrayList<>();

    public MyPageCheckReservationAdapter(Context context){
        this.context = context;
    }
    public void addItem(MyPageCheckReservationDTO item){
        items.add(item);
    }


    @Override
    public int getCount(){return items.size();}
    @Override
    public Object getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        MyPageCheckReservationItemView view = null;
        if(convertView == null){
            view = new MyPageCheckReservationItemView(context);
        }else{
            view = (MyPageCheckReservationItemView)convertView;
        }
        final MyPageCheckReservationDTO item = items.get(position);
        // 펫시터 이름
        view.setB_visit_pet_sitter (item.getB_visit_pet_sitter ());
        // 방문 유형
        view.setB_visit_type (item.getB_visit_type ());
        // 방문 횟수
        view.setB_count (item.getB_count ());
        // 예약 요일
        view.setB_reserve_day (item.getB_reserve_day ());
        // 이용 시간
        view.setB_use_time (item.getB_use_time ());
        // 방문 시작일
        view.setB_visit_startdate (item.getB_visit_startdate ());
        // 방문 시간
        view.setB_visit_time (item.getB_visit_time ());
        // 방문 종료일
        view.setB_visit_endDate (item.getB_visit_endDate ());
        // 결제 일시
        view.setB_sysdate (item.getB_sysdate ());
        // 결제 금액
        view.setB_totalcount (item.getB_totalcount ());
        // 산책 / 돌봄
        view.setB_sitter_type (item.getB_sitter_type ());
        // 요구사항
        view.setB_request (item.getB_request ());
        // 예약자
        view.setC_id (item.getC_id ());


        return view;
    }
}
