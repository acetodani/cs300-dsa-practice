/** MyHashMap — reference solution (separate chaining). */
public class MyHashMap<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        this.buckets = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
    }

    private int bucketIndexFor(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    // ---- M1 ----
    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void put(K key, V value) {
        int i = bucketIndexFor(key);
        Entry<K, V> current = buckets[i];
        while (current != null) {
            if (key.equals(current.key)) {
                current.value = value; // update existing key
                return;
            }
            current = current.next;
        }
        Entry<K, V> node = new Entry<>(key, value);
        node.next = buckets[i];
        buckets[i] = node;
        size++;
        if (loadFactor() > MAX_LOAD_FACTOR) resize();
    }

    public V get(K key) {
        int i = bucketIndexFor(key);
        Entry<K, V> current = buckets[i];
        while (current != null) {
            if (key.equals(current.key)) return current.value;
            current = current.next;
        }
        return null;
    }

    // ---- M2 ----
    public boolean containsKey(K key) {
        int i = bucketIndexFor(key);
        Entry<K, V> current = buckets[i];
        while (current != null) {
            if (key.equals(current.key)) return true;
            current = current.next;
        }
        return false;
    }

    public V remove(K key) {
        int i = bucketIndexFor(key);
        Entry<K, V> prev = null;
        Entry<K, V> current = buckets[i];
        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    buckets[i] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    // ---- M4: resize / rehash ----
    public int capacity() { return buckets.length; }

    public double loadFactor() { return size / (double) buckets.length; }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] old = buckets;
        buckets = (Entry<K, V>[]) new Entry[old.length * 2];
        size = 0;
        for (Entry<K, V> head : old) {
            Entry<K, V> current = head;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }
}
