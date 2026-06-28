/**
 * Tester for LinkedQueue (Milestone M3).
 *
 *     javac LinkedQueue.java LinkedQueueTester.java
 *     java LinkedQueueTester
 *
 * Each test prints PASS or FAIL (expected X, got Y). Make the tests pass by
 * editing LinkedQueue.java — you should not need to edit this file.
 */
public class LinkedQueueTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        guard("M3 testSizeAndEmpty", LinkedQueueTester::testSizeAndEmpty);
        guard("M3 testEnqueuePeek", LinkedQueueTester::testEnqueuePeek);
        guard("M3 testDequeueOrder", LinkedQueueTester::testDequeueOrder);
        guard("M3 testDequeueEmptyThrows", LinkedQueueTester::testDequeueEmptyThrows);
        guard("M3 testEmptyThenReuse", LinkedQueueTester::testEmptyThenReuse);
        guard("M3 testInterleavedStress", LinkedQueueTester::testInterleavedStress);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Tip: when a test fails, check whether dequeuing the LAST element "
                + "left tail dangling — emptying the queue must clear BOTH head and tail.");
        } else {
            System.out.println("All green! Now try LeetCode #232 and #225 in README.md Step 4.");
        }
    }

    private static void testSizeAndEmpty() {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        checkBool("M3 testIsEmpty (new queue)", true, q.isEmpty());
        checkInt("M3 testSize (new queue)", 0, q.size());
        q.enqueue(1);
        checkBool("M3 testIsEmpty (after enqueue)", false, q.isEmpty());
        checkInt("M3 testSize (after enqueue)", 1, q.size());
    }

    private static void testEnqueuePeek() {
        LinkedQueue<String> q = new LinkedQueue<>();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        checkStr("M3 testEnqueue order", "[a, b, c]", q.toString());
        checkStr("M3 testPeek (front stays)", "a", q.peek());
        checkInt("M3 testPeek does not remove", 3, q.size());
    }

    private static void testDequeueOrder() {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        checkInt("M3 testDequeue first", 1, q.dequeue());
        checkInt("M3 testDequeue second", 2, q.dequeue());
        checkStr("M3 testDequeue remaining", "[3]", q.toString());
        checkInt("M3 testDequeue size", 1, q.size());
    }

    private static void testDequeueEmptyThrows() {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        boolean threw = false;
        try { q.dequeue(); } catch (java.util.NoSuchElementException e) { threw = true; }
        checkBool("M3 testDequeue empty throws", true, threw);
        boolean peekThrew = false;
        try { q.peek(); } catch (java.util.NoSuchElementException e) { peekThrew = true; }
        checkBool("M3 testPeek empty throws", true, peekThrew);
    }

    private static void testEmptyThenReuse() {
        // Dequeue the only element, then enqueue again. If tail wasn't cleared
        // when the queue emptied, this reuse will be wrong.
        LinkedQueue<Integer> q = new LinkedQueue<>();
        q.enqueue(5);
        checkInt("M3 testEmptyThenReuse dequeue", 5, q.dequeue());
        checkBool("M3 testEmptyThenReuse isEmpty", true, q.isEmpty());
        q.enqueue(7);
        q.enqueue(8);
        checkStr("M3 testEmptyThenReuse reuse order", "[7, 8]", q.toString());
        checkInt("M3 testEmptyThenReuse reuse peek", 7, q.peek());
    }

    private static void testInterleavedStress() {
        // Drain the queue completely several times, refilling between drains, to
        // catch a dangling tail bug. Final drain must be strict FIFO.
        LinkedQueue<Integer> q = new LinkedQueue<>();
        for (int i = 0; i < 20; i++) q.enqueue(i);
        for (int i = 0; i < 10; i++) q.dequeue();        // remove 0..9
        for (int i = 20; i < 30; i++) q.enqueue(i);      // add 20..29
        // Queue now holds 10..19 then 20..29 == 10..29 in order.
        boolean ordered = true;
        int expected = 10;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            if (v != expected) ordered = false;
            expected++;
        }
        checkBool("M3 testInterleaved stays FIFO", true, ordered);
        checkInt("M3 testInterleaved drained all", 30, expected);
    }

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
        if (ok) { passed++; System.out.println(name + ": PASS"); }
        else { System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")"); }
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
