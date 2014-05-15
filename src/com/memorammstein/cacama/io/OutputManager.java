package com.memorammstein.cacama.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import com.memorammstein.cacama.automata.AutomatonWrapper;
import com.memorammstein.cacama.config.Configuration;
import com.memorammstein.cacama.smarthouse.Building;
import com.memorammstein.cacama.smarthouse.Room;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class OutputManager {
	
	private static String logFilename = null;
	private static File file = null;
	private static BufferedWriter bw = null;
	private static String stringToOutput = null;
	
	public static synchronized void saveStringlnToFile(String string) {
		logFilename = Configuration.getInstance().getProperty("logFilename");
		try {
			file = new File(logFilename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(string + "\n");
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void initAnsi() {
		org.fusesource.jansi.AnsiConsole.systemInstall();
	}
	
	private static void endAnsi() {
		System.out.print("\n");
		saveStringlnToFile("\n");
		org.fusesource.jansi.AnsiConsole.systemUninstall();
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
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = "Automaton name: " + automaton.getName();
		System.out.println(ansi().bg(WHITE).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = "States:";
		System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		java.util.Iterator<Entry<String, String>> it = states.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
	        stringToOutput = pairs.getKey() + " = " + pairs.getValue();
	        System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
	        saveStringlnToFile(stringToOutput);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    stringToOutput = "Alphabet:";
	    System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
	    saveStringlnToFile(stringToOutput);
	    it = alphabet.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
	        stringToOutput = pairs.getKey() + " = " + pairs.getValue();
	        System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
	        saveStringlnToFile(stringToOutput);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    stringToOutput = "Initial state: " + initialState;
	    System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		for (int i = 0; i < finalStates.size(); i++) {
			stringToOutput = "Final state #" + i + ": " + finalStates.get(i);
			System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
			saveStringlnToFile(stringToOutput);
		}
		stringToOutput = "Current state: " + currentState;
		System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
		saveStringlnToFile("Current state: " + currentState);
		endAnsi();
	}
	
	public static synchronized void printAutomatonWrapperCurrent(AutomatonWrapper automaton, String currentState) {
		initAnsi();
		stringToOutput = "printAutomatonCurrent(AutomatonWrapper automaton, String currentState)";
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = "Automaton name: " + automaton.getAutomatonName();
		System.out.println(ansi().bg(WHITE).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = "Current state: " + currentState;
		System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		endAnsi();
	}
	
	public static synchronized void printBuilding(Building building) {
		initAnsi();
		stringToOutput = "printBuilding(Building building)";
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = "Name: " + building.getName();
		System.out.println(ansi().bg(WHITE).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		for (int i = 0; i < building.getRooms().size(); i++) {
			stringToOutput = "Room #" + i + ": " + building.getRooms().get(i).getRoomName();
			System.out.println(ansi().bg(GREEN).fg(WHITE).a(stringToOutput).reset());
			saveStringlnToFile(stringToOutput);
		}
		endAnsi();
	}
	
	public static synchronized void printRoom(Room room) {
		initAnsi();
		stringToOutput = "printRoom(Room room)";
		System.out.println(ansi().bg(CYAN).fg(MAGENTA).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = Calendar.getInstance().getTime().toString();
		System.out.println(ansi().bg(YELLOW).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		stringToOutput = "Name: " + room.getRoomName();
		System.out.println(ansi().bg(WHITE).fg(BLACK).a(stringToOutput).reset());
		saveStringlnToFile(stringToOutput);
		endAnsi();
	}
	
}
