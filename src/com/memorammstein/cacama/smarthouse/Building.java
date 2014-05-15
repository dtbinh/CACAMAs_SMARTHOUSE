package com.memorammstein.cacama.smarthouse;

import java.util.ArrayList;

public class Building implements Runnable {
	
	private ArrayList<Room> rooms = null;
	private String name = null;
	
	public Building(String name) {
		this.name = name;
		rooms = new ArrayList<Room>();
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void removeRoom(int roomIndex) {
		rooms.remove(roomIndex);
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public String getName() {
		return name;
	}
	
	public void start() {
		com.memorammstein.cacama.io.OutputManager.printBuilding(this);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		for (Room room : rooms) {
			room.start();
		}
		
	}
	
}
