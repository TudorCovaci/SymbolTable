import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import language_spec.LanguageSpecification;
import pif.Pif;
import scanner.ProgramScanner;
import symbol_table.SymbolTable;

public class Main {

    public static void main(String[] args) throws IOException {
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
                    throw new RuntimeException("Syntax error on line " + lineNumber + " (token:" + token + ")");
                }
            }
        }
        System.out.println("Syntax is correct");
        pif.writeToFile();
        symbolTable.writeToFile();
    }
}
