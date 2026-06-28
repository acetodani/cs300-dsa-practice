# Project 02 — Queue

Build a generic **queue** (FIFO — first in, first out) from scratch, twice:
once backed by a **circular array** and once backed by a **singly linked list**.
By the end you will understand the wrap-around (modulo) index trick that lets an
array act like an endless conveyor belt, why a linked queue keeps a `tail`
reference, and why both `enqueue` and `dequeue` can be O(1).

> **How this project works:** Learn the idea first using the materials below.
> Then do the warm-up drills. Then build the real thing. Do not skip to the
> code — the readings exist so you never have to guess (or ask an AI) what a
> queue *is*. When you get stuck on a method, the **Hints** section at the
> bottom nudges you step by step.

A queue is a line at a coffee shop: people join at the **back** (enqueue) and
get served from the **front** (dequeue). The first person in line is the first
one served. That is the whole idea — FIFO. Compare it to a stack (LIFO), which
is a pile of plates where you take from the top.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**.

**Watch (pick one, both are great):**
- NeetCode — *Queues in 5 minutes* (clear, fast intro): https://www.youtube.com/watch?v=D6gu-_tmEpQ
- HackerRank — *Queue: A FIFO Data Structure* (whiteboard walk-through): https://www.youtube.com/watch?v=t-uG_dgcTKE

**Read:**
- GeeksforGeeks — *Queue Data Structure*: https://www.geeksforgeeks.org/queue-data-structure/
- Visualgo — *interactive list/queue visualizer* (step through enqueue/dequeue): https://visualgo.net/en/list
- Oracle Java docs — `java.util.Queue` (what the standard library offers): https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Queue.html

**You should be able to answer these before moving on:**
1. In a queue, which end do you *add* to and which end do you *remove* from?
2. What does FIFO stand for, and how is it different from a stack's LIFO?
3. In a **circular** array queue with capacity 5, the front is at index 3 and
   you have 3 elements. What indexes hold your data? (Hint: use modulo to wrap
   past the end.)
4. Why does the linked queue keep a reference to the **tail** node instead of
   walking to the end every time you `enqueue`?

---

## Step 2 — Practice (warm-up drills)

Open `QueueWarmup.java`. These are tiny, guided exercises that get FIFO order
and the wrap-around math into your fingers before you build the full classes.
Run:

```bash
javac QueueWarmup.java QueueWarmupTester.java
java QueueWarmupTester
```

Get all warm-up tests green before starting Step 3. They are deliberately small —
if a drill takes more than a few minutes, re-watch the video above.

---

## Step 3 — Build the project

You will implement two classes:

| File | What you build | Difficulty |
|------|----------------|------------|
| `ArrayQueue.java`  | A generic circular array-backed queue | Core |
| `LinkedQueue.java` | A generic linked-list-backed queue | Stretch |

Each method is broken into numbered `// TODO` steps. Work top to bottom — the
methods are ordered so each one builds on the previous.

### Milestones (check them off as your tests pass)

- [ ] **M1 — ArrayQueue: basics.** `size`, `isEmpty`, `enqueue`, `dequeue`,
      `peek` while there is still room in the array (no wrap, no resize yet).
- [ ] **M2 — ArrayQueue: circular wrap + resize.** Make `enqueue`/`dequeue`
      wrap around using modulo, and grow the array (double it) when it fills up.
- [ ] **M3 — LinkedQueue.** `enqueue` at the tail, `dequeue` at the head, both
      O(1), using `head` + `tail` references.

Run your tests after every milestone:

```bash
javac ArrayQueue.java ArrayQueueTester.java
java ArrayQueueTester

javac LinkedQueue.java LinkedQueueTester.java
java LinkedQueueTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a running
`N/M tests passed` summary. A failing test names the method and the milestone, so
you always know what to fix next.

---

## Step 4 — Interview / LeetCode bridge

A queue is one of the most-used building blocks in interviews — most notably it
is the engine behind **breadth-first search (BFS)**. Try these:

- **Implement Queue using Stacks** — LeetCode #232 (proves you understand FIFO
  from the inside out): https://leetcode.com/problems/implement-queue-using-stacks/
- **Implement Stack using Queues** — LeetCode #225 (the mirror image, good for
  cementing the FIFO-vs-LIFO distinction):
  https://leetcode.com/problems/implement-stack-using-queues/
- **BFS uses a queue.** Almost every breadth-first traversal (shortest path in a
  grid, level-order tree traversal, flood fill) puts unexplored items on a queue
  and pulls them off in FIFO order. Once your `LinkedQueue` works you have the
  exact tool these problems need.
- NeetCode roadmap — find the *Stack* / *Queue* and *Graphs (BFS)* sections:
  https://neetcode.io/roadmap

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary>I don't get the "circular" part of <code>ArrayQueue</code>.</summary>

Picture the array as a circle, not a line. You keep two ideas: `front` (the
index of the next element to dequeue) and `count` (how many elements you have).
The back of the queue is *computed*, not stored: the next free slot is
`(front + count) % capacity`. The `% capacity` is what makes the index "wrap"
from the last slot back to slot 0 instead of running off the end. Try drawing a
5-slot array and walking 3 enqueues, 2 dequeues, 3 more enqueues on paper.
</details>

<details>
<summary>How does <code>enqueue</code> find where to put the new element?</summary>

The new element goes at the first free slot, which is `(front + count) %
capacity`. Store the value there, then do `count++`. You do **not** move `front`
on an enqueue — `front` only changes when you dequeue. If `count == capacity`
before you insert, the array is full: resize first (see the next hint), then
insert.
</details>

<details>
<summary>How does <code>dequeue</code> work, and why <code>% capacity</code> again?</summary>

The element to return is at `array[front]`. Remember it, optionally null out
that slot, then advance the front: `front = (front + 1) % capacity`. The modulo
makes `front` wrap back to 0 after it passes the last index. Finally `count--`
and return the saved value. Throw `NoSuchElementException` if the queue is empty.
</details>

<details>
<summary>My <code>resize</code> scrambles the order.</summary>

When the array is full, the elements are *not* necessarily sitting at indexes
0..count-1 — they may be wrapped around. So copy them out in logical order:
for `i` from 0 to `count-1`, the element is at `array[(front + i) % capacity]`.
Write those into a new, bigger array (double the capacity) at positions
`0, 1, 2, ...`. After copying, set `front = 0`, point your array field at the
new array, and update capacity. Now slot 0 is the front again.
</details>

<details>
<summary><code>LinkedQueue.enqueue</code> works but <code>dequeue</code> breaks on the last element.</summary>

Enqueue adds a node at the tail; dequeue removes the node at the head. The trap
is the special case where you remove the *only* node: after moving `head =
head.next`, `head` becomes null and you must also set `tail = null`, otherwise
`tail` dangles at a node that is no longer in the queue and your next `enqueue`
will be wrong. Always ask: "did this operation empty the queue? then fix BOTH
head and tail."
</details>

---

## Self-check before you call it done

- All milestone tests pass (`N/M tests passed` shows full count) for **both**
  `ArrayQueueTester` and `LinkedQueueTester`.
- You can explain *out loud* why `(front + count) % capacity` finds the next free
  slot, and why a queue is FIFO while a stack is LIFO.
- You can explain why the `LinkedQueue` keeps a `tail` reference (so `enqueue` is
  O(1) instead of O(n)).
- You did **not** copy a solution — compare with `solutions/projects/02-queue/`
  only *after* your own version passes.
