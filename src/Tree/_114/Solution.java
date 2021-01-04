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

}
