/**
 * Tester for ArrayQueue.
 *
 *     javac ArrayQueue.java ArrayQueueTester.java
 *     java ArrayQueueTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing ArrayQueue.java — you
 * should not need to edit this file.
 */
public class ArrayQueueTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M1
        guard("M1 testSizeAndEmpty", ArrayQueueTester::testSizeAndEmpty);
        guard("M1 testEnqueuePeek", ArrayQueueTester::testEnqueuePeek);
        guard("M1 testDequeueOrder", ArrayQueueTester::testDequeueOrder);
        guard("M1 testDequeueEmptyThrows", ArrayQueueTester::testDequeueEmptyThrows);
        // M2
        guard("M2 testCircularWrap", ArrayQueueTester::testCircularWrap);
        guard("M2 testResizeKeepsOrder", ArrayQueueTester::testResizeKeepsOrder);
        guard("M2 testInterleavedStress", ArrayQueueTester::testInterleavedStress);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M1..M2). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now build LinkedQueue (M3), then try LeetCode #232 in README.md Step 4.");
        }
    }

    // ---- M1 ----
    private static void testSizeAndEmpty() {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        checkBool("M1 testIsEmpty (new queue)", true, q.isEmpty());
        checkInt("M1 testSize (new queue)", 0, q.size());
        q.enqueue(1);
        checkBool("M1 testIsEmpty (after enqueue)", false, q.isEmpty());
        checkInt("M1 testSize (after enqueue)", 1, q.size());
    }

    private static void testEnqueuePeek() {
        ArrayQueue<String> q = new ArrayQueue<>();
        q.enqueue("a");
        q.enqueue("b");
        checkStr("M1 testPeek (front stays)", "a", q.peek());
        checkInt("M1 testPeek does not remove", 2, q.size());
        checkStr("M1 testEnqueue order", "[a, b]", q.toString());
    }

    private static void testDequeueOrder() {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        checkInt("M1 testDequeue first", 1, q.dequeue());
        checkInt("M1 testDequeue second", 2, q.dequeue());
        checkStr("M1 testDequeue remaining", "[3]", q.toString());
        checkInt("M1 testDequeue size", 1, q.size());
    }

    private static void testDequeueEmptyThrows() {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        boolean threw = false;
        try { q.dequeue(); } catch (java.util.NoSuchElementException e) { threw = true; }
        checkBool("M1 testDequeue empty throws", true, threw);
        boolean peekThrew = false;
        try { q.peek(); } catch (java.util.NoSuchElementException e) { peekThrew = true; }
        checkBool("M1 testPeek empty throws", true, peekThrew);
    }

    // ---- M2 ----
    private static void testCircularWrap() {
        // Default capacity is 4. Fill, drain part way, then enqueue more so the
        // back index must WRAP around to the front of the array without resizing.
        ArrayQueue<Integer> q = new ArrayQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);          // count 3, capacity 4
        checkInt("M2 testWrap dequeue 1", 1, q.dequeue());
        checkInt("M2 testWrap dequeue 2", 2, q.dequeue());  // front has advanced
        q.enqueue(4);          // these slots reuse the freed front via modulo
        q.enqueue(5);          // count 3, still capacity 4 (no resize yet)
        checkInt("M2 testWrap no resize yet", 4, q.capacity());
        checkStr("M2 testWrap order preserved", "[3, 4, 5]", q.toString());
    }

    private static void testResizeKeepsOrder() {
        // Default capacity 4. Enqueue 5 to force exactly one doubling to 8.
        ArrayQueue<Integer> q = new ArrayQueue<>();
        for (int i = 1; i <= 5; i++) q.enqueue(i);
        checkInt("M2 testResize grew capacity", 8, q.capacity());
        checkInt("M2 testResize size", 5, q.size());
        checkStr("M2 testResize order intact", "[1, 2, 3, 4, 5]", q.toString());
        checkInt("M2 testResize peek front", 1, q.peek());
    }

    private static void testInterleavedStress() {
        // Heavy interleaving forces multiple wraps AND multiple resizes. The
        // queue must still come out in strict FIFO order: 0,1,2,...,29.
        ArrayQueue<Integer> q = new ArrayQueue<>();
        int next = 0;
        // Prime with a few, then alternate 2-in / 1-out so the front keeps moving.
        for (int round = 0; round < 30; round++) {
            q.enqueue(next++);
            q.enqueue(next++);
            q.dequeue(); // pulls the current front
        }
        // We enqueued 60, dequeued 30, so 30 remain. Drain and verify order.
        StringBuilder out = new StringBuilder();
        int firstOut = q.dequeue();
        out.append(firstOut);
        boolean ordered = true;
        int prev = firstOut;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            if (v != prev + 1) ordered = false;
            prev = v;
        }
        checkBool("M2 testInterleaved stays FIFO", true, ordered);
        checkInt("M2 testInterleaved last value", 59, prev);
    }

    // ---- helpers ----
    private static void checkInt(String name, int expected, int actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkBool(String name, boolean expected, boolean actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkStr(String name, String expected, String actual) {
        record(name, expected.equals(actual), expected, String.valueOf(actual));
    }

    private static void record(String name, boolean ok, String expected, String actual) {
        total++;
        if (ok) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /**
     * Run one group of checks; if it throws (e.g. an unfinished method returned
     * null and crashed), report it as a single FAIL instead of letting a stack
     * trace abort the whole run.
     */
    private static void guard(String group, Runnable test) {
        try {
            test.run();
        } catch (Throwable t) {
            total++;
            System.out.println(group + ": FAIL (threw " + t.getClass().getSimpleName()
                + " — likely an unfinished method. Keep going.)");
        }
    }
}
