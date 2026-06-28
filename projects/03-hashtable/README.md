# Project 03 — Hash Table

Build a generic hash map **and** a hash set from scratch using **separate
chaining**. By the end you will understand how a key becomes an array index, why
two keys can "collide" and how a chain fixes that, and what a load factor is and
why we resize — the machinery that makes `HashMap` and `HashSet` give you
average **O(1)** lookups.

> **How this project works:** Learn the idea first using the materials below.
> Then do the warm-up drills. Then build the real thing. Do not skip to the
> code — the readings exist so you never have to guess (or ask an AI) what
> hashing *is*. When you get stuck on a method, the **Hints** section at the
> bottom nudges you step by step.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**.

**Watch (pick one, both are great):**
- NeetCode — *Hashing / Design HashMap* (clear, interview-focused): https://www.youtube.com/watch?v=cNWsgbKwwoU
- HackerRank — *Hash Tables explained* (whiteboard walk-through of chaining): https://www.youtube.com/watch?v=shs0KM3wKv8

**Read:**
- GeeksforGeeks — *Hashing Data Structure*: https://www.geeksforgeeks.org/hashing-data-structure/
- Visualgo — *interactive hash table visualizer* (watch keys land in buckets and chain on collision): https://visualgo.net/en/hashtable
- Oracle Java docs — `java.util.HashMap` (what the standard library offers): https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashMap.html

**The four ideas you must hold in your head (the whole project is these):**
1. **Hash code.** Every Java object can produce an `int` "hash code" computed
   from its contents via `key.hashCode()`. Equal objects (per `.equals`) return
   equal hash codes. It is just a number — possibly huge, possibly negative.
2. **Bucket index = hash mod table length.** We store entries in an array of
   *buckets*. To pick a bucket we squeeze the hash code into a valid index:
   `Math.floorMod(key.hashCode(), buckets.length)`. The mod wraps any int into
   `0 .. length-1`.
3. **Collision.** Two different keys can land in the same bucket. We handle this
   with *separate chaining*: each bucket holds a small **linked list** of
   entries, and a collision just adds another link. Lookups walk that chain
   comparing keys with `.equals`.
4. **Load factor.** `load factor = size / number_of_buckets`. As it grows, chains
   get long and lookups slow toward O(n). When it passes ~0.75 we **resize**:
   make a bigger array and **rehash** every entry into it.

**Why `Math.floorMod` and not `%`?** Java's `%` can return a *negative* result
when the hash code is negative (e.g. `-7 % 4 == -3`), and a negative array index
throws `ArrayIndexOutOfBoundsException`. `Math.floorMod` always returns a value
with the sign of the divisor (your positive table length), so the index is
always in range. Use it everywhere you turn a key into a bucket.

**You should be able to answer these before moving on:**
1. What is a *hash code*, and what does `Math.floorMod(hash, length)` give you?
2. What is a *collision*, and how does separate chaining let two colliding keys
   coexist in the same bucket?
3. Why do we use `Math.floorMod` instead of `%` to compute the bucket index?
4. What is the *load factor*, and why do we resize when it gets too high? Why
   must we *rehash* (re-insert) rather than just copy the old array over?

---

## Step 2 — Practice (warm-up drills)

Open `HashWarmup.java`. These are tiny, guided exercises that get the hashing
arithmetic — bucket indices and collisions — into your fingers before you build
the full class. Run:

```bash
javac HashWarmup.java HashWarmupTester.java
java HashWarmupTester
```

Get all warm-up tests green before starting Step 3. They are deliberately small —
if a drill takes more than a few minutes, re-read Step 1.

---

## Step 3 — Build the project

You will implement two classes:

| File | What you build | Difficulty |
|------|----------------|------------|
| `MyHashMap.java` | A generic `MyHashMap<K, V>` (separate chaining) | Core |
| `MyHashSet.java` | A generic `MyHashSet<E>` built on the map | Stretch |

Each method is broken into numbered `// TODO` steps. Work top to bottom — the
methods are ordered so each one builds on the previous.

### Milestones (check them off as your tests pass)

- [ ] **M1 — Map basics.** `put`, `get`, `size`, `isEmpty` (insert and look up by key)
- [ ] **M2 — containsKey & remove.** membership test and chain removal
- [ ] **M3 — Collisions handled.** colliding keys (and negative-hash keys) coexist — no extra code, it falls out of M1/M2 if your chain logic is right
- [ ] **M4 — Stretch: resize/rehash.** grow the bucket array when load factor > 0.75 and re-insert every entry
- [ ] **M5 — MyHashSet.** `add` (no duplicates), `contains`, `remove`, `size`, `isEmpty`

Run your tests after every milestone:

```bash
javac MyHashMap.java MyHashMapTester.java
java MyHashMapTester
```

and once you start M5:

```bash
javac MyHashMap.java MyHashSet.java MyHashSetTester.java
java MyHashSetTester
```

Each tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a
running `N/M tests passed` summary. A failing test names the method and the
milestone, so you always know what to fix next.

> **Note on M3:** the collision tests build the map with a tiny capacity (4) on
> purpose, so Integer keys `1` and `5` both land in bucket
> `Math.floorMod(1, 4) == Math.floorMod(5, 4) == 1`. If your `put` *links onto*
> the bucket's chain (instead of overwriting the bucket), they pass for free —
> you write no special "collision" code.

---

## Step 4 — Interview / LeetCode bridge

A hash map is the single most common tool in coding interviews: it turns "search
for something" from an O(n) scan into an O(1) lookup, which often turns an
O(n²) brute force into an O(n) solution. Try these with your own `MyHashMap`
(or `java.util.HashMap`) as the key idea:

- **Two Sum** — LeetCode #1. Walk the array once; for each number `x` check a map
  for `target - x`. The map makes "have I seen the complement?" O(1), so the
  whole thing is O(n) instead of O(n²):
  https://leetcode.com/problems/two-sum/
- **Valid Anagram** — LeetCode #242. Count each character in a map (char ->
  count) and compare counts. O(n):
  https://leetcode.com/problems/valid-anagram/
- **Contains Duplicate** — LeetCode #217. Put elements in a *set*; if one is
  already present you found a duplicate. O(n):
  https://leetcode.com/problems/contains-duplicate/
- NeetCode roadmap — *Arrays & Hashing* section (start here):
  https://neetcode.io/roadmap

The pattern to internalize: **whenever you catch yourself about to write a nested
loop to "find" or "count" something, ask if a hash map can remember it instead.**
That single swap is what makes these problems O(n).

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary>I don't know how to start <code>put</code>.</summary>

First compute the bucket: `int i = bucketIndexFor(key)` (it's provided for you).
Then walk the chain at `buckets[i]` with a `current` reference. If you find an
entry whose key `.equals` your key, you're *updating*: set `current.value =
value` and return (don't touch `size`). If you reach the end (`current == null`)
the key is new: make a `new Entry<>(key, value)`, point its `next` at the
current `buckets[i]`, set `buckets[i]` to the new entry, and `size++`. Inserting
at the front of the chain is easiest.
</details>

<details>
<summary>My colliding keys overwrite each other (M3 fails).</summary>

You are probably setting `buckets[i] = new Entry<>(key, value)` *without* first
linking the new entry's `next` to the old `buckets[i]`. That throws away the
existing chain. The fix: `node.next = buckets[i]; buckets[i] = node;` — now the
old chain hangs off the new head and both keys survive. Step through it on paper
with keys 1 then 5 in a 4-bucket table.
</details>

<details>
<summary>My <code>remove</code> drops the rest of the chain.</summary>

Removing from a singly linked chain needs the node *before* the match. Keep two
references: `prev` (starts `null`) and `current` (starts at `buckets[i]`). When
`current` is the match: if `prev == null` the match is the chain head, so set
`buckets[i] = current.next`; otherwise splice it out with
`prev.next = current.next`. Then decrement `size` and return the value. Don't
forget to advance `prev = current; current = current.next` each loop.
</details>

<details>
<summary><code>resize()</code> loses entries or gives wrong indices afterward.</summary>

The order matters. (1) Save the old array. (2) Allocate the new, bigger array
and assign it to `buckets`. (3) Reset `size = 0`. (4) Walk every chain in the
*old* array and `put(entry.key, entry.value)` for each entry. Because `buckets`
now points at the bigger array, `bucketIndexFor` recomputes the *new* index for
each key — that re-computation is the "rehash". If you just copied the chains
into the same slots, keys would be in the wrong buckets and `get` would miss
them.
</details>

<details>
<summary>How is <code>MyHashSet</code> "built on" the map?</summary>

A set is just the *keys* of a map. Store a `MyHashMap<E, Object>` and map every
element to one shared dummy value (`PRESENT`). Then `contains(e)` is
`map.containsKey(e)`, `add(e)` checks `containsKey` first (to return whether it
was new) then `put`s, `remove(e)` checks `containsKey` then `map.remove`, and
`size`/`isEmpty` just delegate. That's exactly how Java's own `HashSet` works.
</details>

---

## Self-check before you call it done

- All milestone tests pass (`N/M tests passed` shows full count) for **both**
  `MyHashMapTester` and `MyHashSetTester`.
- You can explain *out loud* how a key becomes a bucket index, what a collision
  is, and why separate chaining handles it.
- You can explain why we use `Math.floorMod` instead of `%`.
- You can explain what the load factor is and why resizing requires rehashing.
- You did **not** copy a solution — compare with `solutions/projects/03-hashtable/`
  only *after* your own version passes.
