package com.study.android.team_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class CommentFragment extends Fragment {
    private static final String TAG = "lecture";
    Button btn_comment1; // 후기작성 페이지 이동
    ListView commment_listview; // 후기 리스트뷰
    EBoardAdapter adapter; // 후기 정보
    ViewGroup rootView;

    private String userId = null;

    public CommentFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_comment, container, false);


        return rootView;
    }

    public void onStart() {
        super.onStart ();

        userId = getArguments().getString( "userId" );
        Log.d (TAG,"아이디 : " + userId);

        btn_comment1 = rootView.findViewById (R.id.btn_comment1);

        // 후기작성페이지 이동
        btn_comment1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (userId != null) {
                    Toast.makeText (getActivity ().getApplicationContext (),"믿고맡견의 "+userId+" 고객님! 소중한 후기를 남겨주세요!",Toast.LENGTH_SHORT).show ();
                    Intent intent = new Intent (getActivity ().getApplication (),CommentWrite.class);
                    intent.putExtra ("userId",userId);
                    startActivity (intent);

                } else if (userId == null) {

                    new AlertDialog.Builder (getActivity ())
                            .setTitle ("알림")
                            .setMessage ("로그인 후에 이용 가능합니다!")
                            .setCancelable (false)
                            .setIcon (android.R.drawable.ic_dialog_alert)
                            .setPositiveButton ("닫기",null)
                            .show ();

                    return;
                }

            }
        });

        //*******************************************************************************************


        adapter = new EBoardAdapter (getActivity ());

        try {
            String result;
            EBoardDB task = new EBoardDB ();
            result = task.execute().get();
            Log.d(TAG, result);
            JSONObject jObject = new JSONObject (result);
            JSONArray arr = jObject.getJSONArray("returns");

//            SimpleDateFormat format = new SimpleDateFormat ("YYYY-MM-DD");

            for(int i =0;i<arr.length();i++){
                String ename = arr.getJSONObject(i).getString("E_NAME"); // 작성자
                String temp = arr.getJSONObject(i).getString("E_DATE");
                String date = temp.substring (0,10);
                String edate = date; // 날짜
                String estar = arr.getJSONObject(i).getString("E_STAR"); // 평점
                String econtent = arr.getJSONObject(i).getString("E_CONTENT"); // 내용
                String esitter = arr.getJSONObject(i).getString("E_SITTER"); // 사진
                String etitle = arr.getJSONObject (i).getString ("E_TITLE"); // 제목

                EBoardDTO dto = new EBoardDTO (ename,edate,estar,econtent,esitter,etitle);

                adapter.addItem (dto);
            }

        } catch (Exception e) {
            Log.i("DBtest", ".....ERROR.....!");
        }
        commment_listview = rootView.findViewById (R.id.commment_listview);
        commment_listview.setAdapter (adapter);

    }

    public static CommentFragment newInstance(String param1) {

        Bundle args = new Bundle();
        args.putString( "userId", param1 );
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments( args );
        return fragment;
    }

}