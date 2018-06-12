package calculator;

import calculator.util.Pair;
import calculator.util.Token;
import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {

    private void testTemplate(Token type1, Token type2, String expression) throws Exception {
        Tokenizer tokenizer = new Tokenizer(expression);

        Pair<Token, String> result1 = tokenizer.getNextToken();
        Assert.assertEquals(result1.getKey(), type1);

        Pair<Token, String> result2 = tokenizer.getNextToken();
        Assert.assertEquals(result2.getKey(), type2);

        Pair<Token, String> EOF = tokenizer.getNextToken();
        Assert.assertEquals(EOF.getKey(), Token.EOF);
    }

    @Test
    public void testToken() throws Exception {
        testTemplate(Token.NUMBER, Token.WHITE_SPACE, "1234 ");
        testTemplate(Token.WHITE_SPACE, Token.NUMBER, " 1234");
        testTemplate(Token.WHITE_SPACE, Token.NUMBER, " 1234.1234");
        testTemplate(Token.WHITE_SPACE, Token.NUMBER, " 1234.");
        testTemplate(Token.WHITE_SPACE, Token.NUMBER, " .1234");
        testTemplate(Token.WHITE_SPACE, Token.PI, " PI");
        testTemplate(Token.WHITE_SPACE, Token.E, " E");
        testTemplate(Token.WHITE_SPACE, Token.POSITIVE, " +");
        testTemplate(Token.WHITE_SPACE, Token.NEGATIVE, " -");
        testTemplate(Token.WHITE_SPACE, Token.ASIN, " asin");
        testTemplate(Token.WHITE_SPACE, Token.ACOS, " acos");
        testTemplate(Token.WHITE_SPACE, Token.ATAN, " atan");
        testTemplate(Token.WHITE_SPACE, Token.SINH, " sinh");
        testTemplate(Token.WHITE_SPACE, Token.COSH, " cosh");
        testTemplate(Token.WHITE_SPACE, Token.TANH, " tanh");
        testTemplate(Token.WHITE_SPACE, Token.SIN, " sin");
        testTemplate(Token.WHITE_SPACE, Token.COS, " cos");
        testTemplate(Token.WHITE_SPACE, Token.TAN, " tan");
        testTemplate(Token.WHITE_SPACE, Token.POW, " pow");
        testTemplate(Token.WHITE_SPACE, Token.LOG, " log");
        testTemplate(Token.WHITE_SPACE, Token.LEFT_PARENTHESES, " (");
        testTemplate(Token.WHITE_SPACE, Token.RIGHT_PARENTHESES, " )");
        testTemplate(Token.WHITE_SPACE, Token.COMMA, " ,");
        testTemplate(Token.WHITE_SPACE, Token.MULTIPLICATION, " *");
        testTemplate(Token.WHITE_SPACE, Token.DIVISION, " /");
        testTemplate(Token.NUMBER, Token.ADDITION, "1234+");
        testTemplate(Token.NUMBER, Token.SUBSTRACTION, "1234-");
    }

    @Test
    public void testExpressionWithoutBug() throws Exception {
        System.out.println("test expression without bug");
        String expression = "(1 + 2) * 3 + asin(5 / 6) + pow(4, 7)";
        Tokenizer tokenizer = new Tokenizer(expression);

        while (true) {
            Pair<Token, String> result = tokenizer.getNextToken();
            if (result.getKey().equals(Token.EOF))
                break;
            System.out.println(result);
        }
        System.out.println("##############################");
    }

    @Test
    public void testExpressionWithBug() {
        System.out.println("test expression with bug");
        String expression = "(1 ++ 2) * 3 -+ asin(tanh(5 / 6)) + pow(log(4, 7) EOF";
        Tokenizer tokenizer = new Tokenizer(expression);

        Pair<Token, String> result;
        while (true) {
            try {
                result = tokenizer.getNextToken();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            if (result.getKey().equals(Token.EOF))
                break;
            System.out.println(result);
        }
        System.out.println("##############################");
    }
}
