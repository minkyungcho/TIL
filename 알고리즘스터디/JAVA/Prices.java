package code;
public class Prices {
	public static void main(String[] args) {
		int[] prices = {1,2,3,2,3};
//		int[] prices = {498, 501, 470, 489};
		int[] answer = new int[prices.length];
        for(int i=0; i<prices.length; i++){
            int cnt = 0;
            for(int j=i+1; j<=prices.length-1; j++){
                if(prices[i]<=prices[j]){
                    cnt++;
                }else{
                    cnt++;
                    break;
                }
            }
            answer[i] = cnt;
        }
//        answer[prices.length-1] = 0;
        for(int i=0; i<prices.length; i++) {
        	System.out.print(answer[i]+" ");
        }
	}
}
