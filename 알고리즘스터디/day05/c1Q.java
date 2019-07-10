package day05;
import java.util.Arrays;

public class c1Q {

	public int[] solution(int[] array, int[][] commands) {
        int answer[] = new int[commands.length];
        int cut[][] = new int[commandss.length];
        for(int i=0; i<commands.length; i++){
//            cut[i] = Arrays.copyOfRange(array, commands[0][0], commands[0][1]);
            cut[0][i] = Arrays.copyOfRange(array, i, i);
            Arrays.sort(cut);
            answer[i] = cut[commands[0][2]];
        }
        
        
        
        return answer;
    }
	
}

