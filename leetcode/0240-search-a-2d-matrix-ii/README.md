<h2><a href="https://leetcode.com/problems/search-a-2d-matrix-ii">240. Search a 2D Matrix II</a></h2><h3>Medium</h3><hr><p>Write an efficient algorithm that searches for a value <code>target</code> in an <code>m x n</code> integer matrix <code>matrix</code>. This matrix has the following properties:</p>

<ul>
	<li>Integers in each row are sorted in ascending from left to right.</li>
	<li>Integers in each column are sorted in ascending from top to bottom.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/24/searchgrid2.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/24/searchgrid.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 300</code></li>
	<li><code>-10<sup>9</sup> &lt;= matrix[i][j] &lt;= 10<sup>9</sup></code></li>
	<li>All the integers in each row are <strong>sorted</strong> in ascending order.</li>
	<li>All the integers in each column are <strong>sorted</strong> in ascending order.</li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

# LeetCode 240 — Search a 2D Matrix II
 
> **Problem:** LeetCode 240 — Search a 2D Matrix II
> **Difficulty:** Medium
> **Pattern:** Matrix Traversal / Two Pointers (Staircase Search)
> **Main Technique:** Start from Top-Right (or Bottom-Left) Corner
> **Time Complexity:** O(m + n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Write an efficient algorithm that searches for a value `target` in an `m x n` integer matrix `matrix`. This matrix has the following properties:
 
- Integers in each row are sorted in **ascending order** from left to right.
- Integers in each column are sorted in **ascending order** from top to bottom.
### Example 1
 
```text
Input:
 
matrix = [[1,4,7,11,15],
          [2,5,8,12,19],
          [3,6,9,16,22],
          [10,13,14,17,24],
          [18,21,23,26,30]]
target = 5
 
Output:
 
true
```
 
### Example 2
 
```text
Input:
 
matrix = [[1,4,7,11,15],
          [2,5,8,12,19],
          [3,6,9,16,22],
          [10,13,14,17,24],
          [18,21,23,26,30]]
target = 20
 
Output:
 
false
```
 
---
 
## 2. Constraints
 
- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= n, m <= 300`
- `-10^9 <= matrix[i][j] <= 10^9`
- All the integers in each row are sorted in **ascending order**.
- All the integers in each column are sorted in **ascending order**.
- `-10^9 <= target <= 10^9`
---
 
## 3. What Is the Problem Asking?
 
We need to determine whether `target` exists anywhere in a matrix that is sorted **both row-wise and column-wise**, but is **not** a fully sorted single sequence (unlike LeetCode 74 — Search a 2D Matrix, where the whole matrix is one sorted sequence flattened into rows).
 
```
matrix =
1   4   7  11  15
2   5   8  12  19
3   6   9  16  22
10  13  14 17  24
18  21  23 26  30
```
 
Here, row 3 starts with `10`, which is **smaller** than the last element of row 1 (`15`) — so we can't binary search the matrix as one flat sorted array. We need an approach that exploits both the row and column ordering separately.
 
---
 
## 4. Core Idea
 
Start the search from a corner of the matrix where **moving in one direction always increases the value, and moving in the other direction always decreases it**. The **top-right corner** (or equivalently, the bottom-left corner) is exactly such a point:
 
```java
int row = 0;
int col = n - 1;  // start at top-right
```
 
At the top-right corner:
 
- Moving **left** (`col--`) → value **decreases** (row is sorted ascending left→right).
- Moving **down** (`row++`) → value **increases** (column is sorted ascending top→bottom).
This gives us a clean elimination rule at every step:
 
```
if matrix[row][col] == target → found it
if matrix[row][col] >  target → eliminate this column (col--)
if matrix[row][col] <  target → eliminate this row    (row++)
```
 
Each comparison eliminates exactly one full row or one full column, so we never need to revisit it.
 
---
 
## 5. The Three Cases Per Step
 
### 5.1 Value Matches Target
 
```java
if (matrix[row][col] == target) {
    return true;
}
```
 
Found the target — done.
 
### 5.2 Value Is Too Big — Move Left
 
```java
if (matrix[row][col] > target) {
    col--;
}
```
 
Since everything **below** the current cell in this column is even bigger (column sorted ascending downward), the entire column can be discarded. Move one column to the left.
 
### 5.3 Value Is Too Small — Move Down
 
```java
if (matrix[row][col] < target) {
    row++;
}
```
 
Since everything **to the left** of the current cell in this row is even smaller (row sorted ascending leftward reversed — actually smaller values are to the left, so nothing to the left can help either), the entire row can be discarded. Move one row down.
 
---
 
## 6. Important Detail — Why the Top-Right Start Is Essential
 
Starting from the **top-left corner** (`matrix[0][0]`) does **not** work, because from there, both moving right and moving down **increase** the value — there's no direction that decreases it, so you can't eliminate a whole row or column with a single comparison.
 
Only two corners give you one "increasing" direction and one "decreasing" direction simultaneously:
 
```
Top-right corner:    right→left = decreasing,  top→bottom = increasing   ✅ works
Bottom-left corner:  left→right = increasing,   bottom→top = decreasing  ✅ works
Top-left corner:     both directions increasing                          ❌ fails
Bottom-right corner: both directions decreasing                          ❌ fails
```
 
This solution uses the **top-right** corner, but bottom-left works symmetrically.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
 
        int rows = matrix.length;
        int cols = matrix[0].length;
 
        int row = 0;
        int col = cols - 1;
 
        while (row < rows && col >= 0) {
 
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
 
        return false;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Matrix Dimensions**
 
```java
int rows = matrix.length;
int cols = matrix[0].length;
```
 
Store the number of rows and columns for boundary checks.
 
**Starting Position**
 
```java
int row = 0;
int col = cols - 1;
```
 
Start at the **top-right** corner — the key insight that makes this approach work.
 
**While Loop**
 
```java
while (row < rows && col >= 0)
```
 
Continue as long as we're still inside the matrix bounds. If `col` goes below `0` or `row` goes beyond the last row, we've exhausted all possibilities.
 
**Match Found**
 
```java
if (matrix[row][col] == target) {
    return true;
}
```
 
**Value Too Big**
 
```java
else if (matrix[row][col] > target) {
    col--;
}
```
 
Eliminate the current column by moving left.
 
**Value Too Small**
 
```java
else {
    row++;
}
```
 
Eliminate the current row by moving down.
 
**Not Found**
 
```java
return false;
```
 
If the loop exits without finding a match, the target isn't in the matrix.
 
---
 
## 9. Dry Run
 
Input:
 
```
matrix =
1   4   7  11  15
2   5   8  12  19
3   6   9  16  22
10  13  14 17  24
18  21  23 26  30
 
target = 5
```
 
`rows = 5`, `cols = 5`, `row = 0`, `col = 4`
 
| step | row | col | matrix[row][col] | comparison | action |
|---|---|---|---|---|---|
| 1 | 0 | 4 | 15 | 15 > 5 | col-- |
| 2 | 0 | 3 | 11 | 11 > 5 | col-- |
| 3 | 0 | 2 | 7 | 7 > 5 | col-- |
| 4 | 0 | 1 | 4 | 4 < 5 | row++ |
| 5 | 1 | 1 | 5 | 5 == 5 | **match!** |
 
Return `true` ✅ (matches expected output)
 
---
 
## 10. Why Not Binary Search the Whole Matrix (Like LeetCode 74)?
 
In LeetCode 74 (Search a 2D Matrix), the matrix is sorted such that **each row's first element is greater than the previous row's last element** — meaning the whole matrix, read row by row, forms one fully sorted sequence. That lets you binary search it as if it were a flat 1D array.
 
In **this** problem (240), that guarantee does **not** hold:
 
```
Row 1 ends with 15, but Row 3 starts with 10 — not fully sorted as one sequence.
```
 
So a single binary search over the "flattened" matrix won't work here. Instead, we exploit the two **independent** sort orders (row-wise and column-wise) with the staircase/corner search.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Starting from the top-left corner**
Wrong: `row = 0, col = 0` — both directions (right, down) increase the value, giving no way to eliminate anything with one comparison.
Correct: start from top-right (`col = cols - 1`) or bottom-left (`row = rows - 1`).
 
**Mistake 2 — Confusing which direction to move on "too big" vs "too small"**
Wrong: moving `row++` when the value is too big, or `col--` when it's too small — this is backwards for a top-right start.
Correct: `too big → col--` (move left, decreasing); `too small → row++` (move down, increasing).
 
**Mistake 3 — Treating this the same as LeetCode 74 and binary searching the flattened matrix**
This only works when rows are globally sorted end-to-end across the whole matrix, which is **not** guaranteed here.
 
**Mistake 4 — Incorrect loop boundary conditions**
Wrong: `while (row <= rows && col >= 0)` — off-by-one, since valid row indices go up to `rows - 1`.
Correct: `while (row < rows && col >= 0)`.
 
**Mistake 5 — Forgetting the early return on match**
Without an immediate `return true` on match, the loop might continue and accidentally move past the found target, eventually returning `false` incorrectly.
 
---
 
## 12. Edge Cases
 
**Single Element Matrix:**
`[[5]]`, `target = 5` → `row=0, col=0`, matches immediately → Output: `true`
 
**Target Smaller Than Everything:**
`target` less than `matrix[0][0]` → immediately fails all "too big" checks and moves left until `col < 0` → Output: `false`
 
**Target Larger Than Everything:**
`target` greater than `matrix[rows-1][cols-1]` → keeps moving down until `row >= rows` → Output: `false`
 
**Single Row:**
`[[1,3,5,7]]` → behaves like a simple linear/binary search scenario; still works correctly with the staircase approach.
 
**Single Column:**
```
1
3
5
7
```
→ `col` starts and stays at `0`; only `row++` ever triggers → still works correctly.
 
**Target Not Present, Falls "Between" Values:**
`target = 20` in the example matrix → search correctly narrows down and exits with `false` without a false positive.
 
---
 
## 13. Time Complexity
 
At each step, we either decrease `col` or increase `row`. `col` can decrease at most `n` times, and `row` can increase at most `m` times, before the loop must terminate.
 
**Time Complexity = O(m + n)**
 
This is significantly better than the brute-force `O(m × n)` full scan.
 
---
 
## 14. Space Complexity
 
Only two integer pointers (`row`, `col`) are used, regardless of matrix size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(m + n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A staircase search starting from the top-right corner, moving left when the current value is too big and down when it's too small, eliminating one row or column per comparison.
 
**Q2. Why start at the top-right corner specifically?**
Because it's one of only two corners where one movement direction strictly increases the value and the other strictly decreases it — this is what allows a single comparison to eliminate an entire row or column. The top-left and bottom-right corners don't have this property, since both directions move the same way (both increasing or both decreasing).
 
**Q3. Why doesn't this reduce to a simple binary search?**
Because the matrix is sorted independently by row and by column, but is **not** sorted as one continuous flattened sequence — row starts can be smaller than the previous row's end. Binary search over the flattened matrix (as used in LeetCode 74) requires that stronger global-sort guarantee, which this problem doesn't provide.
 
**Q4. What's the time complexity and why?**
`O(m + n)`, because each step of the algorithm strictly decreases `col` or increases `row`, and each can only do so a bounded number of times (`n` and `m` respectively) before the search terminates.
 
**Q5. Could you binary search each row instead?**
Yes — for each of the `m` rows, binary search for `target`, giving `O(m log n)` time. This is a valid alternative, though slightly worse than `O(m + n)` for large, roughly square matrices (e.g., `m log n` vs `m + n` when `m ≈ n`: `n log n` vs `2n`).
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why does moving left from the top-right corner decrease the value?
**Answer:** Because each row is sorted in ascending order from left to right, so moving left means moving toward smaller values in that row.
 
**Question 2:** Why does moving down from the top-right corner increase the value?
**Answer:** Because each column is sorted in ascending order from top to bottom, so moving down means moving toward larger values in that column.
 
**Question 3:** Why can't we eliminate a whole row AND a whole column at the same time?
**Answer:** Because we can only be certain about the sort order relative to the *current* cell in one direction at a time — a single comparison tells us the current value is too big or too small, which only lets us safely discard the column (all values below are bigger) or the row (all values to the left are smaller), not both simultaneously.
 
**Question 4:** How would this change if you started from the bottom-left corner instead?
**Answer:** The elimination directions flip: value too big → move up (`row--`); value too small → move right (`col++`). The overall complexity and correctness remain identical — it's a mirrored version of the same idea.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Search a matrix sorted ascending in both rows AND columns, but not globally flattened-sorted"**
 
Immediately think:
 
```
STAIRCASE SEARCH
Start at TOP-RIGHT (or BOTTOM-LEFT) corner
 
value == target → found
value >  target → move toward smaller values (col-- from top-right)
value <  target → move toward bigger values  (row++ from top-right)
```
 
---
 
## 18. Visual Pattern
 
```
 1   4   7  11  15
 2   5   8  12  19
 3   6   9  16  22
10  13  14 17  24
18  21  23 26  30
                 ↑
              start here (top-right)
 
15 > 5 → move left
11 > 5 → move left
 7 > 5 → move left
 4 < 5 → move down
 5 == 5 → FOUND!
```
 
Think: **TOO BIG → GO LEFT. TOO SMALL → GO DOWN. EACH STEP KILLS A WHOLE ROW OR COLUMN.**
 
---
 
## 19. Alternative Approach
 
**Binary search each row independently:**
 
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int lo = 0, hi = row.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (row[mid] == target) {
                    return true;
                } else if (row[mid] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return false;
    }
}
```
 
This gives `O(m log n)` time and `O(1)` space — correct and reasonably efficient, though the staircase approach's `O(m + n)` is typically faster for large square-ish matrices.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "search a 2D structure." Think of it as **eliminating one full row or column per comparison, guided by a corner where the two sort orders point in opposite directions**.
 
> The matrix gives you two independent monotonic axes (rows increase rightward, columns increase downward). Starting at the corner where these axes pull in opposite directions turns every single comparison into a full row-or-column elimination — that's what collapses the search from `O(m×n)` down to `O(m+n)`.
 
```
Start at (0, cols-1)
      ↓
value == target?  → return true
      ↓ no
value > target?    → move left  (col--)
      ↓ no
value < target      → move down (row++)
      ↓
Repeat until out of bounds
      ↓
Return false
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Search a 2D Matrix | 74 | Binary Search (flattened matrix) |
| Search a 2D Matrix II | 240 | Staircase Search (corner elimination) |
| Kth Smallest Element in a Sorted Matrix | 378 | Binary Search / Heap |
| Younger Employee (staircase variants) | — | Two Pointers on Grids |
| Set Matrix Zeroes | 73 | Matrix / In-place |
| Spiral Matrix | 54 | Boundary Traversal |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 240 — SEARCH 2D MATRIX II       ║
╠══════════════════════════════════════════╣
║ Pattern: Staircase Search (Corner Start)  ║
║                                            ║
║ Start:                                    ║
║ row = 0                                   ║
║ col = cols - 1   (top-right corner)       ║
║                                            ║
║ Loop while row < rows && col >= 0:        ║
║ val = matrix[row][col]                    ║
║                                            ║
║ val == target → return true               ║
║ val >  target → col--   (go left)         ║
║ val <  target → row++   (go down)         ║
║                                            ║
║ Time: O(m + n)                            ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Search 2D Matrix II = Start top-right; too big go left, too small go down — each step kills a whole row or column.**
 
- `col--` → too big
- `row++` → too small
---
 
## 24. 30-Second Interview Explanation
 
> "I start the search at the top-right corner of the matrix. From there, moving left always decreases the value because each row is sorted ascending, and moving down always increases the value because each column is sorted ascending. So at each cell, if the value matches the target I'm done; if it's too big I move left, eliminating that entire column; if it's too small I move down, eliminating that entire row. Since each move eliminates a full row or column, the search takes at most `m + n` steps, giving O(m + n) time and O(1) space, without needing any extra data structures."
 
---
 
## 25. Final Takeaway
 
```
      SEARCH A 2D MATRIX II
                 ↓
     Start: row = 0, col = cols - 1
                 ↓
     matrix[row][col] == target?
           ↓          ↓
          yes         no
           ↓          ↓
        RETURN    val > target?
         true        ↓        ↓
                    yes       no
                     ↓        ↓
                  col--    row++
                     ↓        ↓
              REPEAT UNTIL OUT OF BOUNDS
                     ↓
              RETURN false
```
 
Remember:
 
```
Start top-right corner.
val >  target → col--  (move left, values shrink)
val <  target → row++  (move down, values grow)
val == target → found it
 
Each comparison eliminates a whole row or column → O(m + n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 240 — Search a 2D Matrix II**.
 
