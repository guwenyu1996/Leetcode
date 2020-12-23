package Tree._543;

import Tree.TreeNode;

public class Solution {
    // My solution: recursion
    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode node){
        if(node == null)
            return 0;

        int left = helper(node.left);
        int right = helper(node.right) ;
        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }
}
