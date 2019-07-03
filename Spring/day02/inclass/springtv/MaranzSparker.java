package springtv;

import org.springframework.stereotype.Component;

public class MaranzSparker implements Speaker {

	@Override
	public void up() {
		System.out.println("Maranz speaker Volume up");
	}

	@Override
	public void down() {
		System.out.println("Maranz speaker Volume up");
	}

}
