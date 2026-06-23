# Two Sum - Pattern & Intuition

## Problem

Given an array of integers `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}
```

---

# Pattern Used

## Hash Map Complement Lookup Pattern

The key idea is:

For every number `x`, determine what number is needed to reach the target.

```text
needed = target - x
```

Instead of searching the array again for the needed number, store previously seen numbers in a HashMap and perform an O(1) lookup.

---

# How to Derive the Solution

## Step 1: Brute Force

Compare every pair.

```java
for(int i = 0; i < n; i++) {
    for(int j = i + 1; j < n; j++) {
        if(nums[i] + nums[j] == target) {
            return new int[]{i, j};
        }
    }
}
```

### Complexity

- Time: O(n²)
- Space: O(1)

---

## Step 2: Identify Repeated Work

For every element:

```text
Current Number = nums[i]
```

You need:

```text
target - nums[i]
```

Example:

```text
target = 9

Current = 2

Need = 7
```

Searching for `7` repeatedly costs O(n).

Can we remember numbers already seen?

Answer: Yes, using a HashMap.

---

## Step 3: Convert Search into Lookup

Instead of:

```text
Search entire array for complement
```

Do:

```text
HashMap.containsKey(complement)
```

which is approximately O(1).

---

# Mental Model

For every number:

```text
Current Number = x
```

Calculate:

```text
Complement = target - x
```

Ask:

```text
Have I already seen this complement?
```

If YES:

```text
Answer found.
```

If NO:

```text
Store current number.
```

---

# Dry Run

Input:

```text
nums = [2,7,11,15]
target = 9
```

### Iteration 1

```text
Current = 2
Need = 7

Map = {}
```

Not found.

Store:

```text
Map = {2 -> 0}
```

---

### Iteration 2

```text
Current = 7
Need = 2

Map = {2 -> 0}
```

Found!

Return:

```text
[0,1]
```

---

# Pattern Recognition Checklist

When solving problems, ask:

## Need Fast Lookup?

Use:

```text
HashMap
HashSet
```

---

## Looking for a Pair?

Common clues:

```text
Find two numbers
Pair sum
Pair difference
Pair product
```

Think:

```text
Current Element + Complement
```

---

## Need Index Information?

Use:

```java
HashMap<Value, Index>
```

Example:

```java
Map<Integer, Integer>
```

---

## Need Only Existence Check?

Use:

```java
HashSet<Value>
```

---

# Similar Problems

## HashMap / HashSet Pattern

1. Two Sum
2. Contains Duplicate
3. Two Sum II
4. Subarray Sum Equals K
5. Longest Consecutive Sequence
6. Group Anagrams
7. First Unique Character
8. Happy Number

---

# Interview Heuristic

Whenever you see:

```text
Find pair
Find duplicate
Check existence
Count frequency
Need O(1) lookup
```

Immediately think:

```text
HashMap / HashSet
```

For Two Sum specifically:

```text
Current Number + Needed Complement = Target
```

and

```text
Store previous values
Check whether complement exists
```

This single idea solves a large family of array and hashing interview questions.

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Each element is processed once.

## Space Complexity

```text
O(n)
```

HashMap may store all elements in the worst case.

---

# Key Takeaway

Whenever a problem asks:

"Find a pair that satisfies a condition"

Think:

```text
What value do I need?
Can I store previously seen values?
Can a HashMap turn repeated searches into O(1) lookups?
```

This is the core intuition behind the Hash Map Complement Lookup Pattern.
