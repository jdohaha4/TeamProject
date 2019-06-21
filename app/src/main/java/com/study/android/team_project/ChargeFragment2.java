package com.study.android.team_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

// 산책 요금 페이지
public class ChargeFragment2 extends Fragment {
    private static final String TAG = "lecture";

    ScrollView scrollView4; // 30분 요금 내용
    ScrollView scrollView5; // 60분 요금 내용
    ScrollView scrollView6; // 120분 요금 내용

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.charge_fragment2, container, false);

        scrollView4 = (ScrollView) rootView.findViewById (R.id.scr_char4);
        scrollView5 = (ScrollView) rootView.findViewById (R.id.scr_char5);
        scrollView6 = (ScrollView) rootView.findViewById (R.id.scr_char6);

        final Button btn2_ch1 = rootView.findViewById (R.id.btn2_ch1);
        final Button btn2_ch2 = rootView.findViewById (R.id.btn2_ch2);
        final Button btn2_ch3 = rootView.findViewById (R.id.btn2_ch3);

        // 30분 버튼
        btn2_ch1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn2_ch1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                btn2_ch2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn2_ch3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                // 스크롤뷰 맨위로
                scrollView4.fullScroll (ScrollView.FOCUS_UP);
                scrollView5.fullScroll (ScrollView.FOCUS_UP);
                scrollView6.fullScroll (ScrollView.FOCUS_UP);
                // 감추기
                scrollView4.setVisibility (View.VISIBLE);
                scrollView5.setVisibility (View.GONE);
                scrollView6.setVisibility (View.GONE);
            }
        });

        // 60분 버튼
        btn2_ch2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn2_ch2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                btn2_ch1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn2_ch3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                // 스크롤뷰 맨위로
                scrollView4.fullScroll (ScrollView.FOCUS_UP);
                scrollView5.fullScroll (ScrollView.FOCUS_UP);
                scrollView6.fullScroll (ScrollView.FOCUS_UP);
                // 감추기
                scrollView4.setVisibility (View.GONE);
                scrollView5.setVisibility (View.VISIBLE);
                scrollView6.setVisibility (View.GONE);
            }
        });

        // 120분 버튼
        btn2_ch3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn2_ch3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                btn2_ch2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn2_ch1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                // 스크롤뷰 맨위로
                scrollView4.fullScroll (ScrollView.FOCUS_UP);
                scrollView5.fullScroll (ScrollView.FOCUS_UP);
                scrollView6.fullScroll (ScrollView.FOCUS_UP);
                // 감추기
                scrollView4.setVisibility (View.GONE);
                scrollView5.setVisibility (View.GONE);
                scrollView6.setVisibility (View.VISIBLE);
            }
        });

        return rootView;
    }
}

