package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Add implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 2;
    }

    @Override
    public Double func(Double... args) {
        return args[0] + args[1];
    }
}
