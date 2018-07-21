package calculator.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Triple<T1, T2, T3> {
    private T1 t1;
    private T2 t2;
    private T3 t3;
}
