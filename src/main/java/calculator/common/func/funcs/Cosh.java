package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Cosh implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 1;
    }

    @Override
    public Double func(Double... args) {
        return Math.cosh(args[0]);
    }
}
