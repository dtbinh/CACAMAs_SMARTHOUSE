package com.memorammstein.cacama.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.memorammstein.cacama.config.Configuration;

public class Energy implements AutomatonWrapper {
	
	private Energy_Automaton automaton = null;
	private String automatonName = null;
	private boolean enabled = false;
	private Map<String, Boolean> params = null;
	
	public Energy(String name) {
		setAutomatonName(name);
		params = new HashMap<String, Boolean>();
		params.put("ptoff", false);
		params.put("motion", false);
		automaton = new Energy_Automaton();
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
	 * @param symbol "programmed turn-off-> in effective", "programmed turn-off-> effective", "motion-> off", "motion-> on"
	 */
	public void feed(String wrapperSymbol) {
		Energy_Automaton.Alphabet symbol = null;
		symbol = Energy_Automaton.Alphabet.getSymbol(wrapperSymbol);
		if (enabled) {
			automaton.transitionFunction(symbol);
		}
	}
	
	public boolean isAccepted() {
		return java.util.Arrays.asList(Energy_Automaton.getFinalStates()).contains(automaton.getCurrentState());
	}
	
	public void start() {
		Map<String, String> statesMap = new HashMap<String, String>();
		Map<String, String> alphabetMap = new HashMap<String, String>();
		ArrayList<String> finalStatesArrayList = new ArrayList<String>();
		
		for (Energy_Automaton.State state : Energy_Automaton.State.values()) {
			statesMap.put(state.name(), state.toString());
		}
		
		for (Energy_Automaton.Alphabet symbol : Energy_Automaton.Alphabet.values()) {
			alphabetMap.put(symbol.name(), symbol.toString());
		}
		
		for (Energy_Automaton.State state : Energy_Automaton.getFinalStates()) {
			statesMap.put(state.name(), state.toString());
		}
		
		com.memorammstein.cacama.io.OutputManager.printAutomatonTotal(automaton.getClass(), statesMap, alphabetMap, Energy_Automaton.getInitialState().toString(), finalStatesArrayList, automaton.getCurrentState().toString());
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
