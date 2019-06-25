package day02;

import java.util.Scanner;

public class c3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int testnum = Integer.parseInt(sc.nextLine());
        for(int i=0; i<testnum; i++) {
            String arr[] = new String[2];
            arr = sc.nextLine().split(" ");
            int n = Integer.parseInt(arr[0]);
            String result = "";
            for(int j=0; j<arr[1].length(); j++ ) {
                
                for(int k=0; k<n; k++) {
                    result += arr[1].charAt(j);
                }
            }
            System.out.println(result);
        }
	}

}
