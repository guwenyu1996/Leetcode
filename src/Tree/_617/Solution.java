package Tree._617;

import Tree.TreeNode;

import java.util.Stack;

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

    // Solution 2: DFS iterative
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});

        while(!stack.isEmpty()){
            TreeNode[] t = stack.pop();

            if(t[0] == null){
                t[0] = t[1];
                continue;
            }else if(t[1] == null)
                continue;

            t[0].val += t[1].val;
            if(t[0].left == null)
                t[0].left = t[1].left;
            else
                stack.push(new TreeNode[]{t[0].left, t[1].left});

            if(t[0].right == null)
                t[0].right = t[1].right;
            else
                stack.push(new TreeNode[]{t[0].right, t[1].right});
        }

        return t1;
    }

    // Solution 3: DFS Iterative simplify
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});

        while(!stack.isEmpty()){
            TreeNode[] t = stack.pop();

            if (t[1] == null) {
                continue;
            }

            t[0].val += t[1].val;
            if(t[0].left == null)
                t[0].left = t[1].left;
            else
                stack.push(new TreeNode[]{t[0].left, t[1].left});

            if(t[0].right == null)
                t[0].right = t[1].right;
            else
                stack.push(new TreeNode[]{t[0].right, t[1].right});
        }

        return t1;
    }
}
