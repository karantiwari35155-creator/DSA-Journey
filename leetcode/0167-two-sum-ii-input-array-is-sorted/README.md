<h2><a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted">167. Two Sum II - Input Array Is Sorted</a></h2><h3>Medium</h3><hr><p>Given a <strong>1-indexed</strong> array of integers <code>numbers</code> that is already <strong><em>sorted in non-decreasing order</em></strong>, find two numbers such that they add up to a specific <code>target</code> number. Let these two numbers be <code>numbers[index<sub>1</sub>]</code> and <code>numbers[index<sub>2</sub>]</code> where <code>1 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt;= numbers.length</code>.</p>

<p>Return<em> the indices of the two numbers&nbsp;</em><code>index<sub>1</sub></code><em> and </em><code>index<sub>2</sub></code><em>, <strong>each incremented by one,</strong> as an integer array </em><code>[index<sub>1</sub>, index<sub>2</sub>]</code><em> of length 2.</em></p>

<p>The tests are generated such that there is <strong>exactly one solution</strong>. You <strong>may not</strong> use the same element twice.</p>

<p>Your solution must use only constant extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numbers = [<u>2</u>,<u>7</u>,11,15], target = 9
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The sum of 2 and 7 is 9. Therefore, index<sub>1</sub> = 1, index<sub>2</sub> = 2. We return [1, 2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numbers = [<u>2</u>,3,<u>4</u>], target = 6
<strong>Output:</strong> [1,3]
<strong>Explanation:</strong> The sum of 2 and 4 is 6. Therefore index<sub>1</sub> = 1, index<sub>2</sub> = 3. We return [1, 3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> numbers = [<u>-1</u>,<u>0</u>], target = -1
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The sum of -1 and 0 is -1. Therefore index<sub>1</sub> = 1, index<sub>2</sub> = 2. We return [1, 2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> is sorted in <strong>non-decreasing order</strong>.</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li>The tests are generated such that there is <strong>exactly one solution</strong>.</li>
</ul>

# LeetCode 167 — Two Sum II - Input Array Is Sorted
 
> **Problem:** LeetCode 167 — Two Sum II - Input Array Is Sorted
> **Difficulty:** Medium
> **Pattern:** Two Pointers / Sorted Array
> **Main Technique:** Opposite-Direction Two Pointers
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given a **1-indexed** array of integers `numbers` that is already **sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number.
 
Let these two numbers be `numbers[index1]` and `numbers[index2]` where `1 <= index1 < index2 <= numbers.length`.
 
Return the indices of the two numbers, `index1` and `index2`, **added by one** as an integer array `[index1, index2]` of length 2.
 
The tests are generated such that there is **exactly one solution**. You **may not** use the same element twice.
 
Your solution must use only constant extra space.
 
### Example 1
 
```text
Input:
 
numbers = [2,7,11,15], target = 9
 
Output:
 
[1,2]
 
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2.
We return [1, 2].
```
 
### Example 2
 
```text
Input:
 
numbers = [2,3,4], target = 6
 
Output:
 
[1,3]
 
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3.
We return [1, 3].
```
 
### Example 3
 
```text
Input:
 
numbers = [-1,0], target = -1
 
Output:
 
[1,2]
 
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2.
We return [1, 2].
```
 
---
 
## 2. Constraints
 
- `2 <= numbers.length <= 3 * 10^4`
- `-1000 <= numbers[i] <= 1000`
- `numbers` is sorted in **non-decreasing** order.
- `-1000 <= target <= 1000`
- The tests are generated such that there is **exactly one solution**.
---
 
## 3. What Is the Problem Asking?
 
We need to find two elements in a **sorted** array whose sum equals `target`, and return their **1-indexed** positions.
 
```
numbers = [2, 7, 11, 15], target = 9
index   =  1  2   3   4
 
2 + 7 = 9  → return [1, 2]
```
 
The key extra constraint compared to the classic Two Sum (LeetCode 1) is that the array is **already sorted**, and the solution **must use O(1) extra space** — which rules out the hash map approach normally used for Two Sum.
 
---
 
## 4. Core Idea
 
Because the array is sorted, we can use **two pointers moving toward each other**:
 
```java
int left = 0;
int right = numbers.length - 1;
```
 
At each step, look at `numbers[left] + numbers[right]`:
 
- If the sum is **too small**, we need a bigger sum → move `left` forward (`left++`) to pick a larger number.
- If the sum is **too big**, we need a smaller sum → move `right` backward (`right--`) to pick a smaller number.
- If the sum **matches** the target, we found our answer.
This works *only* because the array is sorted — moving `left` forward always increases the sum, and moving `right` backward always decreases it, so we never need to backtrack.
 
---
 
## 5. The Three Cases Per Step
 
### 5.1 Sum Too Small — Move Left Pointer Right
 
```java
if (numbers[left] + numbers[right] < target) {
    left++;
}
```
 
Example: `numbers = [2,7,11,15]`, `left=0 (2)`, `right=3 (15)` → sum `17` — wait, if instead sum were too small, e.g. `2 + 3 = 5 < 9`, we'd move `left` up to try a bigger number.
 
### 5.2 Sum Too Big — Move Right Pointer Left
 
```java
if (numbers[left] + numbers[right] > target) {
    right--;
}
```
 
Example: `2 + 15 = 17 > 9` → move `right` down to try a smaller number.
 
### 5.3 Sum Matches — Return the Answer
 
```java
if (numbers[left] + numbers[right] == target) {
    return new int[]{left + 1, right + 1};
}
```
 
We add `1` to each index because the problem uses **1-indexed** positions.
 
---
 
## 6. Important Detail — Why This Never Misses the Answer
 
Since the tests guarantee **exactly one solution**, and the array is sorted, the two-pointer approach is guaranteed to find it without ever skipping past it:
 
```
If numbers[left] + numbers[right] < target:
    numbers[left] paired with ANY index between left and right
    would only make the sum smaller or equal — never reach target.
    So numbers[left] can never be part of the answer with any
    remaining index ≤ current right. Safe to discard left.
 
If numbers[left] + numbers[right] > target:
    Symmetric argument — safe to discard right.
```
 
This elimination logic is what makes two pointers correct here, not just fast.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
 
        int left = 0;
        int right = numbers.length - 1;
 
        while (left < right) {
 
            int sum = numbers[left] + numbers[right];
 
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
 
        // Problem guarantees a solution always exists
        return new int[]{-1, -1};
    }
}
```
 
---
 
## 8. Code Explanation
 
**Pointer Initialization**
 
```java
int left = 0;
int right = numbers.length - 1;
```
 
`left` starts at the smallest element, `right` starts at the largest — the two extremes of the sorted array.
 
**While Loop**
 
```java
while (left < right)
```
 
Continue as long as the pointers haven't crossed. Since indices must satisfy `index1 < index2`, we stop the moment `left == right`.
 
**Compute Current Sum**
 
```java
int sum = numbers[left] + numbers[right];
```
 
**Match Found**
 
```java
if (sum == target) {
    return new int[]{left + 1, right + 1};
}
```
 
Convert back to 1-indexed positions before returning.
 
**Sum Too Small**
 
```java
else if (sum < target) {
    left++;
}
```
 
Move `left` forward to increase the sum.
 
**Sum Too Big**
 
```java
else {
    right--;
}
```
 
Move `right` backward to decrease the sum.
 
**Fallback Return**
 
```java
return new int[]{-1, -1};
```
 
Never actually reached given the problem's guarantee, but included for safety/completeness.
 
---
 
## 9. Dry Run
 
Input:
 
```
numbers = [2, 7, 11, 15], target = 9
```
 
`left = 0 (value 2)`, `right = 3 (value 15)`
 
| step | left (val) | right (val) | sum | comparison | action |
|---|---|---|---|---|---|
| 1 | 0 (2) | 3 (15) | 17 | 17 > 9 | right-- |
| 2 | 0 (2) | 2 (11) | 13 | 13 > 9 | right-- |
| 3 | 0 (2) | 1 (7) | 9 | 9 == 9 | **match!** |
 
Return `[0+1, 1+1] = [1, 2]` ✅ (matches expected output)
 
---
 
## 10. Why Not Use a Hash Map (Classic Two Sum Approach)?
 
The classic Two Sum (LeetCode 1) approach stores each value's index in a hash map and checks for `target - numbers[i]`:
 
```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < numbers.length; i++) {
    if (seen.containsKey(target - numbers[i])) {
        return new int[]{seen.get(target - numbers[i]) + 1, i + 1};
    }
    seen.put(numbers[i], i);
}
```
 
This works and is also `O(n)` time, but uses `O(n)` **extra space** for the hash map.
 
This problem explicitly requires **constant extra space**, and since the array is already sorted, the two-pointer technique achieves the same `O(n)` time with `O(1)` space — making it the intended and optimal solution here.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Returning 0-indexed positions**
Wrong: `return new int[]{left, right};`
Correct: `return new int[]{left + 1, right + 1};` — the problem is 1-indexed.
 
**Mistake 2 — Using `<=` instead of `<` in the while loop**
Wrong: `while (left <= right)` — this would allow `left == right`, using the same element twice, which is explicitly disallowed.
Correct: `while (left < right)`.
 
**Mistake 3 — Moving both pointers when the sum doesn't match**
Wrong: incrementing `left` and decrementing `right` in the same step when the sum is off-target — this can skip over the correct pair.
Correct: move **only one** pointer per step, based on whether the sum is too small or too big.
 
**Mistake 4 — Using a hash map / hash set**
This works logically but violates the **O(1) extra space** requirement of this specific problem. Reserve hash maps for the unsorted version (LeetCode 1).
 
**Mistake 5 — Forgetting the array is already sorted and trying to sort it (or a copy) again**
Sorting the array again with tracked original indices adds unnecessary `O(n log n)` time and complexity — the input is already sorted, so two pointers work directly.
 
---
 
## 12. Edge Cases
 
**Two Elements Only:**
`[−1,0]`, `target = -1` → `left=0, right=1`, sum `=-1` → match immediately → Output: `[1,2]`
 
**Answer at the Very Start:**
`[1,2,3,4,4,9,56,90], target=3` → `left` and `right` converge quickly since `1+2=3` is found on the first check after some right-shrinking, or immediately depending on values.
 
**Negative Numbers:**
`[-3,-1,0,2,4,6,8], target=5` → two pointers work the same way; sorted order still holds for negatives.
 
**Target Equals Sum of the Two Smallest Elements:**
`right` will shrink all the way down until it reaches `left + 1` before matching, if applicable.
 
**Target Equals Sum of the Two Largest Elements:**
Matches on the very first check (`left=0`, `right=n-1`).
 
---
 
## 13. Time Complexity
 
Each step moves either `left` forward or `right` backward — the pointers can move at most `n` times in total before meeting.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only two pointer variables (`left`, `right`) are used, regardless of input size — no auxiliary map, set, or array.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Two pointers starting at opposite ends of the sorted array, moving inward based on whether the current sum is too small or too big.
 
**Q2. Why does the two-pointer approach work here but wouldn't necessarily work on an unsorted array?**
Because the array is sorted, moving `left` forward strictly increases the sum and moving `right` backward strictly decreases it — this monotonic relationship lets us safely eliminate one end at each step without missing the answer. On an unsorted array, this monotonicity doesn't exist.
 
**Q3. Why is this problem O(1) space, unlike the original Two Sum?**
The original Two Sum problem (LeetCode 1) doesn't guarantee a sorted array, so it needs a hash map (`O(n)` space) to achieve `O(n)` time. Here, the sorted property lets two pointers achieve the same `O(n)` time using only `O(1)` space.
 
**Q4. What if the array had duplicate values?**
The two-pointer approach still works correctly — duplicates don't break the monotonic sum property. Since the problem guarantees exactly one solution, we don't need special duplicate-skipping logic (unlike, say, 3Sum, which needs to avoid duplicate *triplets*).
 
**Q5. Could binary search be used instead?**
Yes — for each `left`, binary search for `target - numbers[left]` in the remaining subarray. This gives `O(n log n)` time, which is worse than the `O(n)` two-pointer approach, so two pointers is preferred.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why do we add `1` to both `left` and `right` before returning?
**Answer:** The problem specifies 1-indexed positions, but Java arrays are 0-indexed internally, so we convert at the point of returning the answer.
 
**Question 2:** Why use `while (left < right)` instead of `while (left != right)`?
**Answer:** Both would work correctly here since pointers move toward each other one step at a time and can't skip past each other to swap order, but `<` is the conventional, clearly-intentional way to express "until they meet or cross."
 
**Question 3:** How would you adapt this approach for the unsorted version of Two Sum (LeetCode 1)?
**Answer:** You can't directly reuse two pointers on unsorted data — you'd need to either sort it first (losing original indices, so you'd need to track them separately) or use a hash map for `O(n)` time with `O(n)` space instead.
 
**Question 4:** What is the maximum number of iterations the while loop can run?
**Answer:** At most `n - 1` iterations, since each iteration strictly shrinks the `[left, right]` window by moving one pointer inward, and the loop stops once `left >= right`.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Find a pair in a SORTED array that sums to a target"**
 
Immediately think:
 
```
TWO POINTERS FROM OPPOSITE ENDS
left  = start
right = end
 
sum < target  → left++
sum > target  → right--
sum == target → found it
```
 
---
 
## 18. Visual Pattern
 
```
numbers:  [ 2   7   11   15 ]
            ↑              ↑
          left           right
 
sum = 2 + 15 = 17 > 9  →  move right left
 
numbers:  [ 2   7   11   15 ]
            ↑         ↑
          left      right
 
sum = 2 + 11 = 13 > 9  →  move right left
 
numbers:  [ 2   7   11   15 ]
            ↑    ↑
          left right
 
sum = 2 + 7 = 9 == 9  →  MATCH! return [1, 2]
```
 
Think: **TOO SMALL → GROW FROM THE LEFT. TOO BIG → SHRINK FROM THE RIGHT.**
 
---
 
## 19. Alternative Approach
 
**Binary Search per element** — for each `i`, binary search for `target - numbers[i]` in the rest of the array:
 
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            int lo = i + 1, hi = numbers.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (numbers[mid] == complement) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] < complement) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
```
 
This is `O(n log n)` time and `O(1)` space — correct, but slower than the two-pointer approach. Useful to mention in interviews as a fallback if two pointers weren't spotted first.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "search for a pair." Think of it as **shrinking a search window from both ends, guided by the sorted order**.
 
> The sorted property turns "which pair sums to target" into a monotonic decision problem: at every step, exactly one direction (grow the small side, or shrink the large side) is provably correct, so you never need to explore both branches or backtrack.
 
```
left = 0, right = n - 1
      ↓
sum = numbers[left] + numbers[right]
      ↓
sum == target?  → return answer
      ↓ no
sum < target?   → left++  (need a bigger contribution)
      ↓ no
sum > target    → right-- (need a smaller contribution)
      ↓
Repeat until left >= right
```
 
This "shrink from both ends using sorted order" pattern shows up constantly — container with most water, 3Sum (after sorting), sorted squares, etc.
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Two Sum | 1 | Hash Map |
| Two Sum II - Input Array Is Sorted | 167 | Two Pointers |
| 3Sum | 15 | Sort + Two Pointers |
| 3Sum Closest | 16 | Sort + Two Pointers |
| 4Sum | 18 | Sort + Two Pointers |
| Container With Most Water | 11 | Two Pointers |
| Squares of a Sorted Array | 977 | Two Pointers |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 167 — TWO SUM II (SORTED)       ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers (Opposite Ends)     ║
║                                            ║
║ Init:                                     ║
║ left  = 0                                 ║
║ right = numbers.length - 1                ║
║                                            ║
║ Loop while left < right:                  ║
║ sum = numbers[left] + numbers[right]      ║
║                                            ║
║ sum == target → return [left+1, right+1]  ║
║ sum <  target → left++                    ║
║ sum >  target → right--                   ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Two Sum II = Sorted array + two pointers from both ends: too small, move left up; too big, move right down.**
 
- `left`  → grows the sum
- `right` → shrinks the sum
---
 
## 24. 30-Second Interview Explanation
 
> "Since the array is already sorted, I use two pointers — one starting at the first element, one at the last. At each step, I check their sum against the target. If the sum is too small, I move the left pointer forward to increase it; if it's too big, I move the right pointer backward to decrease it. If the sum matches, I return the 1-indexed positions. This works because sorted order guarantees each move strictly increases or decreases the sum, so I never miss the unique answer. This gives O(n) time and, importantly, O(1) extra space, since I'm not using any hash map."
 
---
 
## 25. Final Takeaway
 
```
      TWO SUM II — SORTED ARRAY
                 ↓
     left = 0, right = n - 1
                 ↓
   sum = numbers[left] + numbers[right]
                 ↓
        sum == target?
           ↓          ↓
          yes         no
           ↓          ↓
        RETURN    sum < target?
                     ↓        ↓
                    yes       no
                     ↓        ↓
                  left++   right--
                     ↓        ↓
                  REPEAT UNTIL left >= right
```
 
Remember:
 
```
sum < target  → left++   (need a bigger number)
sum > target  → right--  (need a smaller number)
sum == target → done, return 1-indexed positions
 
Sorted array + two pointers from both ends = O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 167 — Two Sum II - Input Array Is Sorted**.
