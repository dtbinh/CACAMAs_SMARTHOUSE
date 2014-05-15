package com.memorammstein.cacama.smarthouse;

import java.util.ArrayList;

public interface iBuilding extends Runnable {
	public ArrayList<iRoom> getRooms();
	public String getBuildingName();
	public void addRoom(Room room);
	public void removeRoom(int roomIndex);
	public void enable();
	public void disable();
	public void start();
}
