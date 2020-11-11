package Tree._103;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    // Solution 1: DFS + recursive
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null)
            return result;

        DFS(root, 0, result);
        return result;
    }

    public void DFS(TreeNode node, int level, List<List<Integer>> result){
        if(node != null){

            if(level == result.size())
                result.add(new ArrayList<>());

            DFS(node.left, level + 1, result);
            List<Integer> list = result.get(level);
            if(level % 2 == 0)
                list.add(list.size(), node.val);
            else
                list.add(0, node.val);

            DFS(node.right, level + 1, result);
        }

    }


    // Solution 2: iteration
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int level = 0;

        while(!queue.isEmpty()){
            List<Integer> temp = new ArrayList<>();

            int size = queue.size();
            for(int i = 0; i < size; i ++){
                if(level % 2 == 0){
                    TreeNode node = queue.pollFirst();
                    temp.add(node.val);

                    if(node.left != null)
                        queue.addLast(node.left);
                    if(node.right != null)
                        queue.addLast(node.right);
                }else{
                    TreeNode node = queue.pollLast();
                    temp.add(node.val);

                    if(node.right != null)
                        queue.addFirst(node.right);
                    if(node.left != null)
                        queue.addFirst(node.left);
                }
            }
            result.add(temp);
            level ++;
        }
        return result;
    }
}
