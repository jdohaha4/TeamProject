package com.study.android.team_project;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

// 펫시터 소개페이지에 있는 사진 자동 슬라이드 페이저
public class AutoScrollAdapter extends PagerAdapter {

    Context context;
    ArrayList<String> data;

    public AutoScrollAdapter(Context context, ArrayList<String> data1) {
        this.context = context;
        this.data = data1;
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        //뷰페이지 슬라이딩 할 레이아웃 인플레이션
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_auto_scroll,null);
        ImageView image_container = v.findViewById(R.id.image_container);
        int temp = Integer.parseInt (data.get(position));
        Glide.with(context).load(temp).into(image_container);

        container.addView(v);

        //******************* 자동슬라이드 이미지 클릭시 액티비티 이동******************************
        v.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                if (position == 0) {
                    Toast.makeText (context,"1번째 이미지",Toast.LENGTH_SHORT).show ();

                } else if (position == 1) {
                    Toast.makeText (context,"이용요금",Toast.LENGTH_SHORT).show ();
                    Intent intent = new Intent (v.getContext (),Charge.class);
                    v.getContext ().startActivity (intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK));

                } else if (position == 2) {
                    Toast.makeText (context,"믿고맏견의 서비스를 확인하세요!",Toast.LENGTH_SHORT).show ();
                    Intent intent = new Intent (v.getContext (),Service.class);
                    v.getContext ().startActivity (intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK));

                }
            }
        });

        //******************* 자동슬라이드 이미지 클릭시 액티비티 이동 끝******************************
        return v;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}

