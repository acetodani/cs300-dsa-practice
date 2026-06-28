/** LinkedQueue — reference solution (queue backed by a singly linked list). */
public class LinkedQueue<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; this.next = null; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // ---- M3 ----
    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        T value = head.value;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return head.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        return sb.append("]").toString();
    }
}
