package com.memorammstein.cacama.smarthouse;

import java.util.ArrayList;

public class Building implements iBuilding {
	
	private ArrayList<iRoom> rooms = null;
	private String name = null;
	
	public Building(String name) {
		this.name = name;
		rooms = new ArrayList<iRoom>();
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void removeRoom(int roomIndex) {
		rooms.remove(roomIndex);
	}
	
	public ArrayList<iRoom> getRooms() {
		return rooms;
	}
	
	public String getBuildingName() {
		return name;
	}
	
	public void start() {
		com.memorammstein.cacama.io.OutputManager.printBuilding(this);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		for (iRoom room : rooms) {
			room.start();
		}
		
	}

	@Override
	public void enable() {
		for (iRoom room : getRooms()) {
			room.enable();
		}
	}

	@Override
	public void disable() {
		for (iRoom room : getRooms()) {
			room.disable();
		}
	}
	
}
