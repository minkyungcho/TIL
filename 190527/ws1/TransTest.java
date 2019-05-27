package ws1;

public class TransTest {

	public static void main(String[] args) {
		
		Transfortation t[] = new Transfortation[3];
		t[0] = new Subway(1250, 10, "급행", 9);
		t[1] = new Bus(1250, 25, 3318);
		t[2] = new NBus(2500, 5, 270, 2);
		for(Transfortation tr:t) {
			if(tr instanceof NBus) {
				NBus b = (NBus)tr;
				b.fillColor("Yellow");
			}
			System.out.print(tr.toString());
			System.out.println(" 총 요금은 "+tr.getFare());
		}
	}
}
