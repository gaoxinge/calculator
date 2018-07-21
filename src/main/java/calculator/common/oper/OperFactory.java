package calculator.common.oper;

import calculator.common.func.Func;
import calculator.common.func.funcs.*;
import calculator.util.Quintuple;

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

    private static final Quintuple<String, String, String, String, Func> EMPTY_QUINTUPLE = new Quintuple<>();
    public static Oper build(String var, String addition) {
        Integer precedence = null;
        Quintuple<String, String, String, String, Func> quintuple = EMPTY_QUINTUPLE;

        for (Integer index = 0; index < OPER_LIST.size(); index++) {
            quintuple = OPER_LIST.get(index);

            if (quintuple.getT3().equals("-"))
                precedence = index;

            if (var.equals(quintuple.getT1()) && addition.equals(quintuple.getT2()))
                break;
        }

        return new Oper(var, addition, precedence, quintuple.getT4(), quintuple.getT5());
    }
}
