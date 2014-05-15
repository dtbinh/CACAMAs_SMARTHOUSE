package com.memorammstein.cacama.smarthouse;

import java.util.ArrayList;

import com.memorammstein.cacama.automata.AccessControl;
import com.memorammstein.cacama.automata.AutomatonWrapper;

public class Room implements Runnable {
	
	private String roomName = null;
	private ArrayList<AutomatonWrapper> automatons = null;
	
	private AccessControl accessControl = null;
	
	public Room(String roomName) {
		setRoomName(roomName);
		automatons = new ArrayList<AutomatonWrapper>();
		accessControl = new AccessControl(this.getRoomName() + ": " + "Control de acceso");
		addAutomaton(accessControl);
	}
	
	public String getRoomName() {
		return this.roomName;
	}
	
	public void setRoomName(String name) {
		this.roomName = name;
	}
	
	public void addAutomaton(AutomatonWrapper aw) {
		automatons.add(aw);
	}
	
	public void start() {
		com.memorammstein.cacama.io.OutputManager.printRoom(this);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		for (AutomatonWrapper automatonWrapper : automatons) {
			automatonWrapper.start();
		}
	}
	
}
