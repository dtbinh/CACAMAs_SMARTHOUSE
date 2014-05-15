package com.memorammstein.cacama.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.memorammstein.cacama.config.Configuration;

public class Ilumination implements AutomatonWrapper {
	
	private Temperature_Automaton automaton = null;
	private String automatonName = null;
	private boolean enabled = false;
	private Map<String, Integer> range = null;
	
	public Ilumination(String name) {
		setAutomatonName(name);
		range = new HashMap<String, Integer>();
		range.put("minimum", Integer.parseInt(Configuration.getInstance().getProperty("ilumination_minimum")));
		range.put("maximum", Integer.parseInt(Configuration.getInstance().getProperty("ilumination_maximum")));
		automaton = new Temperature_Automaton();
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
	 * @param symbol integer as a string, percentage representation
	 */
	public void feed(String wrapperSymbol) {
		Temperature_Automaton.Alphabet symbol = null;
		Double temp = Double.parseDouble(wrapperSymbol);
		switch (automaton.getCurrentState()) {
		case q0:
			if (temp > range.get("maximum")) {
				symbol = Temperature_Automaton.Alphabet.getSymbol(">max");
			} else if (temp < range.get("minimum")) {
				symbol = Temperature_Automaton.Alphabet.getSymbol("<min");
			}
			break;
		case q1:
			if (temp > range.get("maximum")) {
				symbol = Temperature_Automaton.Alphabet.getSymbol(">max");
			} else if (temp > range.get("minimum")) {
				symbol = Temperature_Automaton.Alphabet.getSymbol(">min");
			}
			break;
		case q2:
			if (temp < range.get("maximum")) {
				symbol = Temperature_Automaton.Alphabet.getSymbol("<max");
			} else if (temp < range.get("minimum")) {
				symbol = Temperature_Automaton.Alphabet.getSymbol("<min");
			}
			break;
		}
		if (enabled) {
			automaton.transitionFunction(symbol);
		}
	}
	
	public boolean isAccepted() {
		return java.util.Arrays.asList(Temperature_Automaton.getFinalStates()).contains(automaton.getCurrentState());
	}
	
	public void start() {
		Map<String, String> statesMap = new HashMap<String, String>();
		Map<String, String> alphabetMap = new HashMap<String, String>();
		ArrayList<String> finalStatesArrayList = new ArrayList<String>();
		
		for (Temperature_Automaton.State state : Temperature_Automaton.State.values()) {
			statesMap.put(state.name(), state.toString());
		}
		
		for (Temperature_Automaton.Alphabet symbol : Temperature_Automaton.Alphabet.values()) {
			alphabetMap.put(symbol.name(), symbol.toString());
		}
		
		for (Temperature_Automaton.State state : Temperature_Automaton.getFinalStates()) {
			statesMap.put(state.name(), state.toString());
		}
		
		com.memorammstein.cacama.io.OutputManager.printAutomatonTotal(automaton.getClass(), statesMap, alphabetMap, Temperature_Automaton.getInitialState().toString(), finalStatesArrayList, automaton.getCurrentState().toString());
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