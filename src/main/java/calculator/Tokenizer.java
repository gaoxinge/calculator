package calculator;

import calculator.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private static final List<Pair<String, String>> tokenList = generateTokenList();
    private static List<Pair<String, String>> generateTokenList() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("WHITE_SPACE", "\\s+"));
        list.add(new Pair<>("NUMBER", "\\d+\\.\\d+|\\d+\\.|\\.\\d+|\\d+"));
        list.add(new Pair<>("PI", "PI"));
        list.add(new Pair<>("E", "E"));
        list.add(new Pair<>("LEFT_PARENTHESES", "\\("));
        list.add(new Pair<>("RIGHT_PARENTHESES", "\\)"));
        list.add(new Pair<>("COMMA", ","));
        list.add(new Pair<>("ADDITION", "\\+"));
        list.add(new Pair<>("SUBSTRACTION", "-"));
        list.add(new Pair<>("MULTIPLICATION", "\\*"));
        list.add(new Pair<>("DIVISION", "/"));
        list.add(new Pair<>("ASIN", "asin"));
        list.add(new Pair<>("ACOS", "acos"));
        list.add(new Pair<>("ATAN", "atan"));
        list.add(new Pair<>("SINH", "sinh"));
        list.add(new Pair<>("COSH", "cosh"));
        list.add(new Pair<>("TANH", "tanh"));
        list.add(new Pair<>("SIN", "sin"));
        list.add(new Pair<>("COS", "cos"));
        list.add(new Pair<>("TAN", "tan"));
        list.add(new Pair<>("POW", "pow"));
        list.add(new Pair<>("LOG", "log"));
        return list;
    }

    private Integer position;
    private String expression;

    public Tokenizer(String expression) {
        this.position = 0;
        this.expression = expression;
    }

    public Pair<String, String> getNextToken() throws Exception {
        if (position >= expression.length())
            return new Pair<>("EOF", "");

        Pair<String, String> result = null;
        for (Pair<String, String> pair: tokenList) {
            String key = pair.getKey();
            String value = pair.getValue();

            Pattern pattern = Pattern.compile(value);
            Matcher matcher = pattern.matcher(expression);

            if (matcher.find(position) && matcher.start() == position) {
                result = new Pair<>(key, matcher.group());
                position = matcher.end();
                break;
            }
        }

        if (result == null)
            throw new Exception(String.format("Illegal character at position %s.", position));

        return result;
    }
}
