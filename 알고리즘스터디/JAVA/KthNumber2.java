package day05;

import java.util.Arrays;

public class c2Q {
	public static void main(String[] args) {
		int[] array = {1,5,2,6,3,7,4};
		int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
		
		System.out.println(Arrays.toString(solution(array, commands)));
	}

	public static int[] solution(int[] array, int[][] commands) {
		int answer[] = new int[commands.length];
		
		for (int i = 0; i < commands.length; i++) {
			int[] cut = new int[commands[i][1] - commands[i][0]];
			
			cut = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
			Arrays.sort(cut);
			
			System.out.println(Arrays.toString(cut));
			answer[i] = cut[commands[i][2] - 1];
		}

		return answer;
	}

}
