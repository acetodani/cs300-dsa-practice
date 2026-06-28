/** MyHashSet — reference solution (built on MyHashMap). */
public class MyHashSet<E> {

    private static final Object PRESENT = new Object();

    private final MyHashMap<E, Object> map;

    public MyHashSet() {
        this.map = new MyHashMap<>();
    }

    public boolean add(E element) {
        if (map.containsKey(element)) return false;
        map.put(element, PRESENT);
        return true;
    }

    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public boolean remove(E element) {
        if (!map.containsKey(element)) return false;
        map.remove(element);
        return true;
    }

    public int size() { return map.size(); }

    public boolean isEmpty() { return map.isEmpty(); }
}
