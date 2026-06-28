# Project 04 — Binary Search Tree

Build a generic **Binary Search Tree** (`BST<T extends Comparable<T>>`) from
scratch. By the end you will be comfortable with the one idea that scares most
beginners — **recursion on a tree** — and you'll see why an in-order traversal
of a BST magically comes out *sorted*.

> **How this project works:** Learn the idea first using the materials below.
> Then do the warm-up drills (they build your recursion confidence on a tiny
> fixed tree before you touch the real class). Then build the real thing. Do not
> skip to the code — the readings exist so you never have to guess (or ask an AI)
> what a tree *is*. When you get stuck on a method, the **Hints** section at the
> bottom nudges you step by step.

This is the first project where **recursion is the main event**. Almost every
public method is a short wrapper that hands the work to a *private recursive
helper*. Watch for that pattern — once you see it once, you'll see it everywhere.

---

## Step 1 — Learn the concept first

Spend ~45–60 minutes here **before writing any code**. Trees take a beat longer
to click than lists, so don't rush this.

**Watch (pick one, both are great):**
- NeetCode — *Binary Search Trees* (clear, beginner-friendly):
  https://www.youtube.com/watch?v=Gj5qBheGOEo
- HackerRank — *Trees: Binary Search Trees* (whiteboard walk-through):
  https://www.youtube.com/watch?v=oSWTXtMglKE

**Read:**
- GeeksforGeeks — *Binary Search Tree Data Structure*:
  https://www.geeksforgeeks.org/binary-search-tree-data-structure/
- Visualgo — *interactive BST visualizer* (insert/search/delete and watch the
  tree change — spend real time here): https://visualgo.net/en/bst
- GeeksforGeeks — *Tree Traversals (In-order, Pre-order, Post-order)*:
  https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

**Some vocabulary you'll meet:**
- **Root** — the single top node; the whole tree hangs off it (like `head` in a
  list). It is `null` when the tree is empty.
- **Node** — holds a value plus two references: `left` and `right` (its children).
- **Leaf** — a node with no children (both `left` and `right` are `null`).
- **Subtree** — any node, viewed as the root of its own little tree. This is the
  key mental trick: a tree is just "a node, plus a left subtree, plus a right
  subtree". That self-similarity is *why* recursion fits trees so naturally.
- **BST property** — for every node, all values in its left subtree are smaller
  and all values in its right subtree are larger. Smaller goes left, larger goes
  right. Always.
- **Height** — the number of *edges* on the longest path from the root down to a
  leaf. We define an empty tree's height as `-1` and a single node's as `0`.

**You should be able to answer these before moving on:**
1. What two references does a tree node hold (besides its value)?
2. State the BST property in your own words. If you insert `5`, then `3`, then
   `8`, where does each one end up relative to `5`?
3. An *in-order* traversal visits left subtree, then the node, then right
   subtree. Why does that produce values in **sorted** order for a BST?
4. Why is searching a *balanced* BST roughly O(log n) but a list is O(n)?
5. What is the base case for almost every recursive tree method? (Hint: it
   involves `null`.)

---

## Step 2 — Practice (warm-up drills)

Open `TreeWarmup.java`. These are tiny, guided recursion exercises on a fixed
little tree (count the nodes, sum the values, find the max depth). They get the
"trust the recursion on the two subtrees" habit into your fingers before you
build the full class. Run:

```bash
javac TreeWarmup.java TreeWarmupTester.java
java TreeWarmupTester
```

Get all warm-up tests green before starting Step 3. Each drill is the same
shape you'll reuse in `BST.java`: handle `node == null` first, then combine the
results of the left and right recursive calls. If a drill takes more than a few
minutes, re-watch the video above — don't reach for an AI.

---

## Step 3 — Build the project

You will implement one class, `BST.java`, in milestone order. Every method is
broken into numbered `// TODO` steps, and the recursive ones come in a pair: a
short public method plus a private `...Helper` that does the recursion. Work top
to bottom — later milestones lean on earlier ones.

| Milestone | What you build | Difficulty |
|-----------|----------------|------------|
| M1 | `insert`, `size`, `isEmpty`, `contains` | Core |
| M2 | `inOrder`, `preOrder`, `postOrder` (return `List<T>`) | Core |
| M3 | `min`, `max`, `height` | Core |
| M4 | `delete` (all three cases) | **Stretch — the hard one** |
| M5 | `levelOrder` (breadth-first, uses a `Queue`) | Interview |

### Milestones (check them off as your tests pass)

- [ ] **M1 — basics.** `insert(value)` keeping the BST property (smaller left,
      larger right; ignore duplicates), plus `size()`, `isEmpty()`,
      `contains(value)`.
- [ ] **M2 — traversals.** `inOrder()`, `preOrder()`, `postOrder()`, each
      returning a `java.util.List<T>`. Confirm `inOrder()` comes out **sorted** —
      that's the "aha" moment.
- [ ] **M3 — min / max / height.** `min()` (leftmost), `max()` (rightmost),
      `height()` (empty tree = `-1`, single node = `0`).
- [ ] **M4 — Stretch: delete.** `delete(value)` handling leaf, one-child, and
      two-child cases. The two-child case uses the **in-order successor**. Read
      the big Hint below *before* you start.
- [ ] **M5 — Interview: level-order.** `levelOrder()` breadth-first traversal
      using a `java.util.Queue` (`java.util.LinkedList` as the queue).

Run your tests after every milestone:

```bash
javac BST.java BSTTester.java
java BSTTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a
running `N/M tests passed` summary. List results are compared by `.toString()`,
so an expected value looks like `[1, 2, 3]`. A failing test names the method and
the milestone, so you always know what to fix next.

**Allowed standard-library classes:** you may use `java.util.List`,
`java.util.ArrayList`, `java.util.LinkedList`, and `java.util.Queue` — the
traversals return real `List`s and `levelOrder` needs a `Queue`. You are still
building the *tree itself* from scratch; these are just the containers your
methods hand back.

---

## Step 4 — Interview / LeetCode bridge

The tree you just built **is** the interview toolkit. Trees and their traversals
are one of the most heavily tested topics. Try these on LeetCode — your own BST
is great warm-up practice for each:

- **Search in a Binary Search Tree** — #700 (this is your `contains`!):
  https://leetcode.com/problems/search-in-a-binary-search-tree/
- **Binary Tree Inorder Traversal** — #94 (this is your `inOrder`):
  https://leetcode.com/problems/binary-tree-inorder-traversal/
- **Maximum Depth of Binary Tree** — #104 (this is your `height`, off by one in
  convention — note LeetCode counts *nodes*, you counted *edges*):
  https://leetcode.com/problems/maximum-depth-of-binary-tree/
- **Binary Tree Level Order Traversal** — #102 (this is your `levelOrder`, the
  BFS pattern): https://leetcode.com/problems/binary-tree-level-order-traversal/
- NeetCode roadmap — *Trees* section (do these in order):
  https://neetcode.io/roadmap

The same recursive shape (base case on `null`, recurse on `left` and `right`)
solves a huge fraction of tree problems. You already know it now.

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary>I don't get the "public method + private helper" pattern.</summary>

The public method (e.g. `contains`) is what the tester calls — it knows nothing
about nodes. It just starts the recursion by calling the helper on `root`:

```java
public boolean contains(T value) {
    return containsHelper(root, value);
}
```

The private helper takes a `Node` and does the actual recursion, calling itself
on `node.left` / `node.right`. We split it this way because the recursion needs a
`Node` parameter to walk down the tree, but the outside world should only ever
hand us a value. Every recursive method here follows that two-part shape.
</details>

<details>
<summary>How does <code>insert</code> "return the subtree"?</summary>

`insertHelper(node, value)` returns the root of the subtree *after* inserting.
The caller re-links it with `node.left = insertHelper(node.left, value);`. If
`node.left` already existed, the helper hands the same node back, so the
assignment is a harmless no-op. If `node.left` was `null`, the helper creates a
new node and hands *that* back, attaching it. One line covers both cases — that's
the whole trick. Walk it on Visualgo: insert into an empty tree, then insert a
smaller value, and watch where it lands.
</details>

<details>
<summary>Why is my <code>inOrder</code> not sorted?</summary>

The three lines inside `inOrderHelper` must be in exactly this order: recurse
**left**, then add **this node's value**, then recurse **right**. If you add the
node before recursing left, you'll get pre-order instead. The ordering of those
three lines is the *entire* difference between in-order, pre-order, and
post-order — get in-order right and the other two are tiny edits.
</details>

<details>
<summary>My <code>height</code> is off by one.</summary>

The base case must return `-1` for `node == null`, not `0`. Then a leaf computes
`1 + Math.max(-1, -1) = 0`, which is the convention we want (single node has
height 0). If your base case returns `0`, every height will be one too big.
</details>

<details>
<summary><b>delete</b> — especially the two-child case (read this carefully).</summary>

`deleteHelper(node, value)` first searches like `contains`: if `value` is smaller
go left, if larger go right, re-linking the returned subtree each time
(`node.left = deleteHelper(node.left, value)`). The interesting part is when you
*find* the node to remove (`cmp == 0`). Three cases:

1. **Leaf (no children).** Just return `null` — the parent's link now points to
   nothing, so the node is gone.
2. **One child.** Return the child that exists: `if (node.left == null) return
   node.right;` and `if (node.right == null) return node.left;`. The parent now
   links straight to the grandchild. (Bonus: these two lines also handle the leaf
   case, because if *both* are null, `node.right` is null, so you return null.)
3. **Two children.** You cannot just remove the node — it has two subtrees that
   need a home. The fix: find the value that should sit in this node's place so
   the BST property still holds. That value is the **in-order successor**: the
   *smallest value in the right subtree* (walk the right child all the way left).
   Why the successor? Because it is the very next value in sorted order, so it's
   larger than everything in the left subtree and smaller than everything else in
   the right subtree — exactly what this slot needs. The steps:

   ```java
   T successor = minValue(node.right);     // smallest value to the right
   node.value = successor;                 // copy it up into this node
   node.right = deleteHelper(node.right, successor); // remove the old successor
   return node;
   ```

   That last line is the elegant part: instead of carefully unhooking the
   successor node yourself, you *recursively delete it*. The successor is the
   leftmost node of the right subtree, so it has at most one child — meaning that
   recursive delete lands in case 1 or 2, which you already handle. You never
   recurse into the hard case twice.

Draw a 3-level tree on paper and delete the root by hand once. It clicks fast.
Then step through it on Visualgo's BST page with the "delete" button.
</details>

---

## Self-check before you call it done

- All milestone tests pass (`N/M tests passed` shows the full count).
- You can explain *out loud* why `inOrder()` produces sorted output.
- You can state the three delete cases and why the two-child case uses the
  in-order successor.
- You can explain why `levelOrder` needs a *queue* (FIFO) and the other
  traversals don't.
- You did **not** copy a solution — compare with
  `solutions/projects/04-binary-tree/` only *after* your own version passes.
