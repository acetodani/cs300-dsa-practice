import java.util.Arrays;

/**
 * Tester for MinHeap.
 *
 *     javac MinHeap.java MinHeapTester.java
 *     java MinHeapTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing MinHeap.java — you should
 * not need to edit this file.
 */
public class MinHeapTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M1
        guard("M1 testInsertSiftUp", MinHeapTester::testInsertSiftUp);
        // M2
        guard("M2 testPeekMin", MinHeapTester::testPeekMin);
        guard("M2 testPeekEmptyThrows", MinHeapTester::testPeekEmptyThrows);
        // M3
        guard("M3 testExtractMinAscending", MinHeapTester::testExtractMinAscending);
        guard("M3 testExtractMinRandomOrder", MinHeapTester::testExtractMinRandomOrder);
        guard("M3 testExtractEmptyThrows", MinHeapTester::testExtractEmptyThrows);
        guard("M3 testInterleaved", MinHeapTester::testInterleaved);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M1..M3). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now build HeapProblems.java (M4, M5).");
        }
    }

    // ---- M1 ----
    private static void testInsertSiftUp() {
        MinHeap h = new MinHeap();
        h.insert(5);
        h.insert(3);
        h.insert(8);
        h.insert(1);
        checkInt("M1 testInsert size", 4, h.size());
        checkBool("M1 testInsert not empty", false, h.isEmpty());
        // After these inserts the smallest (1) must have bubbled to the root.
        checkInt("M1 testInsert root is min (1 sifted up)", 1, h.peekMin());
    }

    // ---- M2 ----
    private static void testPeekMin() {
        MinHeap h = new MinHeap();
        h.insert(42);
        checkInt("M2 testPeekMin single", 42, h.peekMin());
        h.insert(7);
        checkInt("M2 testPeekMin smaller arrives", 7, h.peekMin());
        // peek must NOT remove anything.
        checkInt("M2 testPeekMin does not remove", 2, h.size());
    }

    private static void testPeekEmptyThrows() {
        MinHeap h = new MinHeap();
        boolean threw = false;
        try { h.peekMin(); }
        catch (java.util.NoSuchElementException e) { threw = true; }
        checkBool("M2 testPeekMin empty throws NoSuchElementException", true, threw);
    }

    // ---- M3 ----
    private static void testExtractMinAscending() {
        // Insert in a deliberately jumbled order, then drain with extractMin.
        MinHeap h = new MinHeap();
        int[] in = {5, 3, 8, 1, 9, 2, 7, 4, 6, 0};
        for (int x : in) h.insert(x);
        int[] out = new int[in.length];
        for (int i = 0; i < in.length; i++) out[i] = h.extractMin();
        // A correct heap returns values in ascending order regardless of insert order.
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        checkArr("M3 testExtractMin drains in ascending order", expected, out);
        checkBool("M3 testExtractMin empties the heap", true, h.isEmpty());
    }

    private static void testExtractMinRandomOrder() {
        // Same idea with another random-ish permutation and duplicates.
        MinHeap h = new MinHeap();
        int[] in = {20, 20, 5, 17, 5, 1, 99, 3, 3, 50};
        for (int x : in) h.insert(x);
        int[] out = new int[in.length];
        for (int i = 0; i < in.length; i++) out[i] = h.extractMin();
        int[] expected = in.clone();
        Arrays.sort(expected); // ascending, duplicates kept
        checkArr("M3 testExtractMin handles duplicates, ascending", expected, out);
    }

    private static void testExtractEmptyThrows() {
        MinHeap h = new MinHeap();
        boolean threw = false;
        try { h.extractMin(); }
        catch (java.util.NoSuchElementException e) { threw = true; }
        checkBool("M3 testExtractMin empty throws NoSuchElementException", true, threw);
    }

    private static void testInterleaved() {
        // Mix inserts and extracts; the min should always be correct.
        MinHeap h = new MinHeap();
        h.insert(10);
        h.insert(4);
        checkInt("M3 testInterleaved peek after two inserts", 4, h.peekMin());
        checkInt("M3 testInterleaved extract returns 4", 4, h.extractMin());
        checkInt("M3 testInterleaved peek now 10", 10, h.peekMin());
        h.insert(2);
        h.insert(15);
        checkInt("M3 testInterleaved extract returns 2", 2, h.extractMin());
        checkInt("M3 testInterleaved extract returns 10", 10, h.extractMin());
        checkInt("M3 testInterleaved size", 1, h.size());
    }

    // ---- helpers ----
    private static void checkInt(String name, int expected, int actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkBool(String name, boolean expected, boolean actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkArr(String name, int[] expected, int[] actual) {
        record(name, Arrays.equals(expected, actual),
            Arrays.toString(expected), Arrays.toString(actual));
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
