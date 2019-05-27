package ws1;

public class Subway extends Transfortation{
	private String espress;
	private int line;
	public Subway() {
	}
	public Subway(String espress, int line) {
		this.espress = espress;
		this.line = line;
	}
	public Subway(int fare, int station, String espress, int line) {
		super(fare, station);
		this.espress = espress;
		this.line = line;
	}
	@Override
	public int getFare() {
		// TODO Auto-generated method stub
		int result = 0;
		result = fare + (int)(getStation()*20);
		return result;
	}
	@Override
	public String toString() {
		return "Subway [espress=" + espress + ", line=" + line + "]";
	}
}
