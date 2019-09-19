package http;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

public class Http1 {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://70.12.60.90/test");
		URLConnection conn = url.openConnection();
		
		InputStream is = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		
		int data = 0;
		StringWriter sw = new StringWriter();
		while((data=bis.read()) != -1) {
			char c = (char)data;
			sw.write(c);
		}
		System.out.println(sw.toString());
	}

}
