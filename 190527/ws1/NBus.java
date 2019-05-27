package ws1;

public class NBus extends Bus {
	
	private int time;
	private String color;
	public NBus() {
	}
	public NBus(int time, String color) {
		this.time = time;
		this.color = color;
	}
	public NBus(int fare, int station, int number, int time) {
		super(fare, station, number);
		this.time = time;
//		this.color = color;
		// TODO Auto-generated constructor stub
	}
	public void fillColor(String color) {
		this.color = color;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "NBus [time=" + time + "Ω√ ¿Ã»ƒ, color=" + color + "]";
	}
	@Override
	public int getFare() {
		int result = 0;
		result = fare + (int)(getStation()*10) + (int)(getTime()*20);
		return result;
	}
}
