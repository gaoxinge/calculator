package calculator.layer;

import calculator.common.Type;
import calculator.util.Quadruple;

public class FilterLayer {
    private DeduceLayer deduceLayer;

    public FilterLayer(DeduceLayer deduceLayer) {
        this.deduceLayer = deduceLayer;
    }

    public Quadruple<Type, String, Integer, String> getNextToken() throws Exception {
        Quadruple<Type, String, Integer, String> quadruple = deduceLayer.getNextToken();
        while (quadruple.getKey1().equals(Type.SPACE)) {
            quadruple = deduceLayer.getNextToken();
        }
        return quadruple;
    }
}
