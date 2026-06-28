# Module 04 — Heaps & Priority Queues

Build a **min-heap** of ints from scratch, then use a heap to solve two classic
"top K" interview problems. By the end you will understand the *heap property*,
the clever trick that lets a plain array act like a binary tree, and why a heap
gives you the smallest element instantly but only spends O(log n) to keep itself
sorted on insert and remove.

> **How this module works:** Learn the idea first using the materials below.
> Then do the self-check questions. Then build the real thing. Do not skip to
> the code — the readings exist so you never have to guess (or ask an AI) what a
> heap *is*. This is the hardest module so far, so the `// TODO` steps are
> extra small and the **Hints** at the bottom walk through the two tricky
> helpers (`siftUp` and `siftDown`) line by line.

---

## What is a heap?

A **binary heap** is a *complete binary tree* that obeys the **heap-order
property**:

- **Complete binary tree:** every level is completely full except possibly the
  last, and the last level is filled left-to-right with no gaps. This "no gaps"
  rule is what makes the array trick (below) work.
- **Heap-order property (min-heap):** every parent is **less than or equal to**
  its children. The consequence: the **smallest** element in the whole heap is
  always sitting at the very top (the root). (A max-heap is the mirror image:
  every parent is *greater than or equal to* its children, so the largest sits
  on top.)

Notice the heap is **not** fully sorted — siblings can be in any order, and a
node deep on the left can be larger than a node shallow on the right. The only
promise is *parent ≤ children*. That weaker promise is exactly why a heap is
cheap to maintain.

### The array-as-binary-tree trick

We never build actual `Node` objects with child pointers. Because the tree is
*complete* (no gaps), we can store it level-by-level, left-to-right, in a plain
array. If a node lives at index `i`, then:

- its **parent** is at index `(i - 1) / 2`  (integer division)
- its **left child** is at index `2 * i + 1`
- its **right child** is at index `2 * i + 2`

The root is at index `0`. So the array `[1, 3, 2, 7, 4, 9]` represents a tree
whose root is `1`, whose children are `3` and `2`, and so on. Walk those three
formulas by hand on that array until you believe it — this trick is the single
most important idea in the whole module.

### Why the running times look the way they do

- **`peekMin` is O(1).** The smallest element is *always* at index `0`. No
  searching, no walking — just read `array[0]`.
- **`insert` is O(log n).** You add the new value at the end of the array (the
  next free leaf), then "bubble it up" (sift-up): while it is smaller than its
  parent, swap them. A complete tree of `n` nodes is only about `log2(n)` levels
  tall, so you do at most `log2(n)` swaps.
- **`extractMin` is O(log n).** You can't just delete index `0` (that would
  leave a hole at the root). Instead you move the *last* element up to the root,
  shrink the array by one, then "bubble it down" (sift-down): repeatedly swap it
  with its *smaller* child until it is in a legal spot. Again, at most about
  `log2(n)` swaps because the tree is `log2(n)` levels tall.

A `java.util.PriorityQueue` is exactly this data structure with a nicer API —
when you use it in `HeapProblems.java`, remember **it is a heap underneath**.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**.

**Watch (pick one, both are great):**
- HackerRank — *Heaps* (clear whiteboard walk-through of the heap property and
  sift operations): https://www.youtube.com/watch?v=t0Cq6tVNRBA
- NeetCode — *Heap / Priority Queue* explained: https://www.youtube.com/watch?v=dM_JHpfFITs

**Read / play with:**
- GeeksforGeeks — *Binary Heap*: https://www.geeksforgeeks.org/binary-heap/
- Visualgo — *interactive heap visualizer* (insert/extract and watch the
  bubble-up / bubble-down happen step by step): https://visualgo.net/en/heap
- Oracle Java docs — `java.util.PriorityQueue` (the standard-library heap you
  will use in Step 4 of the build):
  https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/PriorityQueue.html

**You should be able to answer these before moving on:**
1. For a node at index `i`, what are the indices of its parent, left child, and
   right child? Why is the parent formula `(i - 1) / 2` and not `i / 2`?
2. Why can a plain array store a binary tree with no child pointers at all?
   (Hint: what does the *complete* tree rule guarantee about gaps?)
3. In a min-heap, where is the smallest element? Where can the *largest* element
   be? (It must be a leaf — why?)
4. Why is `peekMin` O(1) but `extractMin` O(log n)?

---

## Step 2 — Practice (on paper)

Before you build, do this with pencil and paper (no code):

1. Start with an empty min-heap. Insert `5, 3, 8, 1, 9, 2` one at a time. After
   *each* insert, write out the array and confirm the heap-order property holds
   (every parent ≤ its children). Watch where the `1` ends up.
2. Now call `extractMin` twice on your final heap. Each time, write down which
   value comes out (it should be `1` then `2`) and redraw the array after the
   sift-down settles.
3. Use the Visualgo heap link above to check your work — type in the same
   numbers and step through the animation.

If step 1 and 2 feel shaky, re-watch the video. Getting these by hand first
makes the code almost write itself.

---

## Step 3 — Build the module

You will implement two files:

| File | What you build | Difficulty |
|------|----------------|------------|
| `MinHeap.java`      | A min-heap of ints backed by an array | Core |
| `HeapProblems.java` | Two "top K" problems using a heap     | Core + Stretch |

Each method is broken into numbered `// TODO` steps. Work top to bottom — the
methods are ordered so each one builds on the previous. The two trickiest pieces,
`siftUp` and `siftDown`, are pulled into their own private helper methods with
their own granular TODOs and `// HINT` comments.

### Milestones (check them off as your tests pass)

- [ ] **M1 — `insert` + `siftUp`.** Add at the end, then bubble up.
- [ ] **M2 — `peekMin`.** Return index 0; throw on empty.
- [ ] **M3 — `extractMin` + `siftDown`.** Swap root with last, remove, bubble down.
- [ ] **M4 — `kthSmallest`.** Use a heap to find the kth smallest value.
- [ ] **M5 — `topKLargest`.** The classic "keep a heap of size k" pattern.

Run your tests after every milestone:

```bash
javac MinHeap.java MinHeapTester.java
java MinHeapTester

javac MinHeap.java HeapProblems.java HeapProblemsTester.java
java HeapProblemsTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a running
`N/M tests passed` summary. A failing test names the method and the milestone, so
you always know what to fix next. The headline `MinHeap` test inserts numbers in
random order and then calls `extractMin` over and over — a correct heap hands
them back in **ascending** order.

---

## Step 4 — Interview / LeetCode bridge

The heap you just built **is** the interview toolkit. In an interview you would
reach for `java.util.PriorityQueue` (it is a heap), which is exactly what
`HeapProblems.java` uses. Try these on LeetCode:

- **Kth Largest Element in an Array** — LeetCode #215 (your `kthSmallest` is the
  mirror image): https://leetcode.com/problems/kth-largest-element-in-an-array/
- **Kth Largest Element in a Stream** — #703 (keep a min-heap of size k as
  numbers arrive): https://leetcode.com/problems/kth-largest-element-in-a-stream/
- **Top K Frequent Elements** — #347 (the `topKLargest` pattern, but on
  frequencies): https://leetcode.com/problems/top-k-frequent-elements/
- NeetCode roadmap — *Heap / Priority Queue* section:
  https://neetcode.io/roadmap

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary>I don't understand the parent / child index formulas.</summary>

Lay the tree out in an array level by level, left to right, starting at index 0:

```
index:  0   1   2   3   4   5   6
value: 10  20  30  40  50  60  70
       root  \__/    \__________/
            children   grandchildren
```

The root (index 0) has children at 1 and 2. Index 1 has children at 3 and 4.
Index 2 has children at 5 and 6. Stare at the pattern: a node at index `i` has
children at `2*i + 1` and `2*i + 2`. To go the other way (child → parent),
invert it: a child at index `c` has parent at `(c - 1) / 2` using integer
division. Try it: child `4` → `(4 - 1) / 2 = 1` (correct), child `5` →
`(5 - 1) / 2 = 2` (correct). It is `(i - 1) / 2`, **not** `i / 2`, because of how
the `+1`/`+2` offsets land — check `i = 2`: `(2-1)/2 = 0` (right), but `2/2 = 1`
(wrong).
</details>

<details>
<summary>How do I write <code>siftUp</code>?</summary>

`siftUp` fixes the heap *after* you add a new value at the end. The new value
might be smaller than its parent, which would break the heap-order property, so
you let it "bubble up" toward the root.

Let `i` be the index of the value you just added (the last index). Loop while
`i > 0` (i.e. you are not yet at the root):
1. Compute `parent = (i - 1) / 2`.
2. If `heap[i] >= heap[parent]`, the value is in a legal spot — **stop** (break
   out of the loop).
3. Otherwise `heap[i] < heap[parent]`, which is illegal in a min-heap, so swap
   `heap[i]` and `heap[parent]`, then set `i = parent` and loop again.

You stop either when you hit the root (`i == 0`) or when the parent is already
smaller-or-equal. At most `log2(n)` iterations because each step climbs one level.
</details>

<details>
<summary>How do I write <code>siftDown</code>?</summary>

`siftDown` fixes the heap *after* you move the last element up to the root in
`extractMin`. That moved value is probably too big for the root, so you let it
"sink down" by repeatedly swapping it with its **smaller** child (swapping with
the smaller child keeps *both* children ≤ parent afterward).

Let `i` be the index you are sinking (starts at 0). Loop:
1. Compute `left = 2*i + 1` and `right = 2*i + 2`.
2. Assume the smallest is `i` itself: `int smallest = i;`
3. If `left < size && heap[left] < heap[smallest]`, set `smallest = left`.
4. If `right < size && heap[right] < heap[smallest]`, set `smallest = right`.
   (The `< size` checks are vital — a node may have zero, one, or two children.)
5. If `smallest == i`, the value is already ≤ both children — **stop**.
6. Otherwise swap `heap[i]` and `heap[smallest]`, set `i = smallest`, and loop.

At most `log2(n)` iterations because each step descends one level.
</details>

<details>
<summary>My <code>extractMin</code> returns the right value but the heap is corrupt.</summary>

The order of operations matters. Read `heap[0]` (the value to return) into a
local variable **first**. Then move the *last* element into index 0, shrink the
size by one (so the old last slot is no longer part of the heap), and only *then*
call `siftDown` from index 0. If you sift before shrinking, you might swap the
value back into the slot you are trying to drop. Special-case `size == 1`: after
reading the value, just shrink to 0 — there is nothing to sift.
</details>

<details>
<summary><code>topKLargest</code>: why a min-heap of size k, and why does it stay small?</summary>

To keep the **k largest** values, walk the array and push each value into a
min-heap. Whenever the heap grows past size `k`, call `poll()` (remove the
smallest). The smallest of your "current best k" is exactly what you want to
kick out when a bigger candidate shows up. After the whole pass, the heap holds
the k largest values — pour them into an array and sort it ascending. (A
`java.util.PriorityQueue` is a min-heap by default, which is why this works.)
</details>

---

## Self-check before you call it done

- All milestone tests pass (`N/M tests passed` shows the full count in both
  testers).
- You can recite the parent / left / right index formulas from memory.
- You can explain *out loud* why `peekMin` is O(1) but `insert` and `extractMin`
  are O(log n).
- You can explain why a *complete* tree is what lets an array stand in for a tree.
- You did **not** copy a solution — compare with
  `solutions/modules/04-heaps-priority-queues/` only *after* your own version
  passes.
</content>
</invoke>
