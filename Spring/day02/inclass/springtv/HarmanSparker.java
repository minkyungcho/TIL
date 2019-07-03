package springtv;

import org.springframework.stereotype.Component;

public class HarmanSparker implements Speaker {

	@Override
	public void up() {
		System.out.println("Harman speaker Volume up");
	}

	@Override
	public void down() {
		System.out.println("Harman speaker Volume up");
	}

}
