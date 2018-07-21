package calculator.layer;

import calculator.common.Type;
import calculator.common.func.Func;
import calculator.common.func.FuncFactory;
import calculator.common.num.NumFactory;
import calculator.common.oper.Oper;
import calculator.common.oper.OperFactory;
import calculator.util.Quadruple;
import calculator.util.Triple;

public class TransferLayer {
    private FilterLayer filterLayer;

    public TransferLayer(FilterLayer filterLayer) {
        this.filterLayer = filterLayer;
    }

    public Triple<Type, Object, Integer> getNextToken() throws Exception {
        Quadruple<Type, String, String, Integer> quadruple = filterLayer.getNextToken();

        switch (quadruple.getT1()) {
            case NUM:
                return getNum(quadruple);
            case OPER:
                return getOper(quadruple);
            case FUNC:
                return getFunc(quadruple);
            default:
                return getDefault(quadruple);
        }
    }

    private Triple<Type, Object, Integer> getNum(Quadruple<Type, String, String, Integer> quadruple) {
        Double num = NumFactory.build(quadruple.getT2());
        return new Triple<>(quadruple.getT1(), num, quadruple.getT4());
    }

    private Triple<Type, Object, Integer> getOper(Quadruple<Type, String, String, Integer> quadruple) {
        Oper oper = OperFactory.build(quadruple.getT2(), quadruple.getT3());
        return new Triple<>(quadruple.getT1(), oper, quadruple.getT4());
    }

    private Triple<Type, Object, Integer> getFunc(Quadruple<Type, String, String, Integer> quadruple) {
        Func func = FuncFactory.build(quadruple.getT2());
        return new Triple<>(quadruple.getT1(), func, quadruple.getT4());
    }

    private Triple<Type, Object, Integer> getDefault(Quadruple<Type, String, String, Integer> quadruple) {
        return new Triple<>(quadruple.getT1(), quadruple.getT2(), quadruple.getT4());
    }
}
