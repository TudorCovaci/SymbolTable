package language_spec;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanguageSpecification {

    public static final List<String> operators = new ArrayList<>();
    public static final List<String> separators = new ArrayList<>();
    public static final List<String> reservedWords = new ArrayList<>();
    public static final List<String> allTokens = new ArrayList<>();

    static {
        try {
            readTokens();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        allTokens.addAll(separators);
        allTokens.addAll(operators);
        allTokens.addAll(reservedWords);
    }

    private static void readTokens() throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream("src/main/resources/Tokens.in")));
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("OPERATORS")) {
                break;
            }
            String[] tokenIdPair = line.split(" ");
            reservedWords.add(tokenIdPair[0]);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("SEPARATORS")) {
                break;
            }
            String[] tokenIdPair = line.split(" ");
            operators.add(tokenIdPair[0]);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokenIdPair = line.split(" ");
            if (tokenIdPair[0].equals("space")) {
                separators.add(" ");
            } else {
                separators.add(tokenIdPair[0]);
            }
        }
    }
}
