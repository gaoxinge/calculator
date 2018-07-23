package calculator;

import calculator.layer.*;

public class Eval {
    public static Double eval(String expression) throws Exception {
        TokenLayer tokenLayer = new TokenLayer(expression);
        DeduceLayer deduceLayer = new DeduceLayer(tokenLayer);
        FilterLayer filterLayer = new FilterLayer(deduceLayer);
        TransferLayer transferLayer = new TransferLayer(filterLayer);
        ParseLayer parseLayer = new ParseLayer(transferLayer);
        return parseLayer.getResult();
    }

    public static void main(String[] args) throws Exception {
        String[] expressions = new String[] {
                "1 + 2",
                "1 * - 2",
                "1 ** - - 2",
                "1 + 2 * 3 - + 4 / 5 ** 7",
                "1 + 2 * ( ( 3 - + 4 ) / 5 ) ** 7",
                "random ( )",
                "sin ( 1 )",
                "pow ( 1 , 2 )",
                "max ( 1 )",
                "max ( 1 , 2 )",
                "1 + 2 * max( ( 3 - + 4 ) / 5 , 89 ) ** 7",
        };
        for (String expression: expressions)
            System.out.println(eval(expression));
    }
}
