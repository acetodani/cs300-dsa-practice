/**
 * QueueWarmup — tiny guided drills to get FIFO order and the wrap-around
 * (modulo) math into your fingers BEFORE you build the full queue classes.
 *
 * Each drill is a small static method. Fill in the TODOs, then run:
 *
 *     javac QueueWarmup.java QueueWarmupTester.java
 *     java QueueWarmupTester
 *
 * These are deliberately small. If one takes more than a few minutes, re-watch
 * the video linked in README.md (Step 1) — don't reach for an AI.
 */
public class QueueWarmup {

    /**
     * Drill 1 — Predict the output order.
     * A queue is FIFO: the first value enqueued is the first one dequeued.
     * You enqueue every element of `values` in order, then dequeue them all.
     * Return them in the order they come OUT of the queue.
     *
     * Because a queue preserves order, the answer is just `values` in the same
     * order — but write the loops so you SEE why. (A stack would reverse it.)
     */
    public static int[] fifoOrder(int[] values) {
        // TODO 1: make a result array the same length as values
        // TODO 2: copy each element of values into result at the SAME index
        //         (FIFO keeps order; first in is first out)
        // TODO 3: return result
        return null; // replace this
    }

    /**
     * Drill 2 — Wrap-around index.
     * In a circular array of size `capacity`, if the front is at index `front`
     * and you move forward by `steps` slots, which index do you land on?
     * The trick is modulo: it wraps you back to 0 after the last index.
     *
     * Example: capacity 5, front 3, steps 4  ->  (3 + 4) % 5 = 2.
     */
    public static int wrapIndex(int front, int steps, int capacity) {
        // TODO 1: return (front + steps) % capacity
        return -1; // replace this
    }

    /**
     * Drill 3 — Where is the back?
     * In a circular array queue you store `front` and `count` (how many
     * elements). The next FREE slot (where the next enqueue goes) is
     * (front + count) % capacity. Return that index.
     *
     * Example: capacity 5, front 3, count 3  ->  (3 + 3) % 5 = 1.
     */
    public static int nextFreeSlot(int front, int count, int capacity) {
        // TODO 1: return (front + count) % capacity
        return -1; // replace this
    }

    /**
     * Drill 4 — Net size after a sequence of operations.
     * You are given how many enqueues and how many dequeues happened (in some
     * valid interleaving that never dequeues from an empty queue). Return the
     * final number of elements left in the queue: enqueues add one, dequeues
     * remove one.
     *
     * Example: 5 enqueues, 2 dequeues  ->  3 elements remain.
     */
    public static int finalSize(int enqueues, int dequeues) {
        // TODO 1: return enqueues - dequeues
        return -1; // replace this
    }
}
