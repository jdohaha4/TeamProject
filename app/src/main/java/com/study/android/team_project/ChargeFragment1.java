package com.study.android.team_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

// 돌봄요금 페이지
public class ChargeFragment1 extends Fragment {
    private static final String TAG = "lecture";
    ScrollView scrollView1; // 30분 요금 내용
    ScrollView scrollView2; // 60분 요금 내용
    ScrollView scrollView3; // 120분 요금 내용

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            ViewGroup rootView =
                    (ViewGroup) inflater.inflate(R.layout.charge_fragment1, container, false);

            scrollView1 = (ScrollView) rootView.findViewById (R.id.scr_char1);
            scrollView2 = (ScrollView) rootView.findViewById (R.id.scr_char2);
            scrollView3 = (ScrollView) rootView.findViewById (R.id.scr_char3);

            final Button btn1_ch2 = rootView.findViewById (R.id.btn1_ch2);
            final Button btn1_ch1 = rootView.findViewById (R.id.btn1_ch1);
            final Button btn1_ch3 = rootView.findViewById (R.id.btn1_ch3);

            // 30분 버튼
            btn1_ch1.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    // 버튼 색 변경
                    btn1_ch1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                    btn1_ch2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                    btn1_ch3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                    // 스크롤뷰 맨위로
                    scrollView1.fullScroll (ScrollView.FOCUS_UP);
                    scrollView2.fullScroll (ScrollView.FOCUS_UP);
                    scrollView3.fullScroll (ScrollView.FOCUS_UP);
                    // 감추기
                    scrollView1.setVisibility (View.VISIBLE);
                    scrollView2.setVisibility (View.GONE);
                    scrollView3.setVisibility (View.GONE);
                }
            });

            // 60분 버튼
            btn1_ch2.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    // 버튼 색 변경
                    btn1_ch2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                    btn1_ch1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                    btn1_ch3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                    // 스크롤뷰 맨위로
                    scrollView1.fullScroll (ScrollView.FOCUS_UP);
                    scrollView2.fullScroll (ScrollView.FOCUS_UP);
                    scrollView3.fullScroll (ScrollView.FOCUS_UP);
                    // 감추기
                    scrollView1.setVisibility (View.GONE);
                    scrollView2.setVisibility (View.VISIBLE);
                    scrollView3.setVisibility (View.GONE);
                }
            });

            // 120분 버튼
            btn1_ch3.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    // 버튼 색 변경
                    btn1_ch3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                    btn1_ch2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                    btn1_ch1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                    // 스크롤뷰 맨위로
                    scrollView1.fullScroll (ScrollView.FOCUS_UP);
                    scrollView2.fullScroll (ScrollView.FOCUS_UP);
                    scrollView3.fullScroll (ScrollView.FOCUS_UP);
                    // 감추기
                    scrollView1.setVisibility (View.GONE);
                    scrollView2.setVisibility (View.GONE);
                    scrollView3.setVisibility (View.VISIBLE);
                }
            });

            return rootView;
        }
}
