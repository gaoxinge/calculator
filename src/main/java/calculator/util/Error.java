package calculator.util;

public class Error {
    public static String wrap(String error, String expr, Integer pos) {
        StringBuilder sb = new StringBuilder();
        sb.append(expr);
        sb.append("\n");
        for (Integer i = 0; i < pos; i++)
            sb.append(" ");
        sb.append("^");
        sb.append(error);
        return sb.toString();
    }
}
