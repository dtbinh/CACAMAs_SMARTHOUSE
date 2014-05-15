package com.memorammstein.cacama.automata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.memorammstein.cacama.config.Configuration;

public class AccessControl implements AutomatonWrapper {
	
	private AccessControl_Automaton automaton = null;
	private String automatonName = null;
	private boolean enabled = false;
	private String[] password = {
			Configuration.getInstance().getProperty("AccessControl_pass_digit1"),
			Configuration.getInstance().getProperty("AccessControl_pass_digit2"),
			Configuration.getInstance().getProperty("AccessControl_pass_digit3"),
			Configuration.getInstance().getProperty("AccessControl_pass_digit4"),
			Configuration.getInstance().getProperty("AccessControl_pass_digit5"),
			Configuration.getInstance().getProperty("AccessControl_pass_digit6")
	};
	
	public enum keys {
		star,
		enter,
		number0,
		number1,
		number2,
		number3,
		number4,
		number5,
		number6,
		number7,
		number8,
		number9;
	}
	
	public AccessControl(String name) {
		setAutomatonName(name);
		automaton = new AccessControl_Automaton();
		this.enable();
	}
	
	public String getAutomatonName() {
		return this.automatonName;
	}
	
	public void setAutomatonName(String name) {
		this.automatonName = name;
	}
	
	public void enable() {
		this.enabled = true;
	}
	
	public void disable() {
		this.enabled = false;
	}
	
	/**
	 * @param symbol "star", "enter", "number0", "number1", "number2", "number3", "number4", "number5", "number6", "number7", "number8", "number9"
	 */
	public void feed(String wrapperSymbol) {
		AccessControl_Automaton.Alphabet symbol = null;
		keys key = keys.valueOf(wrapperSymbol);
		switch (key) {
		case enter:
			symbol = AccessControl_Automaton.Alphabet.enter;
			break;
		case number0:
			if (Arrays.asList(password).contains("0")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("0");
			}
			break;
		case number1:
			if (Arrays.asList(password).contains("1")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("1");
			}
			break;
		case number2:
			if (Arrays.asList(password).contains("2")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("2");
			}
			break;
		case number3:
			if (Arrays.asList(password).contains("3")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("3");
			}
			break;
		case number4:
			if (Arrays.asList(password).contains("4")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("4");
			}
			break;
		case number5:
			if (Arrays.asList(password).contains("5")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("5");
			}
			break;
		case number6:
			if (Arrays.asList(password).contains("6")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("6");
			}
			break;
		case number7:
			if (Arrays.asList(password).contains("7")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("7");
			}
			break;
		case number8:
			if (Arrays.asList(password).contains("8")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("8");
			}
			break;
		case number9:
			if (Arrays.asList(password).contains("9")) {
				symbol = AccessControl_Automaton.Alphabet.getSymbol("9");
			}
			break;
		case star:
			symbol = AccessControl_Automaton.Alphabet.star;
			break;
		}
		if (enabled) {
			automaton.transitionFunction(symbol);
		}
	}
	
	public boolean isAccepted() {
		return java.util.Arrays.asList(AccessControl_Automaton.getFinalStates()).contains(automaton.getCurrentState());
	}
	
	public void start() {
		Map<String, String> statesMap = new HashMap<String, String>();
		Map<String, String> alphabetMap = new HashMap<String, String>();
		ArrayList<String> finalStatesArrayList = new ArrayList<String>();
		
		for (AccessControl_Automaton.State state : AccessControl_Automaton.State.values()) {
			statesMap.put(state.name(), state.toString());
		}
		
		for (AccessControl_Automaton.Alphabet symbol : AccessControl_Automaton.Alphabet.values()) {
			alphabetMap.put(symbol.name(), symbol.toString());
		}
		
		for (AccessControl_Automaton.State state : AccessControl_Automaton.getFinalStates()) {
			statesMap.put(state.name(), state.toString());
		}
		
		com.memorammstein.cacama.io.OutputManager.printAutomatonTotal(automaton.getClass(), statesMap, alphabetMap, AccessControl_Automaton.getInitialState().toString(), finalStatesArrayList, automaton.getCurrentState().toString());
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			if (enabled) {
				com.memorammstein.cacama.io.OutputManager.printAutomatonWrapperCurrent(this, automaton.getCurrentState().toString());
				try {
					Thread.sleep(Long.parseLong(Configuration.getInstance().getProperty("RefreshTimeInMillis")));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
