package com.memorammstein.cacama.automata;

public class Energy_Automaton {

	public enum State {
		q0 ("energize"),
		q1 ("empty house"),
		q2 ("programmed turn-off");
		
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
		programmedTurnoffEffective ("programmed turn-off-> effective"),
		programmedTurnoffIneffective ("programmed turn-off-> in effective"),
		motionOff ("motion-> off"),
		motionOn ("motion-> on");
		
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
	
	private static State[] finalStates = {State.q2};
	
	public void transitionFunction(Alphabet symbol) {
		switch (getCurrentState()) {
		case q0:
			switch (symbol) {
			case motionOff:
				currentState = Energy_Automaton.State.q1;
				break;
			case motionOn:
				currentState = Energy_Automaton.State.q0;
				break;
			case programmedTurnoffEffective:
				currentState = Energy_Automaton.State.q2;
				break;
			case programmedTurnoffIneffective:
				currentState = Energy_Automaton.State.q0;
				break;
			}
			break;
		case q1:
			switch (symbol) {
			case motionOff:
				currentState = Energy_Automaton.State.q1;
				break;
			case motionOn:
				currentState = Energy_Automaton.State.q0;
				break;
			case programmedTurnoffEffective:
				currentState = Energy_Automaton.State.q2;
				break;
			case programmedTurnoffIneffective:
				currentState = Energy_Automaton.State.q1;
				break;
			}
			break;
		case q2:
			switch (symbol) {
			case motionOff:
				currentState = Energy_Automaton.State.q2;
				break;
			case motionOn:
				currentState = Energy_Automaton.State.q2;
				break;
			case programmedTurnoffEffective:
				currentState = Energy_Automaton.State.q2;
				break;
			case programmedTurnoffIneffective:
				currentState = Energy_Automaton.State.q0;
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