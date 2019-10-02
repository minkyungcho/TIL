package day04;

import java.util.Scanner;

public class c1Q1193 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();

		int cnt = 0;

		int line = 0; // 몇번째 줄

		int num = 0; //

		int order = 0; // 몇번째 숫자

		int denominator = 0; // 분자

		int numerator = 0; // 분모

		// 몇번째 줄인지 계산

		for (int i = 1; i <= input; i++) {

			if (cnt >= input) {

				break;

			}

			line++;

			cnt += i;

		}

		// 몇번째 숫자인지 계산

		num = (line - 1) * line / 2; // 등차수열

		order = input - num;

		// 홀수번째 줄

		// 분자 : 1 - 3 2 1 - 5 4 3 2 1 - 7 6 5 4 3 2 1 - ...

		// 분모 : 1 - 1 2 3 - 1 2 3 4 5 - 1 2 3 4 5 6 7 - ...

		if (line % 2 == 1) {

			// 분자

			denominator = line - (order - 1);

			// 분모

			numerator = order;

			// 출력

			System.out.println(denominator + "/" + numerator);

		}

		// 짝수번째 줄

		// 분자 : 1 2 - 1 2 3 4 - 1 2 3 4 5 6 - ...

		// 분모 : 2 1 - 4 3 2 1 - 6 5 4 3 2 1 - ...

		else {

			// 분자

			denominator = order;

			// 분모

			numerator = line - (order - 1);

			// 출력

			System.out.println(denominator + "/" + numerator);

		}
	}

}
