package com.memorammstein.cacama.automata;

public interface AutomatonWrapper extends Runnable {
	public boolean isAccepted();
	public String getName();
	public void enable();
	public void disable();
	public void feed(String wrapperSymbol);
}
