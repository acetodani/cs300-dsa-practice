import java.util.NoSuchElementException;

/** ArrayStack — reference solution. Generic stack backed by a resizable array. */
public class ArrayStack<T> {

    private static final int DEFAULT_CAPACITY = 4;

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // ---- M1 ----
    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void push(T value) {
        if (size == data.length) resize();
        data[size] = value;
        size++;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return data[size - 1];
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException();
        T value = data[size - 1];
        data[size - 1] = null;
        size--;
        return value;
    }

    // ---- M2 ----
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = Math.max(1, data.length * 2);
        T[] bigger = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, bigger, 0, size);
        data = bigger;
    }

    // ---- provided helper for the tester ----
    int capacityInternal() {
        return data.length;
    }
}
