<h2><a href="https://leetcode.com/problems/find-missing-and-repeated-values">3227. Find Missing and Repeated Values</a></h2><h3>Easy</h3><hr><p>You are given a <strong>0-indexed</strong> 2D integer matrix <code><font face="monospace">grid</font></code> of size <code>n * n</code> with values in the range <code>[1, n<sup>2</sup>]</code>. Each integer appears <strong>exactly once</strong> except <code>a</code> which appears <strong>twice</strong> and <code>b</code> which is <strong>missing</strong>. The task is to find the repeating and missing numbers <code>a</code> and <code>b</code>.</p>

<p>Return <em>a <strong>0-indexed </strong>integer array </em><code>ans</code><em> of size </em><code>2</code><em> where </em><code>ans[0]</code><em> equals to </em><code>a</code><em> and </em><code>ans[1]</code><em> equals to </em><code>b</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,3],[2,2]]
<strong>Output:</strong> [2,4]
<strong>Explanation:</strong> Number 2 is repeated and number 4 is missing so the answer is [2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[9,1,7],[8,9,2],[3,4,6]]
<strong>Output:</strong> [9,5]
<strong>Explanation:</strong> Number 9 is repeated and number 5 is missing so the answer is [9,5].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == grid.length == grid[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= n * n</code></li>
	<li>For all <code>x</code> that <code>1 &lt;= x &lt;= n * n</code> there is exactly one <code>x</code> that is not equal to any of the grid members.</li>
	<li>For all <code>x</code> that <code>1 &lt;= x &lt;= n * n</code> there is exactly one <code>x</code> that is equal to exactly two of the grid members.</li>
	<li>For all <code>x</code> that <code>1 &lt;= x &lt;= n * n</code> except two of them there is exactly one pair of <code>i, j</code> that <code>0 &lt;= i, j &lt;= n - 1</code> and <code>grid[i][j] == x</code>.</li>
</ul>

# LeetCode 3227 — Find Missing and Repeated Values
 
> **Problem:** LeetCode 3227 — Find Missing and Repeated Values
> **Difficulty:** Easy
> **Pattern:** Matrix / Array Hashing / Sum & Sum-of-Squares Math
> **Main Technique:** Frequency Counting (or Sum/Sum-of-Squares Formula)
> **Time Complexity:** O(n²)
> **Auxiliary Space:** O(n²) (or O(1) with the math trick)
 
---
 
## 1. Problem Statement
 
You are given a **0-indexed** 2D integer matrix `grid` of size `n x n` with values in the range `[1, n²]`. Each integer appears **exactly once** except `a` which appears **twice** and `b` which is **missing**. The task is to find the repeating and missing values `a` and `b`.
 
Return a **0-indexed** integer array `ans` of size `2` where `ans[0]` equals to `a` and `ans[1]` equals to `b`.
 
### Example 1
 
```text
Input:
 
grid = [[1,3],[2,2]]
 
Output:
 
[2,4]
 
Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].
```
 
### Example 2
 
```text
Input:
 
grid = [[9,1,7],[8,9,2],[3,4,6]]
 
Output:
 
[9,5]
 
Explanation: Number 9 is repeated and number 5 is missing so the answer is [9,5].
```
 
---
 
## 2. Constraints
 
- `2 <= n == grid.length == grid[i].length <= 50`
- `1 <= grid[i][j] <= n * n`
- For all `x` in `[1, n * n]` there is exactly one `x` that is not equal to any of the grid members.
- For all `x` in `[1, n * n]` there is exactly one `x` that is equal to exactly two of the grid members.
- For all `x` in `[1, n * n]` except two of them there is exactly one `x` that is equal to exactly one of the grid members.
---
 
## 3. What Is the Problem Asking?
 
The grid is supposed to contain **every number from `1` to `n²` exactly once**, but one number got **duplicated** (`a`) and, as a result, another number is **missing** (`b`) because the total count of numbers still equals `n²`.
 
```
grid = [[1,3],
        [2,2]]
 
n = 2, so numbers should be 1, 2, 3, 4 — each exactly once.
 
Actual numbers present: 1, 3, 2, 2
→ 2 appears twice (repeated = a = 2)
→ 4 never appears (missing = b = 4)
 
Answer: [2, 4]
```
 
We need to identify both `a` (the duplicate) and `b` (the missing value) efficiently.
 
---
 
## 4. Core Idea
 
Since values range from `1` to `n²`, we can use a **frequency array** (or hash set) of size `n² + 1` to count how many times each number appears while scanning the grid:
 
```java
int[] count = new int[n * n + 1];
for (int[] row : grid) {
    for (int val : row) {
        count[val]++;
    }
}
```
 
After counting, we scan through `1` to `n²`:
 
- The number with **count == 2** is the repeated value `a`.
- The number with **count == 0** is the missing value `b`.
This directly identifies both answers in a simple second pass.
 
---
 
## 5. The Two Passes
 
### 5.1 Pass 1 — Count Occurrences of Every Value
 
```java
for (int[] row : grid) {
    for (int val : row) {
        count[val]++;
    }
}
```
 
Builds a frequency table for every value from `1` to `n²`.
 
### 5.2 Pass 2 — Identify the Repeated and Missing Values
 
```java
int repeated = -1, missing = -1;
for (int num = 1; num <= n * n; num++) {
    if (count[num] == 2) {
        repeated = num;
    } else if (count[num] == 0) {
        missing = num;
    }
}
```
 
Scans the counted frequencies once more to pull out the answer.
 
---
 
## 6. Important Detail — Why Counting Works Cleanly Here
 
The problem guarantees that **exactly one** number is duplicated and **exactly one** number is missing — every other number appears exactly once. This means after counting:
 
```
Exactly one value has count == 2   → that's a (the repeated value)
Exactly one value has count == 0   → that's b (the missing value)
Every other value has count == 1   → ignored, as expected
```
 
There's no ambiguity — the frequency array directly and unambiguously reveals both answers.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
 
        int n = grid.length;
        int[] count = new int[n * n + 1];
 
        // Pass 1: count occurrences
        for (int[] row : grid) {
            for (int val : row) {
                count[val]++;
            }
        }
 
        int repeated = -1;
        int missing = -1;
 
        // Pass 2: find the repeated and missing values
        for (int num = 1; num <= n * n; num++) {
            if (count[num] == 2) {
                repeated = num;
            } else if (count[num] == 0) {
                missing = num;
            }
        }
 
        return new int[]{repeated, missing};
    }
}
```
 
---
 
## 8. Code Explanation
 
**Matrix Size**
 
```java
int n = grid.length;
```
 
Since the grid is `n x n`, values range from `1` to `n * n`.
 
**Frequency Array**
 
```java
int[] count = new int[n * n + 1];
```
 
Size `n*n + 1` so we can index directly by value (`count[val]`) without an off-by-one adjustment; index `0` is simply unused.
 
**Counting Pass**
 
```java
for (int[] row : grid) {
    for (int val : row) {
        count[val]++;
    }
}
```
 
Every value in the grid increments its corresponding slot in `count`.
 
**Finding the Answer**
 
```java
for (int num = 1; num <= n * n; num++) {
    if (count[num] == 2) {
        repeated = num;
    } else if (count[num] == 0) {
        missing = num;
    }
}
```
 
Scans all possible values `1..n²`, checking which one appeared twice and which one appeared zero times.
 
**Return**
 
```java
return new int[]{repeated, missing};
```
 
Returns `[a, b]` as required by the problem.
 
---
 
## 9. Dry Run
 
Input:
 
```
grid = [[9,1,7],
        [8,9,2],
        [3,4,6]]
```
 
`n = 3`, values range `1..9`
 
**Counting pass** — flatten grid: `9, 1, 7, 8, 9, 2, 3, 4, 6`
 
| value | count |
|---|---|
| 1 | 1 |
| 2 | 1 |
| 3 | 1 |
| 4 | 1 |
| 5 | 0 |
| 6 | 1 |
| 7 | 1 |
| 8 | 1 |
| 9 | 2 |
 
**Scan for answer:** `count[9] == 2` → `repeated = 9`; `count[5] == 0` → `missing = 5`
 
Return `[9, 5]` ✅ (matches expected output)
 
---
 
## 10. Why Not Sort the Flattened Grid Instead?
 
An alternative: flatten the grid into a 1D array, sort it, then walk through looking for two adjacent equal values (the duplicate) and a gap between consecutive values (the missing one).
 
```java
// Flatten, sort, then scan for a duplicate and a gap
```
 
This works but costs `O(n² log n)` due to sorting, which is strictly worse than the `O(n²)` frequency-counting approach — sorting adds unnecessary overhead when a direct counting pass already solves it in linear time relative to the number of grid cells.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Off-by-one on the frequency array size**
Wrong: `new int[n * n]` — this doesn't leave room for indexing by value `n*n` itself (values go up to `n*n`, but a 0-indexed array of size `n*n` only has valid indices `0` to `n*n - 1`).
Correct: `new int[n * n + 1]`.
 
**Mistake 2 — Confusing which count indicates "repeated" vs "missing"**
Wrong: treating `count == 0` as repeated or `count == 2` as missing.
Correct: `count == 2` → repeated; `count == 0` → missing.
 
**Mistake 3 — Stopping the scan early once one value is found**
Wrong: breaking out of the loop as soon as either `repeated` or `missing` is found — you need **both** before you can safely stop (or simply scan the full range, which is simplest and still `O(n²)`).
Correct: continue scanning until both are found, or just scan the full `1..n*n` range unconditionally.
 
**Mistake 4 — Using a `HashMap<Integer, Integer>` instead of a plain array**
This also works correctly, but a plain array is faster and simpler here since the value range (`1` to `n*n`) is small and known in advance — no need for hashing overhead.
 
**Mistake 5 — Forgetting the order of the returned array**
Wrong: `return new int[]{missing, repeated};`
Correct: the problem expects `[repeated, missing]` i.e. `[a, b]`, in that specific order.
 
---
 
## 12. Edge Cases
 
**Smallest Grid (n = 2):**
`grid = [[1,3],[2,2]]` → values `1..4`, `2` repeated, `4` missing → Output: `[2,4]`
 
**Repeated and Missing Are the Same Number's "Neighbors":**
E.g., `grid` where `5` is missing and `5` is also somehow... not possible per constraints (repeated and missing are always distinct values), so no special handling is needed for that case.
 
**Missing Value at the Boundary (1 or n²):**
Works identically — the frequency array correctly registers a `0` count at either extreme.
 
**Repeated Value at the Boundary:**
Also handled identically — no special-casing needed since the loop checks every value from `1` to `n*n` uniformly.
 
**Large Grid (n = 50):**
`n*n = 2500` — well within efficient array bounds; runs comfortably fast.
 
---
 
## 13. Time Complexity
 
We visit each of the `n²` grid cells once to build the frequency array, then scan `1..n²` once more to extract the answer.
 
**Time Complexity = O(n²)**
 
---
 
## 14. Space Complexity
 
The frequency array has size `n² + 1`.
 
**Auxiliary Space = O(n²)**
 
### Interview Answer
 
- Time Complexity: `O(n²)`
- Auxiliary Space: `O(n²)` (or `O(1)` with the sum/sum-of-squares math trick — see Section 19)
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A frequency-counting approach: count how many times each value from `1` to `n²` appears across the grid, then scan those counts to find the value that appears twice (repeated) and the value that appears zero times (missing).
 
**Q2. Why does this work reliably?**
The problem guarantees exactly one duplicate and exactly one missing value, and every other value appears exactly once — so the frequency array unambiguously reveals both answers with a simple linear scan.
 
**Q3. Can you solve this without extra space (O(1) auxiliary space)?**
Yes — using a math-based approach: compute the sum and sum-of-squares of `1` to `n²` (known formulas) versus the actual sum and sum-of-squares of the grid values, then solve the resulting system of two equations for `a` and `b`. See the Alternative Approach section.
 
**Q4. What's the tradeoff between the frequency-array approach and the math-based approach?**
The frequency array is simpler to understand and implement, at the cost of `O(n²)` extra space. The math-based approach achieves `O(1)` extra space but requires careful handling of large sums (potential overflow) and solving algebraic equations, making it slightly more error-prone to implement correctly.
 
**Q5. Could you use a `boolean[]` "seen" array plus a running sum instead of a full count array?**
Yes — since you only need to distinguish "seen once," "seen twice," and "never seen," a `boolean[]` marking "seen" plus checking for values already marked would let you detect the repeat during the counting pass itself, and computing total sum vs. expected sum would give you the missing value without a second full scan. This is a minor optimization but doesn't change the overall complexity class.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why is the frequency array sized `n*n + 1` rather than `n*n`?
**Answer:** Values range from `1` to `n*n` inclusive, and using the value directly as an array index requires the array to have a valid slot at index `n*n`, meaning the array must have size `n*n + 1` (index `0` goes unused).
 
**Question 2:** How would you find the repeated value alone, without needing the missing value, in a single pass?
**Answer:** You could detect it the moment a count reaches `2` during the counting pass itself, without waiting for a second scan — though you'd still need the second scan (or a running sum comparison) to find the missing value, since "never appeared" can't be detected until you've confirmed absence across the whole range.
 
**Question 3:** What is the sum-based trick for finding a single missing number (without a duplicate) in a simpler variant of this problem?
**Answer:** Compute the expected sum of `1` to `n` using the formula `n*(n+1)/2`, then subtract the actual sum of the array — the difference is the missing number. This problem's added complexity (a duplicate present too) requires a second equation (sum of squares) to solve for two unknowns instead of one.
 
**Question 4:** Why can't you rely on sum alone (without sum-of-squares) to solve this problem's two-unknowns case?
**Answer:** Sum alone gives you `(missing - repeated)` as a single equation with two unknowns, which isn't enough to solve for both individually — you need a second independent equation (like sum of squares) to form a solvable system of two equations for two unknowns.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"One number is missing and one number is duplicated in a range 1..n, find both"**
 
Immediately think:
 
```
FREQUENCY ARRAY (simple, O(n) or O(n²) space)
   count each value → count == 2 is repeated, count == 0 is missing
 
OR
 
SUM & SUM-OF-SQUARES MATH TRICK (O(1) space)
   solve two equations for two unknowns
```
 
---
 
## 18. Visual Pattern
 
```
grid = [[9,1,7],
        [8,9,2],
        [3,4,6]]
 
Flatten: 9 1 7 8 9 2 3 4 6
 
Frequency table (1..9):
1:1  2:1  3:1  4:1  5:0  6:1  7:1  8:1  9:2
                ↑
            missing
                              ↑
                          repeated
 
Answer: [repeated=9, missing=5] → [9, 5]
```
 
Think: **COUNT EVERYTHING. THE ONE WITH COUNT 2 IS THE DUPLICATE. THE ONE WITH COUNT 0 IS THE GAP.**
 
---
 
## 19. Alternative Approach — O(1) Space Using Sum and Sum of Squares
 
Let `S` = sum of `1..n²` (expected), `S2` = sum of squares of `1..n²` (expected).
Let `actualS` = actual sum of grid values, `actualS2` = actual sum of squares of grid values.
 
Since exactly one value `a` is duplicated and one value `b` is missing:
 
```
actualS  - S  = a - b                     ... (1)   (extra 'a' counted, 'b' never counted)
actualS2 - S2 = a² - b²  = (a-b)(a+b)     ... (2)
```
 
From (1): `a - b = D1` (some known difference)
Substituting into (2): `D1 * (a + b) = D2` → `a + b = D2 / D1`
 
Then solve the simple system:
 
```
a + b = sum_ab
a - b = D1
→ a = (sum_ab + D1) / 2
→ b = (sum_ab - D1) / 2
```
 
```java
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        long total = (long) n * n;
 
        long expectedSum = total * (total + 1) / 2;
        long expectedSumSq = total * (total + 1) * (2 * total + 1) / 6;
 
        long actualSum = 0, actualSumSq = 0;
        for (int[] row : grid) {
            for (int val : row) {
                actualSum += val;
                actualSumSq += (long) val * val;
            }
        }
 
        long diff1 = actualSum - expectedSum;              // a - b
        long diff2 = actualSumSq - expectedSumSq;           // a^2 - b^2 = (a-b)(a+b)
        long sumAB = diff2 / diff1;                          // a + b
 
        long a = (sumAB + diff1) / 2;
        long b = (sumAB - diff1) / 2;
 
        return new int[]{(int) a, (int) b};
    }
}
```
 
This achieves `O(n²)` time (still need to scan the grid once) but only `O(1)` **extra** space (excluding the input), using `long` to avoid overflow since sums can grow large for `n` up to `50` (`n² = 2500`, sum up to ~3 million, sum of squares up to ~5 billion — comfortably within `long` range).
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "find two special numbers." Think of it as **reconciling an actual distribution against an expected distribution**.
 
> Whenever a dataset is supposed to be a known, complete set (here, exactly one of each number `1..n²`), and something has gone slightly wrong (a duplicate crowding out a missing value), the fastest and clearest fix is usually either (a) count everything and spot the anomaly directly, or (b) compare aggregate statistics (sum, sum of squares, XOR, etc.) between what you have and what you expect, then solve for the discrepancy algebraically.
 
```
Approach A (Counting):
  Count every value → scan counts → count==2 is repeated, count==0 is missing
 
Approach B (Math):
  Compute actual sum & sum-of-squares
  Compare to expected sum & sum-of-squares (known formulas)
  Solve 2 equations, 2 unknowns → a, b
```
 
Both are valid; the counting approach favors simplicity and clarity, the math approach favors minimal extra space.
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Find Missing and Repeated Values | 3227 | Frequency Counting / Sum Math |
| Set Mismatch | 645 | Frequency Counting / Sum Math (1D version) |
| Missing Number | 268 | Sum Formula / XOR |
| Find the Duplicate Number | 287 | Floyd's Cycle Detection |
| First Missing Positive | 41 | In-place Index Marking |
| Find All Numbers Disappeared in an Array | 448 | In-place Marking |
 
---
 
## 22. Quick Revision Card
 
```
╔════════════════════════════════════════════╗
║ LEETCODE 3227 — MISSING & REPEATED VALUES   ║
╠════════════════════════════════════════════╣
║ Pattern: Frequency Counting                 ║
║                                              ║
║ Step 1:                                     ║
║ count[val]++ for every value in grid        ║
║                                              ║
║ Step 2:                                     ║
║ scan 1..n*n:                                ║
║   count == 2 → repeated (a)                 ║
║   count == 0 → missing (b)                  ║
║                                              ║
║ Return: [a, b]                              ║
║                                              ║
║ Time: O(n²)                                 ║
║ Auxiliary Space: O(n²) [O(1) with math]     ║
╚════════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Find Missing and Repeated Values = Count every value; the one seen twice is the duplicate, the one seen zero times is the gap.**
 
- `count == 2` → repeated
- `count == 0` → missing
---
 
## 24. 30-Second Interview Explanation
 
> "I use a frequency array sized to the number range, `1` to `n²`. I scan the grid once, incrementing the count for each value seen. Then I scan the possible values from `1` to `n²` once more: the value with a count of exactly `2` is the repeated one, and the value with a count of `0` is the missing one. This runs in O(n²) time — proportional to the number of grid cells — using an O(n²) frequency array. If asked for a constant-space alternative, I can instead compare the actual sum and sum-of-squares of the grid against their expected values for a complete `1..n²` set, and solve the resulting two equations to find both values algebraically."
 
---
 
## 25. Final Takeaway
 
```
      FIND MISSING AND REPEATED VALUES
                    ↓
        COUNT EVERY VALUE IN THE GRID
                    ↓
       SCAN VALUES 1 TO n*n:
                    ↓
       count == 2 ?  →  repeated = this value
                    ↓
       count == 0 ?  →  missing  = this value
                    ↓
          RETURN [repeated, missing]
```
 
Remember:
 
```
count[val]++ for every grid value
count == 2 → a (repeated)
count == 0 → b (missing)
 
Alternative O(1)-space trick:
diff1 = actualSum - expectedSum        (a - b)
diff2 = actualSumSq - expectedSumSq    (a² - b² = (a-b)(a+b))
a + b = diff2 / diff1
Solve the 2x2 system for a and b.
```
 
This is the core pattern behind **LeetCode 3227 — Find Missing and Repeated Values**.
 
