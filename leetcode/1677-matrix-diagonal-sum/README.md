<h2><a href="https://leetcode.com/problems/matrix-diagonal-sum">1677. Matrix Diagonal Sum</a></h2><h3>Easy</h3><hr><p>Given a&nbsp;square&nbsp;matrix&nbsp;<code>mat</code>, return the sum of the matrix diagonals.</p>

<p>Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/14/sample_1911.png" style="width: 336px; height: 174px;" />
<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;             [4,<strong>5</strong>,6],
&nbsp;             [<strong>7</strong>,8,<strong>9</strong>]]
<strong>Output:</strong> 25
<strong>Explanation: </strong>Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>Output:</strong> 8
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>5</strong>]]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == mat.length == mat[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

# LeetCode 1572 — Matrix Diagonal Sum
 
> **Problem:** LeetCode 1572 — Matrix Diagonal Sum
> **Difficulty:** Easy
> **Pattern:** Matrix Traversal / Index Manipulation
> **Main Technique:** Two Pointer Indices (i and n-1-i)
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given a square matrix `mat`, return the sum of the matrix diagonals.
 
Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are **not** part of the primary diagonal.
 
### Example 1
 
```text
Input:
 
1 2 3
4 5 6
7 8 9
 
Output:
 
25
 
Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
```
 
### Example 2
 
```text
Input:
 
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
 
Output:
 
8
```
 
### Example 3
 
```text
Input:
 
5
 
Output:
 
5
```
 
---
 
## 2. Constraints
 
- `n == mat.length == mat[i].length`
- `1 <= n <= 100`
- `1 <= mat[i][j] <= 100`
---
 
## 3. What Is the Problem Asking?
 
We need to sum two diagonals of a square matrix:
 
```
Primary Diagonal:    mat[i][i]        (top-left → bottom-right)
Secondary Diagonal:  mat[i][n-1-i]    (top-right → bottom-left)
```
 
For example, in a 3×3 matrix:
 
```
1 2 3
4 5 6
7 8 9
```
 
Primary diagonal: `1, 5, 9`
Secondary diagonal: `3, 5, 7`
 
If we simply add both diagonals, the center element `5` gets counted **twice**. When `n` is odd, the center element (`mat[i][i]` where `i == n-1-i`) must only be counted once.
 
---
 
## 4. Core Idea
 
Instead of building two separate loops for primary and secondary diagonals, use **one loop** with a single index `i` that walks both diagonals at the same time:
 
```java
int primaryIndex = i;
int secondaryIndex = n - 1 - i;
```
 
At each step, add `mat[i][primaryIndex]` and `mat[i][secondaryIndex]` — but **skip the secondary element when it lands on the same cell as the primary element**, which only happens at the exact center of an odd-sized matrix.
 
```java
if (primaryIndex != secondaryIndex) {
    sum += mat[i][secondaryIndex];
}
```
 
This avoids double-counting without needing any extra space or a visited set.
 
---
 
## 5. The Two Diagonals
 
### 5.1 Primary Diagonal — Row i, Column i
 
```java
sum += mat[i][i];
```
 
Example (3×3): visits `mat[0][0], mat[1][1], mat[2][2]` → `1, 5, 9`
 
### 5.2 Secondary Diagonal — Row i, Column n-1-i
 
```java
sum += mat[i][n - 1 - i];
```
 
Example (3×3): visits `mat[0][2], mat[1][1], mat[2][0]` → `3, 5, 7`
 
### 5.3 Avoiding Double Count at the Center
 
When `n` is odd, there is exactly one row where `i == n - 1 - i` (the middle row). At that row, both diagonals point to the **same cell**, so it must be added only once.
 
```java
if (i != n - 1 - i) {
    sum += mat[i][n - 1 - i];
}
```
 
---
 
## 6. Important Boundary Check
 
This check is the entire trick of the problem:
 
```java
if (i == n - 1 - i) {
    // center cell of an odd-sized matrix — don't add secondary again
}
```
 
**Why?** Without it, the middle element of an odd `n` gets added twice, producing a wrong (too large) sum.
 
For even `n`, `i` never equals `n - 1 - i`, so both diagonals are always distinct — the check simply never triggers, which is safe.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int diagonalSum(int[][] mat) {
 
        int n = mat.length;
        int sum = 0;
 
        for (int i = 0; i < n; i++) {
 
            // Primary diagonal
            sum += mat[i][i];
 
            // Secondary diagonal (skip if same cell as primary)
            if (i != n - 1 - i) {
                sum += mat[i][n - 1 - i];
            }
        }
 
        return sum;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Matrix Size**
 
```java
int n = mat.length;
```
 
Since `mat` is square, `n` gives both the number of rows and columns.
 
**Sum Variable**
 
```java
int sum = 0;
```
 
Accumulates the total diagonal sum as we iterate.
 
**Single Loop**
 
```java
for (int i = 0; i < n; i++)
```
 
One pass through all rows is enough — both diagonals are indexed off the same `i`.
 
**Primary Diagonal**
 
```java
sum += mat[i][i];
```
 
Row `i`, column `i` — walks straight from top-left to bottom-right.
 
**Secondary Diagonal (guarded)**
 
```java
if (i != n - 1 - i) {
    sum += mat[i][n - 1 - i];
}
```
 
Row `i`, column `n - 1 - i` — walks from top-right to bottom-left. The guard prevents re-adding the shared center cell.
 
**Return**
 
```java
return sum;
```
 
---
 
## 9. Dry Run
 
Input:
 
```
1 2 3
4 5 6
7 8 9
```
 
`n = 3`, `sum = 0`
 
| i | mat[i][i] | n-1-i | mat[i][n-1-i] | same cell? | sum after this row |
|---|---|---|---|---|---|
| 0 | mat[0][0]=1 | 2 | mat[0][2]=3 | no | 0+1+3 = 4 |
| 1 | mat[1][1]=5 | 1 | mat[1][1]=5 | **yes** (skip) | 4+5 = 9 |
| 2 | mat[2][2]=9 | 0 | mat[2][0]=7 | no | 9+9+7 = 25 |
 
**Final sum = 25** ✅ (matches expected output)
 
---
 
## 10. Why Check `i != n - 1 - i` Instead of Just Summing Both Diagonals Separately?
 
If we sum the primary and secondary diagonals in two separate loops and add the totals, the center cell of an odd-sized matrix is counted in **both** loops, inflating the result.
 
```
Wrong (naive): sum(primary) + sum(secondary)   → double-counts center for odd n
Correct:       single pass with the equality guard
```
 
The guard is the cheapest possible fix — no extra space, no post-processing, no need to track which index was "already visited."
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Forgetting the center-cell guard**
Wrong: always adding both `mat[i][i]` and `mat[i][n-1-i]` unconditionally.
Correct: skip the secondary add when `i == n - 1 - i`.
 
**Mistake 2 — Using two separate loops without deduplication**
Wrong: `for primary diagonal` then `for secondary diagonal`, then adding totals — double-counts the center for odd `n`.
Correct: one loop, one guard.
 
**Mistake 3 — Off-by-one on the secondary index**
Wrong: `mat[i][n - i]` (out of bounds for the last row).
Correct: `mat[i][n - 1 - i]`.
 
**Mistake 4 — Assuming the guard is needed for even n**
For even `n`, `i` never equals `n - 1 - i`, so the guard simply never fires — it's always safe to include, regardless of parity.
 
**Mistake 5 — Using a visited set or extra matrix**
Unnecessary — the index relationship `i` vs `n - 1 - i` already tells you exactly when the cells coincide, with zero extra space.
 
---
 
## 12. Edge Cases
 
**Single Element (n = 1):**
`[[5]]` → `i = 0`, `n - 1 - i = 0` → same cell → only added once → Output: `5`
 
**Even-sized Matrix (n = 4, all 1s):**
All 8 diagonal cells are distinct (4 primary + 4 secondary) → Output: `8`
 
**All Same Values:**
Sum is simply `(count of diagonal cells) × value`.
 
**Large n (n = 100):**
Loop runs 100 times — trivially fast, well within constraints.
 
---
 
## 13. Time Complexity
 
We iterate through the matrix exactly once, doing constant work per row.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only a single integer accumulator (`sum`) is used, regardless of matrix size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A single pass over the rows, computing the primary diagonal index (`i`) and the secondary diagonal index (`n-1-i`) together, with a guard to avoid double-counting the shared center cell.
 
**Q2. Why is a guard needed?**
Because for odd-sized matrices, the middle row's primary and secondary diagonal indices coincide at the same cell — without the guard it would be summed twice.
 
**Q3. Does the guard affect even-sized matrices?**
No — for even `n`, `i` and `n-1-i` are never equal, so the guard condition is always true and both diagonal elements are always added.
 
**Q4. Can this be solved in O(1) extra space?**
Yes — only one accumulator variable is needed; no auxiliary array or visited structure is required.
 
**Q5. Why not just add `mat[i][i]` and `mat[i][n-1-i]` and subtract the center once at the end?**
That also works and is a valid alternative: sum both diagonals fully, then subtract `mat[n/2][n/2]` if `n` is odd. Both approaches are O(n) time and O(1) space — the guard-in-loop version just avoids a separate post-processing step.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why does the secondary diagonal use `n - 1 - i` instead of `n - i`?
**Answer:** Matrix indices are 0-based, so the last valid column index is `n - 1`. Using `n - i` would go out of bounds on the first row.
 
**Question 2:** For which matrices does the center-overlap guard actually trigger?
**Answer:** Only square matrices with odd `n`, and only on the single middle row/column where `i == n - 1 - i`.
 
**Question 3:** Could you solve this without any conditional check?
**Answer:** Yes — sum the two diagonals separately, then subtract the center element once if `n` is odd (`if (n % 2 == 1) sum -= mat[n/2][n/2];`).
 
**Question 4:** What's the total number of diagonal elements summed for a given n?
**Answer:** `2n - 1` if `n` is odd (center shared), or `2n` if `n` is even (no overlap).
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Sum / process both diagonals of a square matrix"**
 
Immediately think:
 
```
PRIMARY:   mat[i][i]
SECONDARY: mat[i][n-1-i]
GUARD:     i == n-1-i  →  center of odd n, don't double count
```
 
---
 
## 18. Visual Pattern
 
```
n = 3
┌───┬───┬───┐
│ P │   │ S │
├───┼───┼───┤
│   │ * │   │   * = center, shared by P and S — count once
├───┼───┼───┤
│ S │   │ P │
└───┴───┴───┘
 
P = primary diagonal cell   S = secondary diagonal cell
```
 
---
 
## 19. Alternative Approach
 
Sum the primary diagonal fully, sum the secondary diagonal fully, add them together, then subtract the center element once if `n` is odd:
 
```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i];
            sum += mat[i][n - 1 - i];
        }
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }
        return sum;
    }
}
```
 
Both approaches are `O(n)` time and `O(1)` space — this is purely a stylistic choice between "guard inside the loop" vs "correct after the loop."
 
---
 
## 20. Senior Engineer Perspective
 
Don't overthink this as a "matrix traversal" problem — it's really an **index-relationship** problem.
 
> The two diagonals are fully described by the relationship between `i` and `n - 1 - i`. The only subtlety is recognizing the single point where they coincide, and handling that overlap exactly once — everything else is a straight linear scan.
 
```
Loop i from 0 to n-1
      ↓
Add mat[i][i]           (primary)
      ↓
i == n-1-i ?
      ↓                    ↓
     yes                   no
      ↓                    ↓
   skip                add mat[i][n-1-i]  (secondary)
      ↓                    ↓
        Continue to next i
              ↓
          Return sum
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Matrix Diagonal Sum | 1572 | Index Manipulation |
| Spiral Matrix | 54 | Boundary Traversal |
| Rotate Image | 48 | Matrix Manipulation |
| Transpose Matrix | 867 | Matrix Manipulation |
| Toeplitz Matrix | 766 | Matrix / Diagonal Check |
| Set Matrix Zeroes | 73 | Matrix / In-place |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════╗
║   LEETCODE 1572 — DIAGONAL SUM        ║
╠══════════════════════════════════════╣
║ Pattern: Index Manipulation           ║
║                                        ║
║ Primary diagonal:                     ║
║ mat[i][i]                             ║
║                                        ║
║ Secondary diagonal:                   ║
║ mat[i][n-1-i]                         ║
║                                        ║
║ Guard (odd n center):                 ║
║ if (i == n-1-i) skip secondary add    ║
║                                        ║
║ Time: O(n)                            ║
║ Auxiliary Space: O(1)                 ║
╚══════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Diagonal Sum = Add mat[i][i] and mat[i][n-1-i] for every row, but count the center cell of an odd matrix only once.**
 
- Primary   → `mat[i][i]`
- Secondary → `mat[i][n-1-i]`
- Guard     → `i != n-1-i`
---
 
## 24. 30-Second Interview Explanation
 
> "I solve Matrix Diagonal Sum with a single pass over the rows. For each row `i`, I add the primary diagonal element `mat[i][i]` and the secondary diagonal element `mat[i][n-1-i]`. The only edge case is when `n` is odd: the middle row has `i == n-1-i`, meaning both diagonals point to the exact same cell, so I add it only once using a simple index-equality check. This gives O(n) time and O(1) auxiliary space, since I only need a single running sum."
 
---
 
## 25. Final Takeaway
 
```
           MATRIX DIAGONAL SUM
                    ↓
        LOOP i FROM 0 TO n-1
                    ↓
        ADD mat[i][i]  (primary)
                    ↓
        i == n-1-i ?
           ↓          ↓
          yes         no
           ↓          ↓
         skip     ADD mat[i][n-1-i]  (secondary)
                    ↓
                RETURN sum
```
 
Remember:
 
```
Primary:   mat[i][i]
Secondary: mat[i][n-1-i]
Guard:     i != n-1-i  →  add secondary
           i == n-1-i  →  skip (already counted via primary)
```
 
This is the core pattern behind **LeetCode 1572 — Matrix Diagonal Sum**.
