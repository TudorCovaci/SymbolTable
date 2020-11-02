package scanner;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import language_spec.LanguageSpecification;

public class ProgramScanner {

    public static boolean isPartOfOperator(String character) {
        return LanguageSpecification.operators.stream()
                                              .anyMatch(op -> op.contains(character));
    }

    public static boolean isEscapedQuote(String line,
                                         int index) {
        return index != 0 && line.charAt(index - 1) == '\\';
    }

    public static Map.Entry<String, Integer> getStringToken(String line,
                                                            int index) {
        StringBuilder token = new StringBuilder();
        int quoteCount = 0;
        while (index < line.length() && quoteCount < 2) {
            if (line.charAt(index) == '"' && !isEscapedQuote(line, index)) {
                quoteCount++;
            }
            token.append(line.charAt(index));
            index++;
        }
        return new AbstractMap.SimpleEntry<>(token.toString(), index);
    }

    public static Map.Entry<String, Integer> getOperatorToken(String line,
                                                              int index) {
        StringBuilder token = new StringBuilder();
        while (index < line.length() && isPartOfOperator(line.substring(index, index + 1))) {
            token.append(line.charAt(index));
            index++;
        }
        return new AbstractMap.SimpleEntry<>(token.toString(), index);
    }

    public static List<String> tokenGenerator(String line) {
        StringBuilder token = new StringBuilder();
        List<String> tokens = new ArrayList<>();
        int index = 0;
        while (index < line.length()) {
            if (line.charAt(index) == '"') {
                if (token.length() != 0) {
                    tokens.add(token.toString());
                }
                Map.Entry<String, Integer> tokenInteger = getStringToken(line, index);
                tokens.add(tokenInteger.getKey());
                index = tokenInteger.getValue();
                token = new StringBuilder();
            } else if (isPartOfOperator(line.substring(index, index + 1))) {
                if (token.length() != 0) {
                    tokens.add(token.toString());
                }
                Map.Entry<String,Integer> tokenIndex = getOperatorToken(line, index);
                tokens.add(tokenIndex.getKey());
                index = tokenIndex.getValue();
                token = new StringBuilder();
            } else if (LanguageSpecification.separators.contains(line.substring(index, index + 1))) {
                if (token.length() != 0) {
                    tokens.add(token.toString());
                }
                tokens.add(line.substring(index, index + 1));
                index += 1;
                token = new StringBuilder();
            } else {
                token.append(line.charAt(index));
                index++;
            }
        }
        if (token.length() != 0) {
            tokens.add(token.toString());
        }
        return tokens;
    }

    public static boolean isIdentifier(String token) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9]*");
        return pattern.matcher(token)
                      .matches();
    }

    public static boolean isConstant(String token) {
        Pattern pattern = Pattern.compile("(0|[+-]?[1-9][0-9]*)|'.'|\".*\"");
        return pattern.matcher(token)
                      .matches();
    }
}
