/** SinglyLinkedList — reference solution. */
public class SinglyLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; this.next = null; }
    }

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // ---- M1 ----
    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
        size++;
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

    // ---- M2 ----
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.value;
    }

    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (value == null ? current.value == null : value.equals(current.value)) return true;
            current = current.next;
        }
        return false;
    }

    public int indexOf(T value) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (value == null ? current.value == null : value.equals(current.value)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    // ---- M3 ----
    public T removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    public T removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (size == 1) {
            T value = head.value;
            head = null;
            size = 0;
            return value;
        }
        Node<T> current = head;
        while (current.next.next != null) current = current.next;
        T value = current.next.value;
        current.next = null;
        size--;
        return value;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        if (index == 0) return removeFirst();
        Node<T> prev = head;
        for (int i = 0; i < index - 1; i++) prev = prev.next;
        T value = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return value;
    }

    // ---- M4 ----
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // ---- M6 ----
    public T findMiddle() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    boolean hasCycleInternal() {
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
