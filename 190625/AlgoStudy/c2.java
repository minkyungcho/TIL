package day02;

import java.util.Scanner;

public class c2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); // ���ڿ� �Է� �ޱ� 
        char carr[] = s.toCharArray(); // �Է¹��� ���ڿ� �ѱ��ھ� �迭�� �ֱ�  
        int arr[] = new int[26]; // ���ĺ� ũ���� int�� �迭 ���� 
        
        // �迭 ��� -1�� ����
        for(int i=0; i<arr.length; i++) {
            arr[i] = -1;
        }
        
        // ��ġ ��
        for(int i=0; i<carr.length;i++) {
            int n = carr[i];
            if(arr[n-97] == -1) {
                arr[n-97] = i;
            }    
        }
        
        // ��� 
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
	}

}
