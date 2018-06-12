package calculator;

import calculator.util.Pair;
import calculator.util.Token;

import java.util.Stack;

public class Parser {
    private Tokenizer tokenizer;
    private Stack<String> operand;
    private Stack<String> operator;

    public Parser(Tokenizer tokenizer) {
        this.operand = new Stack<>();
        this.operator = new Stack<>();
        this.tokenizer = tokenizer;
    }

    public double getValue() throws Exception {
        while (true) {
            Pair<Token, String> pair = tokenizer.getNextToken();
            Token key = pair.getKey();
            String value = pair.getValue();

            switch (key) {
                case WHITE_SPACE:
                    break;
                case NUMBER:
                    break;
                case PI:
                    break;
                case E:
                    break;
                case LEFT_PARENTHESES:
                    break;
                case RIGHT_PARENTHESES:
                    break;
                case COMMA:
                    break;
                case ADDITION:
                    break;
                case SUBSTRACTION:
                    break;
                case MULTIPLICATION:
                    break;
                case DIVISION:
                    break;
                case ASIN:
                    break;
                case ACOS:
                    break;
                case ATAN:
                    break;
                case SINH:
                    break;
                case COSH:
                    break;
                case TANH:
                    break;
                case SIN:
                    break;
                case COS:
                    break;
                case TAN:
                    break;
                case POW:
                    break;
                case LOG:
                    break;
                case EOF:
                    break;
            }
            break;
        }
        return 0.0;
    }
}
