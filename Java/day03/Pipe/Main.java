package pipe;

public class Main {

	public static void main(String[] args) {
		InputThread it = new InputThread("inThread");
		OutputThread ot = new OutputThread("outThread");
		it.connect(ot.getOutput());
		it.start();
		ot.start();
	}
}
