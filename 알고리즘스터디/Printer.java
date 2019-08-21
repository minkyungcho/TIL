package code;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.management.Query;
public class Printer {
	public static void main(String[] args) {
		int[] priorities = {2,1,3,2};
		int location = 2;
		int answer = 0;
		int index = 0;
		int max = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> indexlist = new ArrayList<Integer>();
		
		for(int i=0; i<priorities.length; i++) {
			list.add(priorities[i]);
			indexlist.add(i);
		}
		
		for(int i=0; i<priorities.length-1; i++) {
//			max = priorities[i];
			for(int j=i; j<priorities.length; j++) {
				if(max < priorities[j]) {
					max = priorities[j];
				}
			}
			// 현재 프린트물이 max보다 우선순위가 작으면 맨 뒤로 간다
			if(priorities[i] < max) {
//				int temp = list.get(i);
//				list.remove(i);
//				list.add(temp);
				list.add(list.get(i));
				list.remove(i);
				indexlist.add(indexlist.get(i));
				indexlist.remove(i);
			}else { // 현재 프린트물이 max 보다 크거나 같으면 인쇄를 한다.
				
			}
		}
	}
}
