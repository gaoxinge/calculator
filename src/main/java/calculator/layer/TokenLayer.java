package calculator.layer;

import calculator.common.Type;
import calculator.common.TypeVarRegex;
import calculator.util.Error;
import calculator.util.Pair;
import calculator.util.Triple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenLayer {
    private Integer position;
    private String expression;

    public TokenLayer(String expression) {
        this.position = 0;
        this.expression = expression;
    }

    public Triple<Type, String, Integer> getNextToken() throws Exception {
        if (position >= expression.length())
            return new Triple<>(Type.EOF, "", position);

        Triple<Type, String, Integer> triple = null;
        for (Pair<Type, String> typeVarRegex: TypeVarRegex.TYPE_VAR_REGEX_LIST) {
            Type type = typeVarRegex.getT1();
            String varRegex = typeVarRegex.getT2();

            Pattern pattern = Pattern.compile(varRegex);
            Matcher matcher = pattern.matcher(expression);

            if (matcher.find(position) && matcher.start() == position) {
                triple = new Triple<>(type, matcher.group(), position);
                position = matcher.end();
                break;
            }
        }

        if (triple == null) {
            String error = String.format("Illegal character at position %s.", position);
            throw new Exception(Error.wrap(error, expression, position));
        }

        return triple;
    }
}
