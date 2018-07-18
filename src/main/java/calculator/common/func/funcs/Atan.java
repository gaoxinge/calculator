package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Atan implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 1;
    }

    @Override
    public Double func(Double... args) {
        return Math.atan(args[0]);
    }
}
