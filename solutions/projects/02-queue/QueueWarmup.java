/** QueueWarmup — reference solution. */
public class QueueWarmup {

    public static int[] fifoOrder(int[] values) {
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i];
        }
        return result;
    }

    public static int wrapIndex(int front, int steps, int capacity) {
        return (front + steps) % capacity;
    }

    public static int nextFreeSlot(int front, int count, int capacity) {
        return (front + count) % capacity;
    }

    public static int finalSize(int enqueues, int dequeues) {
        return enqueues - dequeues;
    }
}
