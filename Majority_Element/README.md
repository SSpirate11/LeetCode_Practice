# Majority Element - Pattern & Intuition

## Problem

Given an array `nums` of size `n`, return the majority element. The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
```

---

# Pattern Used

## Boyer-Moore Voting Algorithm

The key idea is:

If we cancel out each occurrence of an element with all the other elements that are different from it, then the majority element will eventually remain.

```text
Potential Candidate + Matching element = Increment Count
Potential Candidate + Mismatching element = Decrement Count
```

When the count reaches zero, we pick the current element as the new candidate.

---

# How to Derive the Solution

## Step 1: Brute Force (HashMap)

Use a frequency map to count occurrences of each number.

```java
Map<Integer, Integer> map = new HashMap<>();
for (int num : nums) {
    map.put(num, map.getOrDefault(num, 0) + 1);
    if (map.get(num) > nums.length / 2) {
        return num;
    }
}
```

### Complexity

- Time: O(n)
- Space: O(n) (Storing up to n/2 elements in the map)

---

## Step 2: Identify Repeated Work

The HashMap approach is fast, but it uses extra space. Is there a way to find the majority element without storing every unique number?

Since we know one element appears more than **half** the time, it will always "win" a survival-of-the-fittest battle against all other elements combined.

---

## Step 3: Implement Boyer-Moore

We maintain a `candidate` and a `count`.
- If `count` is 0, we set `candidate` to the current number.
- If the current number is the same as `candidate`, we increment `count`.
- Otherwise, we decrement `count`.

The majority element will be the last candidate standing.

---

# Mental Model

Imagine a group of people from different parties.
- If two people from **different** parties meet, they both leave (neutralizing each other).
- If two people from the **same** party meet, they stay together.
- Since the majority party has more than 50% of the people, they will always have at least one person remaining at the end, regardless of the order they meet others.

---

# Dry Run

Input:

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

### Iteration 1
```text
num = 2, count = 0
Candidate = 2, count = 1
```

### Iteration 2
```text
num = 2, Candidate = 2
count = 2
```

### Iteration 3
```text
num = 1, Candidate = 2
count = 1 (2 and 1 neutralized each other)
```

### Iteration 4
```text
num = 1, Candidate = 2
count = 0 (2 and 1 neutralized each other)
```

### Iteration 5
```text
num = 1, count = 0
Candidate = 1, count = 1
```

### Iteration 6
```text
num = 2, Candidate = 1
count = 0
```

### Iteration 7
```text
num = 2, count = 0
Candidate = 2, count = 1
```

Result:
```text
2
```

---

# Pattern Recognition Checklist

When solving problems, ask:

## Looking for a Frequency "Winner"?

Use:
```text
Boyer-Moore Voting Algorithm
```

---

## Guaranteed Majoriy? (> n/2)

Common clues:
```text
Majority element
Appears more than half the time
```

---

## Need O(1) Space?

Think:
```text
Can I neutralize elements to find the survivor?
```

---

# Similar Problems

## Majority Element / Voting Pattern

1. Majority Element (n/2)
2. Majority Element II (n/3)
3. Subarray Sum Equals K (Frequency variant)
4. Find the Peak Element (Elimination variant)

---

# Interview Heuristic

Whenever you see:

```text
Majority element
Element > n/2 frequency
O(1) space requirement
O(n) time requirement
```

Immediately think:

```text
Boyer-Moore Voting Algorithm
```

For Majority Element specifically:

```text
Current element == Candidate ? count++ : count--
count == 0 ? candidate = current
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

We traverse the array exactly once.

## Space Complexity

```text
O(1)
```

We only store the candidate and the count, regardless of the input size.

---

# Key Takeaway

Whenever a problem asks for an element that dominates more than half of the space:

"Don't count everyone, just find the survivor."

This insight allows us to move from an $O(n)$ space requirement (HashMap) to $O(1)$ space (Voting), which is a common optimization requested in senior engineering interviews.
