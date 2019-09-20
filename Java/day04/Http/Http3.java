package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http3 {

	public static void main(String[] args) throws Exception {
		// id와 pwd를 전송해서 결과값 받기.
		// 결과 : id, pwd가 일치하는지 불일치하는지.
		String urlstr = "http://70.12.60.111/webview/login.jsp";
//		String urlstr = "http://70.12.60.90/test/login.jsp";
		String id = "ming";
		String pwd = "11";
		urlstr += "?id="+id+"&pwd="+pwd;
		
		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET"); // 해당 url로 connection을 GET방식으로 진행
		conn.setRequestMethod("POST"); // 보안
		conn.setReadTimeout(5000);
		
		InputStream is = conn.getInputStream(); // server로 전송. server와 app 사이에 input stream 생김.
		
		InputStreamReader isr = new InputStreamReader(is); // 결과를 2byte로 읽음. 다시 string으로 바꿔줘야함.
		BufferedReader br = new BufferedReader(isr); // Line 단위
		
		// 로그인 정상적으로 됐을 때
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			String result = null;
			while((result=br.readLine()) != null) {
				System.out.println(result);
			}
		}else { // 로그인 비정상적으로 됐을 때
			System.out.println("Server Down ...");
		}
		
//		conn.disconnect();
	}

}
