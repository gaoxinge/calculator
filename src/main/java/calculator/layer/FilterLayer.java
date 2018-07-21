package calculator.layer;

import calculator.common.Type;
import calculator.util.Quadruple;

public class FilterLayer {
    private DeduceLayer deduceLayer;

    public FilterLayer(DeduceLayer deduceLayer) {
        this.deduceLayer = deduceLayer;
    }

    public Quadruple<Type, String, String, Integer> getNextToken() throws Exception {
        Quadruple<Type, String, String, Integer> quadruple = deduceLayer.getNextToken();
        while (quadruple.getT1().equals(Type.SPACE)) {
            quadruple = deduceLayer.getNextToken();
        }
        return quadruple;
    }
}
