package com.sds;

public class STV implements TV{
	
	private int size;
	private Speaker speaker;
	
	public STV() {
		System.out.println("STV Construct");
	}
	
	public STV(int size) {
		size = this.size;
	}
	
	public STV(int size, Speaker speaker) {
		this.size = size;
		this.speaker = speaker;
	}

	public STV(Speaker speaker) {
		super();
		this.speaker = speaker;
	}

	public void up() {
		speaker.up();
	}
	
	public void turnOn() {
		System.out.println("STV On");
	}
	public void turnOff() {
		System.out.println("STV Off");
	}

	@Override
	public String toString() {
		return "STV [size=" + size + "]";
	}
}
