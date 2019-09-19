package pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class InputThread extends Thread {
	PipedReader input;
	BufferedReader br;
	
	public InputThread(String name) {
		super(name);
		input = new PipedReader();
		br = new BufferedReader(input);
	}

	public void run() {
		// int data = 0;
		// StringWriter sw = new StringWriter();
		
		String data = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			System.out.println("Ready");
//			while ((data = input.read()) != -1) {
//				sw.write(data);
//			}
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}
//			System.out.println("Receiced:" + sw.toString());
			System.out.println("Receiced:" + sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public PipedReader getInput() {
		return input;
	}

	public void connect(PipedWriter output) {
		try {
			input.connect(output);
			System.out.println("connected:");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
