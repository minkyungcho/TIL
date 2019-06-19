package day01;

import java.util.Scanner;

public class c1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().trim();
		String arr[] = str.split(" ");
		int c = 0;
		if(str.isEmpty()) {
			System.out.println(c);
			sc.close();
			return;
		}else {
			c = arr.length;
		}
		sc.close();
		System.out.println(c);
	}
}
