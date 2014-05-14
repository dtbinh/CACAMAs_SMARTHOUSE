package com.memorammstein.cacama.smarthouse;

public class Room implements Runnable {
	
	private String name = null;
	
	public Room(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
