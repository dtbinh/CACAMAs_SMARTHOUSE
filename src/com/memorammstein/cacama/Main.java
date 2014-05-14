package com.memorammstein.cacama;

import com.memorammstein.cacama.smarthouse.Building;
import com.memorammstein.cacama.smarthouse.Room;

public class Main {
	public static void main(String[] args) {
		Building building = new Building("Casa de Memo");
		Room bathroom = new Room("Baño");
		Room bedroom = new Room("Cuarto");
		Room lobby = new Room("Lobby");
		building.addRoom(bathroom);
		building.addRoom(bedroom);
		building.addRoom(lobby);
		building.start();
	}
}
