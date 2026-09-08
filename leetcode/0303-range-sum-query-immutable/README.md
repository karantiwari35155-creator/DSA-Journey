<h2><a href="https://leetcode.com/problems/range-sum-query-immutable">303. Range Sum Query - Immutable</a></h2><h3>Easy</h3><hr><p>Given an integer array <code>nums</code>, handle multiple queries of the following type:</p>

<ol>
	<li>Calculate the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> where <code>left &lt;= right</code>.</li>
</ol>

<p>Implement the <code>NumArray</code> class:</p>

<ul>
	<li><code>NumArray(int[] nums)</code> Initializes the object with the integer array <code>nums</code>.</li>
	<li><code>int sumRange(int left, int right)</code> Returns the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> (i.e. <code>nums[left] + nums[left + 1] + ... + nums[right]</code>).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumArray&quot;, &quot;sumRange&quot;, &quot;sumRange&quot;, &quot;sumRange&quot;]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
<strong>Output</strong>
[null, 1, -1, -3]

<strong>Explanation</strong>
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; nums.length</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>sumRange</code>.</li>
</ul>

# LeetCode 303 — Range Sum Query - Immutable
 
> **Problem:** LeetCode 303 — Range Sum Query - Immutable
> **Difficulty:** Easy
> **Pattern:** Prefix Sum / Precomputation
> **Main Technique:** Cumulative Sum Array
> **Time Complexity:** O(n) to build, O(1) per query
> **Auxiliary Space:** O(n)
 
---
 
## 1. Problem Statement
 
Given an integer array `nums`, handle multiple queries of the following type:
 
1. Calculate the **sum** of the elements of `nums` between indices `left` and `right` **inclusive** where `left <= right`.
Implement the `NumArray` class:
 
- `NumArray(int[] nums)` Initializes the object with the integer array `nums`.
- `int sumRange(int left, int right)` Returns the **sum** of the elements of `nums` between indices `left` and `right` **inclusive** (i.e. `nums[left] + nums[left + 1] + ... + nums[right]`).
### Example 1
 
```text
Input:
 
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 
Output:
 
[null, 1, -1, -3]
 
Explanation:
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
```
 
---
 
## 2. Constraints
 
- `1 <= nums.length <= 10^4`
- `-10^5 <= nums[i] <= 10^5`
- `0 <= left <= right < nums.length`
- At most `10^4` calls will be made to `sumRange`.
---
 
## 3. What Is the Problem Asking?
 
We need to answer **many repeated range-sum queries** on a **fixed (immutable)** array efficiently.
 
```
nums = [-2, 0, 3, -5, 2, -1]
 
sumRange(0, 2) → -2 + 0 + 3        = 1
sumRange(2, 5) → 3 + (-5) + 2 + (-1) = -1
sumRange(0, 5) → -2+0+3-5+2-1      = -3
```
 
The naive approach — looping from `left` to `right` and adding elements every single call — works, but is slow if `sumRange` is called many times (up to `10^4` calls on an array of up to `10^4` elements → worst case `10^8` operations). Since `nums` **never changes** ("Immutable"), we can precompute something once and answer every query in constant time.
 
---
 
## 4. Core Idea
 
Precompute a **prefix sum array** `prefix`, where `prefix[i]` stores the sum of all elements from `nums[0]` up to `nums[i-1]`:
 
```java
prefix[0] = 0;
prefix[i] = prefix[i-1] + nums[i-1];   // for i = 1 to n
```
 
Once we have this array, the sum of any range `[left, right]` can be computed in **O(1)** using simple subtraction:
 
```java
sumRange(left, right) = prefix[right + 1] - prefix[left];
```
 
This works because `prefix[right + 1]` is the sum of everything up to and including `nums[right]`, and `prefix[left]` is the sum of everything **before** `nums[left]` — subtracting removes exactly the unwanted prefix, leaving just the sum of `nums[left..right]`.
 
---
 
## 5. The Two Operations
 
### 5.1 Build the Prefix Sum Array (Done Once, in the Constructor)
 
```java
prefix = new int[nums.length + 1];
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
```
 
Example: `nums = [-2, 0, 3, -5, 2, -1]`
 
```
prefix = [0, -2, -2, 1, -4, -2, -3]
          ↑   ↑   ↑  ↑   ↑   ↑   ↑
        idx: 0   1   2  3   4   5   6
```
 
### 5.2 Answer a Query (Done Every Call, in O(1))
 
```java
return prefix[right + 1] - prefix[left];
```
 
Example: `sumRange(2, 5)` → `prefix[6] - prefix[2] = -3 - (-2) = -1` ✅
 
---
 
## 6. Important Detail — Why the Prefix Array Has Size n+1
 
Using a prefix array of size `n + 1` (instead of `n`), with `prefix[0] = 0` as a sentinel, avoids needing a special case for when `left == 0`:
 
```
Without the extra slot:
sumRange(0, right) would need: prefix[right] alone (no subtraction)
sumRange(left, right) for left > 0 would need: prefix[right] - prefix[left - 1]
→ Two different formulas depending on whether left == 0.
 
With the extra slot (prefix[0] = 0):
sumRange(left, right) is ALWAYS: prefix[right + 1] - prefix[left]
→ One single formula, no special-casing needed.
```
 
This small trick (a leading `0` sentinel) is a common and important prefix-sum idiom.
 
---
 
## 7. Complete Java Code
 
```java
class NumArray {
 
    private int[] prefix;
 
    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }
 
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}
 
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
```
 
---
 
## 8. Code Explanation
 
**Prefix Array Field**
 
```java
private int[] prefix;
```
 
Stores the cumulative sums, computed once and reused across all queries.
 
**Constructor — Build the Prefix Sums**
 
```java
public NumArray(int[] nums) {
    prefix = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
        prefix[i + 1] = prefix[i] + nums[i];
    }
}
```
 
`prefix[0]` stays `0` (Java default). For each `i`, `prefix[i+1]` accumulates the previous total plus the current `nums[i]`.
 
**sumRange — Answer a Query**
 
```java
public int sumRange(int left, int right) {
    return prefix[right + 1] - prefix[left];
}
```
 
A single subtraction gives the answer instantly, regardless of how large the range `[left, right]` is.
 
---
 
## 9. Dry Run
 
Input:
 
```
nums = [-2, 0, 3, -5, 2, -1]
```
 
**Build phase:**
 
| i | nums[i] | prefix[i+1] = prefix[i] + nums[i] |
|---|---|---|
| 0 | -2 | prefix[1] = 0 + (-2) = -2 |
| 1 | 0 | prefix[2] = -2 + 0 = -2 |
| 2 | 3 | prefix[3] = -2 + 3 = 1 |
| 3 | -5 | prefix[4] = 1 + (-5) = -4 |
| 4 | 2 | prefix[5] = -4 + 2 = -2 |
| 5 | -1 | prefix[6] = -2 + (-1) = -3 |
 
`prefix = [0, -2, -2, 1, -4, -2, -3]`
 
**Query phase:**
 
| call | formula | result |
|---|---|---|
| `sumRange(0, 2)` | `prefix[3] - prefix[0] = 1 - 0` | `1` ✅ |
| `sumRange(2, 5)` | `prefix[6] - prefix[2] = -3 - (-2)` | `-1` ✅ |
| `sumRange(0, 5)` | `prefix[6] - prefix[0] = -3 - 0` | `-3` ✅ |
 
All match the expected output `[1, -1, -3]`.
 
---
 
## 10. Why Not Just Loop Over `[left, right]` Every Time?
 
The naive approach:
 
```java
public int sumRange(int left, int right) {
    int sum = 0;
    for (int i = left; i <= right; i++) {
        sum += nums[i];
    }
    return sum;
}
```
 
This is `O(right - left + 1)` **per call**. With up to `10^4` calls on an array of up to `10^4` elements, the worst case is `10^4 × 10^4 = 10^8` operations — likely too slow (and definitely wasteful) for repeated queries on data that never changes.
 
Since the array is **immutable**, the prefix sum trades a one-time `O(n)` build cost for **O(1)** per query — a massive win when there are many queries, which is exactly the scenario this problem is designed around.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Off-by-one in the prefix array size**
Wrong: `prefix = new int[nums.length];` — this doesn't leave room for the leading `0` sentinel, forcing awkward special-casing.
Correct: `prefix = new int[nums.length + 1];`
 
**Mistake 2 — Wrong query formula**
Wrong: `prefix[right] - prefix[left]` — this excludes `nums[right]` from the sum.
Correct: `prefix[right + 1] - prefix[left]` — the `+1` ensures `nums[right]` is included.
 
**Mistake 3 — Recomputing the prefix sums inside `sumRange`**
This defeats the entire purpose of precomputation — the array must be built **once** in the constructor, not on every query call.
 
**Mistake 4 — Forgetting the array is immutable, and trying to "update" `nums` after construction**
This problem specifically doesn't require update support (unlike LeetCode 307 — Range Sum Query - Mutable, which does, and needs a different structure like a Binary Indexed Tree or Segment Tree).
 
**Mistake 5 — Integer overflow with very large sums**
Given `nums[i]` can be up to `±10^5` and length up to `10^4`, the maximum possible prefix sum magnitude is about `10^9`, which still comfortably fits in a Java `int` (max ~`2.1 × 10^9`), so `int` is safe here — but it's worth mentally checking overflow bounds on any prefix-sum problem with different constraints.
 
---
 
## 12. Edge Cases
 
**Single Element Array:**
`nums = [7]` → `prefix = [0, 7]` → `sumRange(0, 0) = prefix[1] - prefix[0] = 7`
 
**Full Range Query:**
`sumRange(0, nums.length - 1)` → returns the sum of the entire array.
 
**Single-Element Range:**
`sumRange(k, k)` → `prefix[k+1] - prefix[k] = nums[k]` — correctly returns just that one element.
 
**All Negative Numbers:**
Prefix sums simply become increasingly negative — the subtraction logic still works identically.
 
**All Zeros:**
Every `sumRange` call correctly returns `0`.
 
---
 
## 13. Time Complexity
 
**Building the prefix array:** one pass through `nums` → `O(n)`, done once in the constructor.
 
**Each `sumRange` call:** a single array lookup and subtraction → `O(1)`.
 
**Total for `q` queries:** `O(n + q)`
 
---
 
## 14. Space Complexity
 
The prefix array stores `n + 1` integers.
 
**Auxiliary Space = O(n)**
 
### Interview Answer
 
- Time Complexity: `O(n)` to build, `O(1)` per query
- Auxiliary Space: `O(n)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A prefix sum array, built once in the constructor, where `prefix[i]` holds the sum of all elements before index `i`. Each `sumRange` query is then answered with a single subtraction.
 
**Q2. Why is this efficient for this specific problem?**
The array is explicitly stated to be **immutable** (never changes after construction), and the problem expects potentially many `sumRange` calls. Precomputing prefix sums trades a one-time `O(n)` cost for `O(1)` per query, which is optimal when queries vastly outnumber updates (here, there are zero updates).
 
**Q3. Why use a prefix array of size `n+1` instead of `n`?**
The extra leading `0` sentinel lets the formula `prefix[right+1] - prefix[left]` work uniformly for every valid `left`, including `left = 0`, without needing a special case.
 
**Q4. What would you do differently if the array *could* be updated (LeetCode 307)?**
A plain prefix sum array becomes inefficient for updates, since a single update would require recomputing all prefix sums after it (`O(n)` per update). For mutable range-sum queries, a **Binary Indexed Tree (Fenwick Tree)** or **Segment Tree** is used instead, giving `O(log n)` for both updates and queries.
 
**Q5. Is `O(n)` extra space acceptable here, or could you do better?**
`O(n)` is expected and standard for this problem — you fundamentally need to store *some* precomputed information proportional to the array size to answer arbitrary range queries in `O(1)`, so this is the optimal space/time tradeoff for the immutable case.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why does `prefix[right + 1] - prefix[left]` give the sum of `nums[left..right]`, not `nums[left..right-1]` or something else?
**Answer:** `prefix[right + 1]` represents the sum of `nums[0..right]` (inclusive), and `prefix[left]` represents the sum of `nums[0..left-1]` (i.e., everything strictly before `left`). Subtracting removes exactly the unwanted prefix, leaving `nums[left] + nums[left+1] + ... + nums[right]`.
 
**Question 2:** What is `prefix[0]` and why must it be `0`?
**Answer:** `prefix[0]` represents "the sum of zero elements" — an empty sum, which is `0` by definition. It acts as the sentinel base case, making the subtraction formula valid even when `left = 0`.
 
**Question 3:** How would this approach need to change for a 2D matrix range-sum query (LeetCode 304)?
**Answer:** You'd build a 2D prefix sum matrix instead, where `prefix[i][j]` stores the sum of the sub-rectangle from `(0,0)` to `(i-1, j-1)`, and use inclusion-exclusion (`prefix[r2+1][c2+1] - prefix[r1][c2+1] - prefix[r2+1][c1] + prefix[r1][c1]`) to answer any rectangular sub-region sum in `O(1)`.
 
**Question 4:** What's the tradeoff of this approach if `sumRange` is called only once or twice, but the array is very large?
**Answer:** Building the full prefix array still costs `O(n)` upfront even if only a couple of queries follow — in that narrow case, a naive per-query loop might actually do less total work. The prefix-sum approach pays off specifically when the number of queries is large relative to a single build cost.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Answer many range-sum queries on an array that never changes"**
 
Immediately think:
 
```
PREFIX SUM ARRAY
prefix[0] = 0
prefix[i] = prefix[i-1] + nums[i-1]
 
sumRange(left, right) = prefix[right+1] - prefix[left]
 
Build once: O(n)
Query:      O(1)
```
 
---
 
## 18. Visual Pattern
 
```
nums:    -2   0   3  -5   2  -1
index:    0   1   2   3   4   5
 
prefix:  0  -2  -2   1  -4  -2  -3
idx:     0   1   2   3   4   5   6
 
sumRange(2, 5):
  = prefix[6] - prefix[2]
  = -3 - (-2)
  = -1
```
 
Think: **PRECOMPUTE THE RUNNING TOTAL ONCE. EVERY RANGE SUM IS JUST A SUBTRACTION.**
 
---
 
## 19. Alternative Approach
 
**Naive per-query summation (no precomputation):**
 
```java
class NumArray {
    private int[] nums;
 
    public NumArray(int[] nums) {
        this.nums = nums;
    }
 
    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
```
 
This uses `O(1)` extra space (beyond storing the input) but `O(n)` time **per query**, making it `O(n × q)` overall for `q` queries — much worse than the prefix-sum approach when there are many queries, though it avoids the upfront `O(n)` build cost and extra `O(n)` prefix array.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "sum a subarray." Think of it as **trading a one-time preprocessing cost for constant-time answers to arbitrarily many future questions**.
 
> Whenever the underlying data doesn't change but you'll be asked the same *kind* of question repeatedly (here, "what's the sum of this range?"), it's almost always worth precomputing a structure that turns each future query into `O(1)` work — even if building that structure costs `O(n)` up front. This is the essence of the prefix-sum pattern, and it generalizes directly to 2D prefix sums, difference arrays, and more advanced structures like Fenwick/Segment Trees once updates enter the picture.
 
```
Constructor:
  Build prefix[] once → O(n)
 
Every sumRange(left, right) call:
  return prefix[right+1] - prefix[left]  → O(1)
 
Total for q queries: O(n + q), NOT O(n * q)
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Range Sum Query - Immutable | 303 | Prefix Sum |
| Range Sum Query 2D - Immutable | 304 | 2D Prefix Sum |
| Range Sum Query - Mutable | 307 | Binary Indexed Tree / Segment Tree |
| Subarray Sum Equals K | 560 | Prefix Sum + Hash Map |
| Contiguous Array | 525 | Prefix Sum + Hash Map |
| Product of Array Except Self | 238 | Prefix/Suffix Products |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 303 — RANGE SUM QUERY IMMUTABLE ║
╠══════════════════════════════════════════╣
║ Pattern: Prefix Sum Precomputation        ║
║                                            ║
║ Build (constructor):                      ║
║ prefix[0] = 0                             ║
║ prefix[i] = prefix[i-1] + nums[i-1]       ║
║                                            ║
║ Query:                                    ║
║ sumRange(left, right) =                   ║
║   prefix[right + 1] - prefix[left]        ║
║                                            ║
║ Time: O(n) build, O(1) per query          ║
║ Auxiliary Space: O(n)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Range Sum Query (Immutable) = Precompute running totals once; every range sum is just two array lookups and a subtraction.**
 
- Build: `prefix[i] = prefix[i-1] + nums[i-1]`
- Query: `prefix[right+1] - prefix[left]`
---
 
## 24. 30-Second Interview Explanation
 
> "Since the array never changes, I precompute a prefix sum array once in the constructor, where `prefix[i]` holds the sum of all elements before index `i`. This uses a leading zero sentinel so the query formula works uniformly. Then, for any `sumRange(left, right)` call, I just return `prefix[right+1] - prefix[left]` — subtracting out everything before `left` from everything up to and including `right`. This turns what would be an O(n) computation per query into a one-time O(n) build followed by O(1) per query, which is ideal since the problem expects many repeated range-sum calls on immutable data."
 
---
 
## 25. Final Takeaway
 
```
     RANGE SUM QUERY — IMMUTABLE
                 ↓
   Constructor: build prefix[] once
   prefix[0] = 0
   prefix[i] = prefix[i-1] + nums[i-1]
                 ↓
   sumRange(left, right) called
                 ↓
   return prefix[right + 1] - prefix[left]
                 ↓
           O(1) PER QUERY
```
 
Remember:
 
```
prefix[0] = 0  (sentinel)
prefix[i] = prefix[i-1] + nums[i-1]
 
sumRange(left, right) = prefix[right+1] - prefix[left]
 
Build once: O(n). Query forever after: O(1).
```
 
This is the core pattern behind **LeetCode 303 — Range Sum Query - Immutable**.
