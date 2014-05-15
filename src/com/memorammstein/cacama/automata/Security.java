package com.memorammstein.cacama.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.memorammstein.cacama.config.Configuration;

public class Security implements AutomatonWrapper {
	
	private Security_Automaton automaton = null;
	private String automatonName = null;
	private boolean enabled = false;
	private Map<String, Boolean> params = null;
	
	public Security(String name) {
		setAutomatonName(name);
		params = new HashMap<String, Boolean>();
		params.put("alarm", false);
		params.put("motion", false);
		params.put("access", false);
		automaton = new Security_Automaton();
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
	 * @param symbol "alarm: off", "alarm: on", "motion: off", "motion: on", "access: off", "access: on"
	 */
	public void feed(String wrapperSymbol) {
		Security_Automaton.Alphabet symbol = null;
		symbol = Security_Automaton.Alphabet.getSymbol(wrapperSymbol);
		if (enabled) {
			automaton.transitionFunction(symbol);
		}
	}
	
	public boolean isAccepted() {
		return java.util.Arrays.asList(Security_Automaton.getFinalStates()).contains(automaton.getCurrentState());
	}
	
	public void start() {
		Map<String, String> statesMap = new HashMap<String, String>();
		Map<String, String> alphabetMap = new HashMap<String, String>();
		ArrayList<String> finalStatesArrayList = new ArrayList<String>();
		
		for (Security_Automaton.State state : Security_Automaton.State.values()) {
			statesMap.put(state.name(), state.toString());
		}
		
		for (Security_Automaton.Alphabet symbol : Security_Automaton.Alphabet.values()) {
			alphabetMap.put(symbol.name(), symbol.toString());
		}
		
		for (Security_Automaton.State state : Security_Automaton.getFinalStates()) {
			statesMap.put(state.name(), state.toString());
		}
		
		com.memorammstein.cacama.io.OutputManager.printAutomatonTotal(automaton.getClass(), statesMap, alphabetMap, Security_Automaton.getInitialState().toString(), finalStatesArrayList, automaton.getCurrentState().toString());
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			if (enabled) {
				com.memorammstein.cacama.io.OutputManager.printAutomatonWrapperCurrent(this, automaton.getCurrentState().toString());
			}
			try {
				Thread.sleep(Long.parseLong(Configuration.getInstance().getProperty("RefreshTimeInMillis")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}