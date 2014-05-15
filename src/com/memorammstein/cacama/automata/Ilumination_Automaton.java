package com.memorammstein.cacama.automata;

public class Ilumination_Automaton {

	public enum State {
		q0 ("in range"),
		q1 ("too dark"),
		q2 ("too bright");
		
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
		lessThanMinimum ("<min"),
		moreThanMinimum (">min"),
		lessThanMaximum ("<max"),
		moreThanMaximum (">max");
		
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
	
	private static State[] finalStates = {State.q0};
	
	public void transitionFunction(Alphabet symbol) {
		if(symbol != null)
		switch (currentState) {
		case q0:
			switch (symbol) {
			case lessThanMinimum:
				currentState = State.q1;
				break;
			case moreThanMaximum:
				currentState = State.q2;
				break;
			default:
				break;
			}
			break;
		case q1:
			switch (symbol) {
			case moreThanMaximum:
				currentState = State.q2;
				break;
			case moreThanMinimum:
				currentState = State.q0;
				break;
			default:
				break;
			}
			break;
		case q2:
			switch (symbol) {
			case lessThanMaximum:
				currentState = State.q0;
				break;
			case lessThanMinimum:
				currentState = State.q1;
				break;
			default:
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