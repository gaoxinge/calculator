package calculator.layer;

import calculator.common.Type;
import calculator.common.func.Func;
import calculator.common.oper.Oper;
import calculator.util.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParseLayer {
    private Stack<Double> operand;
    private Stack<Triple<Type, Object, Integer>> operator;
    private Stack<Integer> guess;
    private TransferLayer transferLayer;

    public ParseLayer(TransferLayer transferLayer) {
        this.operand = new Stack<>();
        this.operator = new Stack<>();
        this.guess = new Stack<>();
        this.transferLayer = transferLayer;
    }

    public Double getResult() throws Exception {
        Triple<Type, Object, Integer> triple = transferLayer.getNextToken();

        if (triple.getT1().equals(Type.EOF))
            throw new Exception("Expression is null or only contains white spaces.");

        while (!triple.getT1().equals(Type.EOF)) {
            switch (triple.getT1()) {
                case NUM:
                    pushNum(triple);
                    break;
                case OPER:
                    pushOper(triple);
                    break;
                case FUNC:
                    pushFunc(triple);
                    break;
                case LEFT:
                    pushLeft(triple);
                    break;
                case COMMA:
                    pushComma(triple);
                    break;
                case RIGHT:
                    pushRight(triple);
                    break;
            }
            triple = transferLayer.getNextToken();
        }

        while (!operator.empty()) {
            if (!operator.peek().getT1().equals(Type.OPER))
                throw new Exception(String.format("There is no corresponding right parenthesis after the function or left parenthesis %s.", operator.peek().getT3()));
            Oper oper = (Oper) operator.peek().getT2();
            Integer position = operator.peek().getT3();
            calculateOper(oper, position);
            operator.pop();
        }

        if (operand.size() != 1)
            throw new Exception("Invalid expression.");

        return operand.pop();
    }

    private void pushNum(Triple<Type, Object, Integer> triple) {
        Double num = (Double) triple.getT2();
        operand.push(num);
    }

    private void pushOper(Triple<Type, Object, Integer> triple) throws Exception {
        Oper oper = (Oper) triple.getT2();

        while (!operator.empty() && operator.peek().getT1().equals(Type.OPER)) {
            Oper top = (Oper) operator.peek().getT2();
            Integer position = operator.peek().getT3();

            if ((oper.getAssociative().equals("left") && top.getPrecedence() <= oper.getPrecedence()) ||
                    (oper.getAssociative().equals("right") && top.getPrecedence() < oper.getPrecedence())) {
                calculateOper(top, position);
                operator.pop();
            } else {
                break;
            }
        }

        operator.push(triple);
    }

    private void pushFunc(Triple<Type, Object, Integer> triple) {
        operator.push(triple);
        guess.push(1);
    }

    private void pushLeft(Triple<Type, Object, Integer> triple) {
        operator.push(triple);
    }

    private void pushComma(Triple<Type, Object, Integer> triple) throws Exception {
        consumeOper();

        if (operator.empty() || !operator.peek().getT1().equals(Type.LEFT))
            throw new Exception(String.format("There is no corresponding left parenthesis before the comma at position %s.", triple.getT3()));

        if (guess.empty())
            throw new Exception(String.format("There is no corresponding function before the comma at position %s.", triple.getT3()));
        guess.push(guess.pop() + 1);
    }

    private void pushRight(Triple<Type, Object, Integer> triple) throws Exception {
        consumeOper();

        if (operator.empty() || !operator.peek().getT1().equals(Type.LEFT))
            throw new Exception(String.format("There is no corresponding left parenthesis before the comma at position %s.", triple.getT3()));
        operator.pop();

        if (!operator.empty() && operator.peek().getT1().equals(Type.FUNC)) {
            Func func = (Func) operator.peek().getT2();
            Integer position = operator.peek().getT3();
            Integer g = guess.peek();

            if (g == 1) {
                if (!operand.empty() && func.checkArgsValid(operand.peek()))
                    operand.push(func.func(operand.pop()));
                else if (func.checkArgsValid())
                    operand.push(func.func());
                else
                    throw new Exception(String.format("Invalid args number of function at position %s.", position));

            } else {
                if (operand.size() < g)
                    throw new Exception(String.format("Invalid args number of function at position %s.", position));
                List<Double> argList = new ArrayList<>();
                for (Integer i = 0; i < g; i++)
                    argList.add(0, operand.pop());
                Double[] argArray = argList.toArray(new Double[argList.size()]);
                if (!func.checkArgsValid(argArray))
                    throw new Exception(String.format("Invalid args number of function at position %s.", position));
                operand.push(func.func(argArray));
            }

            operator.pop();
            guess.pop();
        }
    }

    private void consumeOper() throws Exception {
        while (!operator.empty() && operator.peek().getT1().equals(Type.OPER)) {
            Oper oper = (Oper) operator.peek().getT2();
            Integer position = operator.peek().getT3();
            calculateOper(oper, position);
            operator.pop();
        }
    }

    private void calculateOper(Oper oper, Integer position) throws Exception {
        if (oper.getOpNum().equals("unary")) {
            if (operand.empty())
                throw new Exception(String.format("Invalid operator number of operator at position %s.", position));
            Double num = operand.pop();
            operand.push(oper.getFunc().func(num));
        }

        if (oper.getOpNum().equals("binary")) {
            if (operand.size() < 2)
                throw new Exception(String.format("Invalid operator number of operator at position %s.", position));
            Double right = operand.pop();
            Double left = operand.pop();
            operand.push(oper.getFunc().func(left, right));
        }
    }
}
