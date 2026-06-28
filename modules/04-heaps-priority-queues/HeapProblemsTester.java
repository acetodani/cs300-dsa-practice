import java.util.Arrays;

/**
 * Tester for HeapProblems.
 *
 *     javac MinHeap.java HeapProblems.java HeapProblemsTester.java
 *     java HeapProblemsTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing HeapProblems.java — you
 * should not need to edit this file.
 */
public class HeapProblemsTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M4
        guard("M4 testKthSmallest", HeapProblemsTester::testKthSmallest);
        // M5
        guard("M5 testTopKLargest", HeapProblemsTester::testTopKLargest);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M4..M5). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now try the LeetCode problems in README.md Step 4.");
        }
    }

    // ---- M4 ----
    private static void testKthSmallest() {
        int[] a = {7, 10, 4, 3, 20, 15};
        // Sorted ascending: 3, 4, 7, 10, 15, 20
        checkInt("M4 testKthSmallest k=1 (smallest)", 3, HeapProblems.kthSmallest(a, 1));
        checkInt("M4 testKthSmallest k=3", 7, HeapProblems.kthSmallest(a, 3));
        checkInt("M4 testKthSmallest k=6 (largest)", 20, HeapProblems.kthSmallest(a, 6));

        int[] dup = {5, 5, 5, 2, 8};
        // Sorted ascending: 2, 5, 5, 5, 8
        checkInt("M4 testKthSmallest with duplicates k=2", 5, HeapProblems.kthSmallest(dup, 2));
    }

    // ---- M5 ----
    private static void testTopKLargest() {
        int[] a = {3, 1, 5, 12, 2, 11};
        // The 3 largest are 5, 11, 12 — returned ascending.
        checkArr("M5 testTopKLargest k=3", new int[]{5, 11, 12}, HeapProblems.topKLargest(a, 3));

        // k == 1 returns just the maximum, in a length-1 array.
        checkArr("M5 testTopKLargest k=1", new int[]{12}, HeapProblems.topKLargest(a, 1));

        // k == array length returns the whole array, sorted ascending.
        checkArr("M5 testTopKLargest k=all", new int[]{1, 2, 3, 5, 11, 12},
            HeapProblems.topKLargest(a, a.length));

        int[] dup = {4, 4, 4, 1, 9};
        // The 3 largest (with duplicates) are 4, 4, 9 — ascending.
        checkArr("M5 testTopKLargest with duplicates k=3",
            new int[]{4, 4, 9}, HeapProblems.topKLargest(dup, 3));
    }

    // ---- helpers ----
    private static void checkInt(String name, int expected, int actual) {
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
