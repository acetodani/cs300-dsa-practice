import java.util.NoSuchElementException;

/**
 * Tester for ArrayStack.
 *
 *     javac ArrayStack.java ArrayStackTester.java
 *     java ArrayStackTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing ArrayStack.java — you
 * should not need to edit this file.
 */
public class ArrayStackTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M1 — push / pop / peek
        guard("M1 testSizeAndEmpty", ArrayStackTester::testSizeAndEmpty);
        guard("M1 testPushAndPeek", ArrayStackTester::testPushAndPeek);
        guard("M1 testPopOrder", ArrayStackTester::testPopOrder);
        guard("M1 testPeekDoesNotRemove", ArrayStackTester::testPeekDoesNotRemove);
        guard("M1 testPopEmptyThrows", ArrayStackTester::testPopEmptyThrows);
        guard("M1 testPeekEmptyThrows", ArrayStackTester::testPeekEmptyThrows);
        // M2 — auto-resize
        guard("M2 testResizeGrowsCapacity", ArrayStackTester::testResizeGrowsCapacity);
        guard("M2 testResizeKeepsContents", ArrayStackTester::testResizeKeepsContents);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M1..M2). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now build StackProblems.java (M3..M5).");
        }
    }

    // ---- M1 ----
    private static void testSizeAndEmpty() {
        ArrayStack<Integer> s = new ArrayStack<>();
        checkBool("M1 testIsEmpty (new stack)", true, s.isEmpty());
        checkInt("M1 testSize (new stack)", 0, s.size());
        s.push(42);
        checkBool("M1 testIsEmpty (after push)", false, s.isEmpty());
        checkInt("M1 testSize (after push)", 1, s.size());
    }

    private static void testPushAndPeek() {
        ArrayStack<String> s = new ArrayStack<>();
        s.push("a");
        s.push("b");
        s.push("c");
        checkStr("M1 testPeek returns top", "c", s.peek());
        checkInt("M1 testPush size", 3, s.size());
    }

    private static void testPopOrder() {
        ArrayStack<Integer> s = new ArrayStack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        checkInt("M1 testPop returns top first", 3, s.pop());
        checkInt("M1 testPop returns next", 2, s.pop());
        checkInt("M1 testPop returns last", 1, s.pop());
        checkBool("M1 testPop empties stack", true, s.isEmpty());
    }

    private static void testPeekDoesNotRemove() {
        ArrayStack<Integer> s = new ArrayStack<>();
        s.push(7);
        s.peek();
        checkInt("M1 testPeek does not change size", 1, s.size());
        checkInt("M1 testPeek again same value", 7, s.peek());
    }

    private static void testPopEmptyThrows() {
        ArrayStack<Integer> s = new ArrayStack<>();
        boolean threw = false;
        try { s.pop(); } catch (NoSuchElementException e) { threw = true; }
        checkBool("M1 testPop on empty throws NoSuchElementException", true, threw);
    }

    private static void testPeekEmptyThrows() {
        ArrayStack<Integer> s = new ArrayStack<>();
        boolean threw = false;
        try { s.peek(); } catch (NoSuchElementException e) { threw = true; }
        checkBool("M1 testPeek on empty throws NoSuchElementException", true, threw);
    }

    // ---- M2 ----
    private static void testResizeGrowsCapacity() {
        // DEFAULT_CAPACITY is 4. Push more than 4 to force at least one resize.
        ArrayStack<Integer> s = new ArrayStack<>();
        int startCapacity = s.capacityInternal();
        for (int i = 0; i < 10; i++) s.push(i);
        checkInt("M2 testResize size after 10 pushes", 10, s.size());
        checkBool("M2 testResize capacity grew past start",
            true, s.capacityInternal() > startCapacity);
        checkBool("M2 testResize capacity holds all elements",
            true, s.capacityInternal() >= 10);
    }

    private static void testResizeKeepsContents() {
        // After resizing, the stack must still pop in correct LIFO order.
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; i++) s.push(i); // pushes 0..9
        boolean orderOk = true;
        for (int expected = 9; expected >= 0; expected--) {
            if (s.pop() != expected) { orderOk = false; break; }
        }
        checkBool("M2 testResize preserves LIFO order", true, orderOk);
        checkBool("M2 testResize empty at end", true, s.isEmpty());
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
