/**
 * Tester for SinglyLinkedList.
 *
 *     javac SinglyLinkedList.java SinglyLinkedListTester.java
 *     java SinglyLinkedListTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing SinglyLinkedList.java —
 * you should not need to edit this file.
 */
public class SinglyLinkedListTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        guard("M1 testSizeAndEmpty", SinglyLinkedListTester::testSizeAndEmpty);
        guard("M1 testAddFirst", SinglyLinkedListTester::testAddFirst);
        guard("M1 testAddLast", SinglyLinkedListTester::testAddLast);
        guard("M2 testGet", SinglyLinkedListTester::testGet);
        guard("M2 testContains", SinglyLinkedListTester::testContains);
        guard("M2 testIndexOf", SinglyLinkedListTester::testIndexOf);
        guard("M3 testRemoveFirst", SinglyLinkedListTester::testRemoveFirst);
        guard("M3 testRemoveLast", SinglyLinkedListTester::testRemoveLast);
        guard("M3 testRemoveIndex", SinglyLinkedListTester::testRemoveIndex);
        guard("M4 testReverse", SinglyLinkedListTester::testReverse);
        guard("M6 testFindMiddle", SinglyLinkedListTester::testFindMiddle);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M1..M6). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now try the LeetCode problems in README.md Step 4.");
        }
    }

    // ---- M1 ----
    private static void testSizeAndEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        checkBool("M1 testIsEmpty (new list)", true, list.isEmpty());
        checkInt("M1 testSize (new list)", 0, list.size());
        list.addFirst(1);
        checkBool("M1 testIsEmpty (after add)", false, list.isEmpty());
        checkInt("M1 testSize (after add)", 1, list.size());
    }

    private static void testAddFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        checkStr("M1 testAddFirst order", "[1, 2, 3]", list.toString());
        checkInt("M1 testAddFirst size", 3, list.size());
    }

    private static void testAddLast() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        checkStr("M1 testAddLast order", "[1, 2, 3]", list.toString());
        checkInt("M1 testAddLast size", 3, list.size());
    }

    // ---- M2 ----
    private static void testGet() {
        SinglyLinkedList<String> list = build("a", "b", "c");
        checkStr("M2 testGet(0)", "a", list.get(0));
        checkStr("M2 testGet(2)", "c", list.get(2));
        boolean threw = false;
        try { list.get(3); } catch (IndexOutOfBoundsException e) { threw = true; }
        checkBool("M2 testGet out-of-bounds throws", true, threw);
    }

    private static void testContains() {
        SinglyLinkedList<String> list = build("a", "b", "c");
        checkBool("M2 testContains present", true, list.contains("b"));
        checkBool("M2 testContains absent", false, list.contains("z"));
    }

    private static void testIndexOf() {
        SinglyLinkedList<String> list = build("a", "b", "c");
        checkInt("M2 testIndexOf present", 2, list.indexOf("c"));
        checkInt("M2 testIndexOf absent", -1, list.indexOf("z"));
    }

    // ---- M3 ----
    private static void testRemoveFirst() {
        SinglyLinkedList<Integer> list = buildInts(1, 2, 3);
        checkInt("M3 testRemoveFirst returns", 1, list.removeFirst());
        checkStr("M3 testRemoveFirst remaining", "[2, 3]", list.toString());
        checkInt("M3 testRemoveFirst size", 2, list.size());
    }

    private static void testRemoveLast() {
        SinglyLinkedList<Integer> list = buildInts(1, 2, 3);
        checkInt("M3 testRemoveLast returns", 3, list.removeLast());
        checkStr("M3 testRemoveLast remaining", "[1, 2]", list.toString());
        SinglyLinkedList<Integer> one = buildInts(9);
        checkInt("M3 testRemoveLast single", 9, one.removeLast());
        checkBool("M3 testRemoveLast empties", true, one.isEmpty());
    }

    private static void testRemoveIndex() {
        SinglyLinkedList<Integer> list = buildInts(10, 20, 30, 40);
        checkInt("M3 testRemove(index) returns", 30, list.remove(2));
        checkStr("M3 testRemove(index) remaining", "[10, 20, 40]", list.toString());
        checkInt("M3 testRemove(index) size", 3, list.size());
    }

    // ---- M4 ----
    private static void testReverse() {
        SinglyLinkedList<Integer> list = buildInts(1, 2, 3, 4);
        list.reverse();
        checkStr("M4 testReverse", "[4, 3, 2, 1]", list.toString());
        SinglyLinkedList<Integer> single = buildInts(1);
        single.reverse();
        checkStr("M4 testReverse single", "[1]", single.toString());
    }

    // ---- M6 ----
    private static void testFindMiddle() {
        SinglyLinkedList<Integer> odd = buildInts(1, 2, 3, 4, 5);
        checkInt("M6 testFindMiddle odd", 3, odd.findMiddle());
        SinglyLinkedList<Integer> even = buildInts(1, 2, 3, 4);
        checkInt("M6 testFindMiddle even (second middle)", 3, even.findMiddle());
    }

    // ---- helpers ----
    private static SinglyLinkedList<String> build(String... values) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        for (String v : values) list.addLast(v);
        return list;
    }

    private static SinglyLinkedList<Integer> buildInts(int... values) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int v : values) list.addLast(v);
        return list;
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
