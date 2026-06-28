/**
 * Tester for TreeWarmup.
 *
 *     javac TreeWarmup.java TreeWarmupTester.java
 *     java TreeWarmupTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * You do not need to edit this file — make the warm-up tests pass by editing
 * TreeWarmup.java.
 *
 * All tests run against this fixed little tree (built by hand in sample()):
 * the root is 4; its left child is 2 and its right child is 6; node 2 has
 * children 1 (left) and 3 (right); node 6 has a single right child 7.
 *
 * So: count of nodes = 6, sum of values = 4+2+6+1+3+7 = 23, and the max depth
 * (edges from the root down to the deepest leaf, e.g. 4 -> 6 -> 7) = 2.
 */
public class TreeWarmupTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        guard("testCountNodes", TreeWarmupTester::testCountNodes);
        guard("testSumValues", TreeWarmupTester::testSumValues);
        guard("testMaxDepth", TreeWarmupTester::testMaxDepth);
        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md Step 2 and the Hints section.");
        }
    }

    private static void testCountNodes() {
        check("testCountNodes (empty)", 0, TreeWarmup.countNodes(null));
        check("testCountNodes (single)", 1, TreeWarmup.countNodes(new TreeWarmup.Node(9)));
        check("testCountNodes (6-node tree)", 6, TreeWarmup.countNodes(sample()));
    }

    private static void testSumValues() {
        check("testSumValues (empty)", 0, TreeWarmup.sumValues(null));
        check("testSumValues (single)", 9, TreeWarmup.sumValues(new TreeWarmup.Node(9)));
        check("testSumValues (6-node tree)", 23, TreeWarmup.sumValues(sample()));
    }

    private static void testMaxDepth() {
        check("testMaxDepth (empty)", -1, TreeWarmup.maxDepth(null));
        check("testMaxDepth (single)", 0, TreeWarmup.maxDepth(new TreeWarmup.Node(9)));
        check("testMaxDepth (6-node tree)", 2, TreeWarmup.maxDepth(sample()));
    }

    /**
     * Build the fixed sample tree shown in the class comment, by hand, so these
     * tests do not depend on any BST code.
     */
    private static TreeWarmup.Node sample() {
        TreeWarmup.Node n4 = new TreeWarmup.Node(4);
        TreeWarmup.Node n2 = new TreeWarmup.Node(2);
        TreeWarmup.Node n6 = new TreeWarmup.Node(6);
        TreeWarmup.Node n1 = new TreeWarmup.Node(1);
        TreeWarmup.Node n3 = new TreeWarmup.Node(3);
        TreeWarmup.Node n7 = new TreeWarmup.Node(7);
        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.right = n7;
        return n4;
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
