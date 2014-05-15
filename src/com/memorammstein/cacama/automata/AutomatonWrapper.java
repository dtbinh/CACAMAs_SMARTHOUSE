package com.memorammstein.cacama.automata;

public interface AutomatonWrapper extends Runnable {
	public boolean isAccepted();
	public String getAutomatonName();
	public void setAutomatonName(String name);
	public void enable();
	public void disable();
	public void feed(String wrapperSymbol);
	public void start();
}
