<h2><a href="https://leetcode.com/problems/plus-one">66. Plus One</a></h2><h3>Easy</h3><hr><p>You are given a <strong>large integer</strong> represented as an integer array <code>digits</code>, where each <code>digits[i]</code> is the <code>i<sup>th</sup></code> digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading <code>0</code>&#39;s.</p>

<p>Increment the large integer by one and return <em>the resulting array of digits</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = [1,2,3]
<strong>Output:</strong> [1,2,4]
<strong>Explanation:</strong> The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = [4,3,2,1]
<strong>Output:</strong> [4,3,2,2]
<strong>Explanation:</strong> The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = [9]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
	<li><code>digits</code> does not contain any leading <code>0</code>&#39;s.</li>
</ul>

# LeetCode 66 — Plus One
 
> **Problem:** LeetCode 66 — Plus One
> **Difficulty:** Easy
> **Pattern:** Array / Digit Manipulation
> **Main Technique:** Right-to-Left Carry Propagation
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1) amortized (O(n) only in the rare overflow case)
 
---
 
## 1. Problem Statement
 
You are given a **large integer** represented as an integer array `digits`, where each `digits[i]` is the `i`-th digit of the integer. The digits are ordered from **most significant** to **least significant** in left-to-right order. The large integer does not contain any leading `0`'s.
 
Increment the large integer by one and return the resulting array of digits.
 
### Example 1
 
```text
Input:
 
digits = [1,2,3]
 
Output:
 
[1,2,4]
 
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
```
 
### Example 2
 
```text
Input:
 
digits = [4,3,2,1]
 
Output:
 
[4,3,2,2]
 
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
```
 
### Example 3
 
```text
Input:
 
digits = [9]
 
Output:
 
[1,0]
 
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
```
 
---
 
## 2. Constraints
 
- `1 <= digits.length <= 100`
- `0 <= digits[i] <= 9`
- `digits` does not contain any leading `0`'s.
---
 
## 3. What Is the Problem Asking?
 
We're given a number represented digit-by-digit in an array (most significant digit first), and we need to add `1` to it — just like doing addition by hand, but with a carry that might ripple all the way through.
 
```
digits = [1, 2, 3]  →  represents 123
123 + 1 = 124
→ [1, 2, 4]
```
 
The tricky part is handling **carries** correctly, especially the special case where every digit is `9` (e.g., `999 + 1 = 1000`), which actually **grows the array by one digit**.
 
---
 
## 4. Core Idea
 
Since we're adding `1`, we only need to handle a carry that starts at the **last digit** and potentially ripples leftward:
 
```java
for (int i = digits.length - 1; i >= 0; i--) {
    if (digits[i] < 9) {
        digits[i]++;
        return digits;
    }
    digits[i] = 0;
}
```
 
Walk from the rightmost digit backward:
 
- If the current digit is **less than 9**, simply increment it by one — no carry needed, we're done, return immediately.
- If the current digit **is 9**, adding 1 makes it `10`, so we set this digit to `0` (the carry) and continue to the next digit to the left, carrying the `+1` forward.
If we fall through the entire loop (meaning **every digit was 9**, e.g., `[9,9,9]`), we need to **prepend a `1`** to the front of the array, since a number like `999 + 1 = 1000` gains a new leading digit.
 
---
 
## 5. The Two Cases Per Digit
 
### 5.1 Digit Less Than 9 — Simple Increment, Done
 
```java
if (digits[i] < 9) {
    digits[i]++;
    return digits;
}
```
 
No carry needed beyond this point — we can return immediately since nothing to the left of this digit changes.
 
### 5.2 Digit Equals 9 — Carry Propagates Left
 
```java
digits[i] = 0;
// continue loop to the next digit to the left
```
 
The digit "wraps around" to `0`, and the `+1` carry moves one position to the left.
 
---
 
## 6. Important Detail — Handling the All-Nines Overflow Case
 
If the loop completes without ever returning (every digit was `9`), the entire original array is now all zeros (e.g., `[9,9,9]` became `[0,0,0]` after the loop), and we need a **new array one element longer**, with a leading `1` followed by all the zeros:
 
```java
int[] result = new int[digits.length + 1];
result[0] = 1;
// result[1..] remain 0 by default
return result;
```
 
Example: `[9,9,9]` → after the loop, conceptually `[0,0,0]` → final result `[1,0,0,0]` (representing `1000`).
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int[] plusOne(int[] digits) {
 
        int n = digits.length;
 
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
 
        // Only reached if every digit was 9 (e.g., 999 -> 1000)
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Array Length**
 
```java
int n = digits.length;
```
 
**Right-to-Left Loop**
 
```java
for (int i = n - 1; i >= 0; i--)
```
 
Start from the least significant digit (rightmost) and move toward the most significant digit (leftmost) — exactly how carries propagate in manual addition.
 
**No-Carry Case**
 
```java
if (digits[i] < 9) {
    digits[i]++;
    return digits;
}
```
 
If the digit can absorb the `+1` without wrapping around, do it and return immediately — no further digits are affected.
 
**Carry Case**
 
```java
digits[i] = 0;
```
 
If the digit is `9`, incrementing it would make `10`, so we record the `0` part here and let the loop continue to carry the `1` into the next digit to the left.
 
**All-Nines Overflow**
 
```java
int[] result = new int[n + 1];
result[0] = 1;
return result;
```
 
If we exit the loop naturally (never returned early), every original digit was `9` and is now `0`. We allocate a new, longer array with a leading `1` (Java arrays default-initialize new `int[]` elements to `0`, so the rest are already correct).
 
---
 
## 9. Dry Run — Example With a Carry Chain
 
Input:
 
```
digits = [1, 2, 9]   (represents 129)
```
 
`n = 3`
 
| i | digits[i] | < 9? | action |
|---|---|---|---|
| 2 | 9 | no | digits[2] = 0, continue |
| 1 | 2 | yes | digits[1]++ = 3, return |
 
Result: `[1, 3, 0]` → represents `130` ✅ (129 + 1 = 130)
 
---
 
## 10. Dry Run — All Nines (Overflow Case)
 
Input:
 
```
digits = [9, 9, 9]   (represents 999)
```
 
`n = 3`
 
| i | digits[i] | < 9? | action |
|---|---|---|---|
| 2 | 9 | no | digits[2] = 0 |
| 1 | 9 | no | digits[1] = 0 |
| 0 | 9 | no | digits[0] = 0 |
 
Loop completes without returning → allocate `result = new int[4]`, `result[0] = 1`
 
Result: `[1, 0, 0, 0]` → represents `1000` ✅ (999 + 1 = 1000)
 
---
 
## 11. Why Not Convert to an Integer, Add 1, and Convert Back?
 
A tempting shortcut:
 
```java
long num = 0;
for (int d : digits) num = num * 10 + d;
num++;
return String.valueOf(num).chars().map(c -> c - '0').toArray();
```
 
This is simple, but it has a critical flaw: the problem allows arrays of up to `100` digits — a number with `100` digits vastly exceeds the range of even a `long` (which only holds about 19 decimal digits safely). This approach would **overflow** and give incorrect results for large inputs. The digit-by-digit carry approach has **no such limitation**, since it never needs to represent the whole number as a single numeric type — it works directly on the digit array regardless of how many digits there are.
 
---
 
## 12. Common Mistakes to Avoid
 
**Mistake 1 — Converting to `int` or `long` for large inputs**
As explained above, this overflows for inputs with more than ~9 (int) or ~18 (long) digits, which this problem explicitly allows (up to 100 digits).
 
**Mistake 2 — Forgetting the early return on the no-carry case**
Wrong: continuing the loop even after finding a digit `< 9` and incrementing it — this would incorrectly affect digits that shouldn't change.
Correct: `return digits;` immediately after the successful in-place increment.
 
**Mistake 3 — Forgetting the overflow case entirely**
Wrong: the function only ever modifies the existing array and returns it, without checking whether the loop completed without an early return.
Correct: always have a fallback path for the all-nines case, creating a new, longer array.
 
**Mistake 4 — Getting the new array's leading digit or size wrong**
Wrong: `int[] result = new int[n];` (same size, no room for the extra digit) or forgetting to set `result[0] = 1`.
Correct: `new int[n + 1]` with `result[0] = 1`, relying on Java's default zero-initialization for the rest.
 
**Mistake 5 — Looping left-to-right instead of right-to-left**
Since we're adding `1` (which starts at the least significant digit), the carry must propagate from **right to left** — looping the other direction would apply the increment to the wrong digit entirely.
 
---
 
## 13. Edge Cases
 
**Single Digit, No Carry:**
`[5]` → `digits[0] = 6` → Output: `[6]`
 
**Single Digit, With Carry:**
`[9]` → loop completes without returning → Output: `[1, 0]`
 
**All Nines:**
`[9,9,9]` → Output: `[1,0,0,0]`
 
**Carry Stops Partway Through:**
`[1,9,9]` → last two digits become `0`, then digit `1` (value `1`) increments to `2` → Output: `[2,0,0]`
 
**Largest Allowed Input (100 digits, all nines):**
`[9,9,...,9]` (100 nines) → Output: a 101-element array `[1,0,0,...,0]` — correctly handled since we never convert to a numeric type.
 
---
 
## 14. Time Complexity
 
In the worst case (all digits are `9`), we scan every digit once during the carry loop, then do one more pass implicitly via array allocation (which zero-initializes automatically).
 
**Time Complexity = O(n)**
 
---
 
## 15. Space Complexity
 
In the common case (no overflow), we modify the array **in place** — no extra space needed.
 
In the rare all-nines overflow case, we allocate one new array of size `n + 1`.
 
**Auxiliary Space = O(1)** amortized (or `O(n)` strictly in the overflow case, but this only happens for the specific all-`9`s input)
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)` (in-place) except for the rare overflow case, which needs `O(n)` for the new array
---
 
## 16. Interview Perspective
 
**Q1. What approach did you use?**
A right-to-left scan simulating manual addition: increment the last digit, and if it overflows past 9, set it to 0 and carry the `+1` into the next digit to the left. If every digit overflows, prepend a new leading `1`.
 
**Q2. Why can't you just convert the digit array to a number, add 1, and convert back?**
Because the array can represent numbers with up to 100 digits, which vastly exceeds what any primitive numeric type (`int`, `long`, even `double`) can represent exactly without overflow or precision loss. The digit-by-digit approach avoids this entirely by never needing a single numeric representation of the whole value.
 
**Q3. Why do you only need to handle a `+1` carry, and not a general addition carry?**
Because we're specifically adding `1`, not an arbitrary number — this guarantees the carry can only ever be `0` or `1` at each step, and specifically it can only be nonzero at the very start (the last digit) and then only continues if a `9` is encountered, simplifying the logic considerably compared to general big-number addition.
 
**Q4. When does the array actually need to grow in size?**
Only when **every single digit** is `9` — that's the only scenario where the carry survives all the way past the most significant digit, requiring an entirely new leading digit (e.g., `999 → 1000`, `9999 → 10000`).
 
**Q5. Is this solution in-place?**
Yes, in the common case — the original array is modified directly. Only in the specific all-nines overflow case do we need to allocate a new, slightly larger array, since the original array's length is fixed and can't accommodate an extra digit.
 
---
 
## 17. Interview Challenge Questions
 
**Question 1:** Why does the loop check `digits[i] < 9` rather than `digits[i] != 9`?
**Answer:** Both would work identically here, since digit values are constrained to `0-9` inclusive by the problem — `< 9` and `!= 9` are equivalent for this exact value range, though `< 9` reads slightly more naturally as "can absorb the increment without wrapping."
 
**Question 2:** What is the maximum number of digits that could change due to a single "plus one" operation?
**Answer:** Potentially **all** of them, in the case where every digit is `9` (e.g., `999...9 + 1` changes every digit and adds one more).
 
**Question 3:** How would this problem change if you needed to add an arbitrary number (not just `1`) to the digit array?
**Answer:** You'd need a more general carry mechanism, where the carry could be any value greater than `1` depending on the digit sum at each position, and you'd need to align and add the second number's digits as well — essentially full big-number addition, not just the simplified `+1` case.
 
**Question 4:** Why is allocating a new array acceptable here, given the emphasis on efficiency?
**Answer:** Because it only happens in the rare all-nines case, and even then, it's a single `O(n)` allocation — asymptotically this doesn't change the overall `O(n)` time complexity, it's just a necessary consequence of the output legitimately needing one more digit than the input.
 
---
 
## 18. Pattern Recognition
 
Whenever you see:
 
**"Increment a large number represented as a digit array by one"**
 
Immediately think:
 
```
SCAN RIGHT TO LEFT
digit < 9  → increment it, done, return
digit == 9 → set to 0, carry continues left
 
Loop finishes without returning → all digits were 9
→ allocate new array of size n+1, leading digit = 1
```
 
---
 
## 19. Visual Pattern
 
```
digits = [1, 2, 9]
 
Start from rightmost:
  9 → not < 9 → becomes 0, carry continues
  2 → < 9 → becomes 3 → DONE
 
Result: [1, 3, 0]
```
 
```
digits = [9, 9, 9]
 
Start from rightmost:
  9 → becomes 0, carry continues
  9 → becomes 0, carry continues
  9 → becomes 0, carry continues
  (loop ends, no early return)
 
New array needed: [1, 0, 0, 0]
```
 
Think: **INCREMENT THE LAST DIGIT. IF IT OVERFLOWS PAST 9, RESET IT AND CARRY LEFT. IF EVERYTHING OVERFLOWS, GROW THE NUMBER.**
 
---
 
## 20. Alternative Approach
 
**Using `BigInteger` (simple but less instructive):**
 
```java
import java.math.BigInteger;
 
class Solution {
    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int d : digits) sb.append(d);
 
        BigInteger num = new BigInteger(sb.toString());
        num = num.add(BigInteger.ONE);
 
        String result = num.toString();
        int[] output = new int[result.length()];
        for (int i = 0; i < result.length(); i++) {
            output[i] = result.charAt(i) - '0';
        }
        return output;
    }
}
```
 
This correctly handles arbitrarily large numbers (unlike using `int`/`long`), since `BigInteger` has no fixed size limit. However, it's generally considered less elegant for an interview setting — it sidesteps the core digit-manipulation logic that this problem is designed to test, and has more overhead (string conversions, `BigInteger` arithmetic) compared to the direct carry-propagation approach.
 
---
 
## 21. Senior Engineer Perspective
 
Don't think of this as "add one to a number." Think of it as **simulating manual carry propagation on a fixed-width digit sequence, with a graceful fallback for growing that sequence**.
 
> The core insight is that adding exactly `1` has an extremely limited carry behavior — it can only ever ripple through consecutive `9`s from the right, and stops the instant it hits a non-`9` digit. This bounded, predictable carry pattern is what makes an `O(n)` in-place solution possible, with the array-growth case being a rare, clearly-identifiable edge case (all digits were `9`) rather than a general concern.
 
```
Scan digits right to left
      ↓
digit < 9?
      ↓ yes                ↓ no
increment, return      set to 0, continue left
      ↓
Loop exhausted (never returned)
      ↓
All digits were 9 → allocate new array,
leading digit = 1, rest already 0
```
 
---
 
## 22. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Plus One | 66 | Digit Manipulation / Carry Propagation |
| Add Binary | 67 | Digit Manipulation / Carry Propagation |
| Add Strings | 415 | Digit Manipulation / Carry Propagation |
| Add Two Numbers | 2 | Linked List / Carry Propagation |
| Multiply Strings | 43 | Digit Manipulation |
| Plus One Linked List | 369 | Linked List / Carry Propagation |
 
---
 
## 23. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 66 — PLUS ONE                   ║
╠══════════════════════════════════════════╣
║ Pattern: Right-to-Left Carry Propagation  ║
║                                            ║
║ For i from n-1 down to 0:                 ║
║ digits[i] < 9 → digits[i]++, return       ║
║ digits[i] == 9 → digits[i] = 0, continue  ║
║                                            ║
║ Loop finishes (all were 9):               ║
║ result = new int[n+1]                     ║
║ result[0] = 1                             ║
║ return result                             ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1) / O(n) on overflow  ║
╚══════════════════════════════════════════╝
```
 
---
 
## 24. One-Line Memory Trick
 
**Plus One = Walk right to left; a non-nine absorbs the carry and stops, an all-nine array grows by one digit.**
 
- `digits[i] < 9` → increment, done
- `digits[i] == 9` → reset to 0, carry left
- All 9s → new array, leading `1`
---
 
## 25. 30-Second Interview Explanation
 
> "I scan the digit array from right to left, simulating how a carry propagates in manual addition. If a digit is less than 9, I just increment it and return immediately, since no further digits are affected. If a digit is 9, incrementing it would overflow, so I set it to 0 and let the carry continue into the next digit to the left. If I reach the end of the loop without ever returning, that means every digit was 9 — like 999 becoming 1000 — so I allocate a new array one digit longer, set the leading digit to 1, and let the rest default to 0. This runs in O(n) time and is O(1) space in the common case, only needing O(n) extra space in the rare all-nines scenario."
 
---
 
## 26. Final Takeaway
 
```
                PLUS ONE
                   ↓
     SCAN DIGITS RIGHT TO LEFT
                   ↓
     DIGIT < 9?
           ↓            ↓
          yes           no
           ↓            ↓
    INCREMENT,     SET TO 0,
    RETURN         CONTINUE LEFT
                       ↓
     LOOP ENDS WITHOUT RETURNING
     (ALL DIGITS WERE 9)
                   ↓
     ALLOCATE NEW ARRAY (size n+1)
     LEADING DIGIT = 1, REST = 0
                   ↓
              RETURN RESULT
```
 
Remember:
 
```
digit < 9  → digit++, done
digit == 9 → digit = 0, carry continues left
all 9s     → grow array, prepend a leading 1
 
Simulates manual addition's carry — bounded to a chain of trailing 9s.
```
 
This is the core pattern behind **LeetCode 66 — Plus One**.
 
