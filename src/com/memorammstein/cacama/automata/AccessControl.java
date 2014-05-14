package com.memorammstein.cacama.automata;

public class AccessControl {
	
	private AccessControl_Automaton automaton = null;
	private String name = null;
	private boolean enabled = false;
	
	public AccessControl(String name) {
		this.name = name;
		automaton = new AccessControl_Automaton();
		enable();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void enable() {
		this.enabled = true;
	}
	
	public void disable() {
		this.enabled = false;
	}
	
}
