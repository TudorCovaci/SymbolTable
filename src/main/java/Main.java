import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

import finite_automata.FiniteAutomata;
import finite_automata.Transition;
import language_spec.LanguageSpecification;
import pif.Pif;
import scanner.ProgramScanner;
import symbol_table.SymbolTable;

public class Main {

    public static void main(String[] args) throws IOException {
        runCli();
    }

    private static FiniteAutomata constructFiniteAutomataFromFile() throws FileNotFoundException {
        BufferedReader fileScanner = new BufferedReader(new FileReader("src/main/resources/fa.in"));
        Gson gson = new Gson();
        String jsonString = fileScanner.lines()
                                       .collect(Collectors.joining());
        return gson.fromJson(jsonString, FiniteAutomata.class);
    }

    private static void runCli() throws FileNotFoundException {
        int choice;
        FiniteAutomata finiteAutomata = constructFiniteAutomataFromFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> System.out.println("States: " + finiteAutomata.getStates()
                                                                    .toString());
                case 2 -> System.out.println("Alphabet: " + finiteAutomata.getAlphabet()
                                                                      .toString());
                case 3 -> {
                    System.out.println("Transitions: ");
                    finiteAutomata.getTransitions()
                                  .stream()
                                  .map(Transition::toString)
                                  .forEach(System.out::println);
                }
                case 4 -> System.out.println("Final states: " + finiteAutomata.getFinalStates()
                                                                          .toString());
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void printMenu() {
        String stringBuilder = "Menu\n"
                + "1. Display set of states\n"
                + "2. Display alphabet\n"
                + "3. Display transitions\n"
                + "4. Display final states\n"
                + "0. Quit\n"
                + "Input choice: ";
        System.out.println(stringBuilder);
    }

    private void readProgram() throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        Pif pif = new Pif();
        Scanner scanner = new Scanner(System.in);
        //        System.out.println("Input file name: ");
        //        String fileName = scanner.nextLine();
        int lineNumber = 0;
        String line;
        Scanner fileScanner = new Scanner(new BufferedInputStream(new FileInputStream("src/main/resources/P3")));
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            lineNumber++;
            for (String token : ProgramScanner.tokenGenerator(line)) {
                if (LanguageSpecification.separators.contains(token) || LanguageSpecification.operators.contains(token) ||
                        LanguageSpecification.reservedWords.contains(token)) {
                    pif.add(token, -1);
                } else if (ProgramScanner.isIdentifier(token)) {
                    pif.add(token, symbolTable.add(token));
                } else if (ProgramScanner.isConstant(token)) {
                    pif.add(token, symbolTable.add(token));
                } else {
                    throw new RuntimeException("Lexical error on line " + lineNumber + " (token:" + token + ")");
                }
            }
        }
        System.out.println("Lexical correct");
        pif.writeToFile();
        symbolTable.writeToFile();
    }
}
