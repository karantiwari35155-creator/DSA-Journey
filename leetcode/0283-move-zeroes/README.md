<h2><a href="https://leetcode.com/problems/move-zeroes">283. Move Zeroes</a></h2><h3>Easy</h3><hr><p>Given an integer array <code>nums</code>, move all <code>0</code>&#39;s to the end of it while maintaining the relative order of the non-zero elements.</p>

<p><strong>Note</strong> that you must do this in-place without making a copy of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [0,1,0,3,12]
<strong>Output:</strong> [1,3,12,0,0]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you minimize the total number of operations done?

# LeetCode 283 — Move Zeroes
 
> **Problem:** LeetCode 283 — Move Zeroes
> **Difficulty:** Easy
> **Pattern:** Array / Two Pointers
> **Main Technique:** Read Pointer + Write Pointer with Swap
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given an integer array `nums`, move all `0`'s to the end of it while maintaining the **relative order** of the non-zero elements.
 
**Note** that you must do this **in-place** without making a copy of the array.
 
### Example 1
 
```text
Input:
 
nums = [0,1,0,3,12]
 
Output:
 
[1,3,12,0,0]
```
 
### Example 2
 
```text
Input:
 
nums = [0]
 
Output:
 
[0]
```
 
---
 
## 2. Constraints
 
- `1 <= nums.length <= 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`
**Follow up:** Could you minimize the total number of operations done?
 
---
 
## 3. What Is the Problem Asking?
 
We need to push every `0` in the array to the **end**, while keeping all the **non-zero elements in their original relative order** — and we must do this **in place**, without allocating a new array.
 
```
nums = [0, 1, 0, 3, 12]
 
Non-zero elements in order: 1, 3, 12
Zeros pushed to the end:    0, 0
 
Result: [1, 3, 12, 0, 0]
```
 
Unlike LeetCode 27 (Remove Element), here **order matters** for the non-zero elements, and we can't just "forget about" the zeros — they must actually end up at the end of the array, not just be overwritten and ignored.
 
---
 
## 4. Core Idea
 
Use **two pointers**:
 
- A **read pointer** (`i`) that scans through every element.
- A **write pointer** (`insertPos`) that tracks where the next non-zero element should be placed.
```java
int insertPos = 0;
 
for (int i = 0; i < nums.length; i++) {
    if (nums[i] != 0) {
        int temp = nums[insertPos];
        nums[insertPos] = nums[i];
        nums[i] = temp;
        insertPos++;
    }
}
```
 
Whenever we find a non-zero element, we **swap** it into position `insertPos` (rather than just overwriting, as in Remove Element) — this swap is what naturally pushes zeros toward the back instead of just discarding them, since whatever was previously at `insertPos` (a zero, by invariant) gets moved to where the non-zero element used to be.
 
---
 
## 5. The Two Cases Per Element
 
### 5.1 Element Is Non-Zero — Swap It Into Place
 
```java
if (nums[i] != 0) {
    int temp = nums[insertPos];
    nums[insertPos] = nums[i];
    nums[i] = temp;
    insertPos++;
}
```
 
Swap the current non-zero element with whatever is at `insertPos` (which is guaranteed to be a zero or the same position), then advance `insertPos`.
 
### 5.2 Element Is Zero — Do Nothing
 
No code needed — simply skip. The zero stays where it is *for now*, but will eventually get swapped further right as later non-zero elements are processed and moved past it.
 
---
 
## 6. Important Detail — Why a Swap (Not Just an Overwrite) Is Needed Here
 
Unlike Remove Element (LC 27), where leftover values beyond the returned length didn't matter, **this problem requires the actual zeros to end up physically present at the end of the array**. A simple overwrite (like `nums[insertPos] = nums[i]`) would lose track of where the zeros should go. Using a **swap** instead ensures:
 
```
Whatever was at nums[insertPos] before the swap is a ZERO (by the loop's invariant:
every position from insertPos to i-1, before this swap, holds only zeros).
After swapping, that zero moves to position i (where the non-zero element used to be),
and the non-zero element correctly lands at insertPos.
```
 
This invariant — "everything between `insertPos` and `i` (exclusive) is always `0`" — is what makes the swap-based approach correct.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public void moveZeroes(int[] nums) {
 
        int insertPos = 0;
 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[insertPos];
                nums[insertPos] = nums[i];
                nums[i] = temp;
                insertPos++;
            }
        }
    }
}
```
 
---
 
## 8. Code Explanation
 
**Write Pointer Initialization**
 
```java
int insertPos = 0;
```
 
Tracks the next position where a non-zero element should be placed.
 
**Read Loop**
 
```java
for (int i = 0; i < nums.length; i++)
```
 
Scans through every element in the array exactly once.
 
**Non-Zero Case — Swap**
 
```java
if (nums[i] != 0) {
    int temp = nums[insertPos];
    nums[insertPos] = nums[i];
    nums[i] = temp;
    insertPos++;
}
```
 
Swap the current non-zero value into the `insertPos` slot, moving whatever was previously there (always a zero, by the loop's invariant) out to where the non-zero element used to be. Advance `insertPos` since we've placed one more non-zero element correctly.
 
**Zero Case — No Action**
 
No `else` branch needed — zeros are simply left in place until a later swap moves them further along.
 
**No Return Value**
 
The function modifies `nums` directly (`void` return type) — this matches the problem's requirement to do the operation **in place**.
 
---
 
## 9. Dry Run
 
Input:
 
```
nums = [0, 1, 0, 3, 12]
```
 
`insertPos = 0`
 
| i | nums[i] | != 0? | action | nums after | insertPos after |
|---|---|---|---|---|---|
| 0 | 0 | no | skip | [0,1,0,3,12] | 0 |
| 1 | 1 | yes | swap nums[0]↔nums[1] | [1,0,0,3,12] | 1 |
| 2 | 0 | no | skip | [1,0,0,3,12] | 1 |
| 3 | 3 | yes | swap nums[1]↔nums[3] | [1,3,0,0,12] | 2 |
| 4 | 12 | yes | swap nums[2]↔nums[4] | [1,3,12,0,0] | 3 |
 
Final `nums = [1, 3, 12, 0, 0]` ✅ (matches expected output, non-zero order preserved)
 
---
 
## 10. Why Not Just Overwrite (Like Remove Element) and Then Fill the Rest With Zeros?
 
An alternative valid two-step approach:
 
```java
int insertPos = 0;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] != 0) {
        nums[insertPos++] = nums[i];
    }
}
while (insertPos < nums.length) {
    nums[insertPos++] = 0;
}
```
 
This also works and is `O(n)` time, `O(1)` space — it's a perfectly valid alternative. It does slightly more total "writes" in some cases (every non-zero element might get written once during compaction, then the tail gets explicitly zero-filled), whereas the swap-based approach does exactly one swap per non-zero element and nothing extra for zeros — both are correct, but the swap version is often preferred for its single unified loop and typically fewer total write operations if the array already has runs of non-zeros followed by zeros.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Overwriting instead of swapping**
Wrong: `nums[insertPos] = nums[i]; insertPos++;` without also setting `nums[i]` back to whatever was previously at `insertPos` — this would **duplicate** the non-zero value instead of properly relocating the zero.
Correct: use a full three-line swap (or `Collections.swap`-style logic) so the zero actually moves to where the non-zero element came from.
 
**Mistake 2 — Swapping even when `nums[i]` is already zero**
Wrong: unconditionally swapping every element with `insertPos`, regardless of whether it's zero — this could swap a zero with a zero, wasting operations, or worse, accidentally displace an already-placed non-zero element if the condition isn't checked correctly.
Correct: only swap when `nums[i] != 0`.
 
**Mistake 3 — Forgetting to increment `insertPos` after a successful swap**
Wrong: swapping but not incrementing `insertPos` — this would repeatedly swap into the same position, corrupting the result.
Correct: always `insertPos++` immediately after a non-zero swap.
 
**Mistake 4 — Swapping `nums[insertPos]` with itself unnecessarily when `i == insertPos`**
This isn't actually incorrect (swapping a value with itself is a harmless no-op), but it's worth recognizing as a subtle inefficiency — some implementations add a check `if (i != insertPos)` before swapping to skip these redundant self-swaps, addressing the problem's "minimize total operations" follow-up.
 
**Mistake 5 — Returning a new array instead of modifying `nums` in place**
The method signature is `void moveZeroes(int[] nums)` specifically because the problem requires **in-place** modification — returning a new array (even if logically correct) would not satisfy the problem's explicit requirement.
 
---
 
## 12. Edge Cases
 
**Single Zero:**
`nums = [0]` → loop runs once, `nums[0] == 0`, nothing happens → Output: `[0]` (unchanged, correctly)
 
**No Zeros At All:**
`nums = [1,2,3]` → every element triggers a swap, but since `i == insertPos` at every step, the swaps are all no-ops → Output: `[1,2,3]` (unchanged)
 
**All Zeros:**
`nums = [0,0,0]` → no non-zero elements ever trigger a swap → Output: `[0,0,0]` (unchanged, correctly, since there's nothing to move)
 
**Zeros Already at the End:**
`nums = [1,2,0,0]` → swaps for `1` and `2` are no-ops (`i == insertPos` for both) → Output: `[1,2,0,0]` (unchanged, correctly)
 
**Single Non-Zero Element:**
`nums = [5]` → one no-op swap → Output: `[5]`
 
---
 
## 13. Time Complexity
 
We make exactly one pass through the array, doing constant work (a comparison, and possibly a swap) per element.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only two integer variables (`insertPos`, `temp`) are used, regardless of input size — no extra array is created.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A two-pointer technique: a read pointer scans every element, and a write pointer (`insertPos`) tracks where the next non-zero element belongs. When a non-zero element is found, I swap it into the `insertPos` slot, which naturally relocates whatever zero was sitting there to the position the non-zero element vacated.
 
**Q2. Why is a swap necessary here, unlike in Remove Element (LC 27)?**
Because this problem requires the zeros to actually be **physically present** at the end of the array in their correct positions, not just "don't care about the leftover values" — a swap ensures every zero is properly relocated rather than being overwritten and lost.
 
**Q3. Why does this approach preserve the relative order of non-zero elements?**
Because we process elements strictly left to right, and every non-zero element is placed at the next available `insertPos` in that same left-to-right sequence — this naturally preserves their original relative order.
 
**Q4. What's the "minimize total operations" follow-up asking, and how would you address it?**
It's hinting at avoiding unnecessary self-swaps — when `i == insertPos`, swapping an element with itself technically works but is wasted effort. Adding a simple `if (i != insertPos)` check before performing the swap avoids these no-op operations, slightly reducing total work without changing the overall time complexity.
 
**Q5. How does this compare to the "overwrite then fill with zeros" two-step alternative?**
Both are `O(n)` time and `O(1)` space and are equally valid. The swap-based single-pass approach is often considered slightly more elegant since it handles everything in one loop, while the two-step approach separates "compact non-zeros" and "fill remaining with zeros" into two clearer, more explicit phases.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why is it guaranteed that `nums[insertPos]` is a zero at the moment of any given swap (assuming `i != insertPos`)?
**Answer:** By the loop's invariant, every position from `insertPos` up to (but not including) the current `i` has already been confirmed to hold only zeros — any non-zero element encountered in that range would have already been swapped forward earlier, so what remains at `insertPos` (when `i` has moved ahead of it) must be a zero.
 
**Question 2:** What would happen if you used `nums[insertPos++] = nums[i]` instead of a full swap, without a subsequent zero-fill pass?
**Answer:** The non-zero elements would get correctly compacted toward the front (as in Remove Element), but the zeros would simply be overwritten and "lost" — the tail of the array wouldn't actually contain the correct number of zeros unless you explicitly filled it in afterward.
 
**Question 3:** Is the relative order of the zeros among themselves ever a concern?
**Answer:** No — since all zeros are identical in value, their "relative order" among each other is meaningless; only the relative order of the distinct non-zero values matters, and the algorithm preserves that.
 
**Question 4:** How would you adapt this pattern to move all *negative* numbers to the end instead of zeros, while preserving order of non-negatives?
**Answer:** Simply change the condition from `nums[i] != 0` to `nums[i] >= 0` — the rest of the swap-based logic remains identical, since the underlying pattern (two pointers, swap on a "keep" condition) is agnostic to the specific value being filtered.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Move all elements matching a condition to the end (or the start), in place, preserving relative order of the rest"**
 
Immediately think:
 
```
TWO POINTERS: READ + WRITE, WITH A SWAP
read (i)         → scans every element
write (insertPos) → tracks where the next "kept" element belongs
 
kept condition true  → SWAP nums[i] and nums[insertPos], insertPos++
kept condition false → do nothing (leave it, it'll get swapped later)
```
 
---
 
## 18. Visual Pattern
 
```
nums:  0  1  0  3  12       insertPos=0
       ↑
     i=0 → 0 (skip)
 
nums:  0  1  0  3  12       insertPos=0
          ↑
     i=1 → 1 (non-zero) → swap nums[0]↔nums[1]
 
nums:  1  0  0  3  12       insertPos=1
             ↑
     i=2 → 0 (skip)
 
nums:  1  0  0  3  12       insertPos=1
                ↑
     i=3 → 3 (non-zero) → swap nums[1]↔nums[3]
 
nums:  1  3  0  0  12       insertPos=2
                   ↑
     i=4 → 12 (non-zero) → swap nums[2]↔nums[4]
 
nums:  1  3  12  0  0       insertPos=3
```
 
Think: **NON-ZERO FOUND? SWAP IT TO THE FRONT BOUNDARY, PUSHING A ZERO BACK.**
 
---
 
## 19. Alternative Approach
 
**Overwrite-then-fill (two clear phases):**
 
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int insertPos = 0;
 
        // Phase 1: compact all non-zero elements to the front
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
 
        // Phase 2: fill the remaining positions with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
```
 
Same `O(n)` time and `O(1)` space, just split into two explicit, easy-to-reason-about phases instead of one combined swap-based loop.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "delete zeros." Think of it as **stably partitioning the array into two regions — non-zero first, zero second — using a single swapping pass**.
 
> The swap is the crucial upgrade over a simple overwrite: it guarantees that every value in the array is *preserved somewhere*, just relocated, rather than any value being silently discarded. This "swap to partition, don't overwrite and lose data" mindset generalizes to the broader family of **stable partitioning** problems, where you need to reorder elements around a condition while keeping every original value accounted for.
 
```
insertPos = 0
For each element (read pointer i):
      ↓
Is nums[i] non-zero?
      ↓ yes                              ↓ no
Swap nums[i] and nums[insertPos]        Do nothing
insertPos++                              (zero stays, will be
      ↓                                   swapped further back later)
Continue to next i
      ↓
Loop ends — array is now correctly partitioned
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Move Zeroes | 283 | Two Pointers / Swap-based Partition |
| Remove Element | 27 | Two Pointers / Overwrite (order of leftovers doesn't matter) |
| Remove Duplicates from Sorted Array | 26 | Two Pointers / Overwrite |
| Sort Colors | 75 | Three Pointers / Dutch National Flag |
| Partition Array According to Given Pivot | 2161 | Stable Partitioning |
| String Compression | 443 | Two Pointers / In-place Write |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 283 — MOVE ZEROES               ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers (Read + Write, Swap)║
║                                            ║
║ Init: insertPos = 0                       ║
║                                            ║
║ For each i from 0 to n-1:                 ║
║ if nums[i] != 0:                          ║
║     swap(nums[i], nums[insertPos])        ║
║     insertPos++                           ║
║                                            ║
║ In-place, no return value                 ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Move Zeroes = Swap every non-zero forward into place; the swap naturally pushes a zero backward each time.**
 
- `nums[i] != 0` → swap with `nums[insertPos]`, `insertPos++`
- `nums[i] == 0` → do nothing, it'll get swapped back later
---
 
## 24. 30-Second Interview Explanation
 
> "I use two pointers: a read pointer that scans every element, and a write pointer, insertPos, that tracks where the next non-zero element should go. Whenever I find a non-zero value, I swap it with whatever is currently at insertPos — since everything up to that point that isn't yet placed is guaranteed to be a zero, this swap naturally relocates that zero to where the non-zero element used to be, while the non-zero element lands correctly in place. I then advance insertPos. This single pass preserves the relative order of non-zero elements and correctly pushes all zeros to the end, all in place, giving O(n) time and O(1) extra space."
 
---
 
## 25. Final Takeaway
 
```
               MOVE ZEROES
                    ↓
       SCAN EACH ELEMENT (read pointer i)
                    ↓
       IS nums[i] != 0 ?
                    ↓
              yes → SWAP nums[i] WITH nums[insertPos]
                     insertPos++
              no  → DO NOTHING (zero stays for now)
                    ↓
       REPEAT FOR NEXT ELEMENT
                    ↓
       ARRAY IS NOW CORRECTLY PARTITIONED
       (non-zeros in order, zeros at the end)
```
 
Remember:
 
```
nums[i] != 0 → swap into insertPos, insertPos++
nums[i] == 0 → leave it, it gets swapped back eventually
 
Swap (not overwrite) is what relocates zeros instead of losing them.
Two pointers, one pass, in place → O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 283 — Move Zeroes**.
 
