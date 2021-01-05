package Tree._114;

import Tree.TreeNode;

public class Solution {
    // My solution: recursion
    public void flatten(TreeNode root) {
        helper(root);
    }

    public TreeNode helper(TreeNode node){
        if(node == null)
            return null;

        TreeNode left = helper(node.left);
        TreeNode right = helper(node.right);

        if(left != null){
            TreeNode curr = left;
            while(curr.right != null)
                curr = curr.right;

            curr.right = right;
            node.right = left;
            node.left = null;
        }

        return node;
    }

    // Solution 1: Morris traversal
    public void flatten1(TreeNode root) {
        if(root == null)
            return;

        TreeNode node = root;
        while(node != null){
            if(node.left != null){
                TreeNode rightMost = node.left;
                while(rightMost.right != null)
                    rightMost = rightMost.right;

                rightMost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }

    }

}
