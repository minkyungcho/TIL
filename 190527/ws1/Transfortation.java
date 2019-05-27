package ws1;

public abstract class Transfortation {
	public int fare;
	private int station;
	public Transfortation() {
	}
	public Transfortation(int fare, int station) {
		this.fare = fare;
		this.station = station;
	}
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "fare=" + fare + ", station=" + station+", " ;
	}
	public abstract int getFare();
}
