import java.util.ArrayList;

public class StackT<T> {
    private static final int DEFSIZE = 16;
    private ArrayList<T> array;
    private int head;
    public StackT() {
        array = new ArrayList<T>(DEFSIZE);
        head = 0;
    }
    public final void push(T val) {
        array.set(head++, val);
    }
    public final T pop() {
        return array.get(--head);
    }
    public final T top() {
        return array.get(head-1);
    }
}