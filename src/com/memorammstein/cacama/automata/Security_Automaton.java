package com.memorammstein.cacama.automata;

public class Security_Automaton {

	public enum State {
		q0 ("empty house"),
		q1 ("warning: danger"),
		q2 ("alarm: intruder");
		
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
		alarmOff ("alarm-> off"),
		alarmOn ("alarm-> on"),
		motionOff ("motion-> off"),
		motionOn ("motion-> on"),
		accessOff ("access-> off"),
		accessOn ("access-> on");
		
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
			case accessOff:
				currentState = State.q0;
				break;
			case accessOn:
				currentState = State.q1;
				break;
			case alarmOff:
				currentState = State.q0;
				break;
			case alarmOn:
				currentState = State.q0;
				break;
			case motionOff:
				currentState = State.q0;
				break;
			case motionOn:
				currentState = State.q1;
				break;
			}
			break;
		case q1:
			switch (symbol) {
			case accessOff:
				currentState = State.q1;
				break;
			case accessOn:
				currentState = State.q1;
				break;
			case alarmOff:
				currentState = State.q0;
				break;
			case alarmOn:
				currentState = State.q2;
				break;
			case motionOff:
				currentState = State.q1;
				break;
			case motionOn:
				currentState = State.q1;
				break;
			}
			break;
		case q2:
			switch (symbol) {
			case accessOff:
				currentState = State.q2;
				break;
			case accessOn:
				currentState = State.q2;
				break;
			case alarmOff:
				currentState = State.q0;
				break;
			case alarmOn:
				currentState = State.q2;
				break;
			case motionOff:
				currentState = State.q2;
				break;
			case motionOn:
				currentState = State.q2	;
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