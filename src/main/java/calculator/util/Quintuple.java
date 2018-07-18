package calculator.util;

public class Quintuple<T1, T2, T3, T4, T5> {
    private T1 key1;
    private T2 key2;
    private T3 key3;
    private T4 key4;
    private T5 key5;

    public Quintuple() {}

    public Quintuple(T1 key1, T2 key2, T3 key3, T4 key4, T5 key5) {
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.key5 = key5;
    }

    public T1 getKey1() { return key1; }
    public T2 getKey2() { return key2; }
    public T3 getKey3() { return key3; }
    public T4 getKey4() { return key4; }
    public T5 getKey5() { return key5; }

    public void setKey1(T1 key1) { this.key1 = key1; }
    public void setKey2(T2 key2) { this.key2 = key2; }
    public void setKey3(T3 key3) { this.key3 = key3; }
    public void setKey4(T4 key4) { this.key4 = key4; }
    public void setKey5(T5 key5) { this.key5 = key5; }

    @Override
    public String toString() {
        return String.format("Triple( key1 = %s, key2 = %s, key3 = %s, key4 = %s, key5 = %s )", key1, key2, key3, key4, key5);
    }
}