package language_spec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageSpecification {

    public static final List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "=", "<", "<=", ">", ">=",
                                                                               "+=", "-=", "!", "~", "++", "--", "$"));
    public static final List<String> separators = new ArrayList<>(Arrays.asList("(", ")", "{", "}", ":", ";", " ", "#", "'", "\"", "[" , "]", "#"));
    public static final List<String> reservedWords = new ArrayList<>(
            Arrays.asList("VIN", "rulaj", "piston", "daca", "este", "catTimp", "contact", "serie", "nu", "length", "arata", "citeste"));

    static {
        reservedWords.addAll(operators);
        reservedWords.addAll(separators);
    }
}
