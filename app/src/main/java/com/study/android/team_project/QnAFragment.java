package com.study.android.team_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class QnAFragment extends Fragment {

//    ScrollView scr_qna1; // 전체 스크롤
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_qn_a, container, false);

        // 스크롤 뷰
        final ScrollView scr_qna1 = (ScrollView) rootView.findViewById (R.id.scr_qna1);

        final ImageButton img_btn1 =  rootView.findViewById (R.id.img_btn1);

        // 펫시터 서비스
        final TextView txt_qan1 = rootView.findViewById (R.id.txt_qan1);
        final TextView txt_qan2 = rootView.findViewById (R.id.txt_qan2);
        final TextView txt_qan3 = rootView.findViewById (R.id.txt_qan3);
        // 서비스 가격
        final TextView txt_qan4 = rootView.findViewById (R.id.txt_qan4);
        final TextView txt_qan5 = rootView.findViewById (R.id.txt_qan5);
        // 기타
        final TextView txt_qan6 = rootView.findViewById (R.id.txt_qan6);
        final TextView txt_qan7 = rootView.findViewById (R.id.txt_qan7);
        final TextView txt_qan8 = rootView.findViewById (R.id.txt_qan8);

        final TextView txt_qna_line1 = rootView.findViewById (R.id.txt_qna_line1);
        final TextView txt_qna_line2 = rootView.findViewById (R.id.txt_qna_line2);
        final TextView txt_qna_line3 = rootView.findViewById (R.id.txt_qna_line3);
        final TextView txt_qna_line4 = rootView.findViewById (R.id.txt_qna_line4);
        final TextView txt_qna_line5 = rootView.findViewById (R.id.txt_qna_line5);
        final TextView txt_qna_line6 = rootView.findViewById (R.id.txt_qna_line6);
        final TextView txt_qna_line7 = rootView.findViewById (R.id.txt_qna_line7);
        final TextView txt_qna_line8 = rootView.findViewById (R.id.txt_qna_line8);


        // 토글버튼 클릭시 질문답 보임(서비스 가격 안내 )
        final ToggleButton togBtn_qna_1 = rootView.findViewById (R.id.togBtn_qna_1);
        final ToggleButton togBtn_qna_2 = rootView.findViewById (R.id.togBtn_qna_2);
        // 질문 답 자세히 쓴거  (서비스 가격 안내  )
        final TextView txt_ans1 = rootView.findViewById (R.id.txt_ans1);
        final TextView txt_ans2 = rootView.findViewById (R.id.txt_ans2);

        // 토글버튼 클릭시 질문답 보임(기타)
        final ToggleButton togBtn_qna_4 = rootView.findViewById (R.id.togBtn_qna_4);
        final ToggleButton togBtn_qna_5 = rootView.findViewById (R.id.togBtn_qna_5);
        final ToggleButton togBtn_qna_6 = rootView.findViewById (R.id.togBtn_qna_6);
        // 질문 답 자세히 쓴거  (기타 )
        final TextView txt_ans4 = rootView.findViewById (R.id.txt_ans4);
        final TextView txt_ans5 = rootView.findViewById (R.id.txt_ans5);
        final TextView txt_ans6 = rootView.findViewById (R.id.txt_ans6);

        // 토글버튼 클릭시 질문답 보임(펫시터 서비스)
        final ToggleButton togBtn_qna_7 = rootView.findViewById (R.id.togBtn_qna_7);
        final ToggleButton togBtn_qna_8 = rootView.findViewById (R.id.togBtn_qna_8);
        final ToggleButton togBtn_qna_9 = rootView.findViewById (R.id.togBtn_qna_9);
        // 질문 답 자세히 쓴거  (펫시터 서비스 )
        final TextView txt_ans7 = rootView.findViewById (R.id.txt_ans7);
        final TextView txt_ans8 = rootView.findViewById (R.id.txt_ans8);
        final TextView txt_ans9 = rootView.findViewById (R.id.txt_ans9);

        // 메뉴
        final Button btn_qna1 = rootView.findViewById (R.id.btn_qna1);      // 전체
        final Button btn_qna2 = rootView.findViewById (R.id.btn_qna2);      // 펫시터 서비스
        final Button btn_qna3 = rootView.findViewById (R.id.btn_qna3);      // 서비스 가격 안내
        final Button btn_qna4 = rootView.findViewById (R.id.btn_qna4);      // 기타

        // 전화버튼
        final Button btn_qna_call = rootView.findViewById (R.id.btn_qna_call);

        // 전화걸기
        btn_qna_call.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext (), "믿고맡견의 고객센터입니다!\n   (13:00-14:00 점심시간)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01071066725"));
                startActivity(intent);
            }
        });

        // *************************************메뉴 클릭 부분*********************************************************************************

        // 전체 버튼
        btn_qna1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn_qna1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                btn_qna2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna4.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                scr_qna1.fullScroll (ScrollView.FOCUS_UP);

                // 감추기
                txt_qan1.setVisibility (View.VISIBLE);
                txt_qan2.setVisibility (View.VISIBLE);
                txt_qan3.setVisibility (View.VISIBLE);
                txt_qan4.setVisibility (View.VISIBLE);
                txt_qan5.setVisibility (View.VISIBLE);
                txt_qan6.setVisibility (View.VISIBLE);
                txt_qan7.setVisibility (View.VISIBLE);
                txt_qan8.setVisibility (View.VISIBLE);
                // 서비스 가격 안내
                togBtn_qna_1.setVisibility (View.VISIBLE);
                togBtn_qna_2.setVisibility (View.VISIBLE);
                // 기타
                togBtn_qna_4.setVisibility (View.VISIBLE);
                togBtn_qna_5.setVisibility (View.VISIBLE);
                togBtn_qna_6.setVisibility (View.VISIBLE);
                // 펫시터 서비스
                togBtn_qna_7.setVisibility (View.VISIBLE);
                togBtn_qna_8.setVisibility (View.VISIBLE);
                togBtn_qna_9.setVisibility (View.VISIBLE);
                // 라인
                txt_qna_line1.setVisibility (View.VISIBLE);
                txt_qna_line2.setVisibility (View.VISIBLE);
                txt_qna_line3.setVisibility (View.VISIBLE);
                txt_qna_line4.setVisibility (View.VISIBLE);
                txt_qna_line5.setVisibility (View.VISIBLE);
                txt_qna_line6.setVisibility (View.VISIBLE);
                txt_qna_line7.setVisibility (View.VISIBLE);
                txt_qna_line8.setVisibility (View.VISIBLE);
                img_btn1.setVisibility (View.VISIBLE);
            }
        });

        // 펫시터 서비스 버튼
        btn_qna2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn_qna1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                btn_qna3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna4.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                // 스크롤뷰 맨위로
                scr_qna1.fullScroll (ScrollView.FOCUS_UP);
                // 감추기
                txt_qan1.setVisibility (View.VISIBLE);
                txt_qan2.setVisibility (View.VISIBLE);
                txt_qan3.setVisibility (View.VISIBLE);
                txt_qan4.setVisibility (View.GONE);
                txt_qan5.setVisibility (View.GONE);
                txt_qan6.setVisibility (View.GONE);
                txt_qan7.setVisibility (View.GONE);
                txt_qan8.setVisibility (View.GONE);
                // 서비스 가격 안내
                togBtn_qna_1.setVisibility (View.GONE);
                togBtn_qna_2.setVisibility (View.GONE);
                // 기타
                togBtn_qna_4.setVisibility (View.GONE);
                togBtn_qna_5.setVisibility (View.GONE);
                togBtn_qna_6.setVisibility (View.GONE);
                // 펫시터 서비스
                togBtn_qna_7.setVisibility (View.VISIBLE);
                togBtn_qna_8.setVisibility (View.VISIBLE);
                togBtn_qna_9.setVisibility (View.VISIBLE);
                // 라인 숨기기
                txt_qna_line1.setVisibility (View.VISIBLE);
                txt_qna_line2.setVisibility (View.VISIBLE);
                txt_qna_line3.setVisibility (View.VISIBLE);
                txt_qna_line4.setVisibility (View.GONE);
                txt_qna_line5.setVisibility (View.GONE);
                txt_qna_line6.setVisibility (View.GONE);
                txt_qna_line7.setVisibility (View.GONE);
                txt_qna_line8.setVisibility (View.GONE);

                img_btn1.setVisibility (View.VISIBLE);
            }
        });

        // 요금 안내 버튼
        btn_qna3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn_qna1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));
                btn_qna4.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));

                // 스크롤뷰 맨위로
                scr_qna1.fullScroll (ScrollView.FOCUS_UP);

                // 감추기
                txt_qan1.setVisibility (View.GONE);
                txt_qan2.setVisibility (View.GONE);
                txt_qan3.setVisibility (View.GONE);
                txt_qan4.setVisibility (View.VISIBLE);
                txt_qan5.setVisibility (View.VISIBLE);
                txt_qan6.setVisibility (View.GONE);
                txt_qan7.setVisibility (View.GONE);
                txt_qan8.setVisibility (View.GONE);

                // 서비스 가격 안내
                togBtn_qna_1.setVisibility (View.VISIBLE);
                togBtn_qna_2.setVisibility (View.VISIBLE);
                // 기타
                togBtn_qna_4.setVisibility (View.GONE);
                togBtn_qna_5.setVisibility (View.GONE);
                togBtn_qna_6.setVisibility (View.GONE);
                // 펫시터 서비스
                togBtn_qna_7.setVisibility (View.GONE);
                togBtn_qna_8.setVisibility (View.GONE);
                togBtn_qna_9.setVisibility (View.GONE);

                // 라인 숨기기
                txt_qna_line1.setVisibility (View.GONE);
                txt_qna_line2.setVisibility (View.GONE);
                txt_qna_line3.setVisibility (View.GONE);
                txt_qna_line4.setVisibility (View.GONE);
                txt_qna_line5.setVisibility (View.GONE);
                txt_qna_line6.setVisibility (View.GONE);
                txt_qna_line7.setVisibility (View.VISIBLE);
                txt_qna_line8.setVisibility (View.VISIBLE);

                img_btn1.setVisibility (View.GONE);
            }
        });

        // 기타 버튼
        btn_qna4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // 버튼 색 변경
                btn_qna1.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna2.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna3.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background1));
                btn_qna4.setBackground (ContextCompat.getDrawable(getActivity ().getApplicationContext (),R.drawable.btn_background2));

                // 스크롤뷰 맨위로
                scr_qna1.fullScroll (ScrollView.FOCUS_UP);

                // 감추기
                txt_qan1.setVisibility (View.GONE);
                txt_qan2.setVisibility (View.GONE);
                txt_qan3.setVisibility (View.GONE);
                txt_qan4.setVisibility (View.GONE);
                txt_qan5.setVisibility (View.GONE);
                txt_qan6.setVisibility (View.VISIBLE);
                txt_qan7.setVisibility (View.VISIBLE);
                txt_qan8.setVisibility (View.VISIBLE);

                // 서비스 가격 안내
                togBtn_qna_1.setVisibility (View.GONE);
                togBtn_qna_2.setVisibility (View.GONE);
                // 기타
                togBtn_qna_4.setVisibility (View.VISIBLE);
                togBtn_qna_5.setVisibility (View.VISIBLE);
                togBtn_qna_6.setVisibility (View.VISIBLE);
                // 펫시터 서비스
                togBtn_qna_7.setVisibility (View.GONE);
                togBtn_qna_8.setVisibility (View.GONE);
                togBtn_qna_9.setVisibility (View.GONE);

                // 라인 숨기기
                txt_qna_line1.setVisibility (View.GONE);
                txt_qna_line2.setVisibility (View.GONE);
                txt_qna_line3.setVisibility (View.GONE);
                txt_qna_line4.setVisibility (View.VISIBLE);
                txt_qna_line5.setVisibility (View.VISIBLE);
                txt_qna_line6.setVisibility (View.VISIBLE);
                txt_qna_line7.setVisibility (View.GONE);
                txt_qna_line8.setVisibility (View.GONE);
                img_btn1.setVisibility (View.VISIBLE);
            }
        });
        // *************************************메뉴 클릭 부분 끝*********************************************************************************


        // *************************************텍스트 질문 부분*********************************************************************************


        // *************************************펫시터 서비스 스크롤뷰 *************************************************************************
        togBtn_qna_7.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_7.isChecked ()) {
                    txt_ans7.setVisibility (View.VISIBLE);
                } else {
                    txt_ans7.setVisibility (View.GONE);
                }
            }
        });
        togBtn_qna_8.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_8.isChecked ()) {
                    txt_ans8.setVisibility (View.VISIBLE);
                } else {
                    txt_ans8.setVisibility (View.GONE);
                }
            }
        });
        togBtn_qna_9.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_9.isChecked ()) {
                    txt_ans9.setVisibility (View.VISIBLE);
                } else {
                    txt_ans9.setVisibility (View.GONE);
                }
            }
        });
        // *************************************펫시터 서비스 스크롤뷰  끝*************************************************************************

        // *************************************서비스 가격 안내 스크롤뷰 *************************************************************************
        togBtn_qna_1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_1.isChecked ()) {
                    txt_ans1.setVisibility (View.VISIBLE);
                } else {
                    txt_ans1.setVisibility (View.GONE);
                }
            }
        });
        togBtn_qna_2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_2.isChecked ()) {
                    txt_ans2.setVisibility (View.VISIBLE);
                } else {
                    txt_ans2.setVisibility (View.GONE);
                }
            }
        });

        // *************************************서비스 가격 안내  스크롤뷰  끝*************************************************************************

        // *************************************기타 스크롤뷰 *************************************************************************
        togBtn_qna_4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_4.isChecked ()) {
                    txt_ans4.setVisibility (View.VISIBLE);
                } else {
                    txt_ans4.setVisibility (View.GONE);
                }
            }
        });
        togBtn_qna_5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_5.isChecked ()) {
                    txt_ans5.setVisibility (View.VISIBLE);
                } else {
                    txt_ans5.setVisibility (View.GONE);
                }
            }
        });
        togBtn_qna_6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (togBtn_qna_6.isChecked ()) {
                    txt_ans6.setVisibility (View.VISIBLE);
                } else {
                    txt_ans6.setVisibility (View.GONE);
                }
            }
        });
        // *************************************기타 스크롤뷰  끝*************************************************************************

        // 맨위로 올리는 버튼
        img_btn1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                scr_qna1.fullScroll (ScrollView.FOCUS_UP);
            }
        });

        return rootView;
    }
}