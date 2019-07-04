package day04;

import java.util.Scanner;

public class c2Q2775 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++) {
			int K = sc.nextInt();
			int N = sc.nextInt();
			System.out.println(People(K, N));
		}
	}
	public static int People(int K, int N) {
		int result = 0;
		
		if(K==0) {
			result = N;
		}else {
			for(int i=1; i<=N; i++) {
				result += People(K-1, i);
			}
		}
		return result;
	}
}
