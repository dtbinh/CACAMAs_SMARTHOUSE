package com.memorammstein.cacama.automata;

import com.memorammstein.cacama.config.Configuration;

public class AccessControl_Automaton {
	
	public enum State {
		q0 ("idle"),
		q1 ("transition"),
		q2 ("transition"),
		q3 ("transition"),
		q4 ("transition"),
		q5 ("transition"),
		q6 ("transition"),
		q7 ("transition"),
		q8 ("access granted"),
		q9 ("access not granted");
		
		private String state;
		
		private State(String state) {
			this.state = state;
		}
		
		public String toString() {
			return this.state;
		}
	}
	
	public enum Alphabet {
		star ("*"),
		digit1 (Configuration.getInstance().getProperty("AccessControl_pass_digit1")),
		digit2 (Configuration.getInstance().getProperty("AccessControl_pass_digit2")),
		digit3 (Configuration.getInstance().getProperty("AccessControl_pass_digit3")),
		digit4 (Configuration.getInstance().getProperty("AccessControl_pass_digit4")),
		digit5 (Configuration.getInstance().getProperty("AccessControl_pass_digit5")),
		digit6 (Configuration.getInstance().getProperty("AccessControl_pass_digit6")),
		enter ("enter");
		
		private String symbol;
		
		private Alphabet(String symbol) {
			this.symbol = symbol;
		}
		
		public String toString() {
			return this.symbol;
		}
	}
	
	private State initialState = State.q0;
	
	private State currentState = initialState;
	
	private State[] finalStates = {State.q8};
	
	public void transitionFunction(Alphabet symbol) {
		switch (symbol) {
			
		}
	}
	
}
