package com.study.android.team_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EBoardItemView extends LinearLayout {
    private static final String TAG = "lecture";

    TextView eboard_txt1;   // 작성자
    TextView eboard_txt2;   // 날짜
    TextView eboard_txt3;   // 평점
    TextView eboard_txt4;   // 내용
    TextView eboard_txt5; // 제목
    ImageView  eboard_img1; // 이미지


    public EBoardItemView(Context context){
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_eboard_item_view,this,true);

        eboard_txt1 = findViewById(R.id.eboard_txt1);
        eboard_txt2 = findViewById(R.id.eboard_txt2);
        eboard_txt3 = findViewById(R.id.eboard_txt3);
        eboard_txt4 = findViewById(R.id.eboard_txt4);
        eboard_txt5 = findViewById(R.id.eboard_txt5);
        eboard_img1 = findViewById(R.id.eboard_img1);

    }
    public void setEname(String ename){
        eboard_txt1.setText(ename);
    }
    public void setEdate(String edate){
        eboard_txt2.setText(edate);
    }
    public void setEstar(String estar){
        int comp = Integer.parseInt (estar);
        if (comp == 1) {
            eboard_txt3.setText("★☆☆☆☆");
        } else if (comp == 2) {
            eboard_txt3.setText("★★☆☆☆");
        } else if (comp == 3) {
            eboard_txt3.setText("★★★☆☆");
        } else if (comp == 4) {
            eboard_txt3.setText("★★★★☆");
        } else if (comp == 5) {
            eboard_txt3.setText("★★★★★");
        }

//        eboard_txt3.setText("별점 : "+estar);
    }

    public void setEcontent(String econtent){
        eboard_txt4.setText(econtent);
    }

    // 이미지
    public void setEsitter(final String esitter)
    {

        String imgurl = "http://192.168.219.108:8081/sitterimg/"+esitter;
        Picasso.with(getContext())
                .load(imgurl)
                .placeholder(R.drawable.noimage)//이미지가 존재하지 않을 시                                                                                                     경우 대체 이미지

                .resize(150, 150) // 이미지 크기를 재조정하고 싶을 경우

                .into(eboard_img1);
        Log.d(TAG,"이미지 url : " +imgurl);
    }

    public void setEtitle(String etitle){
        eboard_txt5.setText ("제목 : "+etitle);
    }
}