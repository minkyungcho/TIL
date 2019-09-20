package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http3 {

	public static void main(String[] args) throws Exception {
		// id�� pwd�� �����ؼ� ����� �ޱ�.
		// ��� : id, pwd�� ��ġ�ϴ��� ����ġ�ϴ���.
		String urlstr = "http://70.12.60.111/webview/login.jsp";
//		String urlstr = "http://70.12.60.90/test/login.jsp";
		String id = "ming";
		String pwd = "11";
		urlstr += "?id="+id+"&pwd="+pwd;
		
		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET"); // �ش� url�� connection�� GET������� ����
		conn.setRequestMethod("POST"); // ����
		conn.setReadTimeout(5000);
		
		InputStream is = conn.getInputStream(); // server�� ����. server�� app ���̿� input stream ����.
		
		InputStreamReader isr = new InputStreamReader(is); // ����� 2byte�� ����. �ٽ� string���� �ٲ������.
		BufferedReader br = new BufferedReader(isr); // Line ����
		
		// �α��� ���������� ���� ��
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			String result = null;
			while((result=br.readLine()) != null) {
				System.out.println(result);
			}
		}else { // �α��� ������������ ���� ��
			System.out.println("Server Down ...");
		}
		
//		conn.disconnect();
	}

}
