/**
 * Tester for DoublyLinkedList (Milestone M5).
 *
 *     javac DoublyLinkedList.java DoublyLinkedListTester.java
 *     java DoublyLinkedListTester
 */
public class DoublyLinkedListTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if an unfinished method throws, it's
        // reported as one FAIL and the rest still run, so you always get a summary.
        guard("M5 testAddFirst", DoublyLinkedListTester::testAddFirst);
        guard("M5 testAddLast", DoublyLinkedListTester::testAddLast);
        guard("M5 testPrevLinks", DoublyLinkedListTester::testPrevLinks);
        guard("M5 testRemoveFirst", DoublyLinkedListTester::testRemoveFirst);
        guard("M5 testRemoveLast", DoublyLinkedListTester::testRemoveLast);
        guard("M5 testEmptyAgain", DoublyLinkedListTester::testEmptyAgain);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Tip: when a test fails, check whether you updated BOTH "
                + "the next AND prev links (and head/tail).");
        }
    }

    private static void testAddFirst() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        checkStr("M5 testAddFirst", "[1, 2, 3]", list.toString());
        checkInt("M5 testAddFirst size", 3, list.size());
    }

    private static void testAddLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        checkStr("M5 testAddLast", "[1, 2, 3]", list.toString());
    }

    private static void testPrevLinks() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        checkStr("M5 testPrevLinks (tail->head)", "[3, 2, 1]", list.toStringReverse());
    }

    private static void testRemoveFirst() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1); list.addLast(2); list.addLast(3);
        checkInt("M5 testRemoveFirst returns", 1, list.removeFirst());
        checkStr("M5 testRemoveFirst remaining", "[2, 3]", list.toString());
        checkStr("M5 testRemoveFirst prev intact", "[3, 2]", list.toStringReverse());
    }

    private static void testRemoveLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1); list.addLast(2); list.addLast(3);
        checkInt("M5 testRemoveLast returns", 3, list.removeLast());
        checkStr("M5 testRemoveLast remaining", "[1, 2]", list.toString());
        checkStr("M5 testRemoveLast prev intact", "[2, 1]", list.toStringReverse());
    }

    private static void testEmptyAgain() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(5);
        list.removeFirst();
        checkBool("M5 testEmptyAgain isEmpty", true, list.isEmpty());
        list.addLast(7); // must still work after emptying
        checkStr("M5 testEmptyAgain reuse", "[7]", list.toString());
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
