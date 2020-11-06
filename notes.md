### Array

#### Java knowledge

Java collection: https://www.liaoxuefeng.com/wiki/1252599548343744/1265109905179456

- List: ordered collection

  - ArrayList: similar as array

    - when add a new value in the middle, it moves all value on the right
    - when array is full, it creates a larger array and then copy all values in the old array to the new

  - LinkedList: every element points to the next element

    |                    | ArrayList               | LinkedList             |
    | ------------------ | ----------------------- | ---------------------- |
    | getElement         | fast(retrieve by index) | retrieve from the head |
    | add to the end     | fast                    | fast                   |
    | add/delete element | needs to move values    | do not to move values  |
    |                    |                         |                        |

  - List offers ```contains()``` to check if the list contains certain value, it uses ```equals()``` method not ```=``` to check if two values are same

- set: collection without duplicates

  - Set is used to remove redundant values, it uses ```equals()``` to compare two values

  - HashSet is unordered, TreeSet is ordered as it implements SortedSet interface

    ![1604090006411](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1604090006411.png)

- Map: key-value collection

  - One key can only have one value, if put key with multiple values, old values will be removed, only the last value is kept

  - Key cannot be same, but value can be same

  - Iterate map 

    ```java
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
    }
    
    for (String key : map.keySet()) 
    	Integer value = map.get(key);
    ```

  - Hashmap: use a big array to store all values, the index of value can be computed by hash key 

#### 001 Two Sum

Boundary case: num[i]+num[i]

Solution 1: brute force

Failed reason: when looping a list -> i=0, j=i+1, not start from 0

Time complexity: $O(n^2)$

Solution 2: 2-pass map

Reduce the lookup time from $O(n)$ to $O(1)$, by map

Map(key, value) -> key is set to left-overs, value is set to index

Solution 3: 1-pass map

注意 1)判断 和  put map的顺序 2) num[i]+num[i] = target

Failed case:

- [3,3] 6, expected [0, 1], give[0,0]
- [3,2,4] 6, expected [1,2], give[0,0]

#### 004 Median of two sorted array

Solution 1: separately loop two array

Time complexity: $O(log(min(m,n)))$ , the length of array to search is $min(m,n)$, log is applied because of binary search

#### 011 

Goal is to minimize $(j-i)*min(a_j, a_i)$

**Solution 1**: brute force

画一个矩阵，矩阵里存 $min(a_i, a_j)$.

Complexity: $O(n^2 )$

**Solution 2**: two pointers

怎么去减少矩阵里需要计算的量？

if($a_j$>$a_i$), 移动长边没有意义。因为$(j-i)$ gets smaller,  $min(a_j, a_i)$ might get smaller, but cannot get bigger

#### 015 three sum

**Solution 1**: brute force, use 3 loops to iterate over the array

Complexity: $O(n^3)$

**Solution 2**: two pointers

How can this relate with two sum? Fix one element, let the sum of other two equals to the complement.

Steps: 

- Sort the array
- Find sum of two other 
  - two pointers
  - map

Special cases: 

- remove duplicates e.g. [-1,0,1] [-1,0,1] because of multiple -1 in the array

  when moving pointers of sorted array, skip value same as the one before

  Cannot simply check if the array is already in the result! Time complexity of ```ArrayList.contains()``` is $O(n)$, by looping all elements in the array.  (```HashSet.contains``` use hash to check if an element is in the array, $O(1)$ time complexity)

- Input: [0,0,0,0], expected:[0,0,0], wrong:[0,0,0],[0,0,0]

  boundary case: >= or >

Knowledge: ```Arrays.sort()``` uses quick sort

#### 016 3sum closet

**Solution 1**: two pointers, use an addition variable to store diff, $O(n^2 ) $

**Solution 2**: binary search, fix two numbers, find the third one by binary search

思路错误: difference between 3sum closet and 3sum is 3sum closet find closet value to target, 我在第一遍solution里3 values sum set to max value, 应该是把diff(3sum-target)设成max

#### 026 remove duplicates from sorted array & 027

**Solution**: two pointers, one for sorted part, one for loop. 

思路错误：

- 我用了for循环里套while循环同一个变量,需要考虑很多boundary case, 让情况更复杂。如果两个循环的变量不同 可以考虑这么做，不然通过限制条件，少用一个循环

- dont need to switch element, can simply replace the sorted part and leave the right part 

#### 031 next permutation

Sequences in descending order cannot produce next larger permutation. So from right to left, search for elements on the right side for next larger number, swap two numbers, and reverse the sequence.

思路错误：

- I search for least greater number than the pivot on its right. If found, switch two numbers. switch有问题，e.g. [2, 3, 1]->[3,2,1],but expected [3,1,2]

  Not insert the element, but rather reverse the sequence

- I used two loops to check big/small relationship, which is not needed. 从右到左搜索，在index=i的右边，比x(i)大的值，不需要用loop。如果在i处没有找到，说明所有$j > i, x(j) < x(i)$。那么对于i-1, 只需要比较x(i-1)与x(i)
- How to reverse array? two variable set to the left and right point, swap values at two pointers, and move pointers

Corner case 错误：

- Input: [2, 3, 1], expect: [3,1, 2], output: [3, 2, 2]

  when add an element to an array, need to move the data after the inserted position, and loop from the right to left, otherwise the old data will lose and new array is same for all moved index

- when moving pointers, consider condition  >= or > (actually >=)

#### 033 Search in Rotated Sorted Array

Solution: use binary search，判断左右两边那边升序，判断升序的那一边是否包含target

Binary search基本框架

```java
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;

    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}
```

- Index of middle element of an array: ```left + (right-left)/2```, use ```(left + right)/2``` might lead to overflow
- Time complexity: $O(logn)$

corner case: 

- 判断条件(first if, or nested if)，是>=还是>

#### 034 Find First and Last Position of Element in Sorted Array

Solution: binary search

Binary search 参考文章 https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie-qing-an-shun-xu-yue-du/er-fen-cha-zhao-xiang-jie

注意：

- 开闭区间 [], [)    <- 取决于left, right定义
- 如何移动mid缩小范围：++, or left/right  <- 取决于开闭区间+题目
- return: left or left +-1? 

Corner case:

- [1,3,5,6] 2, expected: 1, output: 0

#### 039 combination sum



#### 041 First missing Positive

**Solution**:

- if 1 is in the array. If no, 1 is the answer

- Clean the array: negative numbers and numbers larger than the length is cannot be the answer. Replace those numbers with 1

- Find a first missing number

  - use a string: each bit of the string represents if the value of index is in the array. e.g. "11110000", 1 at index 1 means 1 is in the original list

    Then find the first 0 in the string

    Space complexity: $O(n)$

  - use the sign of the element in array: e.g. [3, -4, -1, -1], negative sign of -4 at index 1 meaning 1 is in the original list.

    Then find the index of first positive element

思路error:

- When changing the sign of element, use abstract value of the element, because 

#### 042 Trapping Rain Water

**Solution 1**: brute force

Amount of water an index can trap is the minimum of maximum height of bars on two sides.

For every index, use one loop for each side to find maximum height of bar

Time complexity: $ O(n^2) $

**Solution 2**: dynamic programming

Use dynamic programming, instead of loop, to find max height of bar

$ left_{max} = max(left_{max}, height[i]), right_{max} = max(right_{max}, height[i])$

Time complexity: $ O(n) $

**Solution 3**: stack



**Solution 4**: 2 pointers



思路错误：

- Two pointers

  - 当一轮结束，left pointer 需要设置到结束位置

  - Avoid local dilemma. 如果把array以longest bar来切割，只计算water between 最近的two longest bars, 会错过smaller longest bar还可以再装水的情况

    e.g. [4,2,0,3,2,5], expected: 9, output: 5