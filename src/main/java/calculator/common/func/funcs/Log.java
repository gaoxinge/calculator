package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Log implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length == 2;
    }

    @Override
    public Double func(Double... args) {
        return Math.log(args[1]) / Math.log(args[0]);
    }
}
