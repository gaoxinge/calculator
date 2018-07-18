package calculator.common.oper;

import calculator.common.func.Func;
import calculator.common.func.funcs.*;
import calculator.util.Quintuple;
import calculator.util.Triple;

import java.util.ArrayList;
import java.util.List;

public class OperFactory {
    private static final List<Quintuple<String, String, String, String, Func>> OPER_LIST = generateOperList();
    private static List<Quintuple<String, String, String, String, Func>> generateOperList() {
        List<Quintuple<String, String, String, String, Func>> list = new ArrayList<>();
        list.add(new Quintuple<>("+", "unary", "-", "right", new Pos()));
        list.add(new Quintuple<>("-", "unary", "^", "right", new Neg()));
        list.add(new Quintuple<>("**", "binary", "^", "right", new Pow()));
        list.add(new Quintuple<>("*", "binary", "-", "left", new Mul()));
        list.add(new Quintuple<>("/", "binary", "^", "left", new Div()));
        list.add(new Quintuple<>("+", "binary", "-", "left", new Add()));
        list.add(new Quintuple<>("-", "binary", "^", "left", new Sub()));
        return list;
    }

    public static Oper build(String var, String num) {
        final Triple<Integer, String, Func> triple = new Triple<>();
        Integer precedence = null;
        Quintuple<String, String, String, String, Func> quintuple = new Quintuple<>();

        for (Integer index = 0; index < OPER_LIST.size(); index++) {
            quintuple = OPER_LIST.get(index);

            if (quintuple.getKey3().equals("-"))
                precedence = index;

            if (var.equals(quintuple.getKey1()) && num.equals(quintuple.getKey2()))
                break;
        }

        triple.setKey1(precedence);
        triple.setKey2(quintuple.getKey4());
        triple.setKey3(quintuple.getKey5());

        return new Oper() {
            @Override public String getVar() { return var; }
            @Override public String getNum() { return num; }
            @Override public Integer getPrecedence() { return triple.getKey1(); }
            @Override public String getAssociative() { return triple.getKey2(); }
            @Override public Func getValue() { return triple.getKey3(); }
        };
    }
}
