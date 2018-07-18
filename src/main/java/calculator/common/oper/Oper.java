package calculator.common.oper;

import calculator.common.func.Func;

public interface Oper {
    String getVar();
    String getNum();
    Integer getPrecedence();
    String getAssociative();
    Func getValue();
}
