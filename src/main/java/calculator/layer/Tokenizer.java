package calculator.layer;

import calculator.common.Type;
import calculator.common.VarRegex;
import calculator.util.ErrorUtil;
import calculator.util.Pair;
import calculator.util.Triple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private Integer position;
    private String expression;

    public Tokenizer(String expression) {
        this.position = 0;
        this.expression = expression;
    }

    public Triple<Type, String, Integer> getNextToken() throws Exception {
        if (position >= expression.length())
            return new Triple<>(Type.EOF, "", position);

        Triple<Type, String, Integer> result = null;
        for (Pair<Type, String> varRegex: VarRegex.VAR_REGEX_LIST) {
            Type key = varRegex.getKey();
            String value = varRegex.getValue();

            Pattern pattern = Pattern.compile(value);
            Matcher matcher = pattern.matcher(expression);

            if (matcher.find(position) && matcher.start() == position) {
                result = new Triple<>(key, matcher.group(), position);
                position = matcher.end();
                break;
            }
        }

        if (result == null) {
            String error = String.format("Illegal character at position %s.", position);
            throw new Exception(ErrorUtil.wrap(error, expression, position));
        }

        return result;
    }
}
