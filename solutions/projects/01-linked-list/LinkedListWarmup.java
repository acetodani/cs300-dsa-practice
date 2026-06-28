/** LinkedListWarmup — reference solution. */
public class LinkedListWarmup {

    public static int length(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static int sum(Node head) {
        int total = 0;
        Node current = head;
        while (current != null) {
            total += current.value;
            current = current.next;
        }
        return total;
    }

    public static int lastValue(Node head) {
        if (head == null) return -1;
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.value;
    }

    public static Node fromArray(int[] values) {
        Node head = null;
        for (int i = values.length - 1; i >= 0; i--) {
            Node n = new Node(values[i]);
            n.next = head;
            head = n;
        }
        return head;
    }

    public static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; this.next = null; }
    }
}
