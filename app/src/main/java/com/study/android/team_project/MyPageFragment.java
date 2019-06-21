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
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MyPageFragment extends Fragment {
    private static final String TAG = "lecture";

    public static Button btn_reg_diary, btn_reservation_ctm, btn_pet_sitter;

    TextView name, welcome;

    private String userId = null;
    private String result = null;
    private String petId = null;

    public MyPageFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_my_page, container, false);


        ListView listView =  rootView.findViewById(R.id.listView);

        String[] myPageItem = {"반려동물 등록", "반려동물 프로필 보기", "예약 내역 확인",
                "반려동물 일지 확인"};

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter(
                getActivity(),
                R.layout.simple_list_item_1,
                myPageItem
        );



        userId = getArguments().getString( "userId" );

        name = rootView.findViewById( R.id.nameTextView );
        welcome = rootView.findViewById( R.id.meaningText );

        result = getArguments().getString( "result" );
        petId = getArguments().getString( "petId" );



        if (userId != null) {
            name.setText( userId );
            welcome.setText( "님 환영합니다.");
        }else if(userId == null){
            welcome.setText( "로그인 해주세요." );
            name.setText( "로그인이 필요합니다." );

        }
        Log.d( TAG, "유저 아이디는 ? " + userId );
        Log.d( TAG, "로그인 결과는 ?" + result );
        Log.d( TAG, "팻시터 아이디는 ?" + petId );




        listView.setAdapter(listViewAdapter);
        if(userId != null){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){
                        Intent intent = new Intent( getActivity(), MyPageSignPet.class );
                        intent.putExtra( "userId", userId );
                        startActivity( intent );
                    }else if(position == 1){
                        Intent intent = new Intent(getActivity(), MyPagePetProfile.class);
                        intent.putExtra( "userId", userId );
                        startActivity(intent);
                    }else if(position == 2){
                        Intent intent = new Intent(getActivity(), MyPageCheckReservation.class);
                        intent.putExtra( "userId",userId );
                        startActivity(intent);
                    }else if(position == 3) {
                        Intent intent = new Intent( getActivity(), MyPagePetDiary.class );
                        intent.putExtra( "userId", userId );
                        startActivity( intent );
                    }
                }
            });
        } else {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    new AlertDialog.Builder (getActivity ())
                            .setTitle ("알림")
                            .setMessage ("로그인 후에 이용 가능합니다!")
                            .setCancelable (false)
                            .setIcon (android.R.drawable.ic_dialog_alert)
                            .setPositiveButton ("닫기",null)
                            .show ();
                    return;
                }else if(position == 1){
                    new AlertDialog.Builder (getActivity ())
                            .setTitle ("알림")
                            .setMessage ("로그인 후에 이용 가능합니다!")
                            .setCancelable (false)
                            .setIcon (android.R.drawable.ic_dialog_alert)
                            .setPositiveButton ("닫기",null)
                            .show ();
                    return;
                }else if(position == 2){
                    new AlertDialog.Builder (getActivity ())
                            .setTitle ("알림")
                            .setMessage ("로그인 후에 이용 가능합니다!")
                            .setCancelable (false)
                            .setIcon (android.R.drawable.ic_dialog_alert)
                            .setPositiveButton ("닫기",null)
                            .show ();
                    return;
                }else if(position == 3){
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
     }



        Button btn_pet_sitter = rootView.findViewById(R.id.btn_pet_sitter);
        Button btn_reg_diary = rootView.findViewById( R.id.btn_reg_diary );



        btn_reg_diary.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), PetSitterDiaryActivity.class);
                startActivity( intent );
            }
        } );




        if(result == null) {

            btn_pet_sitter.setVisibility( rootView.VISIBLE );
            btn_reg_diary.setVisibility( rootView.GONE );
//            btn_reservation_ctm.setVisibility( rootView.GONE );

        }else if(result.equals( "로그인 성공" )){

            btn_pet_sitter.setVisibility( rootView.GONE);
            btn_reg_diary.setVisibility( rootView.VISIBLE );
//            btn_reservation_ctm.setVisibility( rootView.VISIBLE);

            name.setText( "팻시터 " + petId );
            welcome.setText( "님 환영합니다." );
        }




        //**************펫시터 버튼************


        btn_pet_sitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder( getActivity());
                alert.setMessage( "팻시터 모드로 전환합니다. 팻시터로 로그인 하시겠습니까?")
                        .setCancelable(false).setPositiveButton( "로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(getActivity(), PetSitterLogin.class);
                        startActivity(intent);


                    }
                }).setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        return;

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });


        return rootView;

    }


    public static MyPageFragment newInstance(String param1,String param2, String param3) {

        Bundle args = new Bundle();
        args.putString( "userId", param1 );
        args.putString("result", param2);
        args.putString( "petId", param3 );

        MyPageFragment fragment = new MyPageFragment();
        fragment.setArguments( args );


        return fragment;
    }
}


