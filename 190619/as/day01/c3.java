package day01;

import java.util.Scanner;

public class c3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int result = 0;
		String strs[] = new String[n];
		for(int i=0; i<n; i++) {
			strs[i] = sc.nextLine();
		}
		
		for(int i=0; i<strs.length; i++) {
			int r = 1;
			for(int j=0; j<strs[i].length();j++) {
				if(strs[i].charAt(j)=='O') {
					result = result + r;
					r++;
				}else {
					r = 0;
				}
				System.out.println(strs[i]);
				System.out.println(result);
				System.out.println("----");
			}
			
			
//			System.out.println(strs[i]);
//			System.out.println(result);
			
		}
		
		sc.close();
		
	}

}
