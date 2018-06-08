package calculator.Tokenizer;

import java.util.HashMap;
import java.util.Map;

public class Token {
    public static final Map<String, String> tokenMap = generateTokenMap();
    private static Map<String, String> generateTokenMap() {
        Map<String, String> map = new HashMap<>();
        map.put("WHITE_SPACE", "");
        map.put("NUMBER", "");
        map.put("PI", "");
        map.put("E", "");
        map.put("LEFT_PARENTHESES", "");
        map.put("RIGHT_PARENTHESES", "");
        map.put("COMMA", "");
        map.put("ADDITION", "");
        map.put("SUBSTRACTION", "");
        map.put("MULTIPLICATION", "");
        map.put("DIVISION", "");
        map.put("ASIN", "");
        map.put("ACOS", "");
        map.put("ATAN", "");
        map.put("SIN", "");
        map.put("COS", "");
        map.put("TAN", "");
        map.put("SINH", "");
        map.put("COSH", "");
        map.put("TANH", "");
        map.put("POW", "");
        map.put("LOG", "");
        return map;
    }
}
