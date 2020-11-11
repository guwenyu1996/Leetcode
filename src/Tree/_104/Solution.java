package Tree._104;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // Solution 1: recursion
    public int maxDepth(TreeNode root) {
        return helper(root);
    }

    public int helper(TreeNode node){
        if(node == null)
            return 0;

        return Math.max(helper(node.left) + 1, helper(node.right) + 1);
    }

    // Solution 2: iteration BFS
    public int maxDepth1(TreeNode root) {
        if(root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i ++){
                TreeNode node = queue.poll();

                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }

            level ++;
        }

        return level;
    }
}
