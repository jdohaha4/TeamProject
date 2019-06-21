package com.study.android.team_project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PetSitterLoginAsyncTask extends AsyncTask<String, Void, String> {
    private static final String TAG ="lecture";

    String sendMsg,receiveMsg;
    String[] sendMsg1;

    @Override
    protected String doInBackground(String... strings){
        try {
            String str;

            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL("http://192.168.219.108:8081/petsitter_login");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "id=" + strings[0] + "&pwd=" + strings[1];

            osw.write(sendMsg);
            Log.d(TAG, sendMsg);
            osw.flush();

            //jsp와 통신 성공 시 수행
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                Log.d(TAG, "tmp : " + tmp);
                // jsp에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                    Log.d(TAG, "보내는 str" + str);
                }
                receiveMsg = buffer.toString();
                Log.d(TAG, "ReceiveMsg : " + receiveMsg);
            } else {
                Log.d(TAG, "통신 실패");
                // 통신 실패
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //jsp로부터 받은 리턴 값
        return receiveMsg;
    }
}
