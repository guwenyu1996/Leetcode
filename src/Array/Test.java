package Array;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    Stack<TreeNode> stack = new Stack<> ();
    Stack<Integer> upper = new Stack<> ();
    Stack<Integer> lower = new Stack<> ();

    public boolean isValidBST(TreeNode root) {
        update(root, null, null);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            Integer up = upper.pop();
            Integer lo = lower.pop();

            if(up != null && up <= node.val) return false;
            if(lo != null && lo >= node.val) return false;

            if(node.left != null)
                update(node.left, lo, node.val);
            if(node.right != null)
                update(node.right, node.val, up);
        }
        return true;
    }

    public void update(TreeNode node, Integer upperLimit, Integer lowerLimit){
        stack.push(node);
        upper.push(upperLimit);
        lower.push(lowerLimit);
    }
}