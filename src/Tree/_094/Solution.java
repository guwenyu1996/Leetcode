package Tree._094;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }
class Solution {
    // Solution 1: recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;

    }

    public void traversal(TreeNode node, List<Integer> result){
        if(node != null){
            traversal(node.left, result);
            result.add(node.val);
            traversal(node.right, result);
        }
    }

    // Solution 2: iteration
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer> ();
        Stack<TreeNode> stack = new Stack<> ();
        TreeNode current = root;

        while(current != null || !stack.isEmpty()){
            while(current != null){
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }
}