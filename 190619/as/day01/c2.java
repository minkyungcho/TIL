package day01;

import java.util.Scanner;

public class c2 {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int a = sc.nextInt();
//		int b = sc.nextInt();
//		int c = sc.nextInt();
//		int count = 0;
//		String mul = Integer.toString(a*b*c);
//		System.out.println(mul.length());
//		for(int i=0; i<10; i++) {
//			for(int j=0; j<mul.length(); j++) {
//				if(Integer.toString(i).equals(mul.charAt(j))) {
//					count++;
//					System.out.println(count);
//					break;
//				}else {
//					System.out.println(mul.charAt(j)+" "+i);
//					System.out.println(count);
//				}
//				System.out.println("-----");
//			}
//		}
//		sc.close();
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int count[] = new int[10];
		String mul = Integer.toString(a*b*c);
		for(int i=0; i<mul.length(); i++) {
			count[mul.charAt(i)-'0']++;
		}
		for(int i=0; i<count.length; i++) {
			System.out.println(count[i]);
		}
		sc.close();
	}

}
