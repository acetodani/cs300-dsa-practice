/**
 * Tester for QueueWarmup.
 *
 *     javac QueueWarmup.java QueueWarmupTester.java
 *     java QueueWarmupTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * You do not need to edit this file — make the warm-up tests pass by editing
 * QueueWarmup.java.
 */
public class QueueWarmupTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        guard("testFifoOrder", QueueWarmupTester::testFifoOrder);
        guard("testWrapIndex", QueueWarmupTester::testWrapIndex);
        guard("testNextFreeSlot", QueueWarmupTester::testNextFreeSlot);
        guard("testFinalSize", QueueWarmupTester::testFinalSize);
        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md Step 2 and the Hints section.");
        }
    }

    private static void testFifoOrder() {
        checkArr("testFifoOrder (1,2,3)", new int[]{1, 2, 3},
            QueueWarmup.fifoOrder(new int[]{1, 2, 3}));
        checkArr("testFifoOrder (empty)", new int[]{},
            QueueWarmup.fifoOrder(new int[]{}));
    }

    private static void testWrapIndex() {
        check("testWrapIndex no wrap", 4, QueueWarmup.wrapIndex(1, 3, 5));
        check("testWrapIndex wraps", 2, QueueWarmup.wrapIndex(3, 4, 5));
    }

    private static void testNextFreeSlot() {
        check("testNextFreeSlot no wrap", 3, QueueWarmup.nextFreeSlot(0, 3, 5));
        check("testNextFreeSlot wraps", 1, QueueWarmup.nextFreeSlot(3, 3, 5));
    }

    private static void testFinalSize() {
        check("testFinalSize (5 in, 2 out)", 3, QueueWarmup.finalSize(5, 2));
        check("testFinalSize (4 in, 4 out)", 0, QueueWarmup.finalSize(4, 4));
    }

    private static void check(String name, int expected, int actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    private static void checkArr(String name, int[] expected, int[] actual) {
        total++;
        if (arrEquals(expected, actual)) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + show(expected) + ", got " + show(actual) + ")");
        }
    }

    private static boolean arrEquals(int[] a, int[] b) {
        if (a == null || b == null) return a == b;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private static String show(int[] a) {
        if (a == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
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
