package day02;

import java.util.Scanner;

public class c4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char carr[] = sc.nextLine().toCharArray();
		int arr[] = new int[26];
		for(int i=0; i<carr.length; i++) {
			if(carr[i]>=91) {
				arr[carr[i]-97] += 1;
			}else {
				arr[carr[i]-65] += 1;
			}
		}
		char result = ' ';
		int max = -1;
		int check = 0;
		for(int i=0; i<arr.length; i++) {
			if(max<arr[i]) {
				max = arr[i];
				result = (char)(i+'A');
			}
		}
		for(int i=0; i<arr.length; i++) {
			if(max == arr[i]) {
				check++;
			}
		}
		if(check>=2) {
			result = '?';
		}
		System.out.println(result);
	}
}
