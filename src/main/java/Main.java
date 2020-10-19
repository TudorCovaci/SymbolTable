public class Main {

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();
        System.out.println("Added: " + symbolTable.add("tudor"));
        System.out.println("Added: " +symbolTable.add("tudro"));
        System.out.println("Added: " +symbolTable.add("covaci"));
        System.out.println("Searched: " + symbolTable.search("tudor"));
        System.out.println("Get by index: " + symbolTable.getByIndex(14));
    }
}
