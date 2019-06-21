package com.study.android.team_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

public class CommentWrite extends AppCompatActivity {
    private static final String TAG = "lecture";
    Button btn_write1; // 펫시터 선택
    Button btn_write2; // 등록
    Button btn_write3; // 취소
    Button btn_write4; // 이전으로
    ImageView img_write1; // 펫시터 이미지
    LinearLayout ll_fragment;

    RatingBar ratingBar; //별점
    EditText et_tit; // 제목
    EditText et_con; // 내용
    EditText et_star; // 평점
    EditText et_img; // 이미지

    TextView write_txt;// 아이디
    TextView com_write_txt1; // 글자수 세기

    Intent intent;

    // 펫시터 선택 변수설정
    int choiceIndex=0;
    String sitters = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_comment_write);

        Toolbar myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle ("ORACLE");
        final String userId = getIntent ().getStringExtra ("userId");

        ll_fragment = findViewById (R.id.ll_fragment); // 레이아웃

        btn_write1 = findViewById (R.id.btn_write1); // 펫시터 선택 버튼
        btn_write2 = findViewById (R.id.btn_write2); // 펫시터 등록 버튼
        btn_write3 = findViewById (R.id.btn_write3); // 펫시터 취소 버튼
        img_write1 = findViewById (R.id.img_write1); // 펫시터 이미지
        et_tit = findViewById (R.id.et_tit); // 제목
        et_con = findViewById (R.id.et_con); // 내용
        et_star = findViewById (R.id.et_star); // 평점
        et_img = findViewById (R.id.et_img); // 이미지
        write_txt = findViewById (R.id.write_txt); // 아이디
        ratingBar = findViewById (R.id.ratingBar); // 별점

        com_write_txt1 = findViewById (R.id.com_write_txt1); // 글자수 세기

        et_con.addTextChangedListener (watcher); // 내용 입력시 글자수 체그
        write_txt.setText (userId); // 아이디 받아오기

        ratingBar.setOnRatingBarChangeListener (new RatingBar.OnRatingBarChangeListener () {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                et_star.setText (""+ratingBar.getRating ());

            }
        });
        //********************* 글작성 버튼*************************************
        btn_write2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {

                    final String[] result = new String[1];
                    final String E_NAME = write_txt.getText ().toString (); // 작성자
                    final String E_TITLE = et_tit.getText ().toString (); // 제목
                    final String E_CONTENT = et_con.getText ().toString (); // 내용
                    final String E_SITTER = et_img.getText ().toString (); // 사진
                    final String E_STAR = et_star.getText ().toString (); // 평점
                    Log.d (TAG,"평점1 "+E_STAR);

                    int comp = Integer.parseInt (E_STAR);
                    Log.d (TAG,"평점2 "+comp);
                    if (comp > 5) {
                        new AlertDialog.Builder (CommentWrite.this)
                                .setTitle ("알림")
                                .setMessage ("평점은 5점까지만 입력해 주세요.")
                                .setCancelable (false)
                                .setIcon (android.R.drawable.ic_dialog_alert)
                                .setPositiveButton ("닫기", null)
                                .show ();

                        return;
                    } else if (comp == 0){
                        new AlertDialog.Builder (CommentWrite.this)
                                .setTitle ("알림")
                                .setMessage ("별점을 입력해 주세요!")
                                .setCancelable (false)
                                .setIcon (android.R.drawable.ic_dialog_alert)
                                .setPositiveButton ("닫기", null)
                                .show ();
                        return;
                    } else if (et_tit.length () < 1){
                        new AlertDialog.Builder (CommentWrite.this)
                                .setTitle ("알림")
                                .setMessage ("제목을 입력해 주세요!")
                                .setCancelable (false)
                                .setIcon (android.R.drawable.ic_dialog_alert)
                                .setPositiveButton ("닫기",null)
                                .show ();

                        return;

                    } else if (et_con.length () < 1){
                        new AlertDialog.Builder (CommentWrite.this)
                                .setTitle ("알림")
                                .setMessage ("내용을 입력해 주세요!")
                                .setCancelable (false)
                                .setIcon (android.R.drawable.ic_dialog_alert)
                                .setPositiveButton ("닫기",null)
                                .show ();

                        return;

                    } else if (et_img.length () < 1){
                        new AlertDialog.Builder (CommentWrite.this)
                                .setTitle ("알림")
                                .setMessage ("펫시터를 선택해 주세요!")
                                .setCancelable (false)
                                .setIcon (android.R.drawable.ic_dialog_alert)
                                .setPositiveButton ("닫기",null)
                                .show ();

                        return;

                    } else {

                        final AlertDialog.Builder builder = new AlertDialog.Builder (CommentWrite.this)
                                .setTitle ("감사합니다!")
                                .setMessage ("후기작성을 완료하였습니다!")
                                .setCancelable (false)
                                .setPositiveButton ("등록", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EBoardWriteDB task = new  EBoardWriteDB();
                                        try {
                                            result[0] = task.execute (E_NAME,E_TITLE,E_CONTENT,E_SITTER,E_STAR).get();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace ();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace ();
                                        }

                                        Log.d (TAG,"작성자 : "+E_NAME);
                                        Log.d (TAG,"평가점수 : "+E_STAR);
                                        Log.d (TAG,"제목 : "+E_TITLE);
                                        Log.d (TAG,"내용 : "+E_CONTENT);
                                        Log.d (TAG,"펫시터 : "+E_SITTER);
                                        Log.d (TAG,"전송 : "+task);

                                        finish ();
                                    }
                                }).setNeutralButton ("취소", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText (CommentWrite.this,"취소하셨습니다.",Toast.LENGTH_SHORT).show ();
                                    }
                                });
                        AlertDialog alert = builder.create ();
                        alert.show ();

                    }

                }catch (Exception e) {
                    Log.i("DBtest", ".....ERROR.....!");
                    // Toast.makeText(getApplicationContext (), "정확히 입력해주세요!", Toast.LENGTH_LONG).show();

                    new AlertDialog.Builder (CommentWrite.this)
                            .setTitle ("알림")
                            .setMessage ("별점을 입력해 주세요!")
                            .setCancelable (false)
                            .setIcon (android.R.drawable.ic_dialog_alert)
                            .setPositiveButton ("닫기",null)
                            .show ();

                    return;
                }

            }

        });
        //***************************** 글작성 버튼 끝*************************************

        //********************************취소버튼*****************************************
        btn_write3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder (CommentWrite.this)
                        .setTitle ("알림")
                        .setMessage ("글작성을 취소하시겠습니까?")
                        .setIcon (android.R.drawable.ic_dialog_alert)
                        .setCancelable (false)
                        .setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                try {
                                    ratingBar.setRating (0);
                                    et_tit.setText (null); // 제목
                                    et_con.setText (null); // 내용
                                    et_star.setText (null); // 평점
                                    et_img.setText (null); // 이미지
                                    img_write1.setImageResource (R.drawable.noimage); // 이미지 변경

                                } catch (Exception e) {
                                    e.printStackTrace ();
                                }
                                Toast.makeText (CommentWrite.this,"글작성을 취소하셨습니다.",Toast.LENGTH_SHORT).show ();

                            }
                        }).setNeutralButton ("No", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = builder.create ();
                alert.show ();

            }
        });

        //********************************취소버튼 끝*****************************************


    } // onCreate

    // 테스트
    //************************************** 글자수  세기************************************
    TextWatcher watcher = new TextWatcher() {
        String beforeText;

        @Override
        public void onTextChanged(CharSequence str, int start, int before, int count) {
            byte[] bytes = null;
            try{
                //bytes = str.toString().getBytes("KSC5601");
                bytes = str.toString().getBytes("8859_1");
                int strCount = bytes.length;
                com_write_txt1.setText(strCount + " / 150");
            } catch (UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence str, int start, int count, int after) {
            beforeText = str.toString();
            Log.d(TAG,"beforeTextChanged : " + beforeText);
        }

        @Override
        public void afterTextChanged(Editable strEditable) {
            String str = strEditable.toString();
            Log.d(TAG, "afterTextChanged : " + str);
            try{
                byte[] strBytes = str.getBytes("8859_1");
                if(strBytes.length > 150) {
                    et_con.setText(beforeText);
                    et_con.setSelection(beforeText.length()-1, beforeText.length()-1);
                    // strEditable.delete(strEditable.length()-2, strEditable.length()-1);
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    };


    //****************************** 펫시터 선택 테스트********************************************
    public void btn1sitter(View v) {
        final  String[] sitter = new String[]{"정의만","윤영로","박상원","이병헌","지동원","최소라"};

        AlertDialog.Builder dialog = new AlertDialog.Builder (CommentWrite.this);
        dialog.setTitle ("펫시터 선택")
                .setSingleChoiceItems (sitter, choiceIndex, new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choiceIndex = which;
                    }
                })
                .setPositiveButton ("선택", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sitters = sitter[choiceIndex];


                        try {
                            String E_SITTER = et_img.getText ().toString (); // 사진

                            if (sitters.equals ("정의만")){
                                et_img.setText ("sittera.png");
                                img_write1.setImageResource (R.drawable.sittera);
                            } else if (sitters.equals ("윤영로")) {
                                et_img.setText ("sitterb.png");
                                img_write1.setImageResource (R.drawable.sitterb);
                            } else if (sitters.equals ("박상원")) {
                                et_img.setText ("sitterc.png");
                                img_write1.setImageResource (R.drawable.sitterc);
                            } else if (sitters.equals ("이병헌")) {
                                et_img.setText ("sitterd.png");
                                img_write1.setImageResource (R.drawable.sitterd);
                            } else if (sitters.equals ("지동원")) {
                                et_img.setText ("sittere.png");
                                img_write1.setImageResource (R.drawable.sittere);
                            } else if (sitters.equals ("최소라")) {
                                et_img.setText ("sitterf.jpg");
                                img_write1.setImageResource (R.drawable.sitterf);
                            }
                            Log.d (TAG,"펫시터 : "+sitters);
                            Log.d (TAG,"펫시터 이미지 : "+et_img);

                        }catch (Exception e) {
                            e.printStackTrace ();
                        }
                    }
                })
                .setNeutralButton ("취소", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (CommentWrite.this
                                ,"취소하셨습니다."
                                ,Toast.LENGTH_SHORT).show ();
                        try {
                            et_img.setText (null);
                            img_write1.setImageResource (R.drawable.noimage);
                        } catch (Exception e) {
                            e.printStackTrace ();
                        }
                    }
                });

        dialog.create ();
        dialog.show ();
    }
    //****************************** 펫시터 선택 테스트 끝********************************************

    @Override
    protected void onDestroy() {
        super.onDestroy ();

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
