package Tree._105;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> map;
    Integer pre = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for(int i = 0; i < inorder.length; i ++)
            map.put(inorder[i], i);

        return helper(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int[] inorder, int in_left, int in_right){
        if(in_left > in_right)
            return null;

        TreeNode node = new TreeNode(preorder[pre]);
        int root = map.get(preorder[pre]);

        pre ++;

        node.left = helper(preorder, inorder, in_left, root - 1);
        node.right = helper(preorder, inorder, root + 1, in_right);

        return node;
    }
}
