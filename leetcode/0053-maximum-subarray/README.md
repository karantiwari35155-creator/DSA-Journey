<h2><a href="https://leetcode.com/problems/maximum-subarray">53. Maximum Subarray</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code>, find the <span data-keyword="subarray-nonempty">subarray</span> with the largest sum, and return <em>its sum</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The subarray [4,-1,2,1] has the largest sum 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The subarray [1] has the largest sum 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,-1,7,8]
<strong>Output:</strong> 23
<strong>Explanation:</strong> The subarray [5,4,-1,7,8] has the largest sum 23.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution using the <strong>divide and conquer</strong> approach, which is more subtle.</p>

# LeetCode 53 — Maximum Subarray
 
> **Problem:** LeetCode 53 — Maximum Subarray
> **Difficulty:** Medium
> **Pattern:** Dynamic Programming / Greedy / Array
> **Main Technique:** Kadane's Algorithm
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given an integer array `nums`, find the subarray with the largest sum, and return its sum.
 
### Example 1
 
```text
Input:
 
nums = [-2,1,-3,4,-1,2,1,-5,4]
 
Output:
 
6
 
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
```
 
### Example 2
 
```text
Input:
 
nums = [1]
 
Output:
 
1
 
Explanation: The subarray [1] has the largest sum 1.
```
 
### Example 3
 
```text
Input:
 
nums = [5,4,-1,7,8]
 
Output:
 
23
 
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
```
 
---
 
## 2. Constraints
 
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`
**Follow up:** If you have figured out the `O(n)` solution, try coding another solution using the **divide and conquer** approach, which is more subtle.
 
---
 
## 3. What Is the Problem Asking?
 
We need to find a **contiguous** run of elements (a subarray, not a subsequence — no skipping elements) whose sum is as large as possible.
 
```
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 
Checking [4, -1, 2, 1]: 4 + (-1) + 2 + 1 = 6  ← this is the maximum
 
Other candidates:
[4]: 4
[4,-1,2,1,-5,4]: 5
[-2,1,-3,4,-1,2,1]: 2
```
 
A brute-force check of every possible subarray would be `O(n²)` (or `O(n³)` if sums are recomputed naively each time) — we want something much faster.
 
---
 
## 4. Core Idea — Kadane's Algorithm
 
At every position, we ask a simple question: **"Is it better to extend the previous subarray by adding the current element, or to start a brand new subarray at the current element?"**
 
```java
int currentSum = nums[0];
int maxSum = nums[0];
 
for (int i = 1; i < nums.length; i++) {
    currentSum = Math.max(nums[i], currentSum + nums[i]);
    maxSum = Math.max(maxSum, currentSum);
}
```
 
The logic:
 
- `currentSum + nums[i]` represents **extending** the current best-ending-here subarray.
- `nums[i]` alone represents **restarting** a new subarray from this position.
We take whichever is larger. This works because if `currentSum` ever becomes negative, it can only ever *hurt* future sums if we keep carrying it forward — so it's always at least as good (and often better) to abandon it and start fresh.
 
---
 
## 5. The Two Choices Per Element
 
### 5.1 Extend the Existing Subarray
 
```java
currentSum + nums[i]
```
 
If the running sum so far is still a net positive contribution, keep building on it.
 
### 5.2 Start a New Subarray Here
 
```java
nums[i]
```
 
If the running sum so far has become a net drag (negative), it's better to discard it entirely and start over from the current element alone.
 
```java
currentSum = Math.max(nums[i], currentSum + nums[i]);
```
 
This single line captures the entire decision at each step.
 
---
 
## 6. Important Detail — Why We Also Track a Separate `maxSum`
 
`currentSum` only tracks the best sum of a subarray **ending exactly at the current position** — it can go up and down as we scan, and it doesn't remember the best value it ever reached. We need a **separate** variable, `maxSum`, to remember the **global best** seen across all positions so far:
 
```java
maxSum = Math.max(maxSum, currentSum);
```
 
Without this second tracking variable, we'd only know the best subarray ending at the *very last* position, not the true overall maximum.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int maxSubArray(int[] nums) {
 
        int currentSum = nums[0];
        int maxSum = nums[0];
 
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
 
        return maxSum;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Initialization**
 
```java
int currentSum = nums[0];
int maxSum = nums[0];
```
 
Both start at the first element, since a subarray of length 1 (just `nums[0]`) is always a valid candidate.
 
**Loop From the Second Element Onward**
 
```java
for (int i = 1; i < nums.length; i++)
```
 
We already accounted for `nums[0]` in the initialization, so the loop starts at index `1`.
 
**Decide: Extend or Restart**
 
```java
currentSum = Math.max(nums[i], currentSum + nums[i]);
```
 
Compare "carry forward the existing subarray plus this element" against "start fresh from just this element," keeping whichever is larger.
 
**Update the Global Best**
 
```java
maxSum = Math.max(maxSum, currentSum);
```
 
Every time `currentSum` changes, check if it's now the best subarray sum we've seen anywhere in the array so far.
 
**Return the Global Best**
 
```java
return maxSum;
```
 
---
 
## 9. Dry Run
 
Input:
 
```
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```
 
`currentSum = -2`, `maxSum = -2`
 
| i | nums[i] | currentSum + nums[i] | max(nums[i], sum) | currentSum | maxSum |
|---|---|---|---|---|---|
| 1 | 1 | -2+1=-1 | max(1,-1)=1 | 1 | 1 |
| 2 | -3 | 1+(-3)=-2 | max(-3,-2)=-2 | -2 | 1 |
| 3 | 4 | -2+4=2 | max(4,2)=4 | 4 | 4 |
| 4 | -1 | 4+(-1)=3 | max(-1,3)=3 | 3 | 4 |
| 5 | 2 | 3+2=5 | max(2,5)=5 | 5 | 5 |
| 6 | 1 | 5+1=6 | max(1,6)=6 | 6 | 6 |
| 7 | -5 | 6+(-5)=1 | max(-5,1)=1 | 1 | 6 |
| 8 | 4 | 1+4=5 | max(4,5)=5 | 5 | 6 |
 
**Final maxSum = 6** ✅ (matches expected output — the subarray `[4,-1,2,1]` achieving this sum)
 
---
 
## 10. Why Not Brute Force Every Subarray?
 
The naive approach checks every possible `(start, end)` pair:
 
```java
int maxSum = Integer.MIN_VALUE;
for (int i = 0; i < nums.length; i++) {
    int sum = 0;
    for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        maxSum = Math.max(maxSum, sum);
    }
}
```
 
This is `O(n²)` — for `n` up to `10^5`, that's up to `~10^10` operations, far too slow. Kadane's Algorithm reduces this to a single `O(n)` pass by recognizing that we never need to re-examine all possible starting points explicitly — the "extend or restart" decision at each position implicitly captures the best choice for every possible subarray ending there.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Initializing `maxSum` (or `currentSum`) to `0` instead of `nums[0]`**
Wrong: `int maxSum = 0;` — this silently breaks the algorithm for arrays where **every** element is negative (e.g., `[-3,-1,-2]`), since the correct answer should be `-1` (the least negative single element), but initializing to `0` would incorrectly suggest an empty subarray (sum `0`) is better.
Correct: initialize both to `nums[0]`, and never allow the "empty subarray" option, since the problem requires at least one element.
 
**Mistake 2 — Forgetting to update `maxSum` every iteration**
Wrong: only updating `maxSum` at the very end, after the loop — this misses cases where the best subarray sum occurs partway through, before `currentSum` later drops.
Correct: update `maxSum = Math.max(maxSum, currentSum)` inside the loop, every iteration.
 
**Mistake 3 — Confusing "reset `currentSum` to 0" with "reset to `nums[i]`"**
Wrong: `currentSum = Math.max(0, currentSum + nums[i]);` then separately tracking `nums[i]` — this variant is common for problems where all values are non-negative or where you explicitly want to disallow negative subarrays, but for this problem (where all elements might be negative and a subarray must have at least one element), you must compare against `nums[i]` itself, not `0`.
Correct: `currentSum = Math.max(nums[i], currentSum + nums[i]);`.
 
**Mistake 4 — Starting the loop at `i = 0` after already initializing `currentSum = nums[0]`**
Wrong: this double-counts `nums[0]`, since the first loop iteration would apply the max logic to it again.
Correct: initialize with `nums[0]`, then loop starting from `i = 1`.
 
**Mistake 5 — Assuming Kadane's Algorithm returns the subarray itself, not just the sum**
This implementation only returns the maximum **sum**. If asked to return the actual subarray (its start and end indices), you'd need to additionally track the starting index whenever a "restart" happens, and record the best `(start, end)` pair whenever `maxSum` updates.
 
---
 
## 12. Edge Cases
 
**Single Element:**
`[1]` → `currentSum = maxSum = 1` from initialization, loop doesn't execute → Output: `1`
 
**All Negative Numbers:**
`[-2,-1,-3]` → the algorithm correctly finds the least negative single element (`-1`) as the best "subarray," since starting fresh at each very negative element is always chosen over carrying forward an even worse running sum → Output: `-1`
 
**All Positive Numbers:**
`[1,2,3,4]` → the entire array is always the best choice, since every extension only adds more positive value → Output: `10`
 
**Single Very Negative Number Surrounded by Positives:**
`[5,4,-1,7,8]` → the entire array is worth keeping despite the `-1` dip, since the surrounding positives more than compensate → Output: `23`
 
**Mix With a Clear Best Segment:**
`[-2,1,-3,4,-1,2,1,-5,4]` → correctly isolates `[4,-1,2,1]` as the optimal segment → Output: `6`
 
---
 
## 13. Time Complexity
 
We make exactly one pass through the array, doing constant work per element.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only two variables (`currentSum`, `maxSum`) are used, regardless of input size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Kadane's Algorithm — a single pass where, at each position, I decide whether to extend the current running subarray sum or start fresh from the current element, keeping track of the best sum seen overall.
 
**Q2. Why does "restart if negative" work correctly?**
Because carrying forward a negative running sum can only ever reduce any future sum you'd add it to — so it's never beneficial to keep a negative prefix; discarding it and starting fresh from the current element is always at least as good, and often strictly better.
 
**Q3. Why must you initialize with `nums[0]` instead of `0`?**
Because the problem guarantees the array has at least one element and requires a non-empty subarray — if all elements are negative, the correct answer is the least negative single element, not `0` (which would incorrectly imply an "empty subarray" is a valid, better option).
 
**Q4. Is this dynamic programming or greedy?**
It's often described as a form of dynamic programming (each `currentSum` represents an optimal substructure — the best subarray ending at position `i`, computed from the best subarray ending at position `i-1`), though because the recurrence only ever depends on the immediately preceding state and requires no explicit DP array, it also reads very naturally as a greedy, single-pass algorithm.
 
**Q5. What's the divide-and-conquer alternative mentioned in the follow-up, and how does it compare?**
It splits the array in half recursively, computing the max subarray sum entirely within the left half, entirely within the right half, and the max sum of a subarray that **crosses the midpoint**, then takes the best of the three. This runs in `O(n log n)` time — asymptotically worse than Kadane's `O(n)`, but it's a classic exercise in applying the divide-and-conquer paradigm and is sometimes asked as a follow-up to demonstrate broader algorithmic range.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** What does `currentSum` represent at any given point in the loop?
**Answer:** The maximum sum of any contiguous subarray that ends **exactly** at the current index — not necessarily the best subarray overall, just the best one whose last element is the current one.
 
**Question 2:** Why is a separate `maxSum` variable necessary, rather than just returning `currentSum` at the end of the loop?
**Answer:** Because `currentSum` can decrease after reaching a peak (if subsequent elements are negative enough to still be "worth carrying" without yet triggering a restart) — the true global maximum might have occurred at an earlier position, not necessarily at the very last index, so a separate tracking variable is required to remember the best value seen at any point.
 
**Question 3:** How would you modify this algorithm to also return the actual start and end indices of the optimal subarray?
**Answer:** Track an additional `tempStart` index, reset to the current position whenever you "restart" (i.e., whenever `nums[i] > currentSum + nums[i]`), and whenever `maxSum` is updated, also record `start = tempStart` and `end = i` as the best-known boundaries.
 
**Question 4:** Why is Kadane's Algorithm considered a classic example of "optimal substructure" in dynamic programming?
**Answer:** Because the best subarray sum ending at position `i` can be computed purely from the best subarray sum ending at position `i-1` (plus the current element), without needing to re-examine any earlier elements individually — this recursive relationship, where the optimal solution to a subproblem builds directly on the optimal solution to a smaller subproblem, is the defining characteristic of dynamic programming.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Find the maximum sum of a contiguous subarray"**
 
Immediately think:
 
```
KADANE'S ALGORITHM
currentSum = best sum of a subarray ENDING HERE
maxSum     = best sum seen ANYWHERE so far
 
At each element:
  currentSum = max(nums[i], currentSum + nums[i])
  maxSum = max(maxSum, currentSum)
```
 
---
 
## 18. Visual Pattern
 
```
nums:        -2   1  -3   4  -1   2   1  -5   4
currentSum:  -2   1  -2   4   3   5   6   1   5
maxSum:      -2   1   1   4   4   5   6   6   6
                                      ↑
                                 best subarray
                                 ends here (sum=6)
                                 [4,-1,2,1]
```
 
Think: **CARRY THE SUM FORWARD IF IT HELPS. DROP IT AND RESTART IF IT'S BECOME A LIABILITY.**
 
---
 
## 19. Alternative Approach — Divide and Conquer
 
```java
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
 
    private int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
 
        int mid = left + (right - left) / 2;
 
        int leftMax = maxSubArrayHelper(nums, left, mid);
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        int crossMax = maxCrossingSum(nums, left, mid, right);
 
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
 
    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int sum = 0, leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
 
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
 
        return leftSum + rightSum;
    }
}
```
 
This recursively splits the array, solving the left half, the right half, and the case where the optimal subarray straddles the midpoint, then combines the three results. It runs in `O(n log n)` time — asymptotically worse than Kadane's `O(n)`, but it's the "more subtle" solution the problem's official follow-up explicitly invites, and demonstrates a different algorithmic paradigm (divide and conquer) applied to the same problem.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "check all subarrays." Think of it as **a running decision, at every position, of whether the past is helping you or hurting you**.
 
> The elegant insight of Kadane's Algorithm is that you never need to remember *where* a good subarray started — you only need to remember *how much value* continuing to carry it forward is worth. The moment that value turns negative, it can only ever drag down anything added to it in the future, so the optimal move is always to let it go and start measuring fresh from the current point. This "forget the specifics, just track whether the running total is still helping" mindset is a recurring theme across many single-pass DP-flavored array problems.
 
```
currentSum = nums[0], maxSum = nums[0]
      ↓
For each subsequent element:
      ↓
Extend (currentSum + nums[i]) vs Restart (nums[i])
      ↓
currentSum = the better of the two
      ↓
maxSum = max(maxSum, currentSum)
      ↓
Continue to next element
      ↓
Return maxSum
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Maximum Subarray | 53 | Kadane's Algorithm |
| Maximum Product Subarray | 152 | Modified Kadane's (track min & max) |
| Best Time to Buy and Sell Stock | 121 | Kadane's Algorithm variant |
| Maximum Sum Circular Subarray | 918 | Kadane's Algorithm (with wraparound) |
| Subarray Sum Equals K | 560 | Prefix Sum + Hash Map |
| House Robber | 198 | Dynamic Programming |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 53 — MAXIMUM SUBARRAY           ║
╠══════════════════════════════════════════╣
║ Pattern: Kadane's Algorithm               ║
║                                            ║
║ Init:                                     ║
║ currentSum = nums[0]                      ║
║ maxSum = nums[0]                          ║
║                                            ║
║ For each i from 1 to n-1:                 ║
║ currentSum = max(nums[i],                 ║
║               currentSum + nums[i])       ║
║ maxSum = max(maxSum, currentSum)          ║
║                                            ║
║ Return: maxSum                            ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Maximum Subarray = Kadane's Algorithm: extend if it helps, restart if the running sum has become a liability, track the best seen anywhere.**
 
- `currentSum = max(nums[i], currentSum + nums[i])`
- `maxSum = max(maxSum, currentSum)`
---
 
## 24. 30-Second Interview Explanation
 
> "I use Kadane's Algorithm, which does a single pass while tracking two values: the best sum of a subarray ending exactly at the current position, and the best sum seen anywhere so far. At each element, I decide whether to extend the running subarray by adding the current element, or to discard the running sum and start fresh from the current element alone — I take whichever gives a larger value. This works because a negative running sum can only hurt any future total it's added to, so it's never beneficial to carry it forward. I separately track the global maximum, since the best subarray might not end at the very last position. This runs in O(n) time and O(1) space, compared to the O(n²) brute-force approach of checking every possible subarray."
 
---
 
## 25. Final Takeaway
 
```
             MAXIMUM SUBARRAY
                    ↓
     currentSum = nums[0], maxSum = nums[0]
                    ↓
     FOR EACH SUBSEQUENT ELEMENT:
                    ↓
     EXTEND (currentSum + nums[i])
     vs
     RESTART (nums[i])
                    ↓
     currentSum = THE LARGER OF THE TWO
                    ↓
     maxSum = max(maxSum, currentSum)
                    ↓
     REPEAT FOR NEXT ELEMENT
                    ↓
             RETURN maxSum
```
 
Remember:
 
```
currentSum = max(nums[i], currentSum + nums[i])
maxSum = max(maxSum, currentSum)
 
A negative running sum can only hurt the future — let it go, start fresh.
Kadane's Algorithm: one pass, two tracked values → O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 53 — Maximum Subarray**.
 
