package Tree._102;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    // Solution 2: iteration
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> treeQ = new LinkedList<>();
        Queue<Integer> degreeQ = new LinkedList<> ();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<> ();

        if(root == null)
            return result;
        treeQ.offer(root);
        degreeQ.offer(0);
        int currDegree = 0;

        while(!treeQ.isEmpty()){
            TreeNode node = treeQ.poll();
            int degree = degreeQ.poll();

            if(node.left != null)
                updateQueues(treeQ, degreeQ, node.left, degree+1);

            if(node.right != null)
                updateQueues(treeQ, degreeQ, node.right, degree+1);

            if(degree != currDegree){
                result.add(temp);
                temp = new ArrayList<>();
                currDegree ++;
            }
            temp.add(node.val);
        }
        result.add(temp);
        return result;
    }

    public void updateQueues(Queue<TreeNode> treeQ, Queue<Integer> degreeQ, TreeNode node, int degree){
        treeQ.offer(node);
        degreeQ.offer(degree);
    }
}
