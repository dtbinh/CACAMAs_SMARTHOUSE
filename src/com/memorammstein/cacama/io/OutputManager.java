package com.memorammstein.cacama.io;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import com.memorammstein.cacama.smarthouse.Building;
import com.memorammstein.cacama.smarthouse.Room;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class OutputManager {
	
	private static void initAnsi() {
		org.fusesource.jansi.AnsiConsole.systemInstall();
	}
	
	private static void endAnsi() {
		org.fusesource.jansi.AnsiConsole.systemUninstall();
	}
	
	public static void printAutomatonTotal
	(
			Class<?> automaton, 
			Map<String, String> states, 
			Map<String, String> alphabet, 
			String initialState, 
			ArrayList<String> finalStates, 
			String currentState
	) {
		initAnsi();
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a("printRoom(Room room)").reset());
		endAnsi();
	}
	
	public static void printAutomatonCurrent(Class<?> automaton, String currentState) {
		initAnsi();
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a("printAutomatonCurrent(Class<?> automaton, String currentState)").reset());
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(Calendar.getInstance().getTime().toString()).reset());
		endAnsi();
	}
	
	public static void printBuilding(Building building) {
		initAnsi();
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a("printBuilding(Building building)").reset());
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(Calendar.getInstance().getTime().toString()).reset());
		System.out.println(ansi().bg(WHITE).fg(BLACK).a("Name: " + building.getName()).reset());
		for (int i = 0; i < building.getRooms().size(); i++) {
			System.out.println(ansi().bg(BLACK).fg(WHITE).a("Room #" + i + ": " + building.getRooms().get(i).getName()).reset());
		}
		endAnsi();
	}
	
	public static void printRoom(Room room) {
		initAnsi();
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a("printRoom(Room room)").reset());
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(Calendar.getInstance().getTime().toString()).reset());
		System.out.println(ansi().bg(WHITE).fg(BLACK).a("Name: " + room.getName()).reset());
		endAnsi();
	}
	
}
