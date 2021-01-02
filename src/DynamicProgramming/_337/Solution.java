package DynamicProgramming._337;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // Solution 1: recursion
    public int rob(TreeNode root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    public int[] helper(TreeNode node){
        if(node == null)
            return new int[]{0, 0};

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{rob, notRob};
    }

    // Solution 2: recursion with memo
    public int rob1(TreeNode root) {
        Map<TreeNode, int[]> map = new HashMap<>();
        int[] result = helper1(root, map);

        return Math.max(result[0], result[1]);
    }

    public int[] helper1(TreeNode node, Map<TreeNode, int[]> map){
        if(node == null){
            int[] temp = new int[]{0, 0};
            map.put(node, temp);
            return temp;
        }

        if(map.containsKey(node))
            return map.get(node);

        int[] left = helper1(node.left, map);
        int[] right = helper1(node.right, map);

        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        map.put(node, new int[]{rob, notRob});

        return new int[]{rob, notRob};
    }
}
