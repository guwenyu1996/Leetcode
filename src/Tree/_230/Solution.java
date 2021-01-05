package Tree._230;

import Tree.TreeNode;

public class Solution {

    // My solution
    public int kthSmallest(TreeNode root, int k) {
        int temp = 0;
        TreeNode node = root;

        while(node != null){
            int count = count(node.left);
            temp += count;

            if(temp + 1 == k)
                return node.val;
            else if(temp + 1 < k){
                node = node.right;
                temp += 1;
            }
            else{
                node = node.left;
                temp -= count;
            }
        }

        return 0;
    }

    public int count(TreeNode node){
        if(node == null)
            return 0;

        return count(node.left) + count(node.right) + 1;
    }
}
