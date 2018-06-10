package calculator;

import calculator.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private static final Map<String, String> tokenMap = generateTokenMap();
    private static Map<String, String> generateTokenMap() {
        Map<String, String> map = new HashMap<>();
        map.put("WHITE_SPACE", "[ \n\t]+");
        map.put("NUMBER", "[0-9]*.[0-9]+");
        map.put("PI", "PI");
        map.put("E", "E");
        map.put("LEFT_PARENTHESES", "(");
        map.put("RIGHT_PARENTHESES", ")");
        map.put("COMMA", ",");
        map.put("ADDITION", "+");
        map.put("SUBSTRACTION", "-");
        map.put("MULTIPLICATION", "*");
        map.put("DIVISION", "/");
        map.put("ASIN", "asin");
        map.put("ACOS", "acos");
        map.put("ATAN", "atan");
        map.put("SIN", "sin");
        map.put("COS", "cos");
        map.put("TAN", "tan");
        map.put("SINH", "sinh");
        map.put("COSH", "cosh");
        map.put("TANH", "tanh");
        map.put("POW", "pow");
        map.put("LOG", "log");
        return map;
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
        for (Map.Entry<String, String> entry: tokenMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

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
