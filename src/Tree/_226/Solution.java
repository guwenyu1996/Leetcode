package Tree._226;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // solution 1: recursion
    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
    }

    public void helper(TreeNode node){
        if(node != null){
            helper(node.left);
            helper(node.right);

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }

    // solution 2: iteration
    public TreeNode invertTree1(TreeNode root) {
        if(root == null)
            return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return root;
    }
}
