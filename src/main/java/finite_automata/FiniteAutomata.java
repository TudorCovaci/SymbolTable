package finite_automata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FiniteAutomata {


    private List<State> states;
    private List<String> alphabet;
    @SerializedName("initial_state")
    private State initialState;
    private List<Transition> transitions;
    @SerializedName("final_states")
    private List<State> finalStates;

    public FiniteAutomata() {
    }

    public FiniteAutomata(List<State> states,
                          List<String> alphabet,
                          State initialState,
                          List<Transition> transitions,
                          List<State> finalStates)
    {
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.transitions = transitions;
        this.finalStates = finalStates;
    }


    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(List<State> finalStates) {
        this.finalStates = finalStates;
    }

    public boolean isDeterministic(){
        return transitions.stream()
                          .filter(t -> t.getResultStates()
                                        .size() > 1)
                          .count() <= 0;
    }

    public boolean checkSequence(String seq){
        State state = this.initialState;
        String[] sequenceChars = seq.split("");
        for(String character: sequenceChars){
            State nextState = getNextState(state, character);
            if(nextState == null){
                return false;
            }
            state = nextState;
        }
        return this.finalStates.contains(state);
    }

    public State getNextState(State startingState, String value){
        for(Transition t: transitions){
            if(t.getState().equals(startingState) && t.getLetter().equals(value) && t.getResultStates().size() == 1){
                return t.getResultStates().get(0);
            }
        }
        return null;
    }
}
