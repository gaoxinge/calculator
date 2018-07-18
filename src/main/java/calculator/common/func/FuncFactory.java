package calculator.common.func;

public class FuncFactory {
    public static Func build(String var) {
        Func func = null;
        try {
            func = (Func) Class.forName("calculator.common.func.funcs" + var).newInstance();
        } catch (Exception e) {
            // pass
        }
        return func;
    }
}
