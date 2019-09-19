package day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Fi3 {

	public static void main(String[] args) throws Exception {
		
		FileReader fis = new FileReader("C:\\network\\day03\\test.txt");
		BufferedReader bis = new BufferedReader(fis);
		
		FileWriter fos = new FileWriter("C:\\network\\day03\\test3.txt");
		BufferedWriter bos = new BufferedWriter(fos);
		
		String data = null;
		while((data=bis.readLine()) != null) {
			System.out.println(data);
			bos.write(data);
			bos.newLine();
		}
		
		if(bis != null) {
			bis.close();
		}
		if(bos != null) {
			bos.flush();
			bos.close();
		}
		
	}

}
