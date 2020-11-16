package finite_automata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transition {

    private State state;
    private String letter;
    @SerializedName("results")
    private List<State> resultStates;

    public Transition() {
    }

    public Transition(State state,
                      String letter,
                      List<State> resultStates) {
        this.state = state;
        this.letter = letter;
        this.resultStates = resultStates;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<State> getResultStates() {
        return resultStates;
    }

    public void setResultStates(List<State> resultStates) {
        this.resultStates = resultStates;
    }

    @Override
    public String toString(){
        return "d(" + state.toString() + "," + letter + ")=" + resultStates.toString();
    }
}
