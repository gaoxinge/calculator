package calculator.common.func;

public class FuncFactory {
    private static String firstUpperCase(String var) {
        char[] chs = var.toCharArray();
        chs[0] = (char) (chs[0] - 32);
        return new String(chs);
    }

    public static Func build(String var) {
        Func func = null;
        try {
            func = (Func) Class.forName("calculator.common.func.funcs." + firstUpperCase(var)).newInstance();
        } catch (Exception e) {
            // pass
        }
        return func;
    }
}
