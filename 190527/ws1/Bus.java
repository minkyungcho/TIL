package ws1;

public class Bus extends Transfortation{
	private int number;
	public Bus() {
	}
	public Bus(int number) {
		this.number = number;
	}
	public Bus(int fare, int station, int number) {
		super(fare, station);
		this.number = number;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Bus [number=" + number + "]";
	}
	@Override
	public int getFare() {
		int result = 0;
		result = fare + (int)(getStation()*10);
		return result;
	}
}
