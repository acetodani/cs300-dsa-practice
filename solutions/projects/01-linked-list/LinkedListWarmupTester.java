/**
 * Tester for LinkedListWarmup.
 *
 *     javac LinkedListWarmup.java LinkedListWarmupTester.java
 *     java LinkedListWarmupTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * You do not need to edit this file — make the warm-up tests pass by editing
 * LinkedListWarmup.java.
 */
public class LinkedListWarmupTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each drill runs in isolation: if an unfinished method throws, it's
        // reported as one FAIL and the rest still run, so you always get a summary.
        guard("testLength", LinkedListWarmupTester::testLength);
        guard("testSum", LinkedListWarmupTester::testSum);
        guard("testLastValue", LinkedListWarmupTester::testLastValue);
        guard("testFromArray", LinkedListWarmupTester::testFromArray);
        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md Step 2 and the Hints section.");
        }
    }

    /**
     * Run one drill; if it throws (e.g. an unfinished method returned null and
     * crashed), report it as a single FAIL instead of letting a stack trace
     * abort the whole run.
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

    private static void testLength() {
        check("testLength (empty)", 0, LinkedListWarmup.length(null));
        LinkedListWarmup.Node head = LinkedListWarmup.fromArray(new int[]{5, 6, 7});
        // If fromArray isn't done yet this will be wrong; testFromArray covers that.
        check("testLength (3 nodes)", 3, LinkedListWarmup.length(build(5, 6, 7)));
    }

    private static void testSum() {
        check("testSum (empty)", 0, LinkedListWarmup.sum(null));
        check("testSum (1,2,3,4)", 10, LinkedListWarmup.sum(build(1, 2, 3, 4)));
    }

    private static void testLastValue() {
        check("testLastValue (empty)", -1, LinkedListWarmup.lastValue(null));
        check("testLastValue (9,8,7)", 7, LinkedListWarmup.lastValue(build(9, 8, 7)));
    }

    private static void testFromArray() {
        check("testFromArray (empty)", 0, LinkedListWarmup.length(LinkedListWarmup.fromArray(new int[]{})));
        LinkedListWarmup.Node head = LinkedListWarmup.fromArray(new int[]{2, 4, 6});
        check("testFromArray head value", 2, head == null ? -999 : head.value);
        check("testFromArray length", 3, LinkedListWarmup.length(head));
        check("testFromArray last value", 6, LinkedListWarmup.lastValue(head));
    }

    /** Helper: build a chain directly (independent of the student's fromArray). */
    private static LinkedListWarmup.Node build(int... values) {
        LinkedListWarmup.Node head = null;
        for (int i = values.length - 1; i >= 0; i--) {
            LinkedListWarmup.Node n = new LinkedListWarmup.Node(values[i]);
            n.next = head;
            head = n;
        }
        return head;
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
}
