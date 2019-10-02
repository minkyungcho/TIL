package code;

import java.util.LinkedList;
import java.util.Queue;

public class Truck {

	public static void main(String[] args) {
		int bridge_length = 100; // 2
		int weight = 100; // 10
		int[] truck_weights = {10,10,10,10,10,10,10,10,10,10}; // {7,4,5,6}
		int time = 0;
        int sum = 0;
        int answer = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        // 다리 길이만큼 큐에 0 넣기
        for(int i=0; i<bridge_length; i++){
            q.add(0);
        }
        for(int i=0; i<truck_weights.length; i++) {
        	// 1초 마다 다리 제일 앞에 있는 트럭 빼기 
        	sum -= q.poll();
        	// 초과하지 않으면 트럭 큐에 넣기
        	if(sum + truck_weights[i] <= weight) {
        		// 현재 트럭 큐에 삽입
        		q.add(truck_weights[i]);
        		// sum에 현재 트럭 무게 더하기
        		sum += truck_weights[i];
        	}else { // 초과한다면 0을 큐에 넣기 
        		// 0 넣기
        		q.add(0);
        		// 현재 트럭이 큐에 들어갈때까지 for문 다시 돌리기 위해 인덱스 감소
        		i--;
        	}
        	// for문 돌아갈때마다 1초씩 증가
        	time++;
        }
        // 마지막 트럭까지 넣고 난후에는 마지막 트럭이 다리 빠져나올때까지를 더해야함.
        answer = time + bridge_length;
        System.out.println(answer);
	}
}
