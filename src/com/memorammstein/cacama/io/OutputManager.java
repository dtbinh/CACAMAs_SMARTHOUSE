package com.memorammstein.cacama.io;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import com.memorammstein.cacama.automata.AutomatonWrapper;
import com.memorammstein.cacama.config.Configuration;
import com.memorammstein.cacama.smarthouse.iBuilding;
import com.memorammstein.cacama.smarthouse.iRoom;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class OutputManager {

	private static String stringToOutput = null;
	
	private static void initAnsi() {
		if (Boolean.parseBoolean(Configuration.getInstance().getProperty("PrintColor")))
			org.fusesource.jansi.AnsiConsole.systemInstall();
	}
	
	private static void endAnsi() {
		System.out.print("\n");
		FileManager.getInstance().saveStringlnToFile(WHITE, BLACK, "");
		if (Boolean.parseBoolean(Configuration.getInstance().getProperty("PrintColor")))
			org.fusesource.jansi.AnsiConsole.systemUninstall();
	}
	
	public static synchronized void printColor(Color background, Color foreground, String text) {
		if (Boolean.parseBoolean(Configuration.getInstance().getProperty("PrintColor"))) {
			System.out.println(ansi().bg(background).fg(foreground).a(text).reset());
		} else {
			System.out.println(text);
		}
		FileManager.getInstance().saveStringlnToFile(background, foreground ,text);
	}
	
	public static synchronized void printAutomatonTotal
	(
			Class<?> automaton, 
			Map<String, String> states, 
			Map<String, String> alphabet, 
			String initialState, 
			ArrayList<String> finalStates, 
			String currentState
	) {
		initAnsi();
		stringToOutput = "printAutomatonTotal(Class<?> automaton, Map<String, String> states, Map<String, String> alphabet, String initialState, ArrayList<String> finalStates, String currentState)";
		printColor(CYAN, MAGENTA, stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		printColor(YELLOW, BLACK, stringToOutput);
		stringToOutput = "Automaton class name: " + automaton.getName();
		printColor(WHITE, BLACK, stringToOutput);
		stringToOutput = "States:";
		printColor(GREEN, WHITE, stringToOutput);
		java.util.Iterator<Entry<String, String>> it = states.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
	        stringToOutput = pairs.getKey() + " = " + pairs.getValue();
	        printColor(GREEN, WHITE, stringToOutput);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    stringToOutput = "Alphabet:";
	    printColor(GREEN, WHITE, stringToOutput);
	    it = alphabet.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
	        stringToOutput = pairs.getKey() + " = " + pairs.getValue();
	        printColor(GREEN, WHITE, stringToOutput);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    stringToOutput = "Initial state: " + initialState;
	    printColor(GREEN, WHITE, stringToOutput);
		for (int i = 0; i < finalStates.size(); i++) {
			stringToOutput = "Final state #" + i + ": " + finalStates.get(i);
			printColor(GREEN, WHITE, stringToOutput);
		}
		stringToOutput = "Current state: " + currentState;
		printColor(GREEN, WHITE, stringToOutput);
		endAnsi();
	}
	
	public static synchronized void printAutomatonWrapperCurrent(AutomatonWrapper automaton, String currentState) {
		initAnsi();
		stringToOutput = "printAutomatonCurrent(AutomatonWrapper automaton, String currentState)";
		printColor(CYAN, MAGENTA, stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		printColor(YELLOW, BLACK, stringToOutput);
		stringToOutput = "Automaton name: " + automaton.getAutomatonName();
		printColor(WHITE, BLACK, stringToOutput);
		stringToOutput = "Current state: " + currentState;
		printColor(GREEN, WHITE, stringToOutput);
		endAnsi();
	}
	
	public static synchronized void printBuilding(iBuilding building) {
		initAnsi();
		stringToOutput = "printBuilding(Building building)";
		printColor(CYAN, MAGENTA, stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		printColor(YELLOW, BLACK, stringToOutput);
		stringToOutput = "Name: " + building.getBuildingName();
		printColor(WHITE, BLACK, stringToOutput);
		for (int i = 0; i < building.getRooms().size(); i++) {
			stringToOutput = "Room #" + i + ": " + building.getRooms().get(i).getRoomName();
			printColor(GREEN, WHITE, stringToOutput);
		}
		endAnsi();
	}
	
	public static synchronized void printRoom(iRoom room) {
		initAnsi();
		stringToOutput = "printRoom(Room room)";
		printColor(CYAN, MAGENTA, stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		printColor(YELLOW, BLACK, stringToOutput);
		stringToOutput = "Name: " + room.getRoomName();
		printColor(WHITE, BLACK, stringToOutput);
		for (int i = 0; i < room.getAutomatonWrappers().size(); i++) {
			stringToOutput = "Automaton wrapper #" + i + ": " + room.getAutomatonWrappers().get(i).getAutomatonName();
			printColor(GREEN, WHITE, stringToOutput);
		}
		endAnsi();
	}
	
	public static synchronized void printComplexManagerAction(String action, String subject) {
		initAnsi();
		stringToOutput = "printComplexManagerAction(String action, String subject)";
		printColor(CYAN, MAGENTA, stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		printColor(YELLOW, BLACK, stringToOutput);
		stringToOutput = "Action: " + action;
		printColor(RED, WHITE, stringToOutput);
		stringToOutput = "Subject: " + subject;
		printColor(RED, WHITE, stringToOutput);
		endAnsi();
	}
	
}
