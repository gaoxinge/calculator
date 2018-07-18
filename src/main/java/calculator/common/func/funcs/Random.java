package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Random implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 0;
    }

    @Override
    public Double func(Double... args) {
        return Math.random();
    }
}
