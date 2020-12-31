package DFS._111;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // Solution 1: BFS
    public int minDepth(TreeNode root) {
        if(root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            depth ++;

            for(int i = 0; i < size; i ++){
                TreeNode node = queue.poll();

                if(node.left == null && node.right == null)
                    return depth;

                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
        }

        return depth;
    }

    // Solution 2: recursion
    public int minDepth1(TreeNode root) {
        return helper(root);
    }

    public int helper(TreeNode node){
        if(node == null)
            return 0;

        if(node.left == null && node.right == null)
            return 1;

        int depth = Integer.MAX_VALUE;
        if(node.left != null)
            depth = Math.min(depth, helper(node.left));

        if(node.right != null)
            depth = Math.min(depth, helper(node.right));

        return depth + 1;
    }
}
