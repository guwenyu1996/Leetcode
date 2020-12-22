### Array

#### Java knowledge

Java collection: https://www.liaoxuefeng.com/wiki/1252599548343744/1265109905179456

![Hierarchy of Java Collection framework](https://static.javatpoint.com/images/java-collection-hierarchy.png)

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

**Solution 1**: brute force

Failed reason: when looping a list -> i=0, j=i+1, not start from 0

Time complexity: $O(n^2)$

**Solution 2**: 2-pass map

Reduce the lookup time from $O(n)$ to $O(1)$, by map

第一遍pass, 存map (number, index)

第二遍pass, 在map里面查找(target - 当前num), 并且index不相同 

Map(key, value) -> key is set to left-overs, value is set to index

**Solution 3**: 1-pass map

Loop over array, 先查找当前map里是否存在剩余值，再向map插入新的(number, index) pair。

注意 1)判断 和  put map的顺序 2) num[i]+num[i] = target

Failed case:

- [3,3] 6, expected [0, 1], give[0,0]
- [3,2,4] 6, expected [1,2], give[0,0]

#### 004 Median of two sorted array

Solution 1: separately loop two array

Time complexity: $O(log(min(m,n)))$ , the length of array to search is $min(m,n)$, log is applied because of binary search

#### 011 Contain with most water

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

Special cases: 

- remove duplicates e.g. [-1,0,1] [-1,0,1] because of multiple -1 in the array

  when moving pointers of sorted array, skip value same as the one before

  Cannot simply check if the array is already in the result! Time complexity of ```ArrayList.contains()``` is $O(n)$, by looping all elements in the array.  (```HashSet.contains``` use hash to check if an element is in the array, $O(1)$ time complexity)

- Input: [0,0,0,0], expected:[0,0,0], wrong:[0,0,0],[0,0,0]

  boundary case: >= or >

Knowledge: ```Arrays.sort()``` uses quick sort

Time complexity: $ O(n^2) $, space complexity: $O(n)$

**Solution 3**: hashset

这个解法利用001 two sum。固定数组的第一个数num[i], 希望找到剩下两个num[j] + num[k] = -num[i]

给数组排序。循环遍历数组，固定num[i]，在i的右边调用twosum函数。为了避免重复，如果num[i] = num[i-1], 则跳过当前值。

two sum函数里，用set存数组里遍历过的元素。遍历数组，固定num[j], 寻找target = - num[i] - num[j]。如果set里正好有，则找到一组triple. 移动j到下一个不等于当前num[j]的元素。

边界情况：

- input:[0,0,0,0], expect:[0,0,0], actual: [0,0,0] [0,0,0] 如何避免重复情况？

  if found a triple, find the next value that not equal to the current one.

Time complexity: $O(n^2)$, space complexity: $ O(n) $

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

$ left_{max} = max(left_{max}, height[i-1]), right_{max} = max(right_{max}, height[i+1])$

Time complexity: $ O(n) $

**Solution 3**: stack

参考 https://www.youtube.com/watch?v=cTSfu3j6G7I

低洼是在(递减)(递加)之中，用stack存储单调递减

一个值的left-max是stack的top, right-max是当element

**Solution 4**: 2 pointers

思路错误：

- Two pointers

  - 当一轮结束，left pointer 需要设置到结束位置

  - Avoid local dilemma. 如果把array以longest bar来切割，只计算water between 最近的two longest bars, 会错过smaller longest bar还可以再装水的情况

    e.g. [4,2,0,3,2,5], expected: 9, output: 5

- Dynamic programming
  
  - 注意循环条件，是$i-1$还是$i$, $ left_{max} = max(left_{max}, height[i-1]), right_{max} = max(right_{max}, height[i+1])$ 

#### 048 Rotate Image

**Solution 1**: transpose and then reverse

先转置矩阵（按照对角线反转），再左右翻转矩阵。

转置矩阵，即交换(i,j) 和 (j, i)。需要转置的范围是矩阵的下左三角和右上三角，也就是当row < col. 

左右翻转矩阵，即交换(i, j) 和 (i, len - j - 1)。需要翻转的范围是矩阵的左半和右半，mid = len / 2; 这里不需要(len + 1)/2。假如len = 5, mid = (5+1)/2 = 3, 实际mid是2.

Time complexity: $ O(n^2) $, space complexity: $ O(n) $

**Solution 2**: rotate four rectangles

![compute](https://leetcode.com/problems/rotate-image/Figures/48/48_rectangles.png)

把矩阵当做左上(p1,p1) 右下(p2,p2)为顶点的正方形嵌套。旋转矩形，就是按层次旋转矩形的圈。每次旋转四个点，直到该层旋转完。旋转下一层。

![坐标变换.png](https://pic.leetcode-cn.com/58c76a0a3b1fed08f9546aa0993c99f3d1fff08a63987c960a5ccf23f7c71678-image.png)

当p1 = p2时，循环截止。

代码错误：旋转点的坐标，前一个的行=后一个的列，后一个的行= len - 前一个的列。这道题的难点是表达旋转点的坐标。

#### 049 Group Anagrams

**Solution 0**: my solution

如果两个词为异位词，那么它们按照字母表排列后相同。

如何按字母表顺序sort string? String -> char[] -> Arrays.sort() -> new String

使用HashMap<String, int> 存排序后的字符串和数组下标，```List<List<String>>``` 异位词集合。

从头遍历数组，如果map中不存在该字符串的重拍，则说明这是一个新的异位词。

Time complexity: $O(n^2logn)$, space complexity: $ O(logn) $

**Solution 1**: improved solution 0

需要存储数组下标吗？我们只需要保持异位词在同一个集合中。

使用HashMap<String, List>  e.g. "aer" -> ["aer", "rae"]

最后用```new ArrayList<map.values()>``` 创建数组结果

**Solution 2**: categorize by count

如果两个词为异位词，那么它们的字符统计也相同(含有相同的字符相同次)。

我们可以用一个hashmap <String, List> 存异位词的count 以及集合, string是对异位词的count的hash

如何用String表示count? "aab" = "#2#1#0...."

Time complexity: $O(NK)$, N is the length of arr and K is the maximum length of a string

代码：如何将char ->int? int = char - 'a'

#### 054 Spiral Matrix

**Solution 1**: simulation

这种解法和人的直觉类似。从头开始，按照一个方向走，走到头/遇到之前走过的，调转方向。

所以我们需要一个boolean[] [] 数组，来存放这个数字是否已经被访问过。同时用两个int[]存储上下左右四个方向，即行列值变化。

对每个结点执行如下操作。如果该节点在这个方向的下一节点没有超出边界，并且之前也没有访问过，则继续按照这个方向前行。否则换一个新方向。

Time complexity: $O(n)$, space complexity: $ O(n) $

代码错误：

二维数组行列 array[row] [col]

![img](http://c.biancheng.net/uploads/allimg/181016/3-1Q016131301F7.jpg)

初始java数组 ```int[] array = new int[5]{1, 2, 3, 4, 5} // {1, 2, 3, 4, 5}```

**Solution 2**: layer by layer

我们把结果想成一个不断以左上顶点(r1, c1)和右下顶点(r2, c2)为顶点的正方形嵌套。一个正方形嵌套一个正方形，直至最后正方形为空。如何得到正方形呢？四个方向遍历。

思路错误：

- 当input不是一个正方形，最后会出现(r1, c1) (r2, c2)同行/同列的情况。如果按四个方向遍历的话，最终结果会加入两遍正方形(直线)。如何避免？

  如果两顶点 既不同行，也不同列，遍历左上方向。否则只遍历右下方向。

代码错误：

- 区分 for(), while.

   for循环的截止条件是<= / <?    如果用while 实现for循环，是<= / ?

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 056 Merge Intervals

**Solution 1**: sort

对原始intervals进行排序。排序过后，不用将每个数组与所有数组进行比较，而是将它和之前比他小的数组进行比较。排序后的一个性质：[0, 1], [0, 2]  a[0] <= b[0]

将interval头元素加入一个新链表。链表里存储之前已经比较过的数组。对interval数组进行遍历，将每个数组与之前比较过的最后一个进行比较。判断他们是否重合。如何判断重合，只需判断the current.start与prev.end的关系（注意，排序过的数组，prev.start <= current.start）。

代码错误：

- 如何对Array进行排序，并使用lambda比较器？

  ```Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));```

- 如何删除Array(int[])中指定下标的元素？创建一个list, list to array可以利用```ArrayList.toArray()```方法。如果转成2D Array, 需要指明array size. ```xxx.toArray(new int[5][])```

Time complexity: $ O(nlogn) $, space complexity: $ O(logn) $ sort itself takes $O(logn)$ space.

#### 062 Unique Paths

**Solution 1**: backtracking, time limit reaches

使用递归，如果当前位置不在最底层，可以往下一层递归。如果当前位置不在最右列，可以往右侧递归。

**Solution 2**: math

排列公式: $P(n, k) = \frac{k!}{n!}$

组合公式：$C(n, k) = \frac{n!}{(n-k)!k!}$

边界错误：使用int计算结果会导致溢出，需要用long类型存储

**Solution 3**: dynamic programming

定义状态：dp[i] [j]表示走到(i, j)一共有几种不同路线

初始状态： dp[0] [j] , dp[i] [0]都初始为1

状态转移方程：dp[i] [j] = dp[i-1] [j] + dp[i] [j-1], 走到该格子的所有路线 = 走到该格子上方的所有路线 + 走到该格子左边的所有路线

结果：return dp[m-1] [n-1]，最后一个格子

Time complexity: $O(m*n)$, space complexity: $ O(m*n) $

**Solution 4**: simplified dynamic programming

二维数组dp 可以简化为一维数组dp[row]

状态转移方程：dp[i] = dp[i] + dp[i-1]

Time complexity: $O(m*n)$, space complexity: $ O(n) $

#### 063: Unique Path II

Solution 1: dynamic programming

定义状态：dp[i] [j]

初始状态：需要初始化dp[0] [i], 当且仅当该格子没有障碍，并且左边格子也没有障碍时，dp[0] [1] = 1

状态转移方程：当该格子没有障碍时，dp[i] [j] = dp[i-1] [j] + dp[i] [j-1]，否则为0

结果：return dp[m-1] [n-1]

思路错误：初始化dp[0] [i]时，不是该格子没有障碍就为1。如果左面有障碍，是没有办法走到该格子的。

Time complexity: $ O(m*n) $, space complexity:$O(m*n) $

#### 075 Sort colors

**My solution**: quick sort

这道题需要用quick sort解决带重复数字的排序问题。

标兵为数组的第一个数，使用左右指针遍历数组，左右指针初始为数组的开头和末尾下标。右指针先动（非常重要），寻找第一个>标兵的数。左指针后动，寻找第一个<标兵的数，之后交换左右指针。

一直重复这个过程，直到左右指针相遇，交换数组的左下标和pivot (归位pivot)。

思路错误：

- Quick sort 框架里，为什么==需要右边的指针先走==？

  需要保证左右指针相遇时，值小于pivot

  右指针的移动条件是>pivot, 它碰到<pivot的值截止。

  左指针的移动条件是<pivot, 它碰到>pivot的值停止。

  如果左指针先动，移动后遇到右指针，此时的值>pivot (左指针的跳出条件)。

  如果右指针先动，移动后遇到左指针，此时的值<pivot (右指针的跳出条件)。

- 左右指针的初始值？

  左指针初始为数组下标，即标兵的下标。如果标兵+1，可能溢出。

- 循环的截止条件？

  左右指针不相遇，相遇后跳出循环，swap 标兵和任意指针。

- 标兵的移动条件？

  对于无重复数字的数组，右指针寻找<pivot的值，左指针寻找>pivot的值。

  如果是有重复数字的数组，右指针寻找<=pivot的值,左指针寻找>=pivot的值。

Time complexity: $O(nlogn)$, space complexity: $ O(1)$

**Solution 1**: count -two pass

先遍历一遍数组，用一个数组存0,1,2的个数分别有多少个。

第二遍遍历数组时，按照个数修改原数组。

Time complexity: $O(2n)$, space complexity: $O(3)$

**Solution 2**: one pass

我们希望将数组分为三部分，0 | 1 | 2

使用three pointers, 左指针初始为数组头，代表0部分的末尾，右指针初始为数组结尾，代表2部分的开始。

使用第三个指针curr遍历数组,。遇到0，将curr与left交换，移动curr和left指针；遇到2，将curr与right交换，并移动right指针。知道curr > right。

Time complexity: $ O(n) $, space complexity: $ O(1) $

#### 079 Word Search

**Solution**: backtracking

用backtracking方向，向格子的四个方向查找。

为了防止重复遍历相同的位置，需要维护一个与输入 等大的boolean数组。

简化写法：在递归函数开始判断位置是否合法，而不是在最后四个方向查找时再一个个判断。

#### 088 Merge Sorted Array

**My solution**: 

拷贝原数组，利用two pointers 对新拷贝数组 和 s2进行比较，在原数组进行修改。

Time complexity: $O(m+n)$, space complexity: $ O(m) $

==Java ```Arrays.copyOfRange()``` 或者```Arrays.copyOf()``` 是shallow copy. ```Object.clone()``` 对于primitive types 基本数据类型是深拷贝，对于non-primitive types是浅拷贝。==

**Solution 1**: merge and sort

合并两数组，利用排序算法得到sorted新数组。但这种解法没有利用两数组已经排序过的特性。

Time complexity: $O((n+m)log(n+m))$

**Solution 2**: two points from beginning

Make a copy of num1 array. Maintain two pointers p1 for num1copy, p2 for num2. Compare two arrays from beginning, and push the smaller element in the original array.

Time complexity: $O(m+n)$, space complexity: $ O(m) $

**Solution 3**: two pointers from end

从尾开始修改num1 array，避免影响num1开头的数. 利用two pointers, 一个指针指向num1的末尾，一个指针指向num2的末尾。再使用一个指针存储修改的index下标。从末尾循环比较两数组，知道其中一个到头。将num2的剩余数字拷贝到num1.

为什么只修改num2? 如果num1没有遍历完，则剩下num1的数字已经在num1存储好，不需要额外修改。如果num2没有遍历完，则只剩num2，需要将剩余数字拷贝。

Time complexity: $O(m+n)$, space complexity: $ O(1) $

#### 122 Best time to buy and sell stock II

**Solution 1**: peak valley approach

We can view the total profit as $\sum{ (peak - valley)}$. Then looping over the array can be view as the process to find a valley(continuous decrease) and peak (continuous increase). 

代码错误：找peak 和valley的过程中，注意是>= 还是> 算valley。比如corner case[3,3], 如果是>则陷入死循环

Time complexity: $O(n)$, space complexity: $ O(1) $

**Solution 2**: simplify solution 1

Instead of looking for peak and valley, we can add consecutive profit. e.g. [1, 2, 3], valley = 1, peak = 3. But we can calculate as 2-1 + 3-2 = 2. If the second number is larger than the first one, we add the difference to sum.

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 560 Subarray Sum Equals K

**Solution 1**: brute force

使用双重循环遍历数组，第一重循环表示子数组的开始，第二重循环表示子数组的结束。循环中求子数组sum, 并与k比较。

Time complexity: $O(n^2)$, space complexity: $ O(n^2) $

代码错误：

- 如果当前sum = k, 不能跳出循环。数组存在0的情况，要遍历到数组末尾。
- 如果当前sum > k, 也不能跳出循环。因为数组无序，后面可能出现<0的数。

**Solution 2**: hashmap

sum[i]表示num[0:i]的和。一个直观的想法是，如果sum[i] = sum[j], 则说明num[i:j]为0.

题目要寻找num[i:j]和为k, 也就是寻找sum[j] - sum[i] = k。

用map<int, int> 存 sum/frequency pair。 map的初始值为(0,1)，也就是开始之前 和为0的情况。 遍历数组，计算sum[i]. 如果map里存在sum[i] - k, 则找到一段和为k的子数组。给count叠加sum[i] - k的频率。然后map里更新当前sum[i] frequency的情况。

代码错误：map需要有初始值(0, 1). 否则当sum[i] = k, 这个情况没有被记入。

Time complexity: $O(n)$, space complexity: $O(n)$ 

#### 915 Partition array into disjoint intervals

**My solution**: dynamic programming

我们希望找到max(left) <= min(right)这样一个index

定义状态：leftMax[i] 表示从左边开始遍历字符串string[0, i] 左区间的最大值

rightMin[i]表示从尾部开始遍历字符串[i, end] 右区间的最小值

初始状态： leftMax[0] = array[0], rightMin[last] = array[last]

状态转移方程：leftMax[i] = max(leftMax[i-1], arr[i])

rightMin[i] = min(rightMin[i+1], arr[i])

结果：做头遍历 第一个i满足leftMax[i] <= rightMin[i+1]

Time complexity: $ O(n) $, space complexity: $ O(n) $

**Solution 1**: simply two arrays by using one array

可以只保留rightMin. 在最后一遍从左到右找到满足的i 时，用一个变量存储left_max.

#### 1438 Longest Contious Subarray With Absolute Diff Less Than or Equal to limit

​                     8,2,4,7 limit = 4

​                    ^

​                    ^

max          

min           

### Tree

#### 094/144/145 binary tree traversal

Tree traversal

- pre-order: root-left-right
- in-order: left-root-right
- post-order: left-right-root

Solution

- recursive

  - Time complexity: $T(n) = 2T(n/2) + O(1)$ -> $O(n) $

- iteration: use stack

  - pre-order: add current point to stack, current point always point to left node, 

    ​						when current pointer is null, pop an element, add point to right child

  - Time complexity: $O(n) $ 

```
void traverse(TreeNode root) {
    // 前序遍历 do something here
    traverse(root.left)
    // 中序遍历 do something here
    traverse(root.right)
    // 后序遍历 do something here
}
```



#### 096 Unique Binary Search Tree

Binary Search Tree:

- left subtree of a node contains nodes with keys less than the node
- right subtree of a node contains nodes with keys larger than the node
- left & right subtree is a binary search tree

Solution: dynamic programming

Binary search tree is consisted of: left subtree, root, right subtree

How many types of binary search tree: number of left subtree * number of right subtree

![1604678856554](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1604678856554.png)

Recursive formula:$G(n)$ denotes number of unique binary search tree when the number of nodes is n, $F(i,n)$ denotes number of unique BST when root is i

$G(n) = \sum_{i=1}^{n} F(i, n)$

$F(i, n) = G(i-1)* G(n-i)$ == left subtree * right subtree

$G(n) = \sum_{i=1}^{n} G(i-1)*G(n-i)$

Bottom cases: $G(0) = 1, G(1) = 1$, 没有node也是一种solution

$G(3) = F(1, 3) + F(2, 3) + F(3, 3) = G(0)*G(2)+G(1)*G(1)+G(2)*G(0)$

Time complexity: $O(n^2) $

Corner case: 注意递归循环里的i, n , j取值

#### 098  validate binary search tree

**Solution 1**: recursion

check if left subtree is smaller than upper bound, and right subtree larger than lower bound

Use INTEGER instead of int to store lower and upper limits

Time complexity: $O(n)$, space complexity: $ O(n) $

**Solution 2**: iteration

use stack to store current node, lower limit, upper limit

Time complexity: $O(n)$, space complexity: $ O(n) $

**Solution 3**: in-order traversal (left-root-right)

Check in-order traversal, if every element is smaller than the next element

Use stack to store left child. If left is null, retrieve stack top element, and move pointer to right children. 

思路错误：

- My solution: binary search, 检查parent node > left child && parent node < right child. 

  但问题是不是光检查child nodes, 还要保证parent node > all elements of subtree

  so need to keep both upper and lower limit

Corner case:

- Input [max_int]

  use double to store last element

#### 100 Same tree

**Solution 1**: recursion

Step: 

1. check current point is same
2. recursively check left subtree
3. recursively check right subtree

Time complexity: $ O(n) $, space complexity: this means the length of recursion stack, worst $ O(n) $, best $O(logn)$ when a balanced binary tree

**Solution 2**: iteration

Use deque (two direction queue) to store 遍历元素

==Queue/Deque 接口 不可以把null添加进队列中，否则当```poll()``` 方法返回null时，不知道是取到null 或者 队列为空==。Queue和deque都是接口，实现接口的类有LinkedList和ArrayDeque，Queue和deque的poll接口 如果没有元素 返回的是null

==Stack类可以存储null, stack.peek方法会throw exception 如果没有元素==

参考 https://www.liaoxuefeng.com/wiki/1252599548343744/1265121791832960

Time complexity: $ O(n) $, space complexity: this means the length of recursion stack, worst $ O(n) $, best $O(logn)$ when a balanced binary tree

#### 101 symmetric tree

Java中使用stack, 推荐使用```Dequq = new LinkedList```，不是是Stack类。因为Stack是JDK1.0的遗留类。

当我们把`Deque`作为`Stack`使用时，注意只调用`push()`/`pop()`/`peek()`方法，不要调用`addFirst()`/`removeFirst()`/`peekFirst()`方法，这样代码更加清晰。

**Solution 1**: recursion

Two trees are same: 1) if root values are same, 2) if right subtrees are mirror of left subtree

Time complexity: $ O(n) $, space complexity: $ O(n) $ 

**Solution 2**: iteration

Use two stacks to store left and right subtrees. If root values are same, add left node and right node of left subtree to stack, and right node and left node of right subtree in stack.

思路错误：

当left and right pointer都指向null时，应该是continue, 而不是直接return

#### 102 level order traversal

Solution 1: recursion

List<List<>>代表最终结果，元素里的第几个结点代表第几层。DFS遍历树，递归将左右结点加入列表。

思路误区：DFS也可以实现层次遍历。结果是List<List<>>, 只要把当前层的结点加入对应第i个元素

递归里null值的判断：如果最开始判断root是否为null, 然后递归起点 传递root 和degree。接着判断left right分别是否为null,不是递归left&right 和degree

Solution 2: iteration

最初解法：Use two queues to store treenodes and levels separately. Use another int variable to store current level. When current level is not equal to level pop from queue, it means traversal to a new level. 

==可以看成层次遍历+delimiter==. delimiter是在遍历该层前 the size of the queue. It suggests you how many elements should pop up from queue.

思路错误：

当iteration结束时，不能直接返回结果。因为最后一层的结果还没有加入最终结果。

Notice that Java object use reference copy. 

```
List<List<Integer>> list = new ArrayList<>();
List<Integer> temp = new ArrayList<>();
temp.add(1);
list.add(temp);
temp = new ArrayList<>();  // 有没有这行很重要
temp.add(2);
list.add(temp);
Output: [[1][2]] if new array // [1,2][1,2] if not new array
```

Improved:

One queue to store level is not needed. Can get it from the length of result list.

Use an int variable to store the size of queue. 每一个循环后的The size of queue就是下一层的node数量。循环遍历完该层的结点后，将该层结果加入result.

#### 103 zigzag level order traversal

**Solution 1**: DFS (depth first search) + recursive

和Q102相同的想法。结果是List<List<>>, 只要把当前层的结点加入对应第i个元素。

==递归思路：搞清当前root节点做什么，然后根据定义递归调用子节点。==

该结点：讲该层结点插入result列表，根据level的奇偶性，插入list head or tail.

然后递归结点的左右结点。

Time complexity: $ O(n) $, space complexity: $O(H) $, where H is the height of the tree

**Solution 2**: BFS (breadth first search)

Similar to Q102 iteration solution. BFS + Deque

Intuition: Use an int variable to store the current level. If current level is odd, append to the beginning. If current level is even, append to the last.

Time complexity: $O(n) $, space complexity: $O(n) $ which is the queue size. The queue maximum hold nodes at most across two levels. 

思路错误：

Different adding subtree order of odd level and even level. At odd level, first add left and then right subtree to the end of queue. At even level, first add right and then left subtree to the beginning of queue.

#### 104 maximum depth of binary tree

**Solution 1**: recursion

height = max(left subtree height, right subtree height)+1

Time complexity: $ O(n) $ for traverse all elements, Space complexity: depends on the balance state of tree, $ O(n) $ if completely unbalanced and $ O(logn) $ if balanced

**Solution 2**: iteration

BFS with queue, Similar to Q102 iteration solution

#### 105 construct binary tree from preorder and inorder traversal

![1604948523102](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1604948523102.png)

Pre-order: root-left-right

in-order:left-root-right



#### 226 invert binary tree

**Solution 1**: recursion

递归思路：to invert a node, first invert left child, then invert right child, finally switch two child

语法错误：```switch```在java中是关键词(switch..case), cannot use switch as function name

Time complexity: $O(n) $ as visit every node exactly once, space complexity: $O(h) $ where h is the height of tree

**Solution 2**: iteration (BFS)

The idea is to swap the left and right child of all nodes in the tree. BFS遍历tree, use a queue to store nodes whose children not swap yet. Pop up an element from queue and swap its children. When queue is empty, invertion is done.

这道题不需要delimiter. 每层的结点都是swap children, task没有区别。

#### 236 Lowest Common Ancestor of a Binary Tree

**Solution 1**: recursion

两个节点的最近公共祖先是什么呢？这里有两种可能：

- 左子树包括一个结点，右子树也包括一个结点
- 如果该结点就是其中一个结点，它的左右子树其中之一包括另一个结点

设计一个递归函数，函数的返回值boolean表示该结点及其孩子是否包括任一结点。如果满足上述之一情况，则找到答案。

感受：

- 递归函数的返回值不一定是所求答案，可以设置一个类变量存储答案，使用其他返回值方便递归。
- boolean -> int，需要判断true/false然后赋值，不能强制类型转换

Time complexity: $O(n)$, space complexity: $O(n)$

**Solution 2**: iterative with parent node

BFS遍历二叉树，用map存储结点和它的父节点。直到map里同时包含两个结点，说明两个结点已经被遍历到。

取其中一个结点，用set存储它所有的父节点。用另一个结点向根节点逆推，第一个在set里的结点就是公共父节点。

Time complexity: $O(n)$, space complexity: $O(n)$

#### 617 Merge two binary trees

**My solution**: recursion

利用递归

如果t1, t2都为空，则递归结束。取t1的val (为null则为默认)，和t2的val(null为默认)。相加得到新节点。

递归得到新节点的左孩子和右孩子结点。如果t1/t2为null, 则传null值。

Time complexity: $O(n)$, space complexity: $O(n)$

**Solution 1**: recursion

简化recursion, 如果t1/t2中有一个为null, 则直接返回剩下一个。

Time complexity: $O(n)$, space complexity: $O(n)$

**Solution 2**: iteration

### String

#### 003 longest string without repeating characters

Java length vs length()

array.length, string.length()

**Solution 1**: brute force

Check every substring if they does not carry repeating characters. Use a map to help check (map takes $O(1) $ time to search). 

思路错误：

注意循环变量的上下界，length+1或者length, <或者<=

```
for(int i = 0; i < s.length(); i ++){
	for(int j = i + 1; j <= s.length(); j ++){
```

第二个j, 是下限+1, 所以是<= 而不是<

corner case: Input: " ", expected: 1, output: 0

Time complexity: $O(n^3) $

**Solution 2**: sliding window

Intuition: It's not necessary to repeatedly check a substring if it has duplicate character. If a string $s_{ij}$ is already checked, we then only need to check next character.

Use a hashset as a sliding window. Use two points present the left and right boundary. If the letter at the right point already in the set, 1)move the left point + 1 2) remove letter from set. If not, add letter to set and update the max count. 

Sliding window实际上检测了从i开始 不含重复字符的最长字符串长度。

思路错误：

双指针问题注意上下界 if[i, j] 那么i <=j && j < string.length

Time complexity: $O(2n) $

**Solution 3**: optimized sliding window

Instead of move the left pointer one by one, we can move it directly to the place not contain repeated character. Use a map to store character and its index. 

Time complexity: $O(n)$, right pointer loop over the string

#### 005 Longest Palindromic Substring

Java中整数之间的除法，结果的小数位都被舍弃，只返回整数位数字

最长回文字符串

初始想法：将每个index视为回文字符串的中点，向两边延展，如果两边字符相同，则继续延展；如果字符不同则停止。

思路错误：回文字符串可能有奇数有偶数。以上只适用于奇数情况。

**Solution 1**: brute force

检查每个子串是否为回文字符串 $ O(n^2) $，检查过程从两侧到中间 检查两侧元素是否相同 $O(n) $

Time complexity: $O(n^3)  $

**Solution 2**: 中心扩散 

回文字符串可能是奇数是偶数，奇数中心是一个字符，偶数中心是两个字符。遍历字符串，把每个点当做中心，查看以该点为中心的奇数字符串长度和偶数字符串长度。

Time complexity: $ O (n^2) $ 

难点：数学推导：子字符串长度和字符串起点位置

偶数情况： 0, 1, 2, 3: start-len, start, end, end+len,len=2

奇数情况：0, 1, 2 : start-len, start(end), end+len, len=2

Length of substring: end-start+2(len-1)+1=end-start+2len-1  (Length)

Start point of substring:  (注意Java int除法只保留整数) start - ((length-1)/2)

-1/2这样可以通用奇偶数情况

**Solution 3**: dynamic programming

$ P(i, j) = S_i == Sj \text{ and } P(i-1, j-1)  $ 

state(s, e)

- for s = e, "a" is palindromic
- for s = e-1, "aa" is palindromic if str[s] == str[e]
- for s = e-2, "aba" is palindromic if str[s] == str[e] and "b" is palindromic
- for s = e-3, "abba" is palindromic if str[s] == str[e] and "bb" is palindromic

state transition equation

state(s, e) is true:

- for s=e
- for s=e-1, if str[s] == str[e]
- for s=e-2, if str[s] == str[e] and state[s+1, e-1] is true

思路错误：注意填表i, j 遍历的顺序

计算state[s,e]需要先知道state[s+1,e-1]，所以state[s+1,e-1]需要先计算。需要先计算列，e.g.e-1列，然后再计算e列

Time complexity: $O(n^2) $,  space complexity: $ O(n^2) $

#### 006 Zigzag conversion

初始想法：结果存入```List<List<Character>>```, iterate through the string from left to right, use mode to computer which row to put, index % (2*(row-1)), if result is smaller than rownumber, 下降序列，if larger than rownumber, 上升序列，row =row - result.

corner case: rownumber = 1, mode 模的数为0，需要单独if 判断

**Solution 1**: improved left to right pass

Use ```List<StringBuilder>``` rather than nested list. Use a row pointer, and move the point up or down instead of calculation. When the pointer at the beginning or end of array, change direction.

Corner case: if rownumber = 1, just return the string

Tricks: 1. use stringBuilder to concat, append string/char/chararray/stringbuilder

2. The size of list, is the min(rownumber, string.length())

Time complexity: $ O(n) $, space complexity: $ O(n) $

#### 012 Integer to Roman

**Solution 1**: greedy

Use two arrays to store symbols and their represented values in order, from largest to smallest. Look for largest symbol that can fit into it, and then subtract that value, then look for largest symbol can represent the left. 

Time complexity: $O(1) $, cause loop over the finite array.

#### 013 Roman to Integer

**Solution 1**: from left to right pass

Store Roman-int values the into a mapping so that it is easy to look up. When a smaller symbol is before a larger symbol, need to subtract the smaller one, otherwise just add the symbol.

Time complexity: $ O(n) $

**Solution 2**: from left to right improved

Not only store single value mapping, but also store two digits value mapping. First check if two symbols mapping existed, then check if one symbol mapping existed.

Time complexity: $O(n) $

#### 014 Longest common prefix

代码错误：==使用string.charAt方法之前检查string.length, 不然会抛出异常==

#### 020 valid parentheses

**Solution 1**: stack

Store parenthese to a hashmap to search for a pair easily. If encounter a opening bracket, push it to stack. If encounter a closing bracket, check the element on top of stack. If they are a match, correct. Else it is invalid. In the end, if there is left items in stack, invalid.

思路错误：如果检测到右边括号，而且stack为空，这时也是invalid.

==使用```stack.pop```方法之前需要先检查stack是否为空，不然会抛出异常。==

Time complexity: $O(n) $, space complexity: $ O(n) $

#### 022 Generate Parentheses

**Solution 1**: brute force

生成所有序列，并检查每一个生成字符串是否有效。

如何生成所有长度为2n的序列？使用递归，长度为n的序列就是长度为n-1的序列后加(或者)

如何检查字符串是否有效？1)一个合法的字符串，从左到右，右括号的数量不能大于左括号，2)并且最终左括号的数量等于右括号。

思路错误：判断字符串合法需要满足两个条件，如果只满足1，则最终结果左右数量不平均。

代码优化：在递归的时候，需要每一轮都把结果存到```List<String>```里吗？不需要，用一个char[]类型表示最终字符串，一位一位填即可。

Time complexity: $O(2^{2n} n)$, space complexity: $ O(n) $

**Solution 2**: backtracking

优化solution1, 我们不需要生成所有排列，而是对每一个合法的长度为2n-2的序列，增加一个括号。

需要维持两个遍历，统计当前左 右括号的数量。

递归条件：

- 左括号 < n, 可以递归增加左括号
- 右括号<左括号，可以增加右括号

**Solution 3**: dynamic programming

定义状态：dp[i]代表 用i个()组成的全部解

初始状态： dp[0] = "", dp[1]="()"

状态转移方程：已经dp[0], dp[1], ... dp[n-1], 求dp[n]

把每一个解都看成[a]b, a是dp[p], b代表dp[q], p+q = n-1

dp[n] = (dp[p]) dp[q]

结果：dp[n]

#### 067 Add binary

**Solution 1**: bit by bit computation

Time complexity: $O(max(M,N))$, space complexity: $ O(max(M,N)) $

#### 076 Minimum Window Substring

==滑动窗口思路==

1. 使用双指针，维护一个左闭右开区间[left, right), left = right = 0
2. 不断扩大右指针，直至窗口中的数据满足要求
3. 停止right，增加left指针缩小窗口，直到窗口中的数据不符合要求。
4. 重复2,3，直到right 到达尽头。

right指针相当于在寻找可行解，left指针在优化这个可行解。

需要考虑的问题

1. 当移动 `right` 扩大窗口，即加入字符时，应该更新哪些数据？
2. 什么条件下，窗口应该暂停扩大，开始移动 `left` 缩小窗口？
3. 当移动 `left` 缩小窗口，即移出字符时，应该更新哪些数据？
4. 我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？

**Solution 1**: sliding windows

思路错误：

- 右指针的增加条件 不是找到一个在字符就停止，而是窗口里的值需要包含所有字符

- 左右指针代表的是什么？注意左闭右开，还是左闭右闭

- 如何判断窗口中的数据满足要求？

  用一个hashmap(char, int)存元素出现的次数，初始化map为每个元素在给定字符串中的次数。当map所有 int <= 0, 则说明窗口数据符合要求。

  为什么不是等于0? 有一个窗口出现多个元素

边界情况：字符串中可能包含重复字符，Input:"a" "aa", expected: ""

代码错误：如何遍历map?

```
        for(Map.Entry<Character, Integer> entry: count.entrySet()){
            //entry.getValue()
            //entry.getKey()
        }
```

代码优化：可以不用三个int, 两个存放start end index, 一个存length. 可以优化成2个，end index可以用过start+length计算。

Time complexity: $O(S+T)$, space complexity: $ O(S+T) $

#### 415 Add strings

整体思路和 002 Add two nums / 067 Add binary 类似，一位一位相加。

Char to int: ```Character.getNumericValue(char)``` 或者  ```char - '0'```

char array to int: ```Integer.parseInt(new String(charArray)) ```

从末尾到头遍历两个string, 直到两个全部遍历完。把x赋值为s1的当前index, 如果s1已经到头，那么x为0；同理把y赋值为s2的当前index. 相加两个index, 注意加法进位情况。

遍历结束后，注意最后一位是否需要进位。

Time complexity: $ O(max(M,N)) $, space complexity: $ O(max(M,N))$

代码错误：

char无法赋值为''。char的初始值为"\u0000"，which is a Unicode value denoting ‘null‘ or 0 in decimal. 

不要用char[]来一位一位存string了，直接用==StringBuilder==

#### 459 Repeated Substring Pattern

**My solution**: 

使用一个不断右开的窗口，用窗口匹配窗口右边的字符串。

如果匹配失败，则说明重复字符串寻找失败，把窗口+1。重新匹配右边的字符串。

如果全部字符串匹配成功，并且字符串最后一位匹配窗口最后一位，说明找到重复字符串。

思路错误：

- 遍历字符串的指针，初始值为窗口右边界+1，查找失败时需要回归，也为窗口右边界+1
- 查找成功的条件为，重复字符串一直匹配成功，直到最后一位。

边界情况： input string = "a"，长度为1，直接返回false

Time complexity: > $ O(n) $ 

**Solution 1**: Concatenation

将输入字符串加倍，使用双倍字符串

```PatternPattern``` ->  ```PatternPatternPatternPattern```

```Pattern1Pattern2``` ->  ```Pattern1Pattern2Pattern1Pattern2```

去掉开头和结尾的字符：

```PatternPattern``` ->  ```*atternPatternPatternPatter*```

```Pattern1Pattern2``` ->  ```*attern1Pattern2Pattern1Pattern*```

如果新字符串还含有输入字符串，说明是重复字符串。

Time complexity: $O(n^2)$, space complexity: $ O(n) $



#### 567 Permutation in String

**Solution 1**: sort

如果一个字符串包含了另一个字符串的全排列，那么他们含有相同次数个同样的字符。如果对s1, s2进行排序，s2包括s1, 则说明它包括另一个字符的全排列。

### Heap

#### 215 Kth largest element in the array

Heap (堆) 是一种完全二叉树(除了最下层全满，最下层叶子结点全部靠左)，如果每个结点的值都大于或等于左右孩子结点的值，叫大顶堆。如果每个结点都小于或等于孩子结点的值，叫小顶堆。

堆使用数组存储，使用层次遍历存储堆中元素

**插入元素**：将元素插入至数组末尾，从下到上调整元素顺序，(比较$i$ 和$i/2$ (父节点))，如果没有元素交换，即插入结束

**删除堆中元素**：堆只能删除堆顶元素。将堆顶元素和末尾元素对调，重新对堆(n-1)进行排序。将节点$i$与左右孩子结点($2*i, 2*i+1$)比较，如果结点小于孩子结点则交换。如果没有发生元素交换，排序结束。

**堆排序**：包括两个步骤，建堆，和堆排序。

建堆从无序数组创建堆的过程，从第$n/2$个元素到第$1$ 个元素进行堆化，这些元素都有叶子结点。堆化过程将父结点与左右孩子结点比较，如果小于孩子结点则交换。

堆排序指从堆顶取出最大元素，然后重构堆的过程。这个过程类似删除堆中元素。

数据结构角度理解堆：https://2020.iosdevlog.com/2020/04/08/heap/

Java中利用Priority Queue使用堆：https://blog.csdn.net/u013309870/article/details/71189189

**Solution 1**: heap

Time complexity: adding an element to a heap(size k) is of $O(log k)$, the whole procedure is done n times so time complexity is $O(N*logk)$

Use a heap with smallest element first. Keep the size of heap equal or smaller than k. 最后剩下的元素就是k largest elements.

**Solution 2**: quick sort 快速排序变异

快速排序：

Divide:  将数组划分成以x为标志，x以左的数小于x, x右边的数大于x

![1605541398280](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1605541398280.png)

​			实现：数组的第一个元素设为pivot, 使用两个指针一左一右，左右指针的初始值为数组的开始和结束。右边的指针寻找比x大的元素，左边的指针寻找比x小的元素，交换两指针。当左指针等于右指针，遍历结束，交换pivot和指针。

==需要右边的哨兵先走，这样最后相遇的元素一定小于pivot.==  为什么？右边的指针先向左移动，左右相交，这样相遇的值一定小于哨兵

Conquer: recursively solve two subarrays

本题寻找第k大元素，如果pivot position < k，没有必要对左边元素排序；如果pivot position > k, 没有必要对右边元素进行遍历。

#### 347 Top K Frequent Elements

**Solution 1**: heap

遍历第一遍数组，用一个map<int, int>存储数字及它的频率。

遍历第二遍数组，建立一个大小为k的堆。

Time complexity: $ O(Nlogk) $, space complexity: $ O(N+K) $

### Dynamic Programming

动态规划格式：

定义状态：数组表达的是什么

初始状态： base state情况

状态转移方程：如何用上一个状态得到下一个状态

结果：

#### 53 Maximum subarray

我的解法：遍历数组，如果当前值>0, 或者num[i]>num[i+1]。这种解法的问题是拿不到最优解 [4, -1, 3]

**Solution 1**: greedy

遍历array, keep three variables, current value, current local max, and max value. If curr > curr local max, update curr local. If max of two (curr, curr value) > max value, update max value.

Time complexity: $O(n)$, space complexity: $ O(1) $

思路错误：curr local max = curr / local max + curr, 而不是local max (不然数组不连续)

**Solution 2**: dynamic programming

f(i) 表示以第i个数结尾的 连续子数组的最大和

f(i) = max(nums[i], f(i-1) + nums[i]) , 注意不是max(f(i-1), f(i-1)+nums[i])！是以第i个数为结尾，包括第i个数，否则子数组不连续

result = max(f(i))

#### 064 Minimum Path Sum

Solution 1: dynamic programming 2D

定义状态：dp[i] [j] 表示从(0, 0) 到(i, j)的最少path和

初始状态： dp[0] [i], dp[i] [0]都是当前行相加

状态转移方程：dp[i, j] = arr[i,j] + min(dp[i-1, j], dp[i, j-1])

结果：dp[row-1] [col-1]

Time complexity: $O(mn)$, space complexity: $ O(mn) $

Solution 2: dynamic programming 1D

#### 070 Climing stairs

**Solution 1**: dynamic programming

To reach i-th step there are two ways, 1) take one step from (i-1)th  2) take a step of 2 from (i-2)th

formula: $dp[i] = dp[i-1] + dp[i-2]$

Time complexity: $O(n)$, space complexity: $O(n)$

**Solution 2**: fibonacci number

Simplify solution 1 by keep only two numbers. We don't need entire array, but only last two numbers.

Use another int to store the sum of two. And then update two numbers.

Time complexity: $O(n)$, space complexity: $O(1)$

#### 091 Decode ways

**Solution 1**: 我的思路 dynamic programming

dp[i]表示 substring从0到第i位 解读方式的个数。如果新的一位i 可以被两位数解读，那么substring的末两位要在10和26之间。如果新的一位i可以当做一位数被解读，那么substring[i]一定不能为0

思路错误：

- corner case: 当字符串以'0'开头，一定是无法decode
- 不是所有dp[i]都能被当做新的一位解读

#### 121 Best time to buy and sell stock

Keep two variables, minprice and maxprofit. Loop over the array, check if current number smaller than minprice, or the diff between curr and minprice is larger than maxprofit.

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 139 Word break

**Solution 1**: brute force (recursion)

递归思路：考虑字符串s 是否使用worddict进行拆分

递归关系：假如s从头可以用worddict里的w1拆分，判断剩下的s是否可以用worddict拆分

e.g. s="leetcode", dict=["leet", "code"] 如果leet可以表达s'leet'这部分，剩下的s'code'也能被字典里的数表达吗

边界条件：当s只剩空字符串

Time complexity: $O(2^n)$ (at each position, we can either split or not split it), space complexity: $ O(n) $

**Solution 2**: recursion with memory optimization

可以提升的地方：sol1方法类似backtracking, 需要每次都重算结果，可以把结果存在哪里吗？

使用一个Boolean类型的数组，arr[i]代表string.substring(i) 从i 开始的substring是否能被字典里的词表示

Time complexity: $O(n^3) $ , 头尾两遍遍历字符串$O(n^2)$  , within each iteration we use substring $O(n)$

**Solution 3**: BFS

backtracking的思想是DFS思想，可以用BFS实现这道题。

用queue存储string的index, 表示字符串剩余表达的部分。初始化queue, 将0加入队列。

从队列取出头元素，作为搜索区域的开始下标，用新int变量作为搜索区域的结束下标，遍历字符串。如果这个搜索区域可以被dict表达，将这个搜索区域的结束下标加入队列，作为下一次搜索的开始。

使用一个boolean数组，arr[i]表示是否已经搜索过该节点，避免重复搜索。已经搜索过的结点，结果已经存在queue中。固定搜索区间的开始下标，遍历搜索区间的结束下标。当遍历结束，即开始下标的所有可能性已经遍历过，标志开始下标为visited.

Time complexity: $ O(n^3) $, space complexity: $  O(n) $

**Solution 4**: dynamic programming

这个想法特殊的是第一重循环dp[i] 从0到数组结尾，第二重循环 找前i个 是不是能分成 0 ..j..i, j..i在字典里

string[0, i] 可以被拆分成两部分s1和s2. s1 = string[0, j] 以及 s2 =[j, i]。 如果s1已知可以被拆分，并且s2在字典里，那么string[o, i]可以被表达

定义状态：boolean dp[i] 表示 string[0, i] 是否可以被表达

初始状态：dp[0] = true

状态转移：if dp[j] = true and string[j, i] in dict, dp[i] = true

结果：判断dp[s.length()+1]是否为true

Time complexity: $ O(n^3) $, space complexity: $ O(n) $

==关于遍历字符串时的截止条件，下标<= 或者 <==

注意string.substring(i,j) 截取了string[i, j) 部分，即前开半闭区间。如果遍历区间的开始位置i, 需要满足i < s.length()。如果遍历区间的结束位置j, 则需要满足j <= s.length()

#### 152 Maximum Product Subarray

**Solution 1**: brute force

遍历每一个子数组，算出子数组的乘积。

Time complexity: $ O(n^2) $, space complexity: $O(1) $

**Solution 2**: dynamic programming

参考053 maximum subarray

如果当前数是正数，正*正=正，max[i] = max[i-1] * nums[i]

如果当前数是负数，负*负=正，max[i] = min[i-1] * nums[i]

定义状态：max[i] 表示包括第i个数的最大子数组之积, min[i]表示包括第i个数的最小子数组之积

初始状态：max[0] = nums[0], min[0] = nums[0]

状态转移：max[i] = max{nums[i], max[i-1] * nums[i], min[i-1] * nums[i]} (不论当前值为正负数)

   min[i] = min{nums[i], max[i-1] * nums[i], min[i-1] * nums[i]}

结果：max {max[i]}

Time complexity: $ O(n) $, space complexity: $O(n)$

**Solution 3**: dynamic programming simplified

dp没有用到完整数组，而是数组前一个值

我们可以简化两个数组为两个int值

思路错误：

- 每一轮dp都会修改max, min的值。假如直接修改max, 那么min会用最新的max, 而不是前一个数的max，返回结果错误。

Time complexity: $O(n)$, space complexity: $O(1) $

#### 221 Maximal Square

**Solution 1**: dynamic programming

定义状态：dp[i] [j] 从 (0,0) 到 (i,j) 每个点的最大正方形数

初始状态：dp[0] [i] = matrix[0] [i], dp[i] [0] = matrix[i] [0]

状态转移方程：if dp[i] [j] =1, dp[i] [j] = min(dp[i-1] [j], dp[j-1] [i], dp[i] [j]) + 1

结果：max dp[i] [j]

Time complexity: $ O(m*n) $, space complexity: $O(m*n)$

Solution 2: better dynamic programming



#### 238 Product of Array Except Self

**Solution 1**: dynamic programming

数组上的每个数，都可以看做这个数左边的乘积 * 右边的乘积

![img](https://leetcode.com/problems/product-of-array-except-self/Figures/238/diag-1.png)

定义状态：left[i] 表示在index=i 左边数的乘积，right[i] 表示在index=i右边数的乘积

初始状态： left[0] = 1, right[len-1] = 1

状态转移方程：left[i] = left[i-1] * nums[i-1], right[i] = right[i+1] * right[i]

结果：ans[i] = left[i] * right[i]

一共遍历数组3遍，前两遍分别从左和右计算出left[i] 和right[i], 最后一遍把left*right得到结果

Time complexity: $O(n)$, space complexity: $O(n) $

**Solution 2**: simplified dynamic programming

我们需要维持两个int[]数组吗？可以简化为$O(1)$

为了得到左下三角的乘积，我们可以直接修改结果数组 ans[i] = nums[i-1] * ans[i-1]

为了得到右下三角的乘积，我们可以使用一个int变量。不断更新temp来得到ans[i] = ans[i] * temp

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 322 Coin change

**My solution**: dynamic programming (bottom up approach)

定义状态：dp[i] 表示当amount = i 时，最少能用几枚硬币凑出。如果无法凑出，那么值为-1.

Base state: dp[0] = 0

状态转移：dp[i] = dp[j] + 1 (if 1: j-i in coin list and dp[j] >= 0)

Time complexity: $O(S*n)$, where s denotes the number of coin types, space complexity: $ O(n) $

Solution 1: improved my solution

可以改进的地方：初始dp[i]不为0，而是max_int。这样的话不用对每一个无解状态都修改为-1, 而是在最后返回值判断是不是max_int 从而得知有没有解.

#### 1143 Longest common subsequence

最长公共子串

**Solution 1**: dynamic programming
$$
\begin{equation}
state[i][j] = \begin{cases}
state[i-1][j-1]+1, & \text{if } s1[i] == s2[j]; \\
max(state[i][j-1], state[i-1][j]), & \text{if } s1[i] != s2[j]
\end{cases}
\end{equation}
$$
思路错误：$state[i,j] = max{state[i-1][j], state[i][j-1], state[i-1][j-1] + \text{if} (s1[i] == s2[j])}$

不需要从3个里找max, 如果s1[i] == s2[j], 肯定比剩余两个大

优化：我的思路：创建$[m][n]$ 大小的矩阵，index at 0 代表第一个字母 初始矩阵 $s[0][n]$ , $s[m][0]$ ，然后从index = 1之后填表。

可以创建$[m+1][n+1]$ 大小的矩阵，index at 0代表没有字母的情况，从index at 1, 也就是第一个字母开始循环填表

Time complexity: $O(M*N)$, space complexity: $ O(M*N) $

**Solution 2**: dynamic programming with optimized space

In recursion formula, we only need current column and previous column. Therefore can save space by keeping track of last two columns instead of entire 2D array.

Time complexity: $O(M*N)$, space complexity: $ O(min(M,N)) $

### LinkedList

#### 002 Add two numbers

我的初始解法：将list值转为int, 相加两个int, 讲result转为string后，一位一位生成链表。有一个问题是int list可能是[1,100]位长度，远超过int/long的最大值。计算全部结果后再生成列表的想法不可行。

**Solution**: math

Sum two lists digits by digits. Use an int variable to store the overflow situation. 

我的想法：loop through two lists when either one reaches the end. 第一遍循环：遍历两个list, 第二遍循环：遍历剩余l1, 第三遍循环：遍历剩余l2. 代码重复地方很多。

可以优化成: loop through two lists when both lists reach the end. If one list reaches the end, set the digit of that list to 0. 

思路错误：

- 当l1 + l2 >= 10，而不是>10，需要进位
- Corner case: [9,9], [9,9,9,9], 当遍历完链表，最后一位需要有进位

#### 019 Remove Nth node from end of list

我的解法：recurssion

This problem wants to remove the Nth element from the end of list. My idea is to use recursion to loop through the end of list, and use a variable to keep the length till end. 

Time complexity: $O(n)$, space complexity: $O(n)$

**Solution 1**: two pass 

In the first pass, find the length of list. Then in the second pass, traverse the list until the element (len - n), then skip the removed element and set the next pointer to next.next.

思路错误：Use a prehead pointer for boundary cases, e.g. remove the head of list. If want to remove the head of list, you want to take the node at -1, point the next of the node at -1  to next.next. However -1 is impossible. So use a prehead pointer. 

Time complexity: $O(n)$, space complexity: $ O(1) $

**Solution 2**: two pointers

Use two pointers, one start earlier with n steps. Then move two pointers together until the fast one reaches the end. At this time, the slow pointer moves to the place where the next is the element to be removed.

Time complexity: $O(n)$, space complexity: $O(1) $

#### 021 Merge Two Sorted List

**Solution 1**: iteration

Use ==a prehead pointer== to store head info. Use a curr pointer to pointing to current node. Compare $l1$ and $l2$, connect l1 to curr if $l1 $ is smaller or equal than $l2$. Repeat this until at least one of $l1$ and $l2$ is null. Then connect the remaining list.

思维陷阱：如何判断开头从$l1$开始或者$l2$开始，不需要复杂的if, 设置一个头指针prehead, 随便指向一个值。最后结果返回prehead.next

代码错误：循环的截止条件，是two pointers are not null, not either pointer to null

Time complexity: $O(m+n)$, space complexity: $O(1) $

**Solution 2**: recursion

Recursion formula:

$new list = list1[0] + mergeList(list1[1:], list2), \text{if } list1[0] < list2[0]$

$new list = list2[0] + mergeList(list1, list2[1:])$

Time complexity: $O(m+n)$, space complexity: $O(m+n) $

#### 083 Delete duplicates from sorted list

**Solution**: iteration

Check if a node is duplicate by comparing its with the node after it. It it is duplicate, we can set the next pointer to next.next(skip the next one).

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 086 Partition list

**Solution**: two pointer

Reform the list into two parts, one with elements less than x, the other one with elements larger than x. Initialize two parts with prehead pointer. Traverse the list. If the node value is smaller than x, connect to the smaller list, otherwise connect to larger list.

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 092 Reverse Linked List II

我的解法：iteration

参考 206 reverse linked list iteration 解法，保留四个指针的值

prestart       start     end      end.next

先遍历列表至prestart(start前一个)，reverse start->...-> end这一段，然后设置preStart.next = end, start.next = end.next

思路错误：修改链表时一定不能有环，如果有环没办法判断结尾。如反转链表

1 --> 2 --> 3   -->  4       如果想修改为2 <--3

​          prev     curr    next

2 <--> 3 , 直接修改curr.next = prev是不对的，这样造成列表有环。需要先把2.next = null

**Solution 1**: iteration

#### 138 Copy List with Random Pointer

**Solution 1**: recursion

使用一个map<Node, Node> 存储 old/new node pair. 递归遍历原始链表，如果当前值已经存储在map中，说明新引用已经被创建过。否则重新copy node, 并更新map. 递归遍历当前节点的next, random结点。

代码错误：由于node多了一个random字符项，递归遍历random会导致死循环（无数次遍历链表）。如果map中已经存在当前节点，就不需要更新next, random了。他们已经被更新过。

Time complexity: $O(n)$, space complexity: $ O(n) $

**Solution 2**: iteration with O(n) space

使用一个map<Node, Node> 存储 old/new node pair. 循环遍历链表。循环开始前，在map中加入head pair. 在每次循环中，复制/更新当前节点的next, random值。循环的结束条件是当原始链表通过next遍历到结尾。

Time complexity: $O(n)$, space complexity: $ O(n) $

#### 141 Linked list cycle

题意：判断给定链表里是否有环，pos只作为内部变量我们不能拿到

**Solution 1**: hashset

Use a hashset to record each node reference. If the node is in set, means it have been visited, and there is a cycle.

思路错误：Hashset should store ListNode, not Integer(node.val), because nodes with same value could be different node.

Time complexity: $O(n) $, space complexity: $ O(n) $

**Solution 2**: two pointers

Iterate list by ==two pointers== at different speed. If there is a cycle in list, two pointers will eventually meet. Slow pointer move one stage each round, fast pointer jump two hoops each round.

思路错误：

- 什么时候没有环？不是either slow or fast is null, 如果fast is not null, it is not possible for slow to be null
- slow, fast的初始值不能都设为head, then slow== fast, break the loop. fast should be faster than slow and set to the next of head.

Time complexity: $O(n)$, space complexity: $O(1) $

#### 160 Intersection of two linked lists

**Solution 1**: hashset

Traverse list A and store each node in a hashset. Check every node in list B if it is in the set.

Time complexity: $O(m+n)$, space complexity: $ O(m) $

**Solution 2**: two pointers 链表拼接

思维误区：Length of two lists are different. Traverse A and Traverse B cannot visit the common place in the same time.

http://www.soolco.com/post/64357_1_1.html

When pointer A goes to the end of List A, redirect to the start of B.

When pointer B goes to the end of List B, redirect to the start of A.

情况一：If two lists intersects, pointer A meets pointer B.

![img](http://www.soolco.com/group1/M00/08/E1/rBAADF81QCKAQAssAAHKgD54ifw781.png)

pointer A 走过的：a+c+b; pointer B走过的：b+c+a。最多遍历两遍list

情况二：两链表不相交。每个指针走a+b布后变成null。

循环的停止条件为pointer A != pointer B，要么情况一 两者相遇，要么情况二 两者不相交 都为null

Time complexity: $ O(m+n) $, space complexity: $ O(1) $

**Solution 3**: 消除链表长度差

如果两个链表长度相同，有公共结点，那么分别从头一个一个traverse, 两指针必定会相交。

Time complexity: $O(m)$, space complexity: $ O(1) $

#### 203 Remove linked list elements

To delete a node in the middle is easy, just set the next pointer of current node to the next next node.

To delete a node in the beginning, use a prehead node to store the info.

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 206 Reverse linked list

**Solution 1**: iteration

Keep track of three pointers: prev, curr, next

null   1  -> 2  -> null

prev  cu    next

null<- 1         2  -> null

​           prev     cu    next

What happens if we use two pointers?

null   1  -> 2  -> null

​         cu       next   (lose info)

Change next.next to curr. But we lose the actual next of next pointer.

Change the current node's next pointer to its prev. Next 为了保留下一个头结点。

代码错误：next指针不能在change之后就改成next.next, 以防next为null

Time complexity: $O(n)$, space complexity: $ O(1) $

**Solution 2**: recursion

Assume node $n_{k+1}$ to $n_m$ have been reversed:

$n_1 $ -> $n_2 $ ... $n_k $ -> $n_{k+1} $ <- ... $n_m$

To reverse $n_k$ and $n_{k+1}$, set $n_k$ to curr, curr.next.next = curr(指向自己), curr.next to null

```
public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode p = reverseList(head.next);   // 返回头指针
        head.next.next = head; // reverse
        head.next = null;      // reverse  
        return p;                              // 返回头指针
    }
```



Time complexity: $O(n)$, space complexity: $O(n)$ due to recursion stack

#### 234 Palindrome linked list

我的想法：用stack, 但stack没有array方便

**Solution 1**: convert list to array + two pointers

Copy the linked list to an array. Check if the array is a palindrome. To check the array, use two pointers, one at the start and one at the end. At each step check if values are equal and move pointers inward.

代码错误：

```==``` checks for reference, 如果想要判断Integer的value not reference, 需要用```equals()```

```
Integer a = 10;
Integer b = 10;

System.out.println(a == b); //prints true

Integer c = new Integer(10);
Integer d = new Integer(10);

System.out.println(c == d); //prints false
```

Time complexity: $O(n)$, space complexity: $O(n)$

**Solution 2**: recursion

直觉：如何能用在链表里用两个指针，一个头一个尾，前后比较？想办法用递归，递归到最后一个，同时保留头指针

Recursion formula:

Check list is palindrome, needs to 1) check list.next is palindrome, 2) if front value equals to end value. 如何能保留头指针？设置一个类变量

Time complexity: $O(n)$, space complexity: $ O(n) $

**Solution 3**: reverse the second-half in-place

Steps: 1) find the end of first half 2) reverse the second half 3) check if there is palindrome 4) restore the list

Step1: to find the end of first half, one way is to count how many nodes in the list, and then reverse the list to get first half. Second way is to use ==two runners pointer== technique. Have 2 runners one fast one slow, the fast one moves 2 nodes, the slow one moves 1 node. By the time the fast runner reaches the end, the slow one moves to middle.

Step3: one pointer at beginning, one pointer at the first half, traverse first half and second half of lists

代码错误：

Step2: reverse the list 

循环什么时候截止？curr != null or curr.next != null? 如果是后者，最后一个element没有反转

prev, curr如何赋值？prev不应该设为head, 因为prev实际为反转列表后的最后一项，实则是null. curr应该设为head

最后return 的是curr 还是prev? curr跳出循环时为null, 应该return prev

#### 237 Delete a node in a linked list

我的解法：

因为我们拿不到要删除结点的上一节点，我们可以把要删除结点之后的值向前挪一位，删除最后一个结点

**Solution**:

将待删除结点的值设为下一个结点的值，将待删除结点的next设为下下个结点

#### 445 Add Two Numbers II

**Solution 1**: reverse the list

问题可以被拆分成：1) reverse link list (least significant digits first)  2) add two nums

Time complexity: $O(N1+N2)$, space complexity: $ O(1) $

**Solution 2**: without reverse the list



**Solution 3**: stack

把链表加入栈，利用栈先进后出的特点来反转列表。从栈顶取元素，一位一位相加。

### DFS

#### 200 Number of islands

这道题实际在求有多少个连通的1区块。

**Solution 1**: dfs

深度遍历图，每遍历到一个1位置时，将所有与之连通的1也遍历一遍。将已经访问过的结点值更改为2，来区分访问/未访问过的结点。循环遍历整个二维数组，对每个值进行一次上面的操作。

Time complexity: $ O(M*N) $, space complexity: $O(M*N)$ by worst case

**Solution 2**: bfs

广度遍历图，每遍历到一个1位置时，将其视为广度遍历的根节点。将已经访问过的结点值更改为2，来区分访问/未访问过的结点。在广度优先搜索时，使用stack, stack里存储每个点的index(row * N + columns)。这样可以从index再得到row, column

### Design

#### 146 LRU Cache

这道题要求 下面这三个操作用$ O(1) $时间完成。

- get the key/ check if exists
- put a key
- delete the first add key

Solution: hashmap + double linkedlist

为什么需要双头链表？如果需要在尾部插入元素，在头部删除元素，需要知道结点的前驱后继，方便修改head, tail指针。

为什么需要hashmap? check / get操作需要$ O(1) $ 的复杂度，单有double linkedlist 从头到尾遍历需要 $ O(n) $

Steps:

1. 设计链表结点的数据结构

   ```
   class Node{
   	int key, value;
   	Node prev, next;
   }
   ```

2. 设计链表的数据结构

   使用双向链表，尾端存储最新的数据，头端存储最久的数据。

   ```
   class DoubleList{
   	Node head, tail;
   	int size;
   	
   	// addTail(Node node);
   	// removeNode(Node node);
   	// removeHead();
   }
   ```

3. 设计cache的数据结构

   ```
   class Cache{
   	DoubleList list;
   	int capacity;
   	// int get(int key);
   	// int put(int key, int value);
   }
   ```

   思路错误：

   1. 在cache搜索元素，不能光返回value, 还需要将该节点的顺序调整为末尾。如何调整顺序？先删除该结点，然后将结点插入末尾。
   2. 在cache中插入元素。如果链表中已经有同个key了，需要替换同个key的value值。

   代码错误：

   1. 用this指代类变量
   2. 区别key/value

Time complexity: $O(1)$, space complexity: $ O(n) $

#### 155 Min Stack

要求push, pop, top, getMin都在$ O(1) $ 时间完成。

**My solution**: stack + linkedlist $O(n) $

使用stack维护支持top, pop操作，用linkedlist实现对数字的排序。

但是插入linkedlist的时间复杂度是$O(n) $

**Solution 1**: stack of minimum pairs

使用一个stack<int[]> ，存储当前数字和当前的最小值。栈里的最小值，要不是栈顶的最小值，要不是新加入的元素。

代码错误：Java 初始数组```new int[2]{x, x}``` 语法错误。

- 静态初始化，显示指定每个元素的初始值```int[] intArr = new int[]{1,2,3,4,5,9};``` 
- 动态初始化，指定数组长度，由系统初始化默认值```int[] arr = new int[4];```

**Solution 2**: two stacks

有些时候最小值是重复的，我们可以优化这部分吗？

使用两个stack < int>，一个用来更新最小值，一个去更新当前数值。对于push操作，如果新加入数字<=最小值栈顶，则更新最小值栈。否则只加入数字。

![Diagram of using 2 stacks.](https://leetcode.com/problems/min-stack/Figures/155/two_stacks.png)

边界条件：新加入=最小栈栈顶， 也需要更新一遍最小栈栈顶。也就是说即便是相同的最小值需要重复存储，不然无法判断是哪个最小值弹出。

**Solution 3**: improved two stacks

Sol 2需要重复存储相同的最小值，我们可以优化这个部分吗？

使用一个stack< int> 更新当前数值，一个stack<int[]>更新< 最小值，它重复的次数>



### Greedy

#### 055 Jump game

**Solution 1**: backtracking, time limit exceeds

画出决策树，从第一个节点出发，走遍所有可能的情况。

思路错误：

1. 从当前格子i能够走到的格子范围是什么？

   左边界: i + 1，而不是i

   右边界: i + maxStep和格子长度的最小值(nums.length - 1而不是nums.length，这里用的是数组下标)，按照最大步数走有可能走过边界。

   同时，从最右试到最左，比左到右效率要高。

Time complexity: $O(2^n)$ (there are $2^n$ ways of jumping from the first position to last), space complexity: $O(n) $

**Solution 2**: backtracking + memory

在sol1里，我们会重复回溯到某个结点，造成不必要的时间浪费。

可以用一个boolean[]数组，存放该节点是否已经检查过。检查过的结点就不需要再重复回溯了。

Time complexity: $O(n^2)$ (for every element, we check next element j to its right, j can be max n elements), space complexity: $O(n)$

**Solution 3**: dynamic programming bottom up

和sol2类似，但是用一个enum[] 类型数组，更新该节点情况。

enum: good, bad, unknown. 一个位置是good, 说明从它可以走到结尾。

Time complexity: $O(n^2)$, space complexity: $ O(n) $

**Solution 4**: dynamic programming bottom down

定义状态：enum[] 表示从该结点是否可以走到末尾

初始状态： enum[last] = GOOD

状态转移方程：从尾向头遍历数组。当前处于结点i, 如果从结点i可以走到good结点，则i结点也为good.

结果：enum[0] good or bad?

Time complexity: $O(n^2)$, space complexity: $ O(n) $

**Solution 5**: greedy 

依次遍历数组中每一个位置，并且用一个变量存储 最远可以到达的位置。如果位置>=数组中最后一个位置，说明尾结点可达。

从头到尾遍历数组：

用一个变量存储 最远可以到达的位置。依次遍历数组中每一个位置，如果当前位置 <= 最远可以到达的位置，说明当前位置可达，更新最远可以到达的位置。如果位置>=数组中最后一个位置，说明尾结点可达。

从尾到头遍历数组：

用一个变量i存储 从i可以到达数组最后一个位置。i初始为最后位置。从尾到头 依次遍历数组中元素，如果当前位置可以到达i，则更新i为当前位置。如果i最后为0, 说明尾结点可达。

思路错误：

- 遍历数组中每一个位置 还是遍历到倒数第二个位置？每一个位置
- 如何判断尾结点可达？是位置>=数组中最后一个位置，而不是==.

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 252 Meeting Rooms

**Solution 1**: brute force

比较每两个meeting, 如果他们有重叠，则返回false. 

==如何判断区间重叠？==

Method1: 早开始的meeting要比晚开始的meeting早结束 -> start[0] < start[1] && end[0] <=start[1]，没有overlap

- 如果第一个早开始 -> 第一个结束 < 第二个开始
- 如果第二个早开始 -> 第二个开始 < 第一个结束

Method2: 判断min(end)>max(start)

![1608211274345](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1608211274345.png)



**Solution 2**: sort

区间调度问题：

- 按照左端升序：判断前一个右端>后一个左端？
- 按照右端升序：判断前一个的右端 < 后一个左端？

Time complexity: $O(nlogn)$, space complexity: $O(1)$

#### 253 Meeting Room II

**Solution 1**: sort + priority queue

将数组按照开始时间排序。用一个priority queue存放会议室的结束时间。

遍历数组，如果queue中结束最早的元素 < 当前会议的开始时间，说明该会议室可以承办当前会议。不然需要新开一个会议室。

最后queue的大小就是需要会议室的个数。

代码错误：

- 为什么不按右端排序？左端排序决定会议开始时间，当会议开始时需要判断如何安排会议室

- 为什么需要priority queue? 不能像252一样直接判断数组之间的开始结束关系吗？

  不可以，因为这道题存在重复利用会议室的可能。扎气球的题目，扎一针所有相交的区间都被扎破，但是这道题可以用一针hold 多个数组。

Time complexity: $O(nlogn)$, space complexity: $ O(n) $

**Solution 2**: 扫描线 sweep line

两个int数组分别存会议的开始和结束时间。对数组排序。

用两个指针分别指向数组，指针表示当前会议室最早的结束时间。开始遍历数组，如果开始时间<结束时间，需要多安排一个会议室。否则结束时间指针右移。

Time complexity: $O(nlogn)$, space complexity: $O(n)$

#### 435 Non-overlapping intervals

区间调度问题

给你很多[start, end]的闭区间，请算出这些区间里最多有几个互不相交的闭区间。

e.g. intervals = [1, 3], [2, 4], [3,6] ，这些区间里最多有两个区间互不相交。即[1, 3], [3, 6]

贪心解法：

1. 将数组排序，按每个区间的end升序排序
2. 从interval区间选一个区间x, 这个区间是当前区间里结束最早的
3. 把所有与x相交的区间从集合中删除
4. 重复2,3, 直到intervals为空。之前选出的x就是最大不相交子集

优化解法：

1. 将数组排序，按每个区间的end升序排序
2. 从interval区间选一个区间x, 这个区间是当前区间里结束最早的
3. 寻找下一个与x不相交的区间，更新x
4. 重复2,3 知道数组结束

- 利用排序完数组按end升序排列的性质。为什么按end?

  如果end越小，它的补集越大，也就是留给其它数组的位置最大。

- 如何判断两区间相交？[start1, end1], [start2, end2] (已经排序过，所以end1 <= end2)，

  只要start2 < end1 (是<= 或者< 具体看题目情况)，则两数组相交。

- 需要对每一个x, 都遍历一遍所有数组吗？

  假设有已经排序过的数组A, B, C。会有AC相交，但是BC不相交的情况吗？

  AC相交 -> c. start < a.end, 但是a.end <= b.end, 所以c.start < b.end，BC也相交

  所以不需要把每一个x, 都与所有区间进行核对。如果发现一个新的区间，不与x相交，则更新x

这道题求最少去掉几个元素，可以使剩下区间不相交。也就是求最大不相交区间的补集个数。

**Solution 1**: greedy

代码错误：

- 如何用Lambda写compartor 

  ```Arrays.sort(intervals, (int[] a, int[] b) -> Integer.compare(a[1], b[1]));``` 不要用```a[1] - b[1]``` 可能会有int溢出的情况

- 如果将x设置为array的第一个，需要先判断数组是否为空

Time complexity: $O(nlogn)$, space complexity: $ O(nlogn) $

**Solution 2**: dynamic programming

按照start point对数组进行排序。

定义状态：dp[i] 表示从0到第i个数组，最大不重合的区间数。

初始状态：dp[0] = 1

状态转移方程：if j <i && i,j 不相交，dp[i] = max{dp[j]} + 1

否则dp[i] = 1

结果：dp[intervals.length-1]得到最大不重合的区间数，用intervals.length - dp[intervals.length-1]得到需要去掉的区间数。

Time complexity: $O(n^2)$, space complexity: $O(n)$

代码简化：不用给所有dp[i]都初始为1。设一个max=0, 如果区间i与所有区间j相交，那么dp[i] = max+1= 1

#### 452 Minimum Number of Arrows to Burst Ballons

这道题求，最少有几个相交 interval。第一箭，扎掉尽可能多气球。之后一箭，再扎掉尽可能多气球。

**Solution 1**: greedy

如果按**左**端升序，可能会出现[0,9], [0,6], [7,8], 即前面包后面的情况

需要维护一个右边界最小值。如果区间.start > 右边界最小值，则新区间不与之前的重合。更新count。如果重合，需要更新右边界最小值。

如果按**右**端升序，不可能出现前面包后面的情况，只需要比较新区间start 和 上一个区间.end。

区间 A     -------

区间 B          --------

#### 621 Task Scheduler

**Solution 1**: greedy

final time = execution time + idle time, execution time是任务的个数，idle time是任务之间的间隔

统计每个任务的个数，得到map <char, int>, 只对int部分进行排序。

假设frequency=[5,2,1], n = 3. idle time最大就是 n*(max_fre -1)，也就是频次最高的任务之间间隔n个。

遍历数组，我们希望用剩余的任务填充这部分idle time。取第二高的任务频率j，填充至idle time. 最多填充min(max_fre-1, j)。注意如果j>max_fre, 说明剩余的执行时间会安排在当前idle time之外，但不需要额外idle time。因为j < max_fre. 遍历数组，填充idle time. idle time最小为0，不能为负。

Time complexity: $O(n)$, space complexity: $ O(n) $

Solution 2: 

#### 763 Partition Labels

**My solution**: 

map <char, int >存储每个字符在字符串中的频率。遍历字符串，得到map.

第二遍遍历字符串，用set 存储当前字符串中出现的字符。对每一个字符，更新map 中的频率-1, 并加入set. 

如果set中每一个元素对应的frequency为0，则说明这些元素只出现在当前子字符串中。找到这样一个区间，重置set和length. 

代码：iterate a set

```
Set<String> set = new HashSet<String>();
for (String s : set)
    System.out.println(s);
```

Time complexity: $O(n)$, space complexity: $ O(1) $

可以优化的地方：我们需要知道频率吗？我们只需要知道当前字符最后一次出现的位置

**Solution 1**: greedy

int[26]数组存储每个字符在字符串中最后一次的位置。第一遍遍历字符串，得到数组。

第二遍遍历字符串。int max = Math.max(max, 当前字符最后一次出现的位置)。如果max = 遍历下标i, 则找到这样一个区间。

Time complexity: $O(n)$, space complexity: $O(1) $

### Stack

#### 394 Decode String

**My solution**: use stack

用一个stack < String >存字符串中的关键字(数字&子字符串)，不存储括号[]。

遍历字符串的过程来更新stack. 

- 遇到数字/字符：判断之前的字符是数字还是字符，如果类型相同，则append；类型不同，则需要把之前的字符加入stack
- 遇到左括号 [: 把之前的字符加入stack
- 遇到右括号]: 从stack中pop element, 直到拿到数字

遍历结束后，将剩余stringbuilder的内容压入栈。最后不断从栈顶pop element, 如果遇到数字，则copy当前的字符串n次。直到栈为空。

但是这种解法会有代码重复：遇到右括号和遍历结束都需要不断pop数字。

代码错误：

- 如何copy 字符串n次？如果for循环，str +=str, 会copy字符串2^n次

**Solution 1**: use one stack

用一个stack < char> 存字符串中的数字、字符、以及左括号。

遍历字符串更新stack

- 遇到右括号：pop 栈内元素，直到遇到左括号。pop左括号。pop 数字 得到k值。将字符串复制k遍之后入栈。
- 遇到左括号/数字/字符：当前元素压入栈

遍历结束后，栈内不包括任何[左括号。从栈内取出所有元素即为所求。

**Solution 2**: use two stacks

用一个countStack< int>存数字，一个stringStack< String>存字符; 分别用一个StringBuilder numBuilder和stringBuilder存遇到的数字和字符

遍历字符串

- 遇到数字， append numBuilder
- 遇到字符，append stringBuilder
- 遇到左括号，push number & string to stack，清空当前numBuilder和stringBuilder
- 遇到右括号,  str = last + k*stringBuilder (last是字符串栈pop(), k 是数字栈pop())，更新stringBuilder为str    

遍历结束后，stringBuilder即为所求

思路错误：如果遇到右括号，需要更新stringBuilder为str。如果右边遇到左括号时，还会把stringBuilder内容压入栈

#### 496 Next Greater Element I

**Solution 1**: brute force

双重遍历两个数组，对于每一个s1数组里的元素，遍历一遍s2, 找出它在s2中的位置，以及是否在该位置右边存在一个比它大的数。

Time complexity: $O(m*n)$, space complexity: $ O(m) $

**Solution 2**: brute force + hashmap

还是双重遍历两个数组的思想。但是可以提升线性遍历s2的效率。用一个hashmap 存s2 (num, index)。这样对于每一个s1数组里的元素，我们不用从头遍历s2, 而是在map里拿到它在s2的index, 从当前index之后遍历。

Time complexity: $O(m*n)$, space complexity: $ O(n) $

**Solution 3**: monotonous stack 单调栈

单调栈模板

- 从**左到右**维护单调递减栈， 找元素右侧区域，第一个比自己大的位置

  使用==单调递减栈==，从栈底到栈顶，单调递减，表示从左开始还没找到下一个比它大的数字。用一个hashmap存返回结果 (num, next great num)。

  从头遍历s2。

  - 栈顶元素<当前元素，右边数字比当前元素大，弹出栈顶元素，更新map。直到栈顶元素>当前元素。将当前元素加入栈。
  - 栈顶元素>当前元素。将当前元素加入栈。寻找下一个可能。

  遍历结束后，栈可能还有剩余元素。这些是没有找到next great num的元素。将他们取出，并标记结果。

- 当前项向右找第一个比自己大的位置 —— 从**右向左**维护一个单调递减栈

  使用==单调递减栈==，从栈底到栈顶，单调递减，表示在该数右边比它大的数。

  从尾遍历s2。

  - 栈顶>当前，右边数字比当前元素大。比当前元素大的下一个元素是栈顶元素。并把当前元素入栈。
  - 栈顶<当前，右边数字比当前数字小。弹出所有比当前数字小的栈顶元素。如果栈为空，说明不存在当前元素大的下一个元素。

这道题使用从左到右+hashmap的方法。

Time complexity: $O(m+n)$, space complexity: $ O(m+n) $

#### 503 Next Greater Element II

**Solution 1**: stack with double length array

这道题和496的区别是 数组有环。数组最后一个元素的下一个元素是数组的开头。我们可以将数组的长度翻倍，这样能保证 对于每一个元素，都遍历[i,...last,...i] 这样n遍。

如何将数组长度翻倍？不需要构建新数组，可以循环两遍数组，用取模的方式得到数组下标。

Time complexity: $ O(n) $, space complexity: $ O(n) $

#### 739 Daily Temperatures

**Solution 0**: brute force

双重遍历数组，对于数字里每一个元素，遍历它右边的部分，寻找下一个比它大的元素。

Time complexity: $O(n^2)$, space complexity: $ O(n) $

**Solution 1**: monotonous stack

使用单调递减栈，从右向左遍历数组。

- 栈顶>当前，说明在右侧存在比当前数字大的元素，且该元素就是栈顶。将元素入栈。
- 栈顶<=当前，弹出栈顶。不断重复比较，直到栈顶>当前。将元素入栈。

思路错误：

- 循环遍历栈，并将栈顶元素与当前元素比较，这个过程的遍历条件是？

  栈不为空，并且栈顶元素<当前元素。使用```stack.pop()/peek()```前需要检查栈是否为空

- temperature中有可能有重复数字

  不能从左到右遍历+hashmap这种方法。否则，如果存在相同元素，map只能存储一个值。

- 注意比较关系，栈顶与当前元素是<= 还是 < 弹出？题中寻找warmer, 所以是<=

代码简化：

- 栈里不用同时存储下标和温度，只需要存储下标，通过下标可以找到当天温度。

Time complexity: $O(n)$ (一共有n个元素，最多pop n次), space complexity: $ O(n) $

### Sliding window

#### 239 Sliding Window Maximum

**Solution 1**: deque + monotonous stack 单调栈

使用双头队列deque, 队列头部存最大值，队列尾部存最小值。

当窗口右移，我们将新数与队尾比较，如果队尾<新数，移除队尾 加入新数。

如果deque左端元素不在窗口内，移除deque头部。

为什么使用deque? deque是双头队列，允许在队列头尾进行查找删除。题目要求deque中的元素是窗口元素的子集。如果窗口右移，左边的元素移除窗口，我们需要在deque中也把它删除。

Time complexity: $ O(n) $

**Solution 2**: dynamic programming

![split](https://leetcode.com/problems/sliding-window-maximum/Figures/239/solution.png)

将数组分割成k个一组的n组，最后一组元素可能不满k个。

定义状态：left[i] 表示从当前元素所在block的左端到i的最大值。

right[i]表示从当前元素所在block的右端到i的最大值。

初始状态： 

状态转移方程：left[i] = Max(left[i-1], nums[i]) (非blocking第一个元素)

right[i] = Max(right[i+1], nums[i]) (非blocking最后一个元素)

结果：max(right[i], left[i + k - 1])

### Math

#### 069 Sqrt

**Solution 1**: binary search

设置left = 0, right = x. 每次比较middle和x的大小。

如何判断返回left还是right? 循环条件是left <= right, 跳出条件是right<left。这道题求int floor, 需要返回right. 如果求floor ceiling, 需要返回left. 

代码错误：

- Java 里``` int i = 3/2```, i = 1

- 如何处理Java里溢出情况？ ```long num = i * i(int)``` 这样可以处理i为int_max的情况吗？

  ==Java中的类型转换==分为两种：

  - 自动(隐式)类型转换：表数范围小的类型可以自动转为为表数范围大的类型
    - byte->short->int->long->float->double
    - char->int->long->float->double
    - 写法：```int a = 6; long b = a;```
  - 强制(显式)类型转换：表数范围大的类型需要强制转化为表述小的类型，也叫缩小转换
    - 写法：```(targetType)value```
    - 强制转换可能会出现数据丢失, ```double a = 3.14; int b = (int)a // 3```

  表达式类型的自动提升:

  当一个算数表达式包含多个基本类型的值时，整个算术表达式的数据类型将发生自动提升, 自动提升到表达式中最高等级操作数同样的类型

  ```double a = 3/2; // 1.0``` , int类型操作数与int类型操作数一起操作，返回值还是整数。需要对一个操作数进行类型转换

思路错误：

- binary search的基本框架, 假设循环条件为left <=right, 不需要在while循环里处理left == right的情况。你可以在循环外处理。

边界错误：

- Input: 2147395599，计算平方时需要使用long，而不是int。

Time complexity: $ O(logn) $, space complexity: $ O(1) $

### Backtracking

#### 039 combination sum

Solution: backtracking

画出这道题的树形图

思路错误：

- 为了防止加入重复组合([2,2,3], [3, 2, 2])，加入的数字必须>=之前加入的数。比如加入3， 就不能再加入2了。需要用一个变量存开始可用的数字下标。
- when to throw array impossible numbers?  对这个子树的所有解都尝试过之后，从代码的角度来说就是，当递归语句执行结束之后，需要清除刚刚加入的元素。否则最后结果会包含所有尝试过的路径。
- 找到一组解，需要clear掉结果吗？不需要。因为没有从head开始遍历solution tree, 递归栈里并不为空。不能clear掉结果。
- java 返回值为void的函数，可以直接```return; ```

代码错误：

- 因为List存储的是地址，不能直接把List<>加入result；否则后续如果修改list, result里面的数组也会被修改。需要加入new (list).

Time complexity: $ O(N^{T/M + 1}) $, where N be the number of candidates, T be the target value, M is the minimal value among candidates, space complexity: $ O(T/M) $, the depth of tree

#### 040 Combination Sum II

和39的区别：candidates里每个数只能用一遍，并且结果不能重复

思路错误：

- candidates里包括重复数字，最终结果重复

  如何避免这个情况？假如有重复数字，需要跳过第二次对该数字的遍历。第一次照常执行。

#### 046 Permutations

使用list存当前的permutation, set存已经使用过的数字。已经使用过的数字不能再次使用。

#### 078 Subsets

**Solution 1**: dynamic programming

nums [1, 2, 3]

| nums      | subsets                                             |
| --------- | --------------------------------------------------- |
| []        | []                                                  |
|           | [], [] (复制一遍当前结果)                           |
| [1]       | [], [1]                                             |
|           | [], [1],  **\|**  [], [1]                           |
| [1,2]     | [], [1],   [2], [1,2]                               |
|           | [], [1],   [2], [1,2]  **\|** [], [1],   [2], [1,2] |
| [1, 2, 3] | [], [1],   [2], [1,2], [3], [1,3],   [2,3], [1,2,3] |

Start with empty subsets. At each step, take one element from nums array, generate new subsets from existing subsets. Copy current subsets and add new element to each element of copy subsets.

Time complexity: $ O(N*2^N)$, space complexity: $O(N*2^N)$

**Solution 2**: backtracking

Backtracking is an algorithm for finding all solutions by exploring all potential candidates. If the solution candidate turns to be not a solution (or at least not the last one), backtracking algorithm discards it by making some changes on the previous step.

Time complexity: $ O(N*2^N)$, space complexity: $ O(N*2^N)$

#### 216 Combination sum III

参考 039 combination sum

**Solution 1**: backtracking

画出决策树，决策树第i层代表解的第i个数。

Number of exploration $ P(9, K) = \frac{9!}{(9-K)!} $, each exploration takes constant time. So time complexity: $O(\frac{9!}{(9-K)!})$, space complexity: $ O(K) $

#### 