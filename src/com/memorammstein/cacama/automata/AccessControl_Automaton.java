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
		
		private String stateString;
		
		private State(String state) {
			this.stateString = state;
		}
		
		@Override
		public String toString() {
			return this.stateString;
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
		
		@Override
		public String toString() {
			return this.symbol;
		}

		public static Alphabet getSymbol(String symbolValue) {
			Alphabet temp = null;
			for (Alphabet symbol : Alphabet.values()) {
				if (symbol.toString().equals(symbolValue)) {
					temp = symbol;
				}
			}
			return temp;
		}
	}
	
	private static State initialState = State.q0;
	
	private State currentState = initialState;
	
	private static State[] finalStates = {State.q8};
	
	public void transitionFunction(Alphabet symbol) {
		switch (getCurrentState()) {
		case q0:
			switch (symbol) {
			case digit1:
				currentState = State.q0;
				break;
			case digit2:
				currentState = State.q0;
				break;
			case digit3:
				currentState = State.q0;
				break;
			case digit4:
				currentState = State.q0;
				break;
			case digit5:
				currentState = State.q0;
				break;
			case digit6:
				currentState = State.q0;
				break;
			case enter:
				currentState = State.q0;
				break;
			case star:
				currentState = State.q1;
				break;
			}
			break;
		case q1:
			switch (symbol) {
			case digit1:
				currentState = State.q2;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q2:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q3;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q3:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q4;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q4:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q5;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q5:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q6;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q6:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q7;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q7:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q8;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q8:
			switch (symbol) {
			case digit1:
				currentState = State.q9;
				break;
			case digit2:
				currentState = State.q9;
				break;
			case digit3:
				currentState = State.q9;
				break;
			case digit4:
				currentState = State.q9;
				break;
			case digit5:
				currentState = State.q9;
				break;
			case digit6:
				currentState = State.q9;
				break;
			case enter:
				currentState = State.q9;
				break;
			case star:
				currentState = State.q9;
				break;
			}
			break;
		case q9:
			switch (symbol) {
			case digit1:
				currentState = State.q0;
				break;
			case digit2:
				currentState = State.q0;
				break;
			case digit3:
				currentState = State.q0;
				break;
			case digit4:
				currentState = State.q0;
				break;
			case digit5:
				currentState = State.q0;
				break;
			case digit6:
				currentState = State.q0;
				break;
			case enter:
				currentState = State.q0;
				break;
			case star:
				currentState = State.q0;
				break;
			}
			break;
		}
	}
	
	public static State getInitialState() {
		return initialState;
	}

	public State getCurrentState() {
		return currentState;
	}

	public static State[] getFinalStates() {
		return finalStates;
	}
	
}
