package com.study.android.team_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class PetSitterFragment extends Fragment {
    private static final String TAG = "lecture";
    AutoScrollViewPager autoViewPager; // 사진 슬라이드
    Button btn_sitter;  // 펫시터 보기
    ListView sitter_listview; // 펫시터 리스트뷰
    PetSitterAdapter adapter; // 펫시터 정보
    ScrollView sitter_scr1; // 스크롤뷰
    ViewGroup rootView;

    Button test11;
    static int avg1 = 0;
    static int avg2 = 0;
    static int avg3 = 0;
    static int avg4 = 0;
    static int avg5 = 0;
    static int avg6 = 0;
    ArrayList<PetSitterDTO> dtos = null;

    // 펫시터 선택 변수설정
    int choiceIndex=0;
    String sitters = "";

    //*************

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_pet_sitter, container, false);


        return rootView;
    }

    public void onStart() {
        super.onStart ();

        // *** 스크롤뷰 맨위
        sitter_scr1 = rootView.findViewById (R.id.sitter_scr1);
        sitter_scr1.fullScroll (ScrollView.FOCUS_UP);
        sitter_scr1.post (new Runnable () {
            @Override
            public void run() {
                sitter_scr1.fullScroll (ScrollView.FOCUS_UP);
            }
        });

        //*********************************** 사진 자동 슬라이드 시작*******************************

        String temp1 = R.drawable.dogaaaa+"";
        String temp2 = R.drawable.dogbbbb+"";
        String temp3 = R.drawable.dogcccc+"";

        ArrayList<String> data1 = new ArrayList<> ();
        data1.add(temp1);
        data1.add(temp2);
        data1.add(temp3);

        autoViewPager = rootView.findViewById(R.id.autoViewPager);

        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getActivity ().getApplicationContext (), data1);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(2500); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작


        // *********************************** 사진 자동 슬라이드 끝*************************************

        // ****************************oracle db연동 리스트 출력************************************
        adapter = new PetSitterAdapter (getActivity ());

        try {
            String result;
            PetSitterDB task = new PetSitterDB ();
            result = task.execute().get();
            JSONObject jObject = new JSONObject (result);
            JSONArray arr = jObject.getJSONArray("returns");

            for(int i =0;i<arr.length();i++){
                String name = arr.getJSONObject(i).getString("S_NAME");
                int hit = arr.getJSONObject(i).getInt("S_HIT");
                String contents = arr.getJSONObject(i).getString("S_CONTEXT");
                String sex = arr.getJSONObject (i).getString ("S_SEX");
                String img = arr.getJSONObject (i).getString ("S_IMAGE");

                PetSitterDTO dto = new PetSitterDTO (name,hit,contents,sex,img);

                adapter.addItem (dto);
            }

        } catch (Exception e) {
            Log.i("DBtest", ".....ERROR.....!");
        }

        sitter_listview = rootView.findViewById (R.id.sitter_listview);
        sitter_listview.setAdapter (adapter);
        sitter_listview.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                PetSitterDTO item = (PetSitterDTO) adapter.getItem (position);
                Toast.makeText (getActivity ().getApplicationContext (),
                        "믿고맡견의 펫시터 " + item.getName ()+"님 입니다.",
                        Toast.LENGTH_SHORT).show ();

            }
        });

        // ****************************oracle db연동 리스트 출력 끝************************************

        try {
            String result;
            EBoardDB task = new EBoardDB ();
            result = task.execute().get();
            Log.d(TAG, result);
            JSONObject jObject = new JSONObject (result);
            JSONArray arr = jObject.getJSONArray("returns");

            int sum1 = 0; //최종합
            int count1 = 0; //횟수
            avg1 = 0; //평점

            int sum2 = 0; //최종합
            int count2 = 0; //횟수
            avg2 = 0; //평점

            int sum3 = 0; //최종합
            int count3 = 0; //횟수
            avg3 = 0; //평점

            int sum4 = 0; //최종합
            int count4 = 0; //횟수
            avg4 = 0; //평점

            int sum5 = 0; //최종합
            int count5 = 0; //횟수
            avg5 = 0; //평점

            int sum6 = 0; //최종합
            int count6 = 0; //횟수
            avg6 = 0; //평점


            for(int i =0;i<arr.length();i++){
                String ename = arr.getJSONObject(i).getString("E_NAME"); // 작성자
                String temp = arr.getJSONObject(i).getString("E_DATE");
                String date = temp.substring (0,10);
                String edate = date; // 날짜
                String estar = arr.getJSONObject(i).getString("E_STAR"); // 평점
                String econtent = arr.getJSONObject(i).getString("E_CONTENT"); // 내용
                String esitter = arr.getJSONObject(i).getString("E_SITTER"); // 사진
                String etitle = arr.getJSONObject (i).getString ("E_TITLE"); // 제목

                if (esitter.equals ("sittera.png"))   { //정의만
                    int cmp = Integer.parseInt (estar);
                    sum1 = sum1 + cmp;
                    count1++;

                } else if (esitter.equals ("sitterb.png")) {
                    int cmp = Integer.parseInt (estar);
                    sum2 = sum2 + cmp;
                    count2++;
                } else if (esitter.equals ("sitterc.png")) {
                    int cmp = Integer.parseInt (estar);
                    sum3 = sum3 + cmp;
                    count3++;
                } else if (esitter.equals ("sitterd.png")) {
                    int cmp = Integer.parseInt (estar);
                    sum4 = sum4 + cmp;
                    count4++;
                } else if (esitter.equals ("sittere.png")) {
                    int cmp = Integer.parseInt (estar);
                    sum5 = sum5 + cmp;
                    count5++;
                } else if (esitter.equals ("sitterf.jpg")) {
                    int cmp = Integer.parseInt (estar);
                    sum6 = sum6 + cmp;
                    count6++;
                }
            }

            avg1 = sum1 / count1;
            avg2 = sum2 / count2;
            avg3 = sum3 / count3;
            avg4 = sum4 / count4;
            avg5 = sum5 / count5;
            avg6 = sum6 / count6;

        } catch (Exception e) {
            Log.i("DBtest", ".....ERROR.....!");
        }

        // **************************** AlertDialog 펫시터 보기 *************************************

        btn_sitter = rootView.findViewById (R.id.btn_sitter);
        btn_sitter.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                final  String[] sitter = new String[]{"전체보기","남","여"};
                AlertDialog.Builder dialog = new AlertDialog.Builder (getActivity ());
                dialog  .setTitle ("펫시터 보기")
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
                                Log.d (TAG,"선택 : "+sitters);
                                adapter = new PetSitterAdapter (getActivity ());
                                try {
                                    String result;
                                    PetSitterDB task = new PetSitterDB ();
                                    result = task.execute().get();
                                    JSONObject jObject = new JSONObject (result);
                                    JSONArray arr = jObject.getJSONArray("returns");

                                    for(int i =0;i<arr.length();i++) {
                                        String name = arr.getJSONObject (i).getString ("S_NAME");
                                        int hit = arr.getJSONObject (i).getInt ("S_HIT");
                                        String contents = arr.getJSONObject (i).getString ("S_CONTEXT");
                                        String sex = arr.getJSONObject (i).getString ("S_SEX");
                                        String img = arr.getJSONObject (i).getString ("S_IMAGE");
                                        PetSitterDTO dto = new PetSitterDTO (name, hit, contents, sex, img);

                                        if (sex.equals (sitters)) {
                                            if (sitters.equals ("남")){
                                                Log.d (TAG," 남 펫시터 : " + name);
                                                adapter.addItem (dto);

                                            }else if (sitters.equals ("여")) {
                                                Log.d (TAG," 여 펫시터 : " + name);
                                                adapter.addItem (dto);
                                            }
                                        }
                                        if (sitters.equals ("전체보기")) {
                                            Log.d (TAG," 전체보기 펫시터 :" + name);
                                            adapter.addItem (dto);
                                        }
                                    }
                                } catch (Exception e) {
                                    Log.i("DBtest", ".....ERROR.....!");
                                }
                                sitter_listview.setAdapter (adapter);

                            }
                        }).setNeutralButton ("취소", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (getActivity ().getApplicationContext (),
                                "취소하셨습니다.",Toast.LENGTH_SHORT).show ();
                    }
                }).create().show ();
            }
        });
        // **************************** AlertDialog 펫시터 보기 끝*************************************


    } // onStart()


}