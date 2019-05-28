package ws2;

public abstract class Mobile {
	private String mobileName;
	private int batterySize;
	private String osType;
	public Mobile() {
	}
	public Mobile(String mobileName, int batterySize, String osType) {
		this.mobileName = mobileName;
		this.batterySize = batterySize;
		this.osType = osType;
	}
	protected String getMobileName() {
		return mobileName;
	}
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}
	protected int getBatterySize() {
		return batterySize;
	}
	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}
	protected String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	@Override
	public String toString() {
		return "\t" + mobileName + "\t\t" + batterySize + "\t\t" + osType;
	}
	public abstract int operate(int time); // 분단위
	public abstract int charge(int time); // 분단위
}
