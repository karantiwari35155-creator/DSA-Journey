<h2><a href="https://leetcode.com/problems/remove-element">27. Remove Element</a></h2><h3>Easy</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>val</code>, remove all occurrences of <code>val</code> in <code>nums</code> <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a>. The order of the elements may be changed. Then return <em>the number of elements in </em><code>nums</code><em> which are not equal to </em><code>val</code>.</p>

<p>Consider the number of elements in <code>nums</code> which are not equal to <code>val</code> be <code>k</code>, to get accepted, you need to do the following things:</p>

<ul>
	<li>Change the array <code>nums</code> such that the first <code>k</code> elements of <code>nums</code> contain the elements which are not equal to <code>val</code>. The remaining elements of <code>nums</code> are not important as well as the size of <code>nums</code>.</li>
	<li>Return <code>k</code>.</li>
</ul>

<p><strong>Custom Judge:</strong></p>

<p>The judge will test your solution with the following code:</p>

<pre>
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i &lt; actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,2,3], val = 3
<strong>Output:</strong> 2, nums = [2,2,_,_]
<strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,2,3,0,4,2], val = 2
<strong>Output:</strong> 5, nums = [0,1,4,0,3,_,_,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
</ul>

# LeetCode 27 — Remove Element
 
> **Problem:** LeetCode 27 — Remove Element
> **Difficulty:** Easy
> **Pattern:** Array / Two Pointers
> **Main Technique:** Read Pointer + Write Pointer (In-place Overwrite)
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` **in-place**. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.
 
Consider the number of elements in `nums` which are not equal to `val` be `k`, to get accepted, you need to do the following things:
 
- Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`. The remaining elements of `nums` are not important as well as the size of `nums`.
- Return `k`.
### Example 1
 
```text
Input:
 
nums = [3,2,2,3], val = 3
 
Output:
 
2, nums = [2,2,_,_]
 
Explanation: Your function should return k = 2, with the first two
elements of nums being 2.
It does not matter what you leave beyond the returned k
(hence they are underscores).
```
 
### Example 2
 
```text
Input:
 
nums = [0,1,2,2,3,0,4,2], val = 2
 
Output:
 
5, nums = [0,1,4,0,3,_,_,_]
 
Explanation: Your function should return k = 5, with the first five
elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k
(hence they are underscores).
```
 
---
 
## 2. Constraints
 
- `0 <= nums.length <= 100`
- `0 <= nums[i] <= 50`
- `0 <= val <= 100`
---
 
## 3. What Is the Problem Asking?
 
We need to **remove every occurrence of `val`** from the array, **in place**, and return how many elements remain (`k`). Crucially, the **order doesn't matter**, and anything beyond the first `k` positions is irrelevant — we don't need to "shrink" the array or worry about what's left in the leftover slots.
 
```
nums = [3, 2, 2, 3], val = 3
 
Remove every 3 → remaining elements: 2, 2
 
k = 2
nums should have 2 and 2 somewhere in the first 2 positions.
```
 
Since order doesn't matter, we have flexibility in *how* we compact the array — this opens the door to a very efficient in-place technique.
 
---
 
## 4. Core Idea
 
Use **two pointers**:
 
- A **read pointer** (`i`) that scans through every element of the array.
- A **write pointer** (`k`) that tracks where the next "kept" element should go.
```java
int k = 0;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] != val) {
        nums[k] = nums[i];
        k++;
    }
}
return k;
```
 
Whenever we encounter an element that is **not** equal to `val`, we copy it to position `k` and advance `k`. Elements equal to `val` are simply **skipped** — they're never copied anywhere, effectively "overwritten" by later kept elements.
 
Since `k` only ever increases when we find a value to keep, and `i` always moves forward regardless, `k` never outruns `i` — making the in-place overwrite always safe.
 
---
 
## 5. The Two Cases Per Element
 
### 5.1 Element Is Not `val` — Keep It
 
```java
if (nums[i] != val) {
    nums[k] = nums[i];
    k++;
}
```
 
Copy the value to the write position and advance the write pointer.
 
### 5.2 Element Equals `val` — Skip It
 
No explicit code needed — simply doing nothing (not incrementing `k`, not writing anything) is equivalent to "removing" this element, since it will never appear in the first `k` positions of the final array.
 
---
 
## 6. Important Detail — Why Order Not Mattering Simplifies This
 
If the problem required **preserving the original relative order** of kept elements, this exact approach would still work correctly (in fact, it does naturally preserve order, since we copy elements in the same sequence we read them) — but the fact that order is explicitly **not required** means we don't need to worry about any more complex shifting logic; a simple forward compaction is entirely sufficient and already optimal.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int removeElement(int[] nums, int val) {
 
        int k = 0;
 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
 
        return k;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Write Pointer Initialization**
 
```java
int k = 0;
```
 
Tracks how many elements we've kept so far, and where the next kept element should be written.
 
**Read Loop**
 
```java
for (int i = 0; i < nums.length; i++)
```
 
Scans through every element in the array exactly once.
 
**Keep Condition**
 
```java
if (nums[i] != val) {
    nums[k] = nums[i];
    k++;
}
```
 
If the current element isn't the one we're removing, copy it into position `k` and advance `k`. If it **is** `val`, we simply skip this iteration's body — no write happens, and `k` stays the same, effectively "overwriting" that value on the next successful keep.
 
**Return**
 
```java
return k;
```
 
`k` naturally equals the count of elements not equal to `val` — exactly what the problem asks for.
 
---
 
## 9. Dry Run
 
Input:
 
```
nums = [0, 1, 2, 2, 3, 0, 4, 2], val = 2
```
 
`k = 0`
 
| i | nums[i] | == val (2)? | action | k after |
|---|---|---|---|---|
| 0 | 0 | no | nums[0]=0, k++ | 1 |
| 1 | 1 | no | nums[1]=1, k++ | 2 |
| 2 | 2 | yes | skip | 2 |
| 3 | 2 | yes | skip | 2 |
| 4 | 3 | no | nums[2]=3, k++ | 3 |
| 5 | 0 | no | nums[3]=0, k++ | 4 |
| 6 | 4 | no | nums[4]=4, k++ | 5 |
| 7 | 2 | yes | skip | 5 |
 
Final `nums` (first 5 slots): `[0, 1, 3, 0, 4]`
Return `k = 5` ✅ (matches the expected count; exact leftover order beyond correctness of the first `k` elements can vary, and the problem accepts any valid arrangement)
 
---
 
## 10. Why Not Use a Second Array or `ArrayList`?
 
A simpler-looking approach:
 
```java
List<Integer> result = new ArrayList<>();
for (int num : nums) {
    if (num != val) result.add(num);
}
for (int i = 0; i < result.size(); i++) {
    nums[i] = result.get(i);
}
return result.size();
```
 
This works and is also `O(n)` time, but uses `O(n)` **extra space** for the intermediate list. The two-pointer approach achieves the same result using only a single extra integer variable (`k`), directly satisfying the implicit expectation of in-place, minimal-space array manipulation that this problem (and its related family of problems) is designed to test.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Using `nums[i] == val` to decide when to write, instead of `!=`**
Wrong: writing when the value **matches** `val` — this is backwards; we want to keep everything that **doesn't** match.
Correct: `if (nums[i] != val) { ... }`.
 
**Mistake 2 — Trying to physically shrink or resize the array**
Java arrays have a fixed size once created — you cannot resize `nums` in place. The problem explicitly acknowledges this by only requiring the **first `k` elements** to be correct; nothing beyond that matters.
 
**Mistake 3 — Incrementing `k` even when skipping a `val` match**
Wrong: incrementing `k` unconditionally on every loop iteration — this would leave gaps or incorrect values in the compacted region.
Correct: only increment `k` when actually writing a kept element.
 
**Mistake 4 — Overcomplicating with shifting/removing elements mid-array (like `System.arraycopy` shifts)**
Since order doesn't need to be preserved in a specific removal-shift sense (though this solution happens to preserve relative order anyway), there's no need for more complex mid-array shifting logic — straightforward forward compaction is sufficient and optimal.
 
**Mistake 5 — Forgetting that `val` itself could legitimately be `0`, and mishandling default/uninitialized array values**
Since we don't care about elements beyond index `k`, there's no need to "clean up" or zero-out anything past the returned length — leaving stale values there is explicitly fine per the problem statement.
 
---
 
## 12. Edge Cases
 
**Empty Array:**
`nums = []`, any `val` → loop doesn't execute → Output: `k = 0`
 
**No Occurrences of `val`:**
`nums = [1,2,3]`, `val = 5` → every element is kept, `k` ends up equal to `nums.length` → array unchanged.
 
**All Elements Equal `val`:**
`nums = [4,4,4]`, `val = 4` → nothing is ever written → Output: `k = 0`
 
**Single Element, Matches `val`:**
`nums = [7]`, `val = 7` → skipped → Output: `k = 0`
 
**Single Element, Doesn't Match `val`:**
`nums = [7]`, `val = 3` → kept → Output: `k = 1`, `nums = [7]`
 
---
 
## 13. Time Complexity
 
We make exactly one pass through the array, doing constant work per element.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only a single integer variable (`k`) is used, regardless of input size — no extra array or list is created.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A two-pointer technique: a read pointer scans every element, and a write pointer tracks where the next non-`val` element should be placed, effectively compacting all kept elements toward the front of the array.
 
**Q2. Why is it safe to write into the same array while still reading from it?**
Because the write pointer `k` only ever advances at the same pace or slower than the read pointer `i` — `k` increases only when we keep an element, while `i` always advances — so `k <= i` at all times, meaning we never overwrite a value before we've had the chance to read it.
 
**Q3. Does this approach preserve the original relative order of the kept elements?**
Yes, incidentally — since we process elements strictly left to right and copy them in that same order, the relative order of kept elements is naturally preserved, even though the problem doesn't require this.
 
**Q4. Why doesn't the problem require actually resizing or shrinking the array?**
Because Java (and most languages) arrays have a fixed size once allocated — the problem's design (returning a count `k` and only caring about the first `k` elements) is a common LeetCode convention that sidesteps the impossibility of changing an array's length in place.
 
**Q5. How does this problem relate to Remove Duplicates from Sorted Array (LC 26) or Move Zeroes (LC 283)?**
All three use the same fundamental two-pointer "read and selectively write" compaction pattern — only the *condition* for what counts as "keep" differs (not equal to `val` here, vs. not a duplicate, vs. nonzero, respectively).
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** What would happen if you incremented `k` before checking whether to write, instead of after?
**Answer:** You'd end up either skipping the first valid write position (off-by-one, leaving a stale/incorrect value at index 0) or writing to the wrong index — the increment must happen only after confirming the value should be kept and has been written.
 
**Question 2:** Why is it acceptable for the returned order of elements to differ from a naive "remove and shift" approach?
**Answer:** The problem explicitly states the order may change — this relaxation is what allows for the simplest possible O(n) time, O(1) space solution; a stricter "preserve exact relative positions of remaining elements while shifting" requirement wouldn't actually need different logic here, but the explicit relaxation confirms no extra care is needed.
 
**Question 3:** How would you modify this solution if you instead needed to remove all elements **except** a specific value (i.e., invert the condition)?
**Answer:** Simply flip the condition to `if (nums[i] == val)` instead of `!=` — the rest of the two-pointer logic (write and advance `k` when the condition is true) remains identical.
 
**Question 4:** Is there a scenario where this two-pointer approach would NOT be optimal for an "in-place removal by condition" problem?
**Answer:** If the problem required removing elements from potentially anywhere while preserving *strict* stability under some more complex secondary ordering constraint beyond simple relative order, or if elements needed to be removed based on a condition that depends on *future* elements not yet seen, a single forward pass might not suffice — but for straightforward per-element removal conditions like this one, the two-pointer approach is optimal.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Remove all elements matching some condition from an array, in place, order doesn't matter"**
 
Immediately think:
 
```
TWO POINTERS: READ + WRITE
read (i)  → scans every element
write (k) → only advances when an element should be KEPT
 
condition true (keep)  → nums[k] = nums[i]; k++
condition false (skip) → do nothing, k stays put
 
Return k as the new "valid length"
```
 
---
 
## 18. Visual Pattern
 
```
nums:  0  1  2  2  3  0  4  2      val = 2
       ↑
    i=0, k=0 → 0 != 2 → write nums[0]=0, k=1
 
nums:  0  1  2  2  3  0  4  2
          ↑
    i=1, k=1 → 1 != 2 → write nums[1]=1, k=2
 
nums:  0  1  2  2  3  0  4  2
             ↑
    i=2, k=2 → 2 == 2 → SKIP, k stays 2
 
nums:  0  1  2  2  3  0  4  2
                ↑
    i=3, k=2 → 2 == 2 → SKIP, k stays 2
 
nums:  0  1  2  2  3  0  4  2
                   ↑
    i=4, k=2 → 3 != 2 → write nums[2]=3, k=3
 
... continues ...
 
Final first k=5 elements: [0, 1, 3, 0, 4]
```
 
Think: **KEEP IT → COPY TO WRITE POSITION, ADVANCE. SKIP IT → DO NOTHING.**
 
---
 
## 19. Alternative Approach
 
**Two pointers from opposite ends (optimized for fewer writes when `val` is rare-ish and order truly doesn't matter):**
 
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
 
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
 
        return left;
    }
}
```
 
Instead of always copying forward, this version swaps a `val` match with an element from the **end** of the array, shrinking the effective "active" region from the right. This can be more efficient in practice when `val` occurrences are rare, since it avoids unnecessary self-copies, though it doesn't preserve relative order (which the problem allows) and has the same overall `O(n)` time, `O(1)` space complexity.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "delete elements from an array." Think of it as **compacting the array by selectively copying forward only what should survive**.
 
> Since arrays have fixed size and the problem doesn't require preserving anything beyond the first `k` valid elements, the most efficient mental model isn't "removal" at all — it's "overwrite in place, keeping a running count of how much of the array is still meaningful." This reframing is what unlocks the O(1) space solution, and it's the same reframing that underlies an entire family of "compact this array based on a condition" problems.
 
```
k = 0
For each element (read pointer i):
      ↓
Does it satisfy the "keep" condition (!= val)?
      ↓ yes                    ↓ no
Copy to nums[k], k++          Do nothing
      ↓
Continue to next i
      ↓
Return k (the new valid length)
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Remove Element | 27 | Two Pointers / In-place Write |
| Remove Duplicates from Sorted Array | 26 | Two Pointers / In-place Write |
| Remove Duplicates from Sorted Array II | 80 | Two Pointers / In-place Write (allow 2) |
| Move Zeroes | 283 | Two Pointers / In-place Write |
| String Compression | 443 | Two Pointers / In-place Write |
| Sort Colors | 75 | Three Pointers / Dutch National Flag |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 27 — REMOVE ELEMENT             ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers (Read + Write)      ║
║                                            ║
║ Init: k = 0                               ║
║                                            ║
║ For each i from 0 to n-1:                 ║
║ if nums[i] != val:                        ║
║     nums[k] = nums[i]                     ║
║     k++                                   ║
║                                            ║
║ Return: k (count of kept elements)        ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Remove Element = Copy everything that isn't val forward into a running write position; skip the rest.**
 
- `nums[i] != val` → keep, write to `nums[k]`, `k++`
- `nums[i] == val` → skip entirely
---
 
## 24. 30-Second Interview Explanation
 
> "I use two pointers: a read pointer that scans every element of the array, and a write pointer that tracks where the next kept element should go. Whenever the current element isn't equal to val, I copy it to the write position and advance the write pointer; if it does equal val, I simply skip it, effectively removing it. Since the write pointer only ever advances at the same pace or slower than the read pointer, it's always safe to write in place without corrupting unread data. At the end, the write pointer's value is exactly the count of elements not equal to val, which is what the problem asks me to return. This runs in O(n) time and O(1) extra space."
 
---
 
## 25. Final Takeaway
 
```
              REMOVE ELEMENT
                    ↓
       SCAN EACH ELEMENT (read pointer i)
                    ↓
       IS nums[i] != val ?
                    ↓
              yes → COPY TO nums[k], k++
              no  → SKIP (do nothing)
                    ↓
       REPEAT FOR NEXT ELEMENT
                    ↓
       RETURN k (new valid length)
```
 
Remember:
 
```
nums[i] != val → keep it, write to nums[k], k++
nums[i] == val → skip it entirely
 
Two pointers, one pass, in place → O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 27 — Remove Element**.
 
