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

    @Override
    public boolean equals(Object other){
        if(!(other instanceof State)){
            return false;
        }

        State otherState = (State)other;
        return otherState.identifier.equals(identifier);
    }
}
