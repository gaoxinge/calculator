package calculator.common.func;

public interface Func {
    Boolean checkArgsValid(Double... args);
    Double func(Double... args);
}
