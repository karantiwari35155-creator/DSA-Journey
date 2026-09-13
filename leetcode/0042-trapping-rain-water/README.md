<h2><a href="https://leetcode.com/problems/trapping-rain-water">42. Trapping Rain Water</a></h2><h3>Hard</h3><hr><p>Given <code>n</code> non-negative integers representing an elevation map where the width of each bar is <code>1</code>, compute how much water it can trap after raining.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png" style="width: 412px; height: 161px;" />
<pre>
<strong>Input:</strong> height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [4,2,0,3,2,5]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>

# LeetCode 42 — Trapping Rain Water
 
> **Problem:** LeetCode 42 — Trapping Rain Water
> **Difficulty:** Hard
> **Pattern:** Two Pointers / Precomputation (Prefix & Suffix Max)
> **Main Technique:** Opposite-Direction Two Pointers with Running Max
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.
 
### Example 1
 
```text
Input:
 
height = [0,1,0,2,1,0,1,3,2,1,2,1]
 
Output:
 
6
 
Explanation: The above elevation map (black section) is represented
by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain
water (blue section) are being trapped.
```
 
### Example 2
 
```text
Input:
 
height = [4,2,0,3,2,5]
 
Output:
 
9
```
 
---
 
## 2. Constraints
 
- `n == height.length`
- `1 <= n <= 2 * 10^4`
- `0 <= height[i] <= 10^5`
---
 
## 3. What Is the Problem Asking?
 
We're given a series of vertical bars (like a bar chart / elevation map), and we need to figure out how much rainwater gets **trapped between them** after it rains.
 
```
height = [0,1,0,2,1,0,1,3,2,1,2,1]
 
Visually (■ = bar, ~ = trapped water):
 
3           ■
2       ■ ~ ■ ~ ■
1   ■ ~ ■ ~ ■ ■ ~ ■ ~ ■
0 ■   ■       ■
 
Total trapped water = 6 units
```
 
Water sits at a given position **only if there's a taller (or equal) bar on both its left and its right** — the water is "trapped" between those two boundaries, and its depth at any position is determined by the **shorter** of the two surrounding tallest bars.
 
---
 
## 4. Core Idea
 
For **any given position `i`**, the amount of water it can hold is:
 
```
water[i] = min(maxLeft[i], maxRight[i]) - height[i]
```
 
Where:
 
- `maxLeft[i]` = the tallest bar anywhere to the left of (or at) position `i`.
- `maxRight[i]` = the tallest bar anywhere to the right of (or at) position `i`.
The water level at position `i` is capped by the **shorter** of these two walls — water can't rise higher than the shorter side without overflowing. If this computed level is less than `height[i]` itself, no water sits there (the bar itself is above the water line).
 
The most space-efficient way to compute this is with **two pointers moving toward each other**, tracking the running maximum from each side as we go — avoiding the need to precompute and store full `maxLeft[]` and `maxRight[]` arrays.
 
---
 
## 5. The Two-Pointer Approach
 
```java
int left = 0, right = height.length - 1;
int leftMax = 0, rightMax = 0;
int water = 0;
 
while (left < right) {
    if (height[left] < height[right]) {
        leftMax = Math.max(leftMax, height[left]);
        water += leftMax - height[left];
        left++;
    } else {
        rightMax = Math.max(rightMax, height[right]);
        water += rightMax - height[right];
        right--;
    }
}
```
 
### 5.1 Why Compare `height[left]` and `height[right]` to Decide Which Side to Process?
 
At each step, we process whichever side currently has the **smaller** height. This is safe because:
 
```
If height[left] < height[right]:
    We know for certain that SOME bar on the right side is >= height[right] > height[left].
    Therefore, the water level at 'left' is bounded by leftMax (whatever the tallest bar
    seen so far from the left is) — we don't even need to know the exact rightMax,
    because we already know it's tall enough not to be the limiting factor.
```
 
This is the key insight: we don't need the *exact* `maxRight[i]` for every position — we only need to know that it's **at least as tall** as the side we're currently not processing, which the comparison `height[left] < height[right]` guarantees.
 
---
 
## 6. Important Detail — Why This Doesn't Need Full Prefix/Suffix Arrays
 
A more direct (but less optimal) approach precomputes `maxLeft[]` and `maxRight[]` arrays fully, then computes `water[i]` for every `i`. The two-pointer approach achieves the exact same correctness **without ever storing these full arrays** — it only ever needs the running maximum **from the side currently being processed**, because the comparison at each step guarantees the other side's true maximum is irrelevant (it's already known to be at least as large).
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int trap(int[] height) {
 
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
 
        while (left < right) {
 
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
 
        return water;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Pointer and Tracking Variable Initialization**
 
```java
int left = 0;
int right = height.length - 1;
int leftMax = 0;
int rightMax = 0;
int water = 0;
```
 
`left` and `right` are the two converging pointers. `leftMax` and `rightMax` track the tallest bar seen so far from each respective side. `water` accumulates the total trapped water.
 
**Main Loop**
 
```java
while (left < right)
```
 
Continue until the pointers meet.
 
**Process the Shorter Side**
 
```java
if (height[left] < height[right]) {
    leftMax = Math.max(leftMax, height[left]);
    water += leftMax - height[left];
    left++;
}
```
 
If the left bar is shorter, we know the right side has *some* bar tall enough to not be the limiting factor — so we safely compute water using just `leftMax`.
 
```java
else {
    rightMax = Math.max(rightMax, height[right]);
    water += rightMax - height[right];
    right--;
}
```
 
Symmetric logic for the right side when it's the shorter (or equal) one.
 
**Return Total Water**
 
```java
return water;
```
 
---
 
## 9. Dry Run
 
Input:
 
```
height = [4, 2, 0, 3, 2, 5]
```
 
`left=0, right=5, leftMax=0, rightMax=0, water=0`
 
| step | left | right | height[left] | height[right] | comparison | action | water added | water total |
|---|---|---|---|---|---|---|---|---|
| 1 | 0 | 5 | 4 | 5 | 4 < 5 | process left | leftMax=4, 4-4=0 | 0 |
| 2 | 1 | 5 | 2 | 5 | 2 < 5 | process left | leftMax=4, 4-2=2 | 2 |
| 3 | 2 | 5 | 0 | 5 | 0 < 5 | process left | leftMax=4, 4-0=4 | 6 |
| 4 | 3 | 5 | 3 | 5 | 3 < 5 | process left | leftMax=4, 4-3=1 | 7 |
| 5 | 4 | 5 | 2 | 5 | 2 < 5 | process left | leftMax=4, 4-2=2 | 9 |
| — | 5 | 5 | — | — | left==right | loop ends | — | 9 |
 
**Final water = 9** ✅ (matches expected output)
 
---
 
## 10. Why Not Precompute Full `maxLeft[]` and `maxRight[]` Arrays?
 
The straightforward "prefix/suffix max" approach:
 
```java
int n = height.length;
int[] maxLeft = new int[n];
int[] maxRight = new int[n];
 
maxLeft[0] = height[0];
for (int i = 1; i < n; i++) {
    maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
}
 
maxRight[n - 1] = height[n - 1];
for (int i = n - 2; i >= 0; i--) {
    maxRight[i] = Math.max(maxRight[i + 1], height[i]);
}
 
int water = 0;
for (int i = 0; i < n; i++) {
    water += Math.min(maxLeft[i], maxRight[i]) - height[i];
}
```
 
This is also `O(n)` time, but uses `O(n)` **extra space** for the two arrays. The two-pointer approach achieves the exact same result using only a handful of variables — `O(1)` extra space — by cleverly avoiding the need to know the *exact* max on the side not currently being processed.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Comparing `leftMax` and `rightMax` instead of `height[left]` and `height[right]`**
Wrong: `if (leftMax < rightMax)` — this is subtly different logic and can lead to incorrect processing order.
Correct: compare the **current heights at the pointers**, `height[left]` and `height[right]`, to decide which side to process.
 
**Mistake 2 — Updating `leftMax`/`rightMax` after computing water instead of before**
Wrong: computing `water += leftMax - height[left]` before updating `leftMax` with the current `height[left]` — this could use a stale (too small) `leftMax`, or double-count/miscalculate at the very position itself.
Correct: always update the running max **first**, then compute the water contribution using the updated max.
 
**Mistake 3 — Forgetting that a negative or zero result at a position means no water**
Since `leftMax` (or `rightMax`) is updated to include the current position's height before computing water, `leftMax - height[left]` (or the right equivalent) can never actually go negative — this is a subtle but important reason the update-then-compute order matters.
 
**Mistake 4 — Using `<=` instead of `<` in the main comparison**
Both `<` and `<=` actually work correctly here for the overall algorithm's correctness (it only affects which side happens to process an exactly-equal-height pair first), but consistency matters — pick one and use it correctly throughout.
 
**Mistake 5 — Attempting brute force (checking every position against the true max on each side via nested loops)**
This is `O(n²)` time in the worst case, since for each position you'd scan left and scan right separately to find the true max on each side — far worse than the `O(n)` two-pointer or prefix/suffix array approaches for large inputs (up to `2×10^4` elements).
 
---
 
## 12. Edge Cases
 
**Empty or Single Bar:**
`height = [5]` → `left == right` immediately, loop never executes → Output: `0` (a single bar can't trap any water)
 
**Strictly Increasing Heights:**
`height = [1,2,3,4,5]` → no bar has anything taller to its right to trap water against → Output: `0`
 
**Strictly Decreasing Heights:**
`height = [5,4,3,2,1]` → symmetric to the above, no water trapped → Output: `0`
 
**All Same Height:**
`height = [3,3,3,3]` → completely flat, no trapping possible → Output: `0`
 
**Single Deep Valley:**
`height = [5,0,5]` → the middle position holds `min(5,5) - 0 = 5` units of water → Output: `5`
 
---
 
## 13. Time Complexity
 
Each pointer moves inward exactly once per step, and the two pointers together traverse the array exactly once in total.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only a handful of integer variables (`left`, `right`, `leftMax`, `rightMax`, `water`) are used, regardless of input size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Two pointers starting at opposite ends of the array, each tracking a running maximum from its own side. At each step, I process whichever side currently has the smaller height, since that side's water level is guaranteed to be bounded by its own running max — the other side is already known to be tall enough not to matter.
 
**Q2. Why is it safe to only track one side's max when processing that side?**
Because the decision of *which* side to process is based on `height[left] < height[right]` — if the left bar is shorter, we know for certain the right side has some bar at least that tall (specifically `height[right]` itself), so the true limiting factor for water at `left` must come from the left side's own running max, not the right's exact value.
 
**Q3. What's the brute-force approach and why is it worse?**
For each position, scan left to find the max and scan right to find the max, then take the minimum minus the current height. This is `O(n²)` in the worst case, since each position requires its own left/right scan.
 
**Q4. How does the prefix/suffix array approach compare to the two-pointer approach?**
Both are `O(n)` time, but the prefix/suffix approach uses `O(n)` extra space to explicitly store the max-so-far from each direction for every position, while the two-pointer approach achieves the same result in `O(1)` space by processing one side at a time and only needing that side's running max.
 
**Q5. Could you solve this with a monotonic stack instead?**
Yes — a decreasing monotonic stack of indices can also solve this by computing trapped water "layer by layer" whenever a taller bar is found, popping shorter bars and calculating the water trapped between them. This is also `O(n)` time but uses `O(n)` space for the stack in the worst case, making the two-pointer approach preferable when minimizing space is a priority.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why does the algorithm update `leftMax` (or `rightMax`) *before* computing the water contribution at that position?
**Answer:** Updating first ensures `leftMax` always represents "the tallest bar from the left up to and including the current position," which correctly caps the water level — if we computed water before updating, we might use a stale, too-small max from before the current bar was considered.
 
**Question 2:** Why can we be confident that processing the smaller side never underestimates or overestimates the trapped water?
**Answer:** When we process the side with the smaller current height, we know the other side has a wall at least as tall as the current comparison value — so the *actual* limiting wall for the smaller side must be on its own side (bounded by its own running max), making our calculation using just that side's max provably correct.
 
**Question 3:** What would go wrong if you used a single shared `maxSoFar` variable instead of separate `leftMax` and `rightMax`?
**Answer:** You'd lose the ability to correctly track two independent "walls" growing inward from both ends simultaneously — a single shared max wouldn't correctly represent the tallest bar specifically to the left of the left pointer versus specifically to the right of the right pointer, breaking the core two-pointer invariant.
 
**Question 4:** How would you adapt this two-pointer technique to a 2D version of this problem (Trapping Rain Water II, LeetCode 407)?
**Answer:** The 2D version requires a fundamentally different approach — typically a min-heap (priority queue) based approach that processes cells starting from the boundary inward, always expanding from the currently lowest boundary wall, since a simple two-pointer sweep doesn't generalize cleanly to two dimensions.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Compute trapped water / bounded area between varying heights along a 1D elevation profile"**
 
Immediately think:
 
```
TWO POINTERS FROM BOTH ENDS
leftMax, rightMax = running maximums from each side
 
Process the SHORTER side:
  water at that position = runningMax(that side) - height(that side)
 
The taller side is always guaranteed to be a "tall enough" wall,
so you never need its exact value while processing the shorter side.
```
 
---
 
## 18. Visual Pattern
 
```
height:  4   2   0   3   2   5
         ↑                   ↑
       left                right
 
height[left]=4 < height[right]=5 → process left
leftMax = max(0,4) = 4
water at index 0: 4 - 4 = 0
 
height:      2   0   3   2   5
             ↑               ↑
           left            right
 
height[left]=2 < height[right]=5 → process left
leftMax = max(4,2) = 4
water at index 1: 4 - 2 = 2
 
... continues, always chasing the shorter wall inward ...
```
 
Think: **THE SHORTER WALL DECIDES THE WATER LEVEL. ALWAYS CHASE THE SHORTER SIDE INWARD.**
 
---
 
## 19. Alternative Approach
 
**Monotonic Stack:**
 
```java
import java.util.Stack;
 
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        int i = 0;
 
        while (i < height.length) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
 
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                water += distance * boundedHeight;
            }
            stack.push(i);
            i++;
        }
 
        return water;
    }
}
```
 
This processes the array once, using a stack to track indices of bars in decreasing height order, computing trapped water "layer by layer" whenever a taller bar causes a pop. This is `O(n)` time but `O(n)` space in the worst case (a strictly decreasing then increasing array could push most indices onto the stack).
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "simulate rainfall." Think of it as **each position's water level being bounded by the shorter of its two horizon walls — and realizing you only ever need the shorter wall's exact height to compute that bound**.
 
> The elegant insight of the two-pointer solution is recognizing that you don't need perfect information about both sides at every position — you only need to know *which side is currently the bottleneck*, and process it using information you already have. This "you don't need the full picture, just enough to know who's the limiting factor" mindset is a recurring theme in greedy and two-pointer array problems.
 
```
left = 0, right = n-1
leftMax = 0, rightMax = 0
      ↓
height[left] < height[right] ?
      ↓ yes                          ↓ no
Update leftMax                   Update rightMax
Add (leftMax - height[left])     Add (rightMax - height[right])
Move left++                      Move right--
      ↓
Repeat until left >= right
      ↓
Return accumulated water
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Trapping Rain Water | 42 | Two Pointers / Prefix-Suffix Max |
| Trapping Rain Water II | 407 | Min-Heap / BFS (2D version) |
| Container With Most Water | 11 | Two Pointers |
| Largest Rectangle in Histogram | 84 | Monotonic Stack |
| Product of Array Except Self | 238 | Prefix/Suffix Products |
| Candy | 135 | Two-pass Greedy |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 42 — TRAPPING RAIN WATER        ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers + Running Max       ║
║                                            ║
║ Init:                                     ║
║ left=0, right=n-1                         ║
║ leftMax=0, rightMax=0, water=0            ║
║                                            ║
║ Loop while left < right:                  ║
║ height[left] < height[right]?             ║
║   yes → leftMax=max(leftMax,height[left]) ║
║         water += leftMax - height[left]   ║
║         left++                            ║
║   no  → rightMax=max(rightMax,height[r])  ║
║         water += rightMax - height[right] ║
║         right--                           ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Trapping Rain Water = Two pointers chase the shorter wall inward; that wall's running max IS the water level at that position.**
 
- `height[left] < height[right]` → process left using `leftMax`
- otherwise → process right using `rightMax`
---
 
## 24. 30-Second Interview Explanation
 
> "I use two pointers starting at opposite ends of the array, each tracking the tallest bar seen so far from its own side. At every step, I look at which pointer currently has the smaller height, and process that side — because I know for certain the other side has a wall at least that tall, meaning the water level at the smaller side is entirely determined by its own running maximum. I update that running max, add the difference between it and the current bar's height as trapped water, and move that pointer inward. This continues until the pointers meet. Because I never need the exact maximum from the side I'm not processing — only that it's tall enough — I can solve this in O(n) time using just O(1) extra space, without needing separate prefix and suffix max arrays."
 
---
 
## 25. Final Takeaway
 
```
        TRAPPING RAIN WATER
                 ↓
    left=0, right=n-1
    leftMax=0, rightMax=0
                 ↓
    height[left] < height[right] ?
           ↓             ↓
          yes            no
           ↓             ↓
    leftMax=max(...)   rightMax=max(...)
    water += leftMax    water += rightMax
    - height[left]      - height[right]
    left++              right--
           ↓             ↓
    REPEAT UNTIL left >= right
           ↓
    RETURN water
```
 
Remember:
 
```
Water level at a position = min(maxLeft, maxRight) - height
 
Process the shorter side; its own running max IS the true bound,
since the taller side is already known to be tall enough.
 
Two pointers + running max = O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 42 — Trapping Rain Water**.
 
