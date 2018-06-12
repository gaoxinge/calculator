package calculator;

import calculator.util.Pair;
import calculator.util.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private static final List<Pair<Token, String>> tokenList = generateTokenList();
    private static List<Pair<Token, String>> generateTokenList() {
        List<Pair<Token, String>> list = new ArrayList<>();
        list.add(new Pair<>(Token.WHITE_SPACE, "\\s+"));
        list.add(new Pair<>(Token.NUMBER, "\\d+\\.\\d+|\\d+\\.|\\.\\d+|\\d+"));
        list.add(new Pair<>(Token.PI, "PI"));
        list.add(new Pair<>(Token.E, "E"));
        list.add(new Pair<>(Token.POSITIVE, "\\+"));
        list.add(new Pair<>(Token.NEGATIVE, "-"));
        list.add(new Pair<>(Token.LEFT_PARENTHESES, "\\("));
        list.add(new Pair<>(Token.RIGHT_PARENTHESES, "\\)"));
        list.add(new Pair<>(Token.COMMA, ","));
        list.add(new Pair<>(Token.ASIN, "asin"));
        list.add(new Pair<>(Token.ACOS, "acos"));
        list.add(new Pair<>(Token.ATAN, "atan"));
        list.add(new Pair<>(Token.SINH, "sinh"));
        list.add(new Pair<>(Token.COSH, "cosh"));
        list.add(new Pair<>(Token.TANH, "tanh"));
        list.add(new Pair<>(Token.SIN, "sin"));
        list.add(new Pair<>(Token.COS, "cos"));
        list.add(new Pair<>(Token.TAN, "tan"));
        list.add(new Pair<>(Token.POW, "pow"));
        list.add(new Pair<>(Token.LOG, "log"));
        list.add(new Pair<>(Token.MULTIPLICATION, "\\*"));
        list.add(new Pair<>(Token.DIVISION, "/"));
        list.add(new Pair<>(Token.ADDITION, "\\+"));
        list.add(new Pair<>(Token.SUBSTRACTION, "-"));
        return list;
    }

    private int position;
    private Pair<Token, String> last;
    private String expression;

    public Tokenizer(String expression) {
        this.position = 0;
        this.last = null;
        this.expression = expression;
    }

    public Pair<Token, String> getNextToken() throws Exception {
        if (position >= expression.length())
            return new Pair<>(Token.EOF, "");

        Pair<Token, String> result = null;
        for (Pair<Token, String> pair: tokenList) {
            Token key = pair.getKey();
            String value = pair.getValue();

            // use check function to distinguish Token, i.e.
            //   Token.POSITIVE and Token.ADDITION
            //   Token.NEGATIVE and Token.SUBSTRACTION
            if (key.equals(Token.POSITIVE) && !isPositive())
                continue;
            if (key.equals(Token.NEGATIVE) && !isNegative())
                continue;
            if (key.equals(Token.ADDITION) && !isAddition())
                continue;
            if (key.equals(Token.SUBSTRACTION) && !isSubstraction())
                continue;

            Pattern pattern = Pattern.compile(value);
            Matcher matcher = pattern.matcher(expression);

            if (matcher.find(position) && matcher.start() == position) {
                result = new Pair<>(key, matcher.group());
                position = matcher.end();

                if (!key.equals(Token.WHITE_SPACE))
                    last = result;

                break;
            }
        }

        if (result == null)
            throw new Exception(String.format("Illegal character at position %s.", position));

        return result;
    }


    /*********************************************
     * check whether last token satisfy condition
     *********************************************/
    private boolean isPositive() {
        if (last == null)
            return true;

        Token key = last.getKey();
        return key.equals(Token.POSITIVE) ||
                key.equals(Token.NEGATIVE) ||
                key.equals(Token.LEFT_PARENTHESES) ||
                key.equals(Token.COMMA) ||
                key.equals(Token.MULTIPLICATION) ||
                key.equals(Token.DIVISION) ||
                key.equals(Token.ADDITION) ||
                key.equals(Token.SUBSTRACTION);
    }

    private boolean isNegative() {
        if (last == null)
            return true;

        Token key = last.getKey();
        return key.equals(Token.POSITIVE) ||
                key.equals(Token.NEGATIVE) ||
                key.equals(Token.LEFT_PARENTHESES) ||
                key.equals(Token.COMMA) ||
                key.equals(Token.MULTIPLICATION) ||
                key.equals(Token.DIVISION) ||
                key.equals(Token.ADDITION) ||
                key.equals(Token.SUBSTRACTION);
    }

    private boolean isAddition() {
        if (last == null)
            return false;

        Token key = last.getKey();
        return key.equals(Token.NUMBER) ||
                key.equals(Token.PI) ||
                key.equals(Token.E) ||
                key.equals(Token.RIGHT_PARENTHESES);
    }

    private boolean isSubstraction() {
        if (last == null)
            return false;

        Token key = last.getKey();
        return key.equals(Token.NUMBER) ||
                key.equals(Token.PI) ||
                key.equals(Token.E) ||
                key.equals(Token.RIGHT_PARENTHESES);
    }
}
