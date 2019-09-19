package day03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Fi2 {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("C:\\network\\day03\\test.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);

		FileOutputStream fos = new FileOutputStream("C:\\network\\day03\\test2.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos, 5); // 5byte ������ ����
		
		int data = 0;
		while ((data = bis.read()) != -1) {
			char c = (char) data;
			System.out.print(c);
			fos.write(c);
		}
		if (fis != null) {
			bis.close();
//			fis.close();
		}
		if (fos != null) {
			bos.flush();
			bos.close();
//			fos.close(); // ���� stream�� close �ϸ� �ȿ� �ִ� ���� stream�� close �ȴ�.
		}
	}
}
