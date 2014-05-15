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
		Building building2 = new Building("Casa de Juanpis");
		Room bathroom2 = new Room(building2.getName() + ": " + "Baño");
		Room bedroom2 = new Room(building2.getName() + ": " + "Cuarto");
		Room lobby2 = new Room(building2.getName() + ": " + "Lobby");
		building2.addRoom(bathroom2);
		building2.addRoom(bedroom2);
		building2.addRoom(lobby2);
		building2.start();
	}
}
