package day02;

import java.util.Scanner;

public class c2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); // 문자열 입력 받기 
        char carr[] = s.toCharArray(); // 입력받은 문자열 한글자씩 배열에 넣기  
        int arr[] = new int[26]; // 알파벳 크기의 int형 배열 생성 
        
        // 배열 요소 -1로 만들
        for(int i=0; i<arr.length; i++) {
            arr[i] = -1;
        }
        
        // 위치 비교
        for(int i=0; i<carr.length;i++) {
            int n = carr[i];
            if(arr[n-97] == -1) {
                arr[n-97] = i;
            }    
        }
        
        // 출력 
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
	}

}
