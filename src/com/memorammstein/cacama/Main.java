package com.memorammstein.cacama;

import java.util.ArrayList;

import com.memorammstein.cacama.io.ComplexManager_VisualEdition;
import com.memorammstein.cacama.smarthouse.Building;
import com.memorammstein.cacama.smarthouse.Room;
import com.memorammstein.cacama.smarthouse.iBuilding;

public class Main {
	public static void main(String[] args) {
		ArrayList<iBuilding> buildings = new ArrayList<iBuilding>();
		
		Building building = new Building("Casa de Memo");
		Room bathroom = new Room(building.getBuildingName() + ": " + "Baño");
		Room bedroom = new Room(building.getBuildingName() + ": " + "Cuarto");
		Room lobby = new Room(building.getBuildingName() + ": " + "Lobby");
		building.addRoom(bathroom);
		building.addRoom(bedroom);
		building.addRoom(lobby);
		building.start();
		Building building2 = new Building("Casa de Juanpis");
		Room bathroom2 = new Room(building2.getBuildingName() + ": " + "Baño");
		Room bedroom2 = new Room(building2.getBuildingName() + ": " + "Cuarto");
		Room lobby2 = new Room(building2.getBuildingName() + ": " + "Lobby");
		building2.addRoom(bathroom2);
		building2.addRoom(bedroom2);
		building2.addRoom(lobby2);
		buildings.add(building);
		buildings.add(building2);
		for (iBuilding buildingIteration : buildings) {
			buildingIteration.start();
		}
		ComplexManager_VisualEdition cm = new ComplexManager_VisualEdition(buildings);
		cm.start();
	}
}
