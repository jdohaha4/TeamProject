package com.study.android.team_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;

public class PetSitterItemView extends LinearLayout {

    TextView sitter_txt1; // 펫시터 이름
    TextView sitter_txt2; // 펫시터 성별
    TextView sitter_txt3; //평점
    TextView sitter_txt4; //정보
    ImageView sitter_img1; // 이미지

    public PetSitterItemView(Context context){
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pet_sitter_list_view_item,this,true);

        sitter_txt1 = findViewById(R.id.sitter_txt1);
        sitter_txt2 = findViewById(R.id.sitter_txt2);
        sitter_txt3 = findViewById(R.id.sitter_txt3);
        sitter_txt4 = findViewById(R.id.sitter_txt4);

        sitter_img1 = findViewById (R.id.sitter_img1);

    }
    public void setName(String name){

        sitter_txt1.setText("펫시터 "+name+"님");
        if (name.equals ("정의만")) {
            if (PetSitterFragment.avg1 == 0) {
                sitter_txt3.setText("☆☆☆☆☆");
            } else if (PetSitterFragment.avg1 == 1) {
                sitter_txt3.setText("★☆☆☆☆");
            } else if (PetSitterFragment.avg1 == 2) {
                sitter_txt3.setText("★★☆☆☆");
            } else if (PetSitterFragment.avg1 == 3) {
                sitter_txt3.setText("★★★☆☆");
            } else if (PetSitterFragment.avg1 == 4) {
                sitter_txt3.setText("★★★★☆");
            } else if (PetSitterFragment.avg1 == 5) {
                sitter_txt3.setText("★★★★★");
            }


        } else if (name.equals ("윤영로")) {
            if (PetSitterFragment.avg2 == 0) {
                sitter_txt3.setText("☆☆☆☆☆");
            } else if (PetSitterFragment.avg2 == 1) {
                sitter_txt3.setText("★☆☆☆☆");
            } else if (PetSitterFragment.avg2 == 2) {
                sitter_txt3.setText("★★☆☆☆");
            } else if (PetSitterFragment.avg2 == 3) {
                sitter_txt3.setText("★★★☆☆");
            } else if (PetSitterFragment.avg2 == 4) {
                sitter_txt3.setText("★★★★☆");
            } else if (PetSitterFragment.avg2 == 5) {
                sitter_txt3.setText("★★★★★");
            }


        } else if (name.equals ("박상원")) {
            if (PetSitterFragment.avg3 == 0) {
                sitter_txt3.setText("☆☆☆☆☆");
            } else if (PetSitterFragment.avg3 == 1) {
                sitter_txt3.setText("★☆☆☆☆");
            } else if (PetSitterFragment.avg3 == 2) {
                sitter_txt3.setText("★★☆☆☆");
            } else if (PetSitterFragment.avg3 == 3) {
                sitter_txt3.setText("★★★☆☆");
            } else if (PetSitterFragment.avg3 == 4) {
                sitter_txt3.setText("★★★★☆");
            } else if (PetSitterFragment.avg3 == 5) {
                sitter_txt3.setText("★★★★★");
            }


        } else if (name.equals ("이병헌")) {
            if (PetSitterFragment.avg4 == 0) {
                sitter_txt3.setText("☆☆☆☆☆");
            } else if (PetSitterFragment.avg4 == 1) {
                sitter_txt3.setText("★☆☆☆☆");
            } else if (PetSitterFragment.avg4 == 2) {
                sitter_txt3.setText("★★☆☆☆");
            } else if (PetSitterFragment.avg4 == 3) {
                sitter_txt3.setText("★★★☆☆");
            } else if (PetSitterFragment.avg4 == 4) {
                sitter_txt3.setText("★★★★☆");
            } else if (PetSitterFragment.avg4 == 5) {
                sitter_txt3.setText("★★★★★");
            }

        } else if (name.equals ("지동원")) {
            if (PetSitterFragment.avg5 == 0) {
                sitter_txt3.setText("☆☆☆☆☆");
            } else if (PetSitterFragment.avg5 == 1) {
                sitter_txt3.setText("★☆☆☆☆");
            } else if (PetSitterFragment.avg5 == 2) {
                sitter_txt3.setText("★★☆☆☆");
            } else if (PetSitterFragment.avg5 == 3) {
                sitter_txt3.setText("★★★☆☆");
            } else if (PetSitterFragment.avg5 == 4) {
                sitter_txt3.setText("★★★★☆");
            } else if (PetSitterFragment.avg5 == 5) {
                sitter_txt3.setText("★★★★★");
            }

        } else if (name.equals ("최소라")) {
            if (PetSitterFragment.avg6 == 0) {
                sitter_txt3.setText("☆☆☆☆☆");
            } else if (PetSitterFragment.avg6 == 1) {
                sitter_txt3.setText("★☆☆☆☆");
            } else if (PetSitterFragment.avg6 == 2) {
                sitter_txt3.setText("★★☆☆☆");
            } else if (PetSitterFragment.avg6 == 3) {
                sitter_txt3.setText("★★★☆☆");
            } else if (PetSitterFragment.avg6 == 4) {
                sitter_txt3.setText("★★★★☆");
            } else if (PetSitterFragment.avg6 == 5) {
                sitter_txt3.setText("★★★★★");
            }

        }
    }
    public void setSex(String sex){
        sitter_txt2.setText(sex);
    }
    public void setHit(int hit) {
    }
    public void setContents(String contents) { sitter_txt4.setText (contents); }

    // 이미지
    public void setImg(final String img)
    {
        String imgurl = "http://192.168.219.108:8081/sitterimg/"+img;
        Picasso.with(getContext())
                .load(imgurl)
                .placeholder(R.drawable.noimage)//이미지가 존재하지 않을 시                                                                                                     경우 대체 이미지

                .resize(150, 150) // 이미지 크기를 재조정하고 싶을 경우

                .into(sitter_img1);
        Log.d(TAG,"이미지 url : " +imgurl);
    }
}