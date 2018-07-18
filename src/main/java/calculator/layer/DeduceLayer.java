package calculator.layer;

import calculator.common.Type;
import calculator.util.Quadruple;
import calculator.util.Triple;

public class DeduceLayer {
    private Triple<Type, String, Integer> last;
    private Tokenizer tokenizer;

    public DeduceLayer(Tokenizer tokenizer) {
        this.last = null;
        this.tokenizer = tokenizer;
    }

    public Quadruple<Type, String, Integer, String> getNextToken() throws Exception {
        Triple<Type, String, Integer> triple = tokenizer.getNextToken();

        if (!triple.getKey1().equals(Type.SPACE)) last = triple;

        switch (triple.getKey2()) {
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

        Type type = last.getKey1();
        return type.equals(Type.NUM) || type.equals(Type.RIGHT);
    }

    private Quadruple<Type, String, Integer, String> getAdd(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getKey1(), triple.getKey2(), triple.getKey3(), "binary");
    }

    private Quadruple<Type, String, Integer, String> getPos(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getKey1(), triple.getKey2(), triple.getKey3(), "unary");
    }

    private Boolean isSub() {
        if (last == null)
            return false;

        Type type = last.getKey1();
        return type.equals(Type.NUM) || type.equals(Type.RIGHT);
    }

    private Quadruple<Type, String, Integer, String> getSub(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getKey1(), triple.getKey2(), triple.getKey3(), "binary");
    }

    private Quadruple<Type, String, Integer, String> getNeg(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getKey1(), triple.getKey2(), triple.getKey3(), "unary");
    }

    private Quadruple<Type, String, Integer, String> getDefault(Triple<Type, String, Integer> triple) {
        return new Quadruple<>(triple.getKey1(), triple.getKey2(), triple.getKey3(), "");
    }
}
