package calculator.common;

import calculator.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class VarRegex {
    public static final List<Pair<Type, String>> VAR_REGEX_LIST = generateVarRegexList();
    private static List<Pair<Type, String>> generateVarRegexList() {
        List<Pair<Type, String>> list = new ArrayList<>();
        list.add(new Pair<>(Type.SPACE, "\\s+"));
        list.add(new Pair<>(Type.NUM, "\\d+\\.\\d+|\\d+\\.|\\.\\d+|\\d+"));
        list.add(new Pair<>(Type.NUM, "PI"));
        list.add(new Pair<>(Type.NUM, "E"));
        list.add(new Pair<>(Type.OPER, "\\*\\*"));
        list.add(new Pair<>(Type.OPER, "\\+"));
        list.add(new Pair<>(Type.OPER, "-"));
        list.add(new Pair<>(Type.OPER, "\\*"));
        list.add(new Pair<>(Type.OPER, "/"));
        list.add(new Pair<>(Type.FUNC, "random"));
        list.add(new Pair<>(Type.FUNC, "add"));
        list.add(new Pair<>(Type.FUNC, "sub"));
        list.add(new Pair<>(Type.FUNC, "mul"));
        list.add(new Pair<>(Type.FUNC, "div"));
        list.add(new Pair<>(Type.FUNC, "pow"));
        list.add(new Pair<>(Type.FUNC, "log"));
        list.add(new Pair<>(Type.FUNC, "pos"));
        list.add(new Pair<>(Type.FUNC, "neg"));
        list.add(new Pair<>(Type.FUNC, "abs"));
        list.add(new Pair<>(Type.FUNC, "max"));
        list.add(new Pair<>(Type.FUNC, "min"));
        list.add(new Pair<>(Type.FUNC, "asin"));
        list.add(new Pair<>(Type.FUNC, "acos"));
        list.add(new Pair<>(Type.FUNC, "atan"));
        list.add(new Pair<>(Type.FUNC, "sinh"));
        list.add(new Pair<>(Type.FUNC, "cosh"));
        list.add(new Pair<>(Type.FUNC, "tanh"));
        list.add(new Pair<>(Type.FUNC, "sin"));
        list.add(new Pair<>(Type.FUNC, "cos"));
        list.add(new Pair<>(Type.FUNC, "tan"));
        list.add(new Pair<>(Type.LEFT, "\\("));
        list.add(new Pair<>(Type.COMMA, ","));
        list.add(new Pair<>(Type.RIGHT, "\\)"));
        return list;
    }
}
