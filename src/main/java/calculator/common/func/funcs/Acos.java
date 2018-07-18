package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Acos implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 1;
    }

    @Override
    public Double func(Double... args) {
        return Math.acos(args[0]);
    }
}
