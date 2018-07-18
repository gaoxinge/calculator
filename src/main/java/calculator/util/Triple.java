package calculator.util;

public class Triple<T1, T2, T3> {
    private T1 key1;
    private T2 key2;
    private T3 key3;

    public Triple() {}

    public Triple(T1 key1, T2 key2, T3 key3) {
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
    }

    public T1 getKey1() { return key1; }
    public T2 getKey2() { return key2; }
    public T3 getKey3() { return key3; }

    public void setKey1(T1 key1) { this.key1 = key1; }
    public void setKey2(T2 key2) { this.key2 = key2; }
    public void setKey3(T3 key3) { this.key3 = key3; }

    @Override
    public String toString() {
        return String.format("Triple( key1 = %s, key2 = %s, key3 = %s )", key1, key2, key3);
    }
}
