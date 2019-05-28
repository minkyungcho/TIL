package ws2;

public class Otab extends Mobile{
	public Otab() {
	}
	public Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int operate(int time) {
		// TODO Auto-generated method stub
		int result = getBatterySize();
		result -= time*12; 
		this.setBatterySize(result);
		return result;
	}
	@Override
	public int charge(int time) {
		// TODO Auto-generated method stub
		int result = getBatterySize();
		result += time*8; 
		this.setBatterySize(result);
		return result;
	}
}