package calculator.common.func.funcs;

import calculator.common.func.Func;

public class Max implements Func {

    @Override
    public Boolean checkArgsValid(Double... args) {
        return args.length >= 1;
    }

    @Override
    public Double func(Double... args) {
        Double max = args[0];
        for (Double arg: args)
            if (max < arg)
                max = arg;
        return max;
    }
}
