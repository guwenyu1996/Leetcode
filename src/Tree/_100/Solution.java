package Tree._100;

import java.util.Deque;
import java.util.LinkedList;

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;
        if(p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // Solution 2: iteration
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        Deque<TreeNode> pList = new LinkedList<> ();
        Deque<TreeNode> qList = new LinkedList<>();
        pList.addLast(p);
        qList.addLast(q);

        while(!pList.isEmpty()){
            TreeNode pEle = pList.pollFirst();
            TreeNode qEle = qList.pollFirst();

            if(!check(pEle, qEle)) return false;
            if(pEle == null)
                continue;

            if(!check(pEle.left, qEle.left)) return false;
            if(pEle.left != null){
                pList.addLast(pEle.left);
                qList.addLast(qEle.left);
            }

            if(!check(pEle.right, qEle.right)) return false;
            if(qEle.right != null){
                pList.addLast(pEle.right);
                qList.addLast(qEle.right);
            }
        }

        return true;
    }

    public boolean check(TreeNode p, TreeNode q){
        if(p == null && q == null)
            return true;

        if(p != null && q != null){
            if(p.val == q.val)
                return true;
        }
        return false;
    }
}