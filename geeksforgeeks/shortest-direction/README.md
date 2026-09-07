<h2><a href="https://www.geeksforgeeks.org/problems/shortest-direction4201/1">Shortest direction</a></h2><h3>Difficulty: Basic</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p data-start="111" data-end="195"><span style="font-size: 18.6667px;">Given a string <strong>s</strong> representing a sequence of moves starting from the origin (0, 0), where:</span></p>
<ul>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>N</strong>' represents a move one unit north.</span></li>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>S</strong>' represents a move one unit south.</span></li>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>E</strong>' represents a move one unit east.</span></li>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>W</strong>' represents a move one unit west.</span></li>
</ul>
<p data-start="111" data-end="195"><span style="font-size: 18.6667px;">Find the <strong>shortest</strong> possible sequence of moves that starts from (0, 0) and ends at the same final position as the original sequence.</span></p>
<p data-start="111" data-end="195"><span style="font-size: 18.6667px;">If there are multiple shortest sequences, return the <strong>lexicographically</strong> <strong>smallest</strong> one.</span></p>
<p data-start="111" data-end="195"><span style="font-size: 18.6667px;">The returned string should contain only the <strong>minimum</strong> number of moves required to reach the final destination.</span></p>
<p><span style="font-size: 14pt;"><strong>Examples:</strong></span></p>
<pre><span style="font-size: 14pt;"><strong>Input: s</strong> = "SSSNEEEW"
<strong>Output:</strong> EESS
<strong>Explanation</strong>: Following the path SSSNEEEW and EESS gets you at the same final point. There's no shorter path possible.
</span></pre>
<pre><span style="font-size: 14pt;"><strong>Input</strong>: s = "NESNWES"
<strong>Output:</strong> E
<strong>Explanation</strong>: Following the path NESNWES and E gets you at the same final point. There's no shorter path possible.
</span></pre>
<p><span style="font-size: 14pt;"><strong>Constraints:</strong><br>1 ≤ |s| ≤ 10<sup>5</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Flipkart</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Strings</code>&nbsp;