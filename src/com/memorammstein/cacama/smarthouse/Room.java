package com.memorammstein.cacama.smarthouse;

import java.util.ArrayList;

import com.memorammstein.cacama.automata.*;

public class Room implements iRoom {
	
	private String roomName = null;
	private ArrayList<AutomatonWrapper> automatons = null;
	
	private AccessControl accessControl = null;
	private Temperature temperature = null;
	private Ilumination ilumination = null;
	private Security security = null;
	private Energy energy = null;
	
	public Room(String roomName) {
		setRoomName(roomName);
		automatons = new ArrayList<AutomatonWrapper>();
		accessControl = new AccessControl(this.getRoomName() + ": " + "Control de acceso");
		temperature = new Temperature(this.getRoomName() + ": " + "Temperatura");
		ilumination = new Ilumination(this.getRoomName() + ": " + "Iluminación");
		security = new Security(this.getRoomName() + ": " + "Seguridad");
		energy = new Energy(this.getRoomName() + ": " + "Energía");
		addAutomaton(accessControl);
		addAutomaton(temperature);
		addAutomaton(ilumination);
		addAutomaton(security);
		addAutomaton(energy);
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

	@Override
	public ArrayList<AutomatonWrapper> getAutomatonWrappers() {
		return automatons;
	}

	@Override
	public void enable() {
		for (AutomatonWrapper aw : getAutomatonWrappers()) {
			aw.enable();
		}
	}

	@Override
	public void disable() {
		for (AutomatonWrapper aw : getAutomatonWrappers()) {
			aw.disable();
		}
	}
	
}
