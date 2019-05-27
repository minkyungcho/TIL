package ws2;

public class Ltab extends Mobile{
	public Ltab() {
	}

	public Ltab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
		// TODO Auto-generated constructor stub
	}

//	
//	@Override
//	public String toString() {
//		return "Ltab [getMobileName()=" + getMobileName() + ", getBatterySize()=" + getBatterySize() + ", getOsType()="
//				+ getOsType() + "]";
//	}

	@Override
	public int operate(int time) {
		// TODO Auto-generated method stub
		int result = getBatterySize();
		result -= time*10; 
		return result;
	}

	@Override
	public int charge(int time) {
		// TODO Auto-generated method stub
		int result = getBatterySize();
		result += time*10; 
		return result;
	}
	
	
}
