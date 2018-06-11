package calculator;

import calculator.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {

    private void testTemplate(String type, String expression) throws Exception {
        Tokenizer tokenizer = new Tokenizer(expression);

        Pair<String, String> result;
        while (true) {
            result = tokenizer.getNextToken();
            if (result.getKey().equals("EOF"))
                break;
            Assert.assertEquals(result.getKey(), type);
            Assert.assertEquals(result.getValue(), expression);
        }
    }

    @Test
    public void testToken() throws Exception {
        testTemplate("WHITE_SPACE", "     ");
        testTemplate("NUMBER", "1234.1234");
        testTemplate("NUMBER", "1234.");
        testTemplate("NUMBER", ".1234");
        testTemplate("PI", "PI");
        testTemplate("E", "E");
        testTemplate("LEFT_PARENTHESES", "(");
        testTemplate("RIGHT_PARENTHESES", ")");
        testTemplate("COMMA", ",");
        testTemplate("ADDITION", "+");
        testTemplate("SUBSTRACTION", "-");
        testTemplate("MULTIPLICATION", "*");
        testTemplate("DIVISION", "/");
        testTemplate("ASIN", "asin");
        testTemplate("ACOS", "acos");
        testTemplate("ATAN", "atan");
        testTemplate("SIN", "sin");
        testTemplate("COS", "cos");
        testTemplate("TAN", "tan");
        testTemplate("SINH", "sinh");
        testTemplate("COSH", "cosh");
        testTemplate("TANH", "tanh");
        testTemplate("POW", "pow");
        testTemplate("LOG", "log");
    }

    @Test
    public void testExpressionWithoutBug() throws Exception {
        System.out.println("test expression without bug");
        String expression = "(1 + 2) * 3 + asin(5 / 6) + pow(4, 7)";
        Tokenizer tokenizer = new Tokenizer(expression);

        Pair<String, String> result;
        while (true) {
            result = tokenizer.getNextToken();
            if (result.getKey().equals("EOF"))
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

        Pair<String, String> result;
        while (true) {
            try {
                result = tokenizer.getNextToken();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            if (result.getKey().equals("EOF"))
                break;
            System.out.println(result);
        }
        System.out.println("##############################");
    }
}
