package calculator.common.num;

public class NumFactory {
    public static Double build(String var) {
        switch (var) {
            case "PI":
                return Math.PI;
            case "E":
                return Math.E;
            default:
                return Double.valueOf(var);
        }
    }
}
