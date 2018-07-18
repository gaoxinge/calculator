package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Neg implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 1;
    }

    @Override
    public Double func(Double... args) {
        return -args[0];
    }
}
