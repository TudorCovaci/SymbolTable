package finite_automata;

public class State {

    private String identifier;

    public State() {
    }

    public State(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString(){
        return identifier;
    }
}
