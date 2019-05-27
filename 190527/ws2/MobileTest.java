package ws2;

public class MobileTest {

	public static void main(String[] args) {
		// 각각의 Mobile 객체 생성
        // 생성된 객체의 정보 출력
        // 각각의 Mobile 객체에 10분씩 충전 
        // 10분 충전 후 객체 정보 출력
        // 각각의 Mobile 객체에 5분씩 통화
        // 5분 통화 후 객체 정보 출력
		Mobile mo[] = new Mobile[2];
		mo[0] = new Ltab("Ltab", 500, "AP-01");
		mo[1] = new Otab("Otab", 1000, "AND-20");
		
		for(Mobile mob:mo) {
			System.out.println(mob.toString());
		}
		
	}


}
