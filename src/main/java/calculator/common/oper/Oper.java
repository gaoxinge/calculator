package calculator.common.oper;

import calculator.common.func.Func;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Oper {
    private String var;
    private String opNum;
    private Integer precedence;
    private String associative;
    private Func func;
}
