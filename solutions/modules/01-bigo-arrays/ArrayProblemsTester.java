/**
 * Tester for ArrayProblems.
 *
 *     javac ArrayProblems.java ArrayProblemsTester.java
 *     java ArrayProblemsTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * You do not need to edit this file — make the tests pass by editing
 * ArrayProblems.java.
 *
 * Note on arrays: int[] values are compared by printing them with
 * java.util.Arrays.toString(...), so "expected"/"got" read like [1, 2, 3].
 */
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayProblemsTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns a placeholder and crashes), it's reported as one FAIL and
        // the rest still run — so you always get a summary instead of a stack trace.
        guard("testSum", ArrayProblemsTester::testSum);
        guard("testMax", ArrayProblemsTester::testMax);
        guard("testReverseInPlace", ArrayProblemsTester::testReverseInPlace);
        guard("testContains", ArrayProblemsTester::testContains);
        guard("testSecondLargest", ArrayProblemsTester::testSecondLargest);
        guard("testRemoveDuplicatesSorted", ArrayProblemsTester::testRemoveDuplicatesSorted);
        guard("testRotateLeft", ArrayProblemsTester::testRotateLeft);
        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md Step 2 and the Hints section.");
        } else {
            System.out.println("All green! Now try the LeetCode bridge in README.md Step 4.");
        }
    }

    // ---- Drill 1: sum ----
    private static void testSum() {
        record("sum (empty -> 0)", 0, ArrayProblems.sum(new int[]{}));
        record("sum (1,2,3,4 -> 10)", 10, ArrayProblems.sum(new int[]{1, 2, 3, 4}));
        record("sum (with negatives)", 4, ArrayProblems.sum(new int[]{-3, 2, 5}));
        record("sum (single element)", 42, ArrayProblems.sum(new int[]{42}));
    }

    // ---- Drill 2: max ----
    private static void testMax() {
        record("max (positives)", 9, ArrayProblems.max(new int[]{3, 9, 1, 7}));
        record("max (all negatives)", -2, ArrayProblems.max(new int[]{-5, -2, -9}));
        record("max (single element)", 8, ArrayProblems.max(new int[]{8}));
        recordThrows("max (empty -> NoSuchElementException)",
                () -> ArrayProblems.max(new int[]{}));
    }

    // ---- Drill 3: reverseInPlace ----
    private static void testReverseInPlace() {
        int[] even = {1, 2, 3, 4};
        ArrayProblems.reverseInPlace(even);
        recordArray("reverseInPlace (even length)", new int[]{4, 3, 2, 1}, even);

        int[] odd = {1, 2, 3, 4, 5};
        ArrayProblems.reverseInPlace(odd);
        recordArray("reverseInPlace (odd length)", new int[]{5, 4, 3, 2, 1}, odd);

        int[] one = {7};
        ArrayProblems.reverseInPlace(one);
        recordArray("reverseInPlace (single element)", new int[]{7}, one);

        int[] empty = {};
        ArrayProblems.reverseInPlace(empty);
        recordArray("reverseInPlace (empty)", new int[]{}, empty);
    }

    // ---- Drill 4: contains ----
    private static void testContains() {
        record("contains (present)", true, ArrayProblems.contains(new int[]{4, 8, 15, 16}, 15));
        record("contains (absent)", false, ArrayProblems.contains(new int[]{4, 8, 15, 16}, 23));
        record("contains (empty array)", false, ArrayProblems.contains(new int[]{}, 1));
        record("contains (first element)", true, ArrayProblems.contains(new int[]{5, 6, 7}, 5));
    }

    // ---- Drill 5: secondLargest ----
    private static void testSecondLargest() {
        record("secondLargest (distinct values)", 7, ArrayProblems.secondLargest(new int[]{5, 9, 1, 7}));
        record("secondLargest (dups of the max)", 7, ArrayProblems.secondLargest(new int[]{5, 9, 9, 7}));
        record("secondLargest (5,5,3 -> 3)", 3, ArrayProblems.secondLargest(new int[]{5, 5, 3}));
        record("secondLargest (negatives)", -5, ArrayProblems.secondLargest(new int[]{-2, -5, -2}));
        recordThrows("secondLargest (all same -> NoSuchElementException)",
                () -> ArrayProblems.secondLargest(new int[]{4, 4, 4}));
        recordThrows("secondLargest (single element -> NoSuchElementException)",
                () -> ArrayProblems.secondLargest(new int[]{1}));
        recordThrows("secondLargest (empty -> NoSuchElementException)",
                () -> ArrayProblems.secondLargest(new int[]{}));
    }

    // ---- Drill 6: removeDuplicatesSorted ----
    private static void testRemoveDuplicatesSorted() {
        recordArray("removeDuplicatesSorted (some dups)", new int[]{1, 2, 3, 5},
                ArrayProblems.removeDuplicatesSorted(new int[]{1, 1, 2, 3, 3, 3, 5}));
        recordArray("removeDuplicatesSorted (no dups)", new int[]{1, 2, 3},
                ArrayProblems.removeDuplicatesSorted(new int[]{1, 2, 3}));
        recordArray("removeDuplicatesSorted (all same)", new int[]{4},
                ArrayProblems.removeDuplicatesSorted(new int[]{4, 4, 4, 4}));
        recordArray("removeDuplicatesSorted (empty)", new int[]{},
                ArrayProblems.removeDuplicatesSorted(new int[]{}));
    }

    // ---- Drill 7: rotateLeft ----
    private static void testRotateLeft() {
        recordArray("rotateLeft (by 2)", new int[]{3, 4, 5, 1, 2},
                ArrayProblems.rotateLeft(new int[]{1, 2, 3, 4, 5}, 2));
        recordArray("rotateLeft (k == 0, unchanged)", new int[]{1, 2, 3},
                ArrayProblems.rotateLeft(new int[]{1, 2, 3}, 0));
        recordArray("rotateLeft (k == length, unchanged)", new int[]{1, 2, 3},
                ArrayProblems.rotateLeft(new int[]{1, 2, 3}, 3));
        recordArray("rotateLeft (k > length, uses modulo)", new int[]{3, 4, 5, 1, 2},
                ArrayProblems.rotateLeft(new int[]{1, 2, 3, 4, 5}, 7));
        recordArray("rotateLeft (empty)", new int[]{},
                ArrayProblems.rotateLeft(new int[]{}, 3));
    }

    // ---- helpers (template-style PASS/FAIL with expected/got) ----

    /** Record an int result. */
    private static void record(String name, int expected, int actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /** Record a boolean result. */
    private static void record(String name, boolean expected, boolean actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /** Record an int[] result, compared via Arrays.toString. */
    private static void recordArray(String name, int[] expected, int[] actual) {
        total++;
        if (Arrays.equals(expected, actual)) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + Arrays.toString(expected)
                    + ", got " + Arrays.toString(actual) + ")");
        }
    }

    /** Record a test that should throw NoSuchElementException. */
    private static void recordThrows(String name, Runnable action) {
        total++;
        try {
            action.run();
            System.out.println(name + ": FAIL (expected NoSuchElementException, got no exception)");
        } catch (NoSuchElementException expected) {
            passed++;
            System.out.println(name + ": PASS");
        } catch (Exception other) {
            System.out.println(name + ": FAIL (expected NoSuchElementException, got "
                    + other.getClass().getSimpleName() + ")");
        }
    }

    /**
     * Run one group of checks; if it throws (e.g. an unfinished method returned
     * a placeholder and crashed), report it as a single FAIL instead of letting a
     * stack trace abort the whole run.
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
