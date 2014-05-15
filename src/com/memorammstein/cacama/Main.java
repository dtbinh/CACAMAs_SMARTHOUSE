package com.memorammstein.cacama;

import com.memorammstein.cacama.smarthouse.Building;
import com.memorammstein.cacama.smarthouse.Room;

public class Main {
	public static void main(String[] args) {
		Building building = new Building("Casa de Memo");
		Room bathroom = new Room(building.getName() + ": " + "Baño");
		Room bedroom = new Room(building.getName() + ": " + "Cuarto");
		Room lobby = new Room(building.getName() + ": " + "Lobby");
		building.addRoom(bathroom);
		building.addRoom(bedroom);
		building.addRoom(lobby);
		building.start();
	}
}
