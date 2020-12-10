package Tree._236;

import Tree.TreeNode;

import java.util.*;

class Solution {
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return ans;
    }

    public boolean helper(TreeNode curr, TreeNode p, TreeNode q){
        if(curr == null)
            return false;

        boolean mid = false;
        if(curr.equals(p) || curr.equals(q)){
            mid = true;
        }

        boolean left = helper(curr.left, p, q);
        boolean right = helper(curr.right, p, q);

        if( (left && right) || (mid && (left || right)))
            ans = curr;

        return left || right || mid;
    }

    // Solution 1
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        queue.add(root);
        map.put(root, null);

        while(!map.containsKey(p) || !map.containsKey(q)){
            TreeNode curr = queue.poll();

            if(curr.left != null){
                map.put(curr.left, curr);
                queue.add(curr.left);
            }

            if(curr.right != null){
                map.put(curr.right, curr);
                queue.add(curr.right);
            }
        }

        // traverse p
        Set<TreeNode> set = new HashSet<>();
        TreeNode node = p;
        while(node != null){
            set.add(node);
            node = map.get(node);
        }

        node = q;
        while(node != null){
            if(set.contains(node))
                return node;
            node = map.get(node);
        }
        return null;
    }
}
