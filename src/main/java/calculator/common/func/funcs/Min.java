package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Min implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length >= 1;
    }

    @Override
    public Double func(Double... args) {
        Double min = args[0];
        for (Double arg: args)
            if (min > arg)
                min = arg;
        return min;
    }
}
