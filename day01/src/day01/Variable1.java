package day01;

// Java Application
public class Variable1 {

	public static void main(String[] args) {
		int a = 50;
		int b = 20;
		int c = 30;
		
		int temp_max = 0;
		int temp_min = 0;
//  min
		if (a < b) {
			temp_min = a;
			if (c < temp_min)
				temp_min = c;
		}
		else {
			temp_min = b;
			if (c < temp_min)
				temp_min = c;
		}
		
//	max		
		if (a > b) {
			temp_max = a;
			if (c > temp_max) {
				temp_max = c;
			}
		}

		else {
			temp_max = b;
			if (c > temp_max) {
				temp_max = c;
			}
		}
		System.out.println(temp_min);
		System.out.println(temp_max);
	}

}
