package com.example.p577;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    public static String getString(String urlstr){
        String result = null;
        // 가장 안전하게 코드 작성
        URL url = null; // url 객체 생성
        HttpURLConnection hcon = null; // url 커넥션 객체 생성
        InputStream is = null; // 서버에 접속하여 가져오는 통로 생성

        try {
            url = new URL(urlstr);
            hcon = (HttpURLConnection) url.openConnection();
            hcon.setConnectTimeout(10000);
            hcon.setRequestMethod("GET");
            is = new BufferedInputStream(hcon.getInputStream()); // 요청을 하면 inputStream 만들어짐
            result = convertStr(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hcon.disconnect();
        }
        return result;
    }

    public static String convertStr(InputStream is){
        BufferedReader br = null; //
        br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String temp;
        try{
            while((temp = br.readLine()) != null){
                sb.append(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
