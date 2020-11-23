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

#### 122 Best time to buy and sell stock II

**Solution 1**: peak valley approach

We can view the total profit as $\sum{ (peak - valley)}$. Then looping over the array can be view as the process to find a valley(continuous decrease) and peak (continuous increase). 

代码错误：找peak 和valley的过程中，注意是>= 还是> 算valley。比如corner case[3,3], 如果是>则陷入死循环

Time complexity: $O(n)$, space complexity: $ O(1) $

**Solution 2**: simplify solution 1

Instead of looking for peak and valley, we can add consecutive profit. e.g. [1, 2, 3], valley = 1, peak = 3. But we can calculate as 2-1 + 3-2 = 2. If the second number is larger than the first one, we add the difference to sum.

Time complexity: $O(n)$, space complexity: $ O(1) $

#### 738 monotone increasing digits



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

### Divide and Conquer

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

​			实现：数组的第一个元素设为pivot, 使用两个指针一左一右，右边的指针寻找比x大的元素，左边的指针寻找比x小的元素，交换两指针。当左指针等于右指针，遍历结束，交换pivot和指针。

==需要右边的哨兵先走，这样最后相遇的元素一定小于pivot.== 

Conquer: recursively solve two subarrays

本题寻找第k大元素，如果pivot position < k，没有必要对左边元素排序；如果pivot position > k, 没有必要对右边元素进行遍历。

### Dynamic Programming

#### 53 Maximum subarray

我的解法：遍历数组，如果当前值>0, 或者num[i]>num[i+1]。这种解法的问题是拿不到最优解 [4, -1, 3]

**Solution 1**: greedy

遍历array, keep three variables, current value, current local max, and max value. If curr > curr local max, update curr local. If max of two (curr, curr value) > max value, update max value.

Time complexity: $O(n)$, space complexity: $ O(1) $

思路错误：curr local max = curr / local max + curr, 而不是local max (不然会出现local max + 间隔一段后的int)

#### 121 Best time to buy and sell stock

Keep two variables, minprice and maxprofit. Loop over the array, check if current number smaller than minprice, or the diff between curr and minprice is larger than maxprofit.

Time complexity: $O(n)$, space complexity: $ O(1) $

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

### DFS

#### 200 Number of islands

这道题实际在求有多少个连通的1区块。

**Solution 1**: dfs

深度遍历图，每遍历到一个1位置时，将所有与之连通的1也遍历一遍。将已经访问过的结点值更改为2，来区分访问/未访问过的结点。循环遍历整个二维数组，对每个值进行一次上面的操作。

Time complexity: $ O(M*N) $, space complexity: $O(M*N)$ by worst case

**Solution 2**: bfs

广度遍历图，每遍历到一个1位置时，将其视为广度遍历的根节点。将已经访问过的结点值更改为2，来区分访问/未访问过的结点。在广度优先搜索时，使用stack, stack里存储每个点的index(row * N + columns)。这样可以从index再得到row, column