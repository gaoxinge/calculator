package calculator.layer;

import calculator.function.Function;
import calculator.function.FunctionFactory;
import calculator.util.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParseLayer {
    private Stack<Double> operand;
    private Stack<Triple<Token, String, Integer>> operator;
    private String expression;
    private Inter inter;

    public ParseLayer(String expression) {
        this.operand = new Stack<>();
        this.operator = new Stack<>();
        this.expression = expression;
        this.inter = new Inter(expression);
    }

    public double getValue() throws Exception {
        while (true) {
            Triple<Token, String, Integer> token = inter.getNextToken();
            Token key = token.getKey1();
            String value = token.getKey2();

            if (key.equals(Token.EOF))
                break;

            switch (key) {
                case NUMBER:
                    pushValue(Double.valueOf(value));
                    break;
                case PI:
                    pushValue(Math.PI);
                    break;
                case E:
                    pushValue(Math.E);
                    break;
                case POS:
                case NEG:
                case FUNC:
                case LEFT:
                    operator.push(token);
                    break;
                case RIGHT:
                    pushRight(token);
                    break;
                case COMMA:
                    pushComma(token);
                    break;
                case MUL:
                case DIV:
                    pushMulOrDiv(token);
                    break;
                case ADD:
                case SUB:
                    pushAddorSub(token);
                    break;
            }
        }

        while (operator.size() > 0)
            calculate();

        if (operand.size() != 1) {
            String error = String.format("Get %s results that does not equal to one.", operand.size());
            throw new Exception(ErrorUtil.wrapError(error, expression));
        } else {
            return operand.pop();
        }
    }

    private void pushValue(Double value) {
        while (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();

            if (key.equals(Token.POS)) {
                operator.pop();
            } else if (key.equals(Token.NEG)) {
                operator.pop();
                value = -value;
            } else {
                break;
            }
        }
        operand.push(value);
    }

    private void pushRight(Triple<Token, String, Integer> token) throws Exception {
        while (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();

            if (!key.equals(Token.LEFT) && !key.equals(Token.COMMA)) {
                calculate();
            } else {
                break;
            }
        }

        List<Double> params = new ArrayList<>();
        while (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();

            if (key.equals(Token.COMMA)) {
                operator.pop();
                if (operand.empty()) {
                    String error = "There is no value after ','.";
                    throw new Exception(ErrorUtil.wrapError(error, expression, t.getKey3()));
                }
                params.add(0, operand.pop());
            }
        }

        Integer leftPosition;
        if (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();
            leftPosition = t.getKey3();

            if (key.equals(Token.LEFT)) {
                operator.pop();
            } else {
                String error = "No '(' matches ')'.";
                throw new Exception(ErrorUtil.wrapError(error, expression, t.getKey3(), token.getKey3()));
            }
        } else {
            String error = "No '(' matches ')'.";
            throw new Exception(ErrorUtil.wrapError(error, expression, 0, token.getKey3()));
        }

        if (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();
            String value = t.getKey2();

            if (key.equals(Token.FUNC)) {
                Function function;
                try {
                    function = FunctionFactory.build(value);
                } catch (Exception e) {
                    throw new Exception(ErrorUtil.wrapError(e.getMessage(), expression, t.getKey3()));
                }

                if (function.getArgsNum() > 1) {
                    if (operand.empty()) {
                        String error = "There is no value after '('.";
                        throw new Exception(ErrorUtil.wrapError(error, expression, leftPosition));
                    }
                    params.add(0, operand.pop());
                }

                if (params.size() != function.getArgsNum()) {
                    String error = String.format("Invalid number of args: expected %s, actual given: %s", function.getArgsNum(), params.size());
                    throw new Exception(ErrorUtil.wrapError(error, expression, t.getKey3()));
                }

                Double[] args = params.toArray(new Double[params.size()]);
                Double v;
                try {
                    v = function.f(args);
                } catch (Exception e) {
                    throw new Exception(ErrorUtil.wrapError(e.getMessage(), expression, t.getKey3()));
                }
                pushValue(v);
            }
        } else {
            if (operand.empty()) {
                String error = "There is no value after '('.";
                throw new Exception(ErrorUtil.wrapError(error, expression, leftPosition));
            }
            params.add(0, operand.pop());

            if (params.size() != 1) {
                String error = "Invalid number of values between '(' and ')'.";
                throw new Exception(ErrorUtil.wrapError(error, expression, leftPosition, token.getKey3()));
            }
            pushValue(params.get(0));
        }
    }

    private void pushComma(Triple<Token, String, Integer> token) throws Exception {
        while (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();

            if (!key.equals(Token.LEFT) && !key.equals(Token.COMMA)) {
                calculate();
            } else {
                break;
            }
        }
        operator.push(token);
    }

    private void pushMulOrDiv(Triple<Token, String, Integer> token) throws Exception {
        while (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();

            if (key.equals(Token.MUL) || key.equals(Token.DIV)) {
                calculate();
            }  else {
                break;
            }
        }
        operator.push(token);
    }

    private void pushAddorSub(Triple<Token, String, Integer> token) throws Exception {
        while (operator.size() > 0) {
            Triple<Token, String, Integer> t = operator.peek();
            Token key = t.getKey1();

            if (!key.equals(Token.LEFT) && !key.equals(Token.COMMA)) {
                calculate();
            } else {
                break;
            }
        }
        operator.push(token);
    }

    private void calculate() throws Exception {
        Triple<Token, String, Integer> t = operator.pop();
        Token key = t.getKey1();

        if (operand.empty()) {
            String error = String.format("No right operand for operator %s", t.getKey2());
            throw new Exception(ErrorUtil.wrapError(error, expression, t.getKey3()));
        }
        Double right = operand.pop();

        if (operand.empty()) {
            String error = String.format("No left operand for operator %s", t.getKey2());
            throw new Exception(ErrorUtil.wrapError(error, expression, t.getKey3()));
        }
        Double left = operand.pop();

        try {
            switch (key) {
                case MUL:
                    pushValue(left * right);
                    break;
                case DIV:
                    pushValue(left / right);
                    break;
                case ADD:
                    pushValue(left + right);
                    break;
                case SUB:
                    pushValue(left - right);
                    break;
                default:
                    String error = String.format("Operator %s is not implemented.", t.getKey2());
                    throw new Exception(ErrorUtil.wrapError(error, expression, t.getKey3()));
            }
        } catch (Exception e) {
            throw new Exception(ErrorUtil.wrapError(e.getMessage(), expression, t.getKey3()));
        }
    }
}
