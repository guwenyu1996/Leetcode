package Tree._230;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    // My solution
    public int kthSmallest(TreeNode root, int k) {
        int temp = 0;
        TreeNode node = root;

        while(node != null){
            int count = count(node.left);
            temp += count;

            if(temp + 1 == k)
                return node.val;
            else if(temp + 1 < k){
                node = node.right;
                temp += 1;
            }
            else{
                node = node.left;
                temp -= count;
            }
        }

        return 0;
    }

    public int count(TreeNode node){
        if(node == null)
            return 0;

        return count(node.left) + count(node.right) + 1;
    }

    // Solution 1: inorder + recursion
    public int kthSmallest1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();

        inorder(root, list);
        return list.get(k-1);
    }

    public void inorder(TreeNode node, List<Integer> list){
        if(node == null)
            return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    // Solution 2: inorder + iteration
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            k --;
            if(k == 0)
                return node.val;
            node = node.right;
        }

        return 0;
    }
}
