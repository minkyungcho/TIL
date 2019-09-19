package day03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fi1 {

	public static void main(String[] args) {
		FileReader fi = null;
		BufferedReader br = null;
		try {
			fi = new FileReader("test.txt");
			br = new BufferedReader(fi);
			String data = null;
			while((data=br.readLine()) != null) {
				System.out.println(data);
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fi != null) {
				try {
					fi.close();
				} catch (IOException e) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					try {
						fi.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
