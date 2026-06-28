import java.util.List;

/**
 * Tester for BST.
 *
 *     javac BST.java BSTTester.java
 *     java BSTTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing BST.java — you should not
 * need to edit this file.
 *
 * List results (from the traversals) are compared by their .toString(), so an
 * expected value looks like "[1, 2, 3]".
 *
 * Most tests use Integer data. testStringGenerics proves the class is generic by
 * using a BST<String> as well.
 *
 * The trees in these tests are built by inserting values in an order that
 * produces a small, predictable shape. For example inserting 5, 3, 8, 2, 4, 7, 9
 * builds: root 5; left child 3 (with children 2 and 4); right child 8 (with
 * children 7 and 9).
 */
public class BSTTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M1
        guard("M1 testSizeAndEmpty", BSTTester::testSizeAndEmpty);
        guard("M1 testInsertAndContains", BSTTester::testInsertAndContains);
        guard("M1 testInsertIgnoresDuplicates", BSTTester::testInsertIgnoresDuplicates);
        // M2
        guard("M2 testInOrderIsSorted", BSTTester::testInOrderIsSorted);
        guard("M2 testPreOrder", BSTTester::testPreOrder);
        guard("M2 testPostOrder", BSTTester::testPostOrder);
        // M3
        guard("M3 testMinMax", BSTTester::testMinMax);
        guard("M3 testHeight", BSTTester::testHeight);
        // M4 (stretch)
        guard("M4 testDeleteLeaf", BSTTester::testDeleteLeaf);
        guard("M4 testDeleteOneChild", BSTTester::testDeleteOneChild);
        guard("M4 testDeleteTwoChildren", BSTTester::testDeleteTwoChildren);
        guard("M4 testDeleteRoot", BSTTester::testDeleteRoot);
        // M5 (interview)
        guard("M5 testLevelOrder", BSTTester::testLevelOrder);
        // generics
        guard("Generics testStringGenerics", BSTTester::testStringGenerics);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M1..M5). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now try the LeetCode problems in README.md Step 4.");
        }
    }

    // ---- M1 ----
    private static void testSizeAndEmpty() {
        BST<Integer> tree = new BST<>();
        checkBool("M1 testIsEmpty (new tree)", true, tree.isEmpty());
        checkInt("M1 testSize (new tree)", 0, tree.size());
        tree.insert(5);
        checkBool("M1 testIsEmpty (after insert)", false, tree.isEmpty());
        checkInt("M1 testSize (after insert)", 1, tree.size());
    }

    private static void testInsertAndContains() {
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        checkInt("M1 testInsert size", 7, tree.size());
        checkBool("M1 testContains present (root)", true, tree.contains(5));
        checkBool("M1 testContains present (leaf)", true, tree.contains(9));
        checkBool("M1 testContains present (mid)", true, tree.contains(4));
        checkBool("M1 testContains absent", false, tree.contains(6));
        checkBool("M1 testContains absent (empty)", false, new BST<Integer>().contains(1));
    }

    private static void testInsertIgnoresDuplicates() {
        BST<Integer> tree = buildInts(5, 3, 8);
        tree.insert(5);
        tree.insert(3);
        checkInt("M1 testInsert duplicates ignored (size)", 3, tree.size());
        checkStr("M1 testInsert duplicates ignored (inOrder)", "[3, 5, 8]", str(tree.inOrder()));
    }

    // ---- M2 ----
    private static void testInOrderIsSorted() {
        // Insert deliberately out of order; in-order MUST come out sorted.
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9, 1, 6);
        checkStr("M2 testInOrder is sorted", "[1, 2, 3, 4, 5, 6, 7, 8, 9]", str(tree.inOrder()));
        checkStr("M2 testInOrder (empty)", "[]", str(new BST<Integer>().inOrder()));
    }

    private static void testPreOrder() {
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        // pre-order = node, left, right
        checkStr("M2 testPreOrder", "[5, 3, 2, 4, 8, 7, 9]", str(tree.preOrder()));
    }

    private static void testPostOrder() {
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        // post-order = left, right, node
        checkStr("M2 testPostOrder", "[2, 4, 3, 7, 9, 8, 5]", str(tree.postOrder()));
    }

    // ---- M3 ----
    private static void testMinMax() {
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        checkInt("M3 testMin", 2, tree.min());
        checkInt("M3 testMax", 9, tree.max());
        boolean threw = false;
        try { new BST<Integer>().min(); } catch (java.util.NoSuchElementException e) { threw = true; }
        checkBool("M3 testMin empty throws", true, threw);
    }

    private static void testHeight() {
        checkInt("M3 testHeight (empty)", -1, new BST<Integer>().height());
        checkInt("M3 testHeight (single)", 0, buildInts(5).height());
        // 5 -> 3 -> 2 -> 1 is a left-leaning chain: 3 edges deep.
        checkInt("M3 testHeight (left chain)", 3, buildInts(5, 3, 2, 1).height());
        // Balanced 7-node tree: 2 edges deep.
        checkInt("M3 testHeight (balanced)", 2, buildInts(5, 3, 8, 2, 4, 7, 9).height());
    }

    // ---- M4 (stretch) ----
    private static void testDeleteLeaf() {
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        tree.delete(2); // 2 is a leaf
        checkInt("M4 testDeleteLeaf size", 6, tree.size());
        checkBool("M4 testDeleteLeaf gone", false, tree.contains(2));
        checkStr("M4 testDeleteLeaf inOrder", "[3, 4, 5, 7, 8, 9]", str(tree.inOrder()));
    }

    private static void testDeleteOneChild() {
        // 6 has exactly one child (7) after this build.
        BST<Integer> tree = buildInts(5, 3, 8, 6, 7);
        tree.delete(6);
        checkInt("M4 testDeleteOneChild size", 4, tree.size());
        checkBool("M4 testDeleteOneChild gone", false, tree.contains(6));
        // 7 should now hang where 6 was; in-order stays sorted.
        checkStr("M4 testDeleteOneChild inOrder", "[3, 5, 7, 8]", str(tree.inOrder()));
        checkBool("M4 testDeleteOneChild child kept", true, tree.contains(7));
    }

    private static void testDeleteTwoChildren() {
        // Delete 3, which has two children (2 and 4). In-order successor is 4.
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        tree.delete(3);
        checkInt("M4 testDeleteTwoChildren size", 6, tree.size());
        checkBool("M4 testDeleteTwoChildren gone", false, tree.contains(3));
        checkBool("M4 testDeleteTwoChildren children kept", true, tree.contains(2) && tree.contains(4));
        checkStr("M4 testDeleteTwoChildren inOrder", "[2, 4, 5, 7, 8, 9]", str(tree.inOrder()));
    }

    private static void testDeleteRoot() {
        // Deleting the root with two children: successor is 7.
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        tree.delete(5);
        checkInt("M4 testDeleteRoot size", 6, tree.size());
        checkBool("M4 testDeleteRoot gone", false, tree.contains(5));
        checkStr("M4 testDeleteRoot inOrder still sorted", "[2, 3, 4, 7, 8, 9]", str(tree.inOrder()));
        // Deleting an absent value should be a no-op.
        tree.delete(100);
        checkInt("M4 testDelete absent is no-op", 6, tree.size());
    }

    // ---- M5 (interview) ----
    private static void testLevelOrder() {
        BST<Integer> tree = buildInts(5, 3, 8, 2, 4, 7, 9);
        // BFS: root level, then depth 1, then depth 2 (left to right).
        checkStr("M5 testLevelOrder", "[5, 3, 8, 2, 4, 7, 9]", str(tree.levelOrder()));
        checkStr("M5 testLevelOrder (empty)", "[]", str(new BST<Integer>().levelOrder()));
        checkStr("M5 testLevelOrder (single)", "[42]", str(buildInts(42).levelOrder()));
    }

    // ---- generics ----
    private static void testStringGenerics() {
        BST<String> tree = new BST<>();
        tree.insert("mango");
        tree.insert("apple");
        tree.insert("pear");
        tree.insert("banana");
        checkInt("Generics testString size", 4, tree.size());
        checkBool("Generics testString contains", true, tree.contains("banana"));
        // Strings are Comparable, so in-order comes out alphabetically sorted.
        checkStr("Generics testString inOrder sorted", "[apple, banana, mango, pear]", str(tree.inOrder()));
        checkStr("Generics testString min", "apple", tree.min());
        checkStr("Generics testString max", "pear", tree.max());
    }

    // ---- helpers ----
    private static BST<Integer> buildInts(int... values) {
        BST<Integer> tree = new BST<>();
        for (int v : values) tree.insert(v);
        return tree;
    }

    private static String str(List<?> list) {
        return String.valueOf(list);
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
