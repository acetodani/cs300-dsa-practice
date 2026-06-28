/** ArrayQueue — reference solution (generic circular array-backed queue). */
public class ArrayQueue<T> {

    private Object[] array;
    private int front;
    private int count;

    public ArrayQueue() {
        this.array = new Object[4];
        this.front = 0;
        this.count = 0;
    }

    // ---- M1 ----
    public int size() { return count; }

    public boolean isEmpty() { return count == 0; }

    public void enqueue(T value) {
        if (count == array.length) resize();
        int back = (front + count) % array.length;
        array[back] = value;
        count++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        T value = (T) array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        count--;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return (T) array[front];
    }

    // ---- M2 ----
    private void resize() {
        Object[] newArray = new Object[array.length * 2];
        for (int i = 0; i < count; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        array = newArray;
        front = 0;
    }

    public int capacity() { return array.length; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(array[(front + i) % array.length]);
            if (i < count - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
