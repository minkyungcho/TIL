package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

class SendThread extends Thread {
	String id;
	String pwd;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void run() {
		String urlstr = "http://70.12.60.90/test/login.jsp";
		String id = "aa";
		String pwd = "11";
		urlstr += "?id=" + id + "&pwd=" + pwd;

		URL url = null;

		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);
			
			is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String result = null;
				while((result=br.readLine()) != null) {
					System.out.println(result);
				}
			}else { // 로그인 비정상적으로 됐을 때
				System.out.println("Server Down ...");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
}

public class Http4 {

	public static void main(String[] args) {
		// thread 만들고
		// thread invocation해서
		// main thread에서 id,pwd scanner로 입력 받기
		// thread를 통해 서버로 전송하기.
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Input .. ");
			String id = sc.next();
			String pwd = sc.next();

			SendThread st = new SendThread();
			st.setId(id);
			st.setPwd(pwd);
			st.start();
		}
	}

}
