<h2><a href="https://leetcode.com/problems/container-with-most-water">11. Container With Most Water</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>height</code> of length <code>n</code>. There are <code>n</code> vertical lines drawn such that the two endpoints of the <code>i<sup>th</sup></code> line are <code>(i, 0)</code> and <code>(i, height[i])</code>.</p>

<p>Find two lines that together with the x-axis form a container, such that the container contains the most water.</p>

<p>Return <em>the maximum amount of water a container can store</em>.</p>

<p><strong>Notice</strong> that you may not slant the container.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg" style="width: 600px; height: 287px;" />
<pre>
<strong>Input:</strong> height = [1,8,6,2,5,4,8,3,7]
<strong>Output:</strong> 49
<strong>Explanation:</strong> The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>


# LeetCode 11 — Container With Most Water
 
> **Problem:** LeetCode 11 — Container With Most Water
> **Difficulty:** Medium
> **Pattern:** Two Pointers / Greedy
> **Main Technique:** Opposite-Direction Two Pointers, Move the Shorter Wall
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `i`-th line are `(i, 0)` and `(i, height[i])`.
 
Find two lines that together with the x-axis form a container, such that the container contains the most water.
 
Return the maximum amount of water a container can store.
 
**Notice** that you may not slant the container.
 
### Example 1
 
```text
Input:
 
height = [1,8,6,2,5,4,8,3,7]
 
Output:
 
49
 
Explanation: The above vertical lines are represented by array
[1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section)
the container can contain is 49.
```
 
### Example 2
 
```text
Input:
 
height = [1,1]
 
Output:
 
1
```
 
---
 
## 2. Constraints
 
- `n == height.length`
- `2 <= n <= 10^5`
- `0 <= height[i] <= 10^4`
---
 
## 3. What Is the Problem Asking?
 
We need to pick **two lines** (indices `i` and `j`) that, together with the x-axis, form a container. The amount of water it can hold is:
 
```
area = width × height
     = (j - i) × min(height[i], height[j])
```
 
The `min()` is crucial — water can't rise above the **shorter** of the two walls, since the container isn't slanted (water would simply spill over the shorter side).
 
```
height = [1,8,6,2,5,4,8,3,7]
index  =  0 1 2 3 4 5 6 7 8
 
Picking i=1 (height 8) and j=8 (height 7):
width = 8 - 1 = 7
height = min(8, 7) = 7
area = 7 × 7 = 49  ← this is the maximum
```
 
We need to find the pair `(i, j)` that **maximizes** this area, out of all `O(n²)` possible pairs.
 
---
 
## 4. Core Idea
 
Instead of checking every pair (which would be `O(n²)`), use **two pointers starting at the two ends** of the array and move them **inward**, always moving the pointer at the **shorter** line:
 
```java
int left = 0, right = height.length - 1;
int maxArea = 0;
 
while (left < right) {
    int width = right - left;
    int currentHeight = Math.min(height[left], height[right]);
    maxArea = Math.max(maxArea, width * currentHeight);
 
    if (height[left] < height[right]) {
        left++;
    } else {
        right--;
    }
}
```
 
The key insight: starting with the **widest possible container** (both pointers at the extreme ends), any move that **keeps or shrinks** the limiting (shorter) height can never produce a better result than what we've already checked — so we should always move the pointer at the shorter wall, **hoping** to find a taller wall that might compensate for the reduced width.
 
---
 
## 5. Why Always Move the Shorter Pointer?
 
At each step, the **width** always shrinks as pointers move inward — that part is guaranteed to get worse. The only way to potentially find a *larger* area is to find a taller wall to increase the height factor. Since the **current area is limited by the shorter wall**, moving the taller wall inward can **never help** — you'd only ever get an equal or smaller height cap while still losing width:
 
```
If we move the TALLER pointer inward:
  - width decreases
  - height cap is still bounded by the (unchanged) shorter wall,
    or gets WORSE if the new position is even shorter
  → strictly cannot improve the result
 
If we move the SHORTER pointer inward:
  - width decreases
  - height cap MIGHT improve, if we find a taller wall
  → this is the only move that has any chance of finding a better area
```
 
This greedy elimination argument is what guarantees correctness — we never need to check the pair we're "giving up" on, because it's mathematically provable that no better answer could come from keeping the shorter wall in place while moving the taller one.
 
---
 
## 6. Important Detail — Why This Never Misses the Optimal Answer
 
Suppose at some point `height[left] < height[right]`. The current area is `(right - left) * height[left]`. Now consider **any** other pairing involving `left` and some index `k < right`:
 
```
area(left, k) = (k - left) * min(height[left], height[k])
             <= (k - left) * height[left]      [since min(...) <= height[left]]
             <  (right - left) * height[left]  [since k < right means smaller width]
             = area(left, right)   [current area]
```
 
So pairing `left` with **any** index strictly between `left` and `right` can never beat the current `area(left, right)` — meaning it's safe to permanently discard `left` (move past it) once we've compared it against `right`, because no future pairing of `left` with something closer than `right` could ever be better than what we already computed.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int maxArea(int[] height) {
 
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
 
        while (left < right) {
 
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * currentHeight);
 
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
 
        return maxArea;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Pointer Initialization**
 
```java
int left = 0;
int right = height.length - 1;
int maxArea = 0;
```
 
Start with the widest possible container — both pointers at the array's extremes.
 
**Main Loop**
 
```java
while (left < right)
```
 
Continue until the pointers meet.
 
**Compute Current Area**
 
```java
int width = right - left;
int currentHeight = Math.min(height[left], height[right]);
maxArea = Math.max(maxArea, width * currentHeight);
```
 
Width is simply the distance between the pointers; height is capped by the shorter of the two lines. Update the running maximum if this container is better.
 
**Move the Shorter Pointer Inward**
 
```java
if (height[left] < height[right]) {
    left++;
} else {
    right--;
}
```
 
Always advance whichever side is currently the limiting (shorter) wall, since that's the only move with any chance of improving the result.
 
**Return the Best Area Found**
 
```java
return maxArea;
```
 
---
 
## 9. Dry Run
 
Input:
 
```
height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
index  =  0  1  2  3  4  5  6  7  8
```
 
`left=0, right=8, maxArea=0`
 
| step | left | right | height[left] | height[right] | width | min height | area | maxArea | move |
|---|---|---|---|---|---|---|---|---|---|
| 1 | 0 | 8 | 1 | 7 | 8 | 1 | 8 | 8 | left++ (1<7) |
| 2 | 1 | 8 | 8 | 7 | 7 | 7 | 49 | 49 | right-- (8>=7) |
| 3 | 1 | 7 | 8 | 3 | 6 | 3 | 18 | 49 | right-- (8>=3) |
| 4 | 1 | 6 | 8 | 8 | 5 | 8 | 40 | 49 | right-- (8>=8) |
| 5 | 1 | 5 | 8 | 4 | 4 | 4 | 16 | 49 | right-- |
| 6 | 1 | 4 | 8 | 5 | 3 | 5 | 15 | 49 | right-- |
| 7 | 1 | 3 | 8 | 2 | 2 | 2 | 4 | 49 | right-- |
| 8 | 1 | 2 | 8 | 6 | 1 | 6 | 6 | 49 | right-- |
| — | 1 | 1 | — | — | — | — | — | 49 | loop ends |
 
**Final maxArea = 49** ✅ (matches expected output)
 
---
 
## 10. Why Not Check Every Pair (Brute Force)?
 
The naive approach checks every `(i, j)` pair:
 
```java
int maxArea = 0;
for (int i = 0; i < height.length; i++) {
    for (int j = i + 1; j < height.length; j++) {
        int area = (j - i) * Math.min(height[i], height[j]);
        maxArea = Math.max(maxArea, area);
    }
}
```
 
This is `O(n²)`, which for `n` up to `10^5` means up to `~10^10` operations — far too slow. The two-pointer approach reduces this to `O(n)` by mathematically proving that entire ranges of pairs can be safely skipped without ever checking them individually.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Moving the taller pointer instead of the shorter one**
Wrong: `if (height[left] > height[right]) { left++; } else { right--; }` — this is backwards logic and can skip over the actual optimal pairing.
Correct: move the pointer at the **shorter** (limiting) line.
 
**Mistake 2 — Moving both pointers simultaneously**
Wrong: incrementing `left` and decrementing `right` together in the same step — this can skip over the true optimal pair, since only one side's movement is justified as "safe to discard" at any given step.
Correct: move only one pointer per iteration, based on which side is currently shorter.
 
**Mistake 3 — Using `<=` instead of computing `min()` correctly**
Wrong: `Math.max(height[left], height[right])` for the height factor — this ignores the physical reality that water spills over the shorter wall.
Correct: `Math.min(height[left], height[right])`.
 
**Mistake 4 — Forgetting this is different from Trapping Rain Water (LC 42)**
This problem asks for the max area using **exactly two** lines as the container walls (everything between them is irrelevant to the area calculation), whereas Trapping Rain Water sums up trapped water across **every** position based on surrounding walls. Applying the wrong problem's logic here gives incorrect results.
 
**Mistake 5 — Off-by-one in width calculation**
Wrong: `right - left + 1` or `right - left - 1` — the width between two vertical lines at positions `left` and `right` is simply `right - left` (the distance between their x-coordinates).
Correct: `width = right - left`.
 
---
 
## 12. Edge Cases
 
**Two Lines Only:**
`[1,1]` → `left=0, right=1`, width=1, height=min(1,1)=1 → Output: `1`
 
**All Same Height:**
`[5,5,5,5]` → widest pair (first and last) always gives the max area since height never changes → Output depends on `n`, e.g., for 4 elements: `3 × 5 = 15`
 
**Strictly Increasing Heights:**
`[1,2,3,4,5]` → the algorithm must explore intermediate widths since the tallest walls are at the far right — moving the shorter (left) pointer inward tests progressively taller left walls against the fixed tall right wall.
 
**Strictly Decreasing Heights:**
Symmetric to increasing — moving the shorter (right) pointer inward each time.
 
**One Very Tall Line Among Short Ones:**
`[1,100,1,1,1]` → the widest container (using index 0 and index 4) will likely be limited by the shorter of those two short walls; the algorithm correctly explores whether pairing the tall middle line with something can do better, though in this specific case the tall single line can only ever pair with a short wall on one side, capping its contribution.
 
---
 
## 13. Time Complexity
 
Each step moves either `left` forward or `right` backward — combined, the pointers can move at most `n` times before meeting.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only a few integer variables (`left`, `right`, `maxArea`, and temporaries for width/height) are used, regardless of input size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Two pointers starting at both ends of the array, computing the area at each step and always moving the pointer at the shorter line inward, since that's the only move with any chance of improving the result.
 
**Q2. Why is it safe to always move the shorter pointer, rather than checking all pairs?**
Because for a fixed shorter wall, pairing it with any index closer than the current opposite pointer can only produce a smaller or equal area — the width strictly decreases, and the height cap can't improve beyond the current shorter wall's height. This means once we've compared the shorter wall against the current opposite wall, no closer pairing involving that shorter wall could ever be better, so it's mathematically safe to move past it.
 
**Q3. What's the brute-force alternative and why is it worse?**
Checking every pair `(i, j)` with `i < j` gives `O(n²)` time, which is far too slow for `n` up to `10^5` (potentially ~10 billion operations). The two-pointer approach reduces this to `O(n)`.
 
**Q4. How is this different from Trapping Rain Water (LeetCode 42)?**
This problem picks exactly **two** lines to form a single container, ignoring everything between them — only the two endpoint heights and their distance matter. Trapping Rain Water instead computes water trapped at **every** position based on the tallest walls to its left and right, summing contributions across the whole array — a fundamentally different aggregation.
 
**Q5. Could this be solved with dynamic programming or precomputed prefix/suffix max arrays like Trapping Rain Water?**
Not directly in the same way — since only two specific lines matter here (not a running max over all positions), prefix/suffix max arrays don't naturally map onto this problem's requirement. The greedy two-pointer elimination argument is the standard and most efficient technique for this particular problem.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why does moving the taller pointer while keeping the shorter one fixed never help?
**Answer:** The area is capped by `min(height[left], height[right])`. If we keep the shorter wall fixed and move the taller wall inward, the width shrinks while the height cap remains bounded by the same shorter wall (or gets worse if the new position happens to be even shorter) — so the resulting area can only be equal to or worse than before, never better.
 
**Question 2:** Could there be a case where moving the shorter pointer produces a strictly worse immediate area, but is still the correct move?
**Answer:** Yes — the *immediate* area computed at the next step might indeed be smaller (since width has decreased), but the point of moving the shorter pointer is to search for a *potentially* taller wall further inward that could eventually produce a **larger** area at some future step, not to guarantee immediate improvement at every single step.
 
**Question 3:** How would you prove no valid pair is ever skipped incorrectly by this greedy approach?
**Answer:** By the argument in Section 6: for any current `left` and `right` where `height[left] < height[right]`, any pairing of `left` with an index `k` strictly between `left` and `right` is provably no better than the current `area(left, right)`, because it has both a smaller width and a height cap that's still bounded by `height[left]`. This means it's always safe to advance past `left` without checking those intermediate pairings individually.
 
**Question 4:** What is the maximum number of iterations the while loop can run?
**Answer:** At most `n - 1` iterations, since each iteration strictly shrinks the `[left, right]` window by moving exactly one pointer inward, and the loop stops once `left >= right`.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Pick two elements from an array to maximize width × min(height) (or a similar area-like product)"**
 
Immediately think:
 
```
TWO POINTERS FROM OPPOSITE ENDS
left  = start
right = end
 
At each step:
  area = (right - left) * min(height[left], height[right])
  update maxArea
 
  move the SHORTER side inward (that's the only move that can help)
```
 
---
 
## 18. Visual Pattern
 
```
height:  1   8   6   2   5   4   8   3   7
         ↑                               ↑
       left                            right
 
width = 8, min(1,7)=1, area=8
height[left]=1 < height[right]=7 → move left inward
 
height:  1   8   6   2   5   4   8   3   7
             ↑                           ↑
           left                        right
 
width = 7, min(8,7)=7, area=49  ← BEST SO FAR
height[left]=8 >= height[right]=7 → move right inward
 
... continues, always chasing whichever side is currently shorter ...
```
 
Think: **THE SHORTER WALL IS THE BOTTLENECK. MOVE IT — IT'S THE ONLY SIDE THAT CAN POSSIBLY IMPROVE THINGS.**
 
---
 
## 19. Alternative Approach
 
There isn't a meaningfully different **efficient** alternative for this specific problem beyond the two-pointer greedy technique — the brute-force `O(n²)` all-pairs check (shown in Section 10) is really the only other approach, and it's asymptotically worse. Some solutions add a minor optimization to skip over consecutive shorter bars on the moving side without recomputing area at every single intermediate step (since a shorter bar than the one just processed on the same side can't possibly produce a better result), but this doesn't change the overall `O(n)` worst-case complexity — it's a constant-factor practical speedup at best.
 
```java
// Minor optimization: skip past shorter bars on the moving side
while (left < right && height[left] <= height[left])  // conceptually skip non-improving bars
```
 
In practice, the simple version shown in Section 7 is what's expected and is already optimal in Big-O terms.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "check pairs of walls." Think of it as **greedily eliminating provably suboptimal search space using a width-vs-height tradeoff argument**.
 
> The elegant insight here is recognizing that starting from the widest possible container and only ever sacrificing width when there's a *chance* of gaining height (by moving the currently limiting, shorter wall) is provably never worse than checking every pair exhaustively. This "start at the extreme, greedily discard the side that can't possibly help" mindset is the same one that powers Two Sum II's opposite-direction pointers and many other two-pointer array problems.
 
```
left = 0, right = n - 1, maxArea = 0
      ↓
Compute area = (right - left) * min(height[left], height[right])
      ↓
Update maxArea
      ↓
height[left] < height[right] ?
      ↓ yes              ↓ no
   left++              right--
      ↓
Repeat until left >= right
      ↓
Return maxArea
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Container With Most Water | 11 | Two Pointers (Greedy Elimination) |
| Trapping Rain Water | 42 | Two Pointers + Running Max |
| Two Sum II - Input Array Is Sorted | 167 | Two Pointers |
| 3Sum | 15 | Sort + Two Pointers |
| Largest Rectangle in Histogram | 84 | Monotonic Stack |
| Squares of a Sorted Array | 977 | Two Pointers |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 11 — CONTAINER WITH MOST WATER  ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers (Greedy Elimination)║
║                                            ║
║ Init:                                     ║
║ left = 0                                  ║
║ right = height.length - 1                 ║
║ maxArea = 0                               ║
║                                            ║
║ Loop while left < right:                  ║
║ width = right - left                      ║
║ h = min(height[left], height[right])      ║
║ maxArea = max(maxArea, width * h)         ║
║                                            ║
║ height[left] < height[right] → left++     ║
║ else                          → right--   ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Container With Most Water = Start widest, always move the shorter wall inward — it's the only side that can possibly do better.**
 
- `height[left] < height[right]` → `left++`
- otherwise → `right--`
---
 
## 24. 30-Second Interview Explanation
 
> "I use two pointers, starting at the two ends of the array, since that gives the widest possible container. At each step, I compute the area using the distance between the pointers and the shorter of the two heights, since water can't rise above the shorter wall, and I track the maximum area seen. Then I move whichever pointer is at the shorter line inward — because keeping the shorter wall fixed and moving the taller one can never improve the result, since width only shrinks and the height cap stays the same or gets worse. Moving the shorter wall is the only move with any chance of finding a taller wall that compensates for the lost width. This greedy elimination lets me solve the problem in O(n) time and O(1) space, instead of checking all O(n²) pairs."
 
---
 
## 25. Final Takeaway
 
```
       CONTAINER WITH MOST WATER
                  ↓
     left = 0, right = n - 1
                  ↓
     area = (right-left) * min(height[left], height[right])
                  ↓
     maxArea = max(maxArea, area)
                  ↓
     height[left] < height[right] ?
           ↓             ↓
          yes            no
           ↓             ↓
        left++        right--
           ↓             ↓
     REPEAT UNTIL left >= right
                  ↓
             RETURN maxArea
```
 
Remember:
 
```
area = (right - left) * min(height[left], height[right])
 
Always move the SHORTER wall inward — it's the only side
that could possibly lead to a larger area.
 
Two pointers, greedy elimination → O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 11 — Container With Most Water**.
 
<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>
