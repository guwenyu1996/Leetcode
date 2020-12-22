package Tree._617;

import Tree.TreeNode;

public class Solution {
    // My solution: recursion
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return helper(t1, t2);
    }

    public TreeNode helper(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null)
            return null;

        int v1 = (t1 == null)? 0 : t1.val;
        int v2 = (t2 == null)? 0 : t2.val;

        TreeNode node = new TreeNode(v1 + v2);
        node.left = helper((t1 == null)? null : t1.left, (t2 == null)? null : t2.left);
        node.right = helper((t1 == null)? null : t1.right, (t2 == null)? null : t2.right);

        return node;
    }

    // Solution 1: recursion
    public TreeNode helper1(TreeNode t1, TreeNode t2){
        if(t1 == null)
            return t2;
        else if(t2 == null)
            return t1;

        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = helper(t1.left, t2.left);
        node.right = helper(t1.right, t2.right);

        return node;
    }
}
