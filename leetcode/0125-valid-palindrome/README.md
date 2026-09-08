<h2><a href="https://leetcode.com/problems/valid-palindrome">125. Valid Palindrome</a></h2><h3>Easy</h3><hr><p>A phrase is a <strong>palindrome</strong> if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.</p>

<p>Given a string <code>s</code>, return <code>true</code><em> if it is a <strong>palindrome</strong>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;A man, a plan, a canal: Panama&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;amanaplanacanalpanama&quot; is a palindrome.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;race a car&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;raceacar&quot; is not a palindrome.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot; &quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s is an empty string &quot;&quot; after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of printable ASCII characters.</li>
</ul>

# LeetCode 125 — Valid Palindrome
 
> **Problem:** LeetCode 125 — Valid Palindrome
> **Difficulty:** Easy
> **Pattern:** Two Pointers / String Processing
> **Main Technique:** Opposite-Direction Two Pointers with Character Filtering
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 
Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.
 
### Example 1
 
```text
Input:
 
s = "A man, a plan, a canal: Panama"
 
Output:
 
true
 
Explanation: "amanaplanacanalpanama" is a palindrome.
```
 
### Example 2
 
```text
Input:
 
s = "race a car"
 
Output:
 
false
 
Explanation: "raceacar" is not a palindrome.
```
 
### Example 3
 
```text
Input:
 
s = " "
 
Output:
 
true
 
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
```
 
---
 
## 2. Constraints
 
- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.
---
 
## 3. What Is the Problem Asking?
 
We need to check whether a string is a palindrome, but **only considering letters and digits**, and **ignoring case**.
 
```
s = "A man, a plan, a canal: Panama"
 
After filtering + lowercasing:
"amanaplanacanalpanama"
 
Read forward:  a m a n a p l a n a c a n a l p a n a m a
Read backward: a m a n a p l a n a c a n a l p a n a m a
 
→ Same → Palindrome → true
```
 
Punctuation, spaces, and casing are all irrelevant — only the alphanumeric "core" of the string matters, compared case-insensitively.
 
---
 
## 4. Core Idea
 
Instead of building a new cleaned string first and then checking if it's a palindrome (which uses `O(n)` extra space), we use **two pointers moving toward each other directly on the original string**, skipping over non-alphanumeric characters as we go:
 
```java
int left = 0;
int right = s.length() - 1;
```
 
At each step:
 
1. Skip `left` forward past any non-alphanumeric character.
2. Skip `right` backward past any non-alphanumeric character.
3. Compare the lowercase versions of `s.charAt(left)` and `s.charAt(right)`.
4. If they differ, it's not a palindrome. If they match, move both pointers inward and repeat.
This lets us check the palindrome property in a single pass with **no extra string allocation**.
 
---
 
## 5. The Two Operations Per Step
 
### 5.1 Skip Non-Alphanumeric Characters
 
```java
while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
    left++;
}
while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
    right--;
}
```
 
This advances each pointer past commas, spaces, colons, etc., until it lands on an actual letter or digit (or the pointers meet).
 
### 5.2 Compare Characters (Case-Insensitive)
 
```java
if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
    return false;
}
```
 
If the lowercase characters at both pointers don't match, the string isn't a palindrome — return immediately.
 
---
 
## 6. Important Detail — Why the Bounds Checks Matter
 
Every skip loop includes `left < right` as a guard:
 
```java
while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
    left++;
}
```
 
Without this, if the string has **no alphanumeric characters at all** (e.g., `s = ",."`), the pointer could run past the other pointer and even out of the string bounds, causing an `IndexOutOfBoundsException`. The `left < right` check ensures we stop advancing once the pointers meet, safely treating an all-punctuation string as an (empty, and therefore valid) palindrome.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public boolean isPalindrome(String s) {
 
        int left = 0;
        int right = s.length() - 1;
 
        while (left < right) {
 
            // Skip non-alphanumeric characters from the left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
 
            // Skip non-alphanumeric characters from the right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
 
            // Compare lowercase versions of both characters
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
 
            left++;
            right--;
        }
 
        return true;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Pointer Initialization**
 
```java
int left = 0;
int right = s.length() - 1;
```
 
`left` starts at the beginning of the string, `right` starts at the end.
 
**Outer While Loop**
 
```java
while (left < right)
```
 
Continue as long as the pointers haven't met or crossed.
 
**Skip Non-Alphanumeric — Left Side**
 
```java
while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
    left++;
}
```
 
Moves `left` forward past punctuation/spaces until it lands on a letter or digit.
 
**Skip Non-Alphanumeric — Right Side**
 
```java
while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
    right--;
}
```
 
Moves `right` backward past punctuation/spaces until it lands on a letter or digit.
 
**Case-Insensitive Comparison**
 
```java
if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
    return false;
}
```
 
If the two characters don't match (ignoring case), the string cannot be a palindrome.
 
**Move Pointers Inward**
 
```java
left++;
right--;
```
 
If the characters matched, shrink the window and continue checking.
 
**Return True**
 
```java
return true;
```
 
If the loop completes without finding any mismatch, the string is a valid palindrome.
 
---
 
## 9. Dry Run
 
Input:
 
```
s = "A man, a plan, a canal: Panama"
```
 
`left = 0 ('A')`, `right = 29 ('a')` (last character)
 
| step | left char | right char | lowercase match? | action |
|---|---|---|---|---|
| 1 | 'A' | 'a' | 'a' == 'a' ✅ | left++, right-- |
| 2 | ' ' (skip) → 'm' | 'm' | 'm' == 'm' ✅ | left++, right-- |
| 3 | 'a' | 'a' | ✅ | left++, right-- |
| ... | ... | ... | ... | ... continues matching through the entire cleaned string |
| final | pointers meet/cross | — | all matched | loop ends |
 
Return `true` ✅ (matches expected output — cleaned string `"amanaplanacanalpanama"` is a palindrome)
 
---
 
## 10. Why Not Build a Cleaned String First?
 
A simpler-looking approach:
 
```java
StringBuilder sb = new StringBuilder();
for (char c : s.toCharArray()) {
    if (Character.isLetterOrDigit(c)) {
        sb.append(Character.toLowerCase(c));
    }
}
String cleaned = sb.toString();
return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
```
 
This works and is also `O(n)` time, but uses `O(n)` **extra space** for the cleaned string and its reverse.
 
The two-pointer approach achieves the same result while only ever using a constant number of pointer/index variables — filtering and comparing "in place" on the original string, which is the more space-efficient and typically expected solution for this problem.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Forgetting the `left < right` guard inside the skip loops**
Wrong: `while (!Character.isLetterOrDigit(s.charAt(left))) { left++; }` — can run out of bounds if the string has no alphanumeric characters, or if all remaining characters are non-alphanumeric.
Correct: always guard with `left < right`.
 
**Mistake 2 — Comparing characters without lowercasing them**
Wrong: `if (s.charAt(left) != s.charAt(right))` — this fails on inputs like `"Aa"`, which should be considered a palindrome after case normalization.
Correct: compare `Character.toLowerCase(...)` on both sides.
 
**Mistake 3 — Using `Character.isLetter` instead of `Character.isLetterOrDigit`**
Wrong: this would incorrectly skip over digit characters, which the problem explicitly says should count as alphanumeric.
Correct: use `Character.isLetterOrDigit`.
 
**Mistake 4 — Building a cleaned string with regex replace, which is less efficient**
`s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase()` works but typically has more overhead than manual character filtering, and still uses `O(n)` extra space — fine for correctness, but not optimal for the "single pass, O(1) space" ideal.
 
**Mistake 5 — Off-by-one after a match**
Wrong: forgetting to increment `left` and decrement `right` after a successful character match, causing an infinite loop.
Correct: always move both pointers inward after each successful comparison.
 
---
 
## 12. Edge Cases
 
**Empty String After Filtering:**
`s = " "` → no alphanumeric characters at all → pointers immediately meet/cross → Output: `true` (vacuously a palindrome)
 
**Single Character:**
`s = "a"` → `left == right` from the start, loop body never runs → Output: `true`
 
**All Punctuation:**
`s = ".,;:!?"` → both pointers skip everything and meet in the middle without ever comparing → Output: `true`
 
**Mixed Case:**
`s = "Aa"` → lowercased comparison `'a' == 'a'` → Output: `true`
 
**Not a Palindrome:**
`s = "race a car"` → cleaned to `"raceacar"`, first char `'r'` vs last char `'r'` matches, but eventually `'c'` vs `'a'` mismatches → Output: `false`
 
**Numbers Included:**
`s = "12321"` → digits count as alphanumeric → cleaned stays `"12321"`, which is a palindrome → Output: `true`
 
---
 
## 13. Time Complexity
 
Each character in the string is visited (and potentially skipped) at most once across the combined movement of `left` and `right`.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only two pointer variables (`left`, `right`) are used, regardless of string length — no cleaned copy of the string is created.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Two pointers starting at both ends of the string, skipping non-alphanumeric characters and comparing lowercase versions of the remaining characters until the pointers meet.
 
**Q2. Why is this better than building a cleaned string first?**
The two-pointer approach uses `O(1)` extra space, while building a cleaned string (and possibly its reverse) uses `O(n)` extra space. Both are `O(n)` time, but the two-pointer version is more memory-efficient.
 
**Q3. Why do the skip loops need the `left < right` guard?**
Without it, if the string contains only non-alphanumeric characters (or the remaining unchecked portion does), the pointer could advance past the other pointer and eventually run out of the string's valid index range, causing an out-of-bounds error.
 
**Q4. How do you handle case-insensitivity?**
By comparing `Character.toLowerCase(s.charAt(left))` against `Character.toLowerCase(s.charAt(right))` instead of the raw characters.
 
**Q5. What Java method determines whether a character counts as "alphanumeric" here?**
`Character.isLetterOrDigit(char)`, which returns `true` for both letters (any case) and digit characters.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why use `Character.isLetterOrDigit` instead of manually checking ASCII ranges like `'a'-'z'`, `'A'-'Z'`, `'0'-'9'`?
**Answer:** `Character.isLetterOrDigit` is more robust and readable — it correctly handles the full range of what Java considers letters and digits without manually hardcoding ASCII bounds, though for this specific problem (which only guarantees printable ASCII input) manual range checks would also work.
 
**Question 2:** What happens if `left` and `right` start equal (single-character string)?
**Answer:** The `while (left < right)` condition is immediately `false`, so the loop body never executes, and the function returns `true` — correct, since a single character is trivially a palindrome.
 
**Question 3:** How would you modify this solution to also return the cleaned string itself, not just whether it's a palindrome?
**Answer:** You'd need to build the cleaned string separately (e.g., with a `StringBuilder`) in addition to running the two-pointer check, which would then require `O(n)` extra space — the two goals (checking in `O(1)` space vs. returning the cleaned string) are in tension.
 
**Question 4:** Is this solution stable if the input string is extremely large (e.g., `2 * 10^5` characters, per constraints)?
**Answer:** Yes — since it's `O(n)` time and `O(1)` space with no recursion or extra allocations, it scales linearly and comfortably handles the maximum input size within the given constraints.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Check if a string is a palindrome, ignoring case and/or ignoring certain characters"**
 
Immediately think:
 
```
TWO POINTERS FROM BOTH ENDS
left  = 0
right = length - 1
 
SKIP characters that don't count (non-alphanumeric)
COMPARE remaining characters case-insensitively
MOVE both pointers inward on a match
MISMATCH → not a palindrome
POINTERS MEET/CROSS → it IS a palindrome
```
 
---
 
## 18. Visual Pattern
 
```
s = "A man, a plan, a canal: Panama"
     ↑                             ↑
   left                          right
 
'A' vs 'a' → lowercase match ('a' == 'a') → move inward
 
s = "A man, a plan, a canal: Panama"
      ↑                           ↑
    left                        right
 
' ' is not alphanumeric → skip left forward
'm' vs 'm' → match → move inward
 
... continues until pointers meet, all matching ...
```
 
Think: **SKIP THE NOISE. COMPARE WHAT'S LEFT. MEET IN THE MIDDLE WITHOUT A MISMATCH = PALINDROME.**
 
---
 
## 19. Alternative Approach
 
**Build a cleaned string, then compare it to its reverse:**
 
```java
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String cleaned = sb.toString();
        String reversed = sb.reverse().toString();
        return cleaned.equals(reversed);
    }
}
```
 
This is also `O(n)` time but uses `O(n)` extra space for the cleaned string and its reversed copy. Simpler to write and reason about, but less space-efficient than the two-pointer approach.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "clean the string, then check a palindrome." Think of it as **a single converging scan that filters and compares simultaneously**.
 
> The two-pointer technique lets you fuse two logically separate steps — "remove noise" and "check symmetry" — into one pass, because both steps only ever need to look at the current pointer positions, never anything already discarded. This fusion is what turns an `O(n)` time + `O(n)` space solution into `O(n)` time + `O(1)` space.
 
```
left = 0, right = length - 1
      ↓
Skip non-alphanumeric on both sides
      ↓
Characters match (case-insensitive)?
      ↓                    ↓
     yes                   no
      ↓                    ↓
Move both pointers      RETURN false
inward, repeat
      ↓
Pointers meet/cross → RETURN true
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Valid Palindrome | 125 | Two Pointers / String Filtering |
| Valid Palindrome II | 680 | Two Pointers + One Deletion Allowed |
| Palindrome Linked List | 234 | Two Pointers / Fast-Slow |
| Longest Palindromic Substring | 5 | Expand Around Center / DP |
| Palindromic Substrings | 647 | Expand Around Center |
| Two Sum II - Input Array Is Sorted | 167 | Two Pointers |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 125 — VALID PALINDROME          ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers + Char Filtering    ║
║                                            ║
║ Init:                                     ║
║ left  = 0                                 ║
║ right = s.length() - 1                    ║
║                                            ║
║ Loop while left < right:                  ║
║ - skip non-alphanumeric (both sides)      ║
║ - compare lowercase chars                 ║
║ - mismatch → return false                 ║
║ - match → left++, right--                 ║
║                                            ║
║ No mismatch found → return true           ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Valid Palindrome = Two pointers from both ends, skip non-alphanumeric characters, compare lowercase, meet in the middle without a mismatch.**
 
- Skip → `Character.isLetterOrDigit`
- Compare → `Character.toLowerCase`
---
 
## 24. 30-Second Interview Explanation
 
> "I use two pointers, one starting at the beginning of the string and one at the end. At each step, I skip over any non-alphanumeric characters from both sides, then compare the lowercase versions of the two characters I land on. If they ever don't match, I return false immediately. If they match, I move both pointers inward and continue. If the pointers meet or cross without ever finding a mismatch, the string is a valid palindrome. This avoids building any cleaned copy of the string, giving O(n) time and O(1) auxiliary space."
 
---
 
## 25. Final Takeaway
 
```
           VALID PALINDROME
                 ↓
   left = 0, right = length - 1
                 ↓
   Skip non-alphanumeric on both sides
                 ↓
   lowercase(s[left]) == lowercase(s[right])?
           ↓                    ↓
          yes                   no
           ↓                    ↓
   left++, right--         RETURN false
           ↓
   REPEAT UNTIL left >= right
           ↓
        RETURN true
```
 
Remember:
 
```
Skip non-alphanumeric characters as you go.
Compare characters case-insensitively.
No mismatch by the time pointers meet = palindrome.
 
Two pointers + on-the-fly filtering = O(n) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 125 — Valid Palindrome**.
 
