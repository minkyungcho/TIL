package ws;

import java.util.Arrays;
import java.util.Random;

public class Ticket {
	private int[] winningNumber;
	private int[] my_Number;
	private int[] ran_Number;
	
//	public Ticket() {
//	}
	public Ticket(int[] my_Number) {
		this.my_Number = my_Number;
	}
	public Ticket() {
		Random r = new Random();
		ran_Number = new int[6];
		for(int i=0; i<ran_Number.length;i++) {
			ran_Number[i] = r.nextInt(45)+1;
			for(int j=0; j<i;j++) {
				if(ran_Number[i] == ran_Number[j]) {
					i--;
					break;
				}
			}
		}
//		System.out.println(Arrays.toString(ran_Number));
		
	}
	public int check() {
		return 0;
	}
	public void depositPrize() {
		
	}
	public void printPrize() {
		
	}
	@Override
	public String toString() {
		return "Ticket [winningNumber=" + Arrays.toString(winningNumber) + ", my_Number=" + Arrays.toString(my_Number)
				+ ", ran_Number=" + Arrays.toString(ran_Number) + "]";
	}
	
	
}
