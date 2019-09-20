package http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Http2 {

	public static void main(String[] args) throws Exception{
		String urlstr = "http://70.12.60.90/test/oracle.zip";
		URL url = new URL(urlstr);
		InputStream is = url.openStream();
		BufferedInputStream bis = new BufferedInputStream(is,1024);
		
		int data = 0;
		// oracle.zip
		
		String fileName = "oracle.zip";
		FileOutputStream fos = new FileOutputStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos) ;		
		
		System.out.println("Start ...");
		while((data=bis.read()) != -1) {
			bos.write(data);
//			System.out.println("*"+data);
		}
		System.out.println("Finish ...");
		is.close();
		bis.close();
	}

}
