package Tree._098;

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
    // My initial solution: check left&right node, but didnt check all elements in subtree
    public boolean isValidBST(TreeNode root) {
        return check(root);
    }

    public boolean check(TreeNode node){
        if(node == null)
            return true;

        if(node.left != null && node.val <= node.left.val)
            return false;
        else if(node.right != null && node.val >= node.right.val)
            return false;

        return check(node.left) && check(node.right);
    }

    // Solution 1: recursion, use INT to store upper and lower bound
    public boolean check(TreeNode node, Integer lower, Integer upper){
        if(node == null)
            return true;

        if(lower != null && node.val <= lower) return false;
        if(upper != null && node.val >= upper) return false;

        if(!check(node.left, lower, node.val)) return false;
        if(!check(node.right, node.val, upper)) return false;

        return true;
    }

    // Solution 2: iteration
    public Stack<TreeNode> stack = new Stack<> ();
    public Stack<Integer> upper = new Stack<> ();
    public Stack<Integer> lower = new Stack<>();


    public boolean isValidBST1(TreeNode root) {
        update(root, null, null);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            Integer up = upper.pop();
            Integer lo = lower.pop();

            if(node == null) continue;

            if(up != null && up <= node.val) return false;
            if(lo != null && lo >= node.val) return false;

            if(node.left != null)
                update(node.left, lo, node.val);
            if(node.right != null)
                update(node.right, node.val, up);
        }
        return true;
    }

    public void update(TreeNode node, Integer lowerLimit, Integer upperLimit){
        stack.push(node);
        upper.push(upperLimit);
        lower.push(lowerLimit);
    }

    // Solution 3: inorder traversal
    public boolean isValidBST3(TreeNode root) {
        TreeNode current = root;
        double last = -Double.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<> ();

        while(current != null || !stack.isEmpty()){
            while(current != null){
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if(current.val <= last)
                return false;
            last = current.val;
            current = current.right;
        }
        return true;
    }
}