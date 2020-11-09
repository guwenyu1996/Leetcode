package Tree._101;

import java.util.Stack;

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
    // Solution 1: recursion
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;

        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;

        if(left.val != right.val)
            return false;

        return check(left.left, right.right) && check(left.right, right.left);
    }

    // Solution 3: iteration
    public boolean isSymmetric2(TreeNode root) {
        if(root == null)
            return true;
        TreeNode left, right;
        Stack<TreeNode> leftSt = new Stack<TreeNode> ();
        Stack<TreeNode> rightSt = new Stack<TreeNode> ();

        leftSt.push(root.left);
        rightSt.push(root.right);

        while(!leftSt.isEmpty()){
            left = leftSt.pop();
            right = rightSt.pop();

            if(left == null && right == null) continue;
            if(left == null || right == null) return false;

            if(left.val != right.val) return false;

            leftSt.push(left.left);
            leftSt.push(left.right);
            rightSt.push(right.right);
            rightSt.push(right.left);
        }
        return true;
    }
}