package http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpWS {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://70.12.60.90/test");
		
		InputStream is = url.openStream();
		
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String data = null;
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		
		while((data=br.readLine()) != null) {
			System.out.println(data);
			bw.write(data);
			bw.newLine();
			
		}
	}

}
