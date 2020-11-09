package finite_automata;

public class Transition {

    private State state;
    private String letter;
    private State result;

    public Transition() {
    }

    public Transition(State state,
                      String letter,
                      State result) {
        this.state = state;
        this.letter = letter;
        this.result = result;
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

    public State getResult() {
        return result;
    }

    public void setResult(State result) {
        this.result = result;
    }

    @Override
    public String toString(){
        return "d(" + state.toString() + "," + letter + ")=" + result.toString();
    }
}
