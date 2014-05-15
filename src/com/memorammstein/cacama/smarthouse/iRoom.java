package com.memorammstein.cacama.smarthouse;

import java.util.ArrayList;

import com.memorammstein.cacama.automata.AutomatonWrapper;

public interface iRoom extends Runnable {
	public ArrayList<AutomatonWrapper> getAutomatonWrappers();
	public String getRoomName();
	public void addAutomaton(AutomatonWrapper aw);
	public void enable();
	public void disable();
	public void start();
}
