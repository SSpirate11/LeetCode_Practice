# Sort Colors - Pattern & Intuition

## Problem

Given an array `nums` containing only `0`, `1`, and `2`, sort the array in-place without using any built-in sorting algorithm.

```java
class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;
                mid++;
            }
            else if (nums[mid] == 1) {
                mid++;
            }
            else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}
```

---

# Pattern Used

## Dutch National Flag Algorithm (Three-Way Partitioning)

The key idea is:

Partition the array into three distinct regions using three pointers (`low`, `mid`, `high`).

```text
[0s Region][1s Region][Unknown/Unprocessed][2s Region]
```

By moving elements to their correct regions in a single pass, we achieve sorting without multiple traversals.

---

# How to Derive the Solution

## Step 1: Brute Force (Counting Sort)

Count the occurrences of each color and overwrite the array.

```java
int zero = 0, one = 0, two = 0;
for(int num : nums) {
    if(num == 0) zero++;
    else if(num == 1) one++;
    else two++;
}
int index = 0;
while(zero-- > 0) nums[index++] = 0;
while(one-- > 0) nums[index++] = 1;
while(two-- > 0) nums[index++] = 2;
```

### Complexity

- Time: O(n) (Two passes)
- Space: O(1)

---

## Step 2: Identify Repeated Work

The counting approach requires two passes through the array. Can we sort it as we encounter each element?

Yes, by maintaining "boundaries" for each value.

---

## Step 3: Convert to Three-Way Partitioning

Define three pointers:
- `low`: Where the next `0` should go.
- `mid`: The current element we are inspecting.
- `high`: Where the next `2` should go.

Elements between `low` and `mid-1` are all `1`s.

---

# Mental Model

At any point:

```text
Indices 0 to low-1       => All 0s
Indices low to mid-1     => All 1s
Indices mid to high      => Unprocessed
Indices high+1 to end    => All 2s
```

Ask:
- **Is it 0?** Swap with `low`. It belongs on the left.
- **Is it 1?** Do nothing, just move `mid`. It belongs in the middle.
- **Is it 2?** Swap with `high`. It belongs on the right. (Don't move `mid` yet, as the swapped element needs inspection!)

---

# Dry Run

Input:

```text
nums = [2, 0, 2, 1, 1, 0]
```

### Iteration 1
```text
low=0, mid=0, high=5
nums[mid] = 2
Swap with high.
nums = [0, 0, 2, 1, 1, 2], high=4
```

### Iteration 2
```text
low=0, mid=0, high=4
nums[mid] = 0
Swap with low.
nums = [0, 0, 2, 1, 1, 2], low=1, mid=1
```

### ... (Continue processing until mid > high)

Result:
```text
[0, 0, 1, 1, 2, 2]
```

---

# Pattern Recognition Checklist

When solving problems, ask:

## Only a Few Distinct Values?

Use:
```text
Dutch National Flag
Three-Way Partitioning
```

---

## Need In-Place Sorting?

Common clues:
```text
Space O(1)
Sort in a single pass
Partition around a pivot
```

---

## Three Regions Needed?

Use:
```text
Low, Mid, High pointers
```

---

# Similar Problems

## Partitioning / Two-Pointer Pattern

1. Sort Colors
2. Move Zeroes
3. Partition Array according to Pivot
4. Sort Array by Parity
5. Remove Element

---

# Interview Heuristic

Whenever you see:

```text
Sort 3 distinct values
Partition array into 3 parts
Single pass O(n) sorting
Constant extra space
```

Immediately think:

```text
Dutch National Flag Algorithm (3 Pointers)
```

For Sort Colors specifically:

```text
0s to the front
2s to the back
1s stay in the middle
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Each element is processed at most once.

## Space Complexity

```text
O(1)
```

Only three pointers are used regardless of input size.

---

# Key Takeaway

Whenever a problem asks:

"Partition or sort an array with a small fixed number of states"

Think:

```text
Can I use multiple pointers as boundaries?
Can I swap elements to their designated regions in one pass?
```

This is the core intuition behind the Dutch National Flag Algorithm.
