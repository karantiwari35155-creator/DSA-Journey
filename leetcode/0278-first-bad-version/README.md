<h2><a href="https://leetcode.com/problems/first-bad-version">278. First Bad Version</a></h2><h3>Easy</h3><hr><p>You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.</p>

<p>Suppose you have <code>n</code> versions <code>[1, 2, ..., n]</code> and you want to find out the first bad one, which causes all the following ones to be bad.</p>

<p>You are given an API <code>bool isBadVersion(version)</code> which returns whether <code>version</code> is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, bad = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong>
call isBadVersion(3) -&gt; false
call isBadVersion(5)&nbsp;-&gt; true
call isBadVersion(4)&nbsp;-&gt; true
Then 4 is the first bad version.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, bad = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bad &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

# LeetCode 278 — First Bad Version
 
> **Problem:** LeetCode 278 — First Bad Version
> **Difficulty:** Easy
> **Pattern:** Binary Search
> **Main Technique:** Binary Search on the Answer (Find First True in a Boolean Sequence)
> **Time Complexity:** O(log n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 
Suppose you have `n` versions `[1, 2, ..., n]` and you want to find out the first bad one, which causes all the following ones to be bad.
 
You are given an API `bool isBadVersion(version)` which returns whether `version` is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 
### Example 1
 
```text
Input:
 
n = 5, bad = 4
 
Output:
 
4
 
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.
```
 
### Example 2
 
```text
Input:
 
n = 1, bad = 1
 
Output:
 
1
```
 
---
 
## 2. Constraints
 
- `1 <= bad <= n <= 2^31 - 1`
---
 
## 3. What Is the Problem Asking?
 
We have versions numbered `1` to `n`, and once a version is "bad," **every version after it is also bad**. This creates a clean split point:
 
```
1   2   3   4   5   6   7   8   9   10
G   G   G   B   B   B   B   B   B   B    ← G = good, B = bad
 
First bad version = 4
```
 
We need to find the exact index where "good" transitions to "bad" — using the fewest possible calls to the given `isBadVersion(version)` API, since each call is treated as expensive (e.g., it might represent an actual test run or network call in the real-world scenario this problem models).
 
---
 
## 4. Core Idea
 
Since the sequence of good/bad versions is **monotonic** (all goods come before all bads, with no mixing), this is a textbook **binary search** problem — specifically, "find the first `true` in a sorted boolean sequence."
 
```java
int left = 1;
int right = n;
 
while (left < right) {
    int mid = left + (right - left) / 2;
    if (isBadVersion(mid)) {
        right = mid;       // mid could be the answer, or the answer is even earlier
    } else {
        left = mid + 1;    // mid is good, so the answer must be after it
    }
}
 
return left;
```
 
At each step, we check the middle version:
 
- If it's **bad**, the first bad version is at `mid` or **somewhere earlier** — so we narrow the search to `[left, mid]`, keeping `mid` itself as a candidate (we don't rule it out).
- If it's **good**, the first bad version must be **after** `mid` — so we narrow the search to `[mid+1, right]`.
This is exactly the same idea as binary searching for the leftmost `true` in a boolean array — we never need to check every version individually.
 
---
 
## 5. The Two Cases Per Check
 
### 5.1 `mid` Is Bad — Narrow to the Left Half (Including mid)
 
```java
if (isBadVersion(mid)) {
    right = mid;
}
```
 
`mid` might **be** the first bad version, or the actual first bad version could be even earlier — either way, we know it's somewhere in `[left, mid]`.
 
### 5.2 `mid` Is Good — Narrow to the Right Half (Excluding mid)
 
```java
else {
    left = mid + 1;
}
```
 
Since `mid` is confirmed good, the first bad version (if it exists within our search range) must come strictly **after** `mid`.
 
---
 
## 6. Important Detail — Why `right = mid` (Not `mid - 1`) When Bad
 
Unlike a classic binary search for an *exact value* (where you'd exclude `mid` once checked), here we're searching for a **boundary** — the first occurrence of `true` (bad). Since `mid` itself is a valid candidate for being that boundary, we **must keep it in the search range** when it's bad:
 
```
right = mid;      // correct: mid is still a candidate answer
right = mid - 1;  // WRONG: this could skip over the actual answer if mid IS the first bad version
```
 
This is the classic **"find leftmost true"** binary search template, distinct from the classic "find exact target" template.
 
---
 
## 7. Complete Java Code
 
```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
 
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
 
        int left = 1;
        int right = n;
 
        while (left < right) {
 
            int mid = left + (right - left) / 2;
 
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
 
        return left;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Search Bounds**
 
```java
int left = 1;
int right = n;
```
 
Versions are numbered from `1` to `n` (1-indexed, per the problem statement), so the search range starts as the full version range.
 
**Binary Search Loop**
 
```java
while (left < right)
```
 
Unlike a classic binary search that uses `left <= right`, this "find the boundary" style loop uses `left < right` — the loop ends precisely when `left == right`, at which point that single remaining value **is** the answer (no further comparison needed).
 
**Midpoint Calculation**
 
```java
int mid = left + (right - left) / 2;
```
 
Avoids potential overflow compared to `(left + right) / 2`, which matters here since `n` can be as large as `2^31 - 1` (dangerously close to `Integer.MAX_VALUE`).
 
**Bad Version Found — Narrow Left (Inclusive)**
 
```java
if (isBadVersion(mid)) {
    right = mid;
}
```
 
**Good Version Found — Narrow Right (Exclusive)**
 
```java
else {
    left = mid + 1;
}
```
 
**Return the Converged Value**
 
```java
return left;
```
 
By the time the loop ends, `left == right`, and this value is guaranteed to be the first bad version.
 
---
 
## 9. Dry Run
 
Input:
 
```
n = 5, bad = 4
```
 
`left = 1, right = 5`
 
| step | left | right | mid | isBadVersion(mid) | action |
|---|---|---|---|---|---|
| 1 | 1 | 5 | 3 | false (3 is good) | left = 4 |
| 2 | 4 | 5 | 4 | true (4 is bad) | right = 4 |
| — | 4 | 4 | — | left == right | loop ends |
 
Return `left = 4` ✅ (matches expected output)
 
Notice this matches the problem's own example trace: checking version `3` (false), then narrowing and checking version `4` (true) — though the exact midpoint sequence can vary slightly depending on implementation details, the core logic converges correctly.
 
---
 
## 10. Why Not Just Call `isBadVersion` for Every Version from 1 to n?
 
The naive linear approach:
 
```java
for (int i = 1; i <= n; i++) {
    if (isBadVersion(i)) {
        return i;
    }
}
```
 
This is `O(n)` in the worst case (if the first bad version is near the end, or `n` itself is bad). Given `n` can be up to `2^31 - 1` (over 2 billion), this would be catastrophically slow and would make an enormous number of API calls. Binary search reduces this to `O(log n)` — for `n = 2^31 - 1`, that's only about **31 calls** in the worst case, an enormous improvement, and directly addresses the problem's explicit goal of **minimizing API calls**.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Using `right = mid - 1` instead of `right = mid` when a bad version is found**
Wrong: this can cause the search to skip over the actual first bad version if `mid` itself is the answer.
Correct: `right = mid;` — always keep `mid` as a candidate when it's bad.
 
**Mistake 2 — Using `left <= right` as the loop condition (classic binary search style)**
Wrong: this style is designed for finding an *exact* target and returning `-1` if not found — it doesn't cleanly handle the "find the boundary" pattern here without extra post-loop logic.
Correct: `while (left < right)`, which naturally converges to the answer without needing a separate "not found" case (since a bad version is guaranteed to exist, per the constraints).
 
**Mistake 3 — Computing `mid` with `(left + right) / 2`**
Wrong: for very large `n` (up to `2^31 - 1`), `left + right` can overflow a 32-bit `int`.
Correct: `left + (right - left) / 2`, which never overflows.
 
**Mistake 4 — Starting `left` at `0` instead of `1`**
The problem explicitly states versions are numbered `[1, 2, ..., n]` — starting the search at `0` would waste a comparison on a version number that doesn't exist, and could subtly break the logic depending on how bounds are handled.
Correct: `left = 1`.
 
**Mistake 5 — Calling `isBadVersion` more times than necessary (e.g., re-checking `mid` after narrowing)**
Each call to `isBadVersion` should be used to make exactly one narrowing decision — calling it redundantly (e.g., checking the same `mid` twice) wastes API calls, which the problem explicitly asks us to minimize.
 
---
 
## 12. Edge Cases
 
**Only One Version, Which Is Bad:**
`n = 1, bad = 1` → `left = right = 1` immediately, loop never executes → Output: `1`
 
**First Version Is the Bad One:**
`n = 5, bad = 1` → binary search converges quickly toward the very first version being confirmed bad.
 
**Last Version Is the Bad One:**
`n = 5, bad = 5` → binary search converges toward the very last version.
 
**Very Large n:**
`n = 2^31 - 1` → still resolves in about 31 steps, thanks to `O(log n)` behavior — this is precisely why the overflow-safe midpoint calculation matters.
 
**All Versions Are Bad (bad = 1, effectively):**
Handled naturally — the first version itself gets confirmed as bad extremely early in the search.
 
---
 
## 13. Time Complexity
 
Binary search halves the search range at each step.
 
**Time Complexity = O(log n)**
 
This also directly corresponds to the **number of API calls** to `isBadVersion`, which is exactly what the problem asks us to minimize.
 
---
 
## 14. Space Complexity
 
Only a few integer variables (`left`, `right`, `mid`) are used, regardless of `n`.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(log n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Binary search on the range of version numbers, treating the good/bad status as a monotonic boolean sequence — I narrow the search range based on whether the middle version is bad or good, converging on the exact boundary where "good" transitions to "bad."
 
**Q2. Why does binary search apply here, given there's no literal sorted array?**
Because the *property* being searched (bad-ness) is monotonic across the version numbers — once a version is bad, everything after it is also bad. This monotonic structure is exactly what binary search requires; you don't need a literal array, just a predicate that behaves consistently across an ordered range.
 
**Q3. Why use `right = mid` instead of `right = mid - 1` when the middle version is bad?**
Because we're searching for the **first** occurrence of "bad," and `mid` itself might be that first occurrence — excluding it via `mid - 1` risks skipping over the correct answer if `mid` truly is the boundary.
 
**Q4. What's the significance of minimizing API calls here, and how does binary search address it?**
The problem models `isBadVersion` as a potentially expensive operation (e.g., running an actual test suite in a real CI/CD pipeline). Binary search minimizes calls to `O(log n)`, which for very large `n` is a massive improvement over a linear scan — turning billions of potential calls into roughly 30.
 
**Q5. Why is `left + (right - left) / 2` preferred over `(left + right) / 2`?**
To avoid integer overflow — since `n` can be as large as `2^31 - 1` (very close to `Integer.MAX_VALUE`), adding `left + right` directly could overflow a 32-bit signed integer, producing an incorrect (and possibly negative) midpoint.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** How does this problem's binary search pattern differ from a "find exact target" binary search?
**Answer:** A "find exact target" search uses `left <= right` and typically returns `-1` (or similar) if the exact value isn't found, adjusting bounds with `mid ± 1` on both sides. This "find the first true/boundary" pattern uses `left < right`, only ever excludes `mid` when it's confirmed *not* the answer (i.e., when it's good), and the loop naturally converges to the answer without a separate "not found" case.
 
**Question 2:** What would happen if the problem didn't guarantee at least one bad version exists?
**Answer:** The current algorithm would still run without error, but the returned `left` value at the end wouldn't necessarily represent a real "first bad version" — it would just be whatever version number the range narrowed to, which could be a false positive if no version was actually bad. In such a case, an additional check (`isBadVersion(left)`) would be needed after the loop to confirm validity.
 
**Question 3:** Could this problem be solved with exponential/galloping search instead of standard binary search, and would that help?
**Answer:** Exponential search could be useful if you had reason to believe the first bad version was very close to the beginning of a very large range (to avoid wasting comparisons on a huge already-known-good prefix), but since we have no such prior information here and the full range `[1, n]` is already known and bounded, standard binary search is the appropriate and sufficient choice.
 
**Question 4:** Is this problem's structure similar to any other well-known binary search variants?
**Answer:** Yes — it's structurally identical to "find the first element in a sorted array that satisfies a given condition," which also appears in problems like finding the first element greater than or equal to a target, or finding the boundary in `Arrays.binarySearch`-style lower-bound queries.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Find the first position where a condition becomes true, given the condition is monotonic (once true, always true after)"**
 
Immediately think:
 
```
BINARY SEARCH ON THE BOUNDARY
left = start, right = end
 
while (left < right):
    mid = left + (right - left) / 2
    condition(mid) is TRUE  → right = mid       (keep mid as candidate)
    condition(mid) is FALSE → left = mid + 1     (mid ruled out)
 
return left  (converged boundary — the first TRUE)
```
 
---
 
## 18. Visual Pattern
 
```
Versions:  1   2   3   4   5
Status:    G   G   G   B   B
 
left=1, right=5, mid=3 → isBadVersion(3)=false → left=4
 
Versions:  1   2   3   4   5
                       ↑   ↑
                     left right
 
mid=4 → isBadVersion(4)=true → right=4
 
left == right == 4 → ANSWER = 4
```
 
Think: **BAD FOUND? IT MIGHT BE THE FIRST ONE — KEEP IT. GOOD FOUND? THE ANSWER IS STRICTLY LATER.**
 
---
 
## 19. Alternative Approach
 
There isn't a meaningfully different *efficient* alternative — the linear scan shown in Section 10 is the only other approach, and it's asymptotically far worse (`O(n)` vs `O(log n)`). Some implementations phrase the binary search slightly differently (e.g., using `left <= right` with explicit tracking of the "best answer so far"), but this is functionally equivalent to the boundary-search template shown here:
 
```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        int answer = n;
 
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
 
        return answer;
    }
}
```
 
This variant explicitly tracks the best candidate answer found so far and continues shrinking the range using `mid - 1` / `mid + 1` on both sides — same `O(log n)` complexity, just a slightly different (equally valid) binary search bookkeeping style.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "search an array." Think of it as **binary searching directly over a range of integers using a monotonic predicate**, with no actual array in memory at all.
 
> The generalization here is important: binary search doesn't require a physical sorted array — it only requires that you can evaluate a monotonic condition at any point in an ordered range. This is the exact same principle behind "binary search on the answer" techniques used in optimization problems (e.g., "find the minimum capacity such that X is possible"), where the "array" is conceptual and the "elements" are just integers being tested against a yes/no predicate.
 
```
left = 1, right = n
      ↓
mid = left + (right - left) / 2
      ↓
isBadVersion(mid) ?
      ↓ true                  ↓ false
right = mid                 left = mid + 1
(mid still a candidate)     (mid ruled out)
      ↓
Repeat until left == right
      ↓
Return left (the first bad version)
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| First Bad Version | 278 | Binary Search on Boundary |
| Binary Search | 704 | Classic Binary Search |
| Find First and Last Position of Element in Sorted Array | 34 | Binary Search Variant (boundaries) |
| Search Insert Position | 35 | Binary Search (lower bound) |
| Capacity To Ship Packages Within D Days | 1011 | Binary Search on the Answer |
| Koko Eating Bananas | 875 | Binary Search on the Answer |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 278 — FIRST BAD VERSION         ║
╠══════════════════════════════════════════╣
║ Pattern: Binary Search on Boundary        ║
║                                            ║
║ Init:                                     ║
║ left = 1                                  ║
║ right = n                                 ║
║                                            ║
║ Loop while left < right:                  ║
║ mid = left + (right-left)/2               ║
║                                            ║
║ isBadVersion(mid) == true  → right = mid   ║
║ isBadVersion(mid) == false → left = mid+1  ║
║                                            ║
║ Return: left (== right at convergence)    ║
║                                            ║
║ Time: O(log n)                            ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**First Bad Version = Binary search for the first "true" in a monotonic good→bad sequence; bad keeps mid as a candidate, good rules it out.**
 
- `isBadVersion(mid)` true  → `right = mid`
- `isBadVersion(mid)` false → `left = mid + 1`
---
 
## 24. 30-Second Interview Explanation
 
> "Since once a version is bad, every version after it is also bad, the good/bad status across versions forms a monotonic sequence — this is a classic binary search on a boundary. I check the middle version: if it's bad, the first bad version is at or before this point, so I narrow the search to include it; if it's good, the first bad version must come strictly after it, so I exclude this point and search the right half. I use left + (right-left)/2 for the midpoint to avoid integer overflow, since n can be very large. The loop continues until left and right converge, at which point that value is guaranteed to be the first bad version. This runs in O(log n) time, directly minimizing the number of expensive isBadVersion calls, which is exactly what the problem asks for."
 
---
 
## 25. Final Takeaway
 
```
            FIRST BAD VERSION
                    ↓
       left = 1, right = n
                    ↓
       mid = left + (right-left)/2
                    ↓
       isBadVersion(mid) ?
           ↓              ↓
         true            false
           ↓              ↓
      right = mid     left = mid + 1
      (keep as              (rule out,
       candidate)            search right)
           ↓
     REPEAT UNTIL left == right
                    ↓
             RETURN left
```
 
Remember:
 
```
isBadVersion(mid) == true  → right = mid    (mid could BE the answer)
isBadVersion(mid) == false → left = mid + 1 (mid is ruled out)
 
Binary search on a monotonic predicate, not a literal array.
O(log n) time, O(1) space — minimizes expensive API calls.
```
 
This is the core pattern behind **LeetCode 278 — First Bad Version**.
 
