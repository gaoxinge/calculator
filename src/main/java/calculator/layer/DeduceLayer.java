package calculator.layer;

import calculator.common.Type;
import calculator.util.Quadruple;
import calculator.util.Triple;

public class DeduceLayer {
    private Triple<Type, String, Integer> last;
    private TokenLayer tokenLayer;

    public DeduceLayer(TokenLayer tokenLayer) {
        this.last = null;
        this.tokenLayer = tokenLayer;
    }

    public Quadruple<Type, String, String, Integer> getNextToken() throws Exception {
        Triple<Type, String, Integer> triple = tokenLayer.getNextToken();

        if (!triple.getT1().equals(Type.SPACE)) last = triple;

        switch (triple.getT2()) {
            case "+":
                if (isAdd()) return getAdd(triple);
                else         return getPos(triple);
            case "-":
                if (isSub()) return getSub(triple);
                else         return getNeg(triple);
            default:
                return getDefault(triple);
        }
    }

    private Boolean isAdd() {
        if (last == null)
            return false;

        Type type = last.getT1();
        return type.equals(Type.NUM) || type.equals(Type.RIGHT);
    }

    private Quadruple<Type, String, String, Integer> getAdd(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getT1(), triple.getT2(), "binary", triple.getT3());
    }

    private Quadruple<Type, String, String, Integer> getPos(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getT1(), triple.getT2(), "unary", triple.getT3());
    }

    private Boolean isSub() {
        if (last == null)
            return false;

        Type type = last.getT1();
        return type.equals(Type.NUM) || type.equals(Type.RIGHT);
    }

    private Quadruple<Type, String, String, Integer> getSub(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getT1(), triple.getT2(), "binary", triple.getT3());
    }

    private Quadruple<Type, String, String, Integer> getNeg(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getT1(), triple.getT2(), "unary", triple.getT3());
    }

    private Quadruple<Type, String, String, Integer> getDefault(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getT1(), triple.getT2(), "", triple.getT3());
    }
}
