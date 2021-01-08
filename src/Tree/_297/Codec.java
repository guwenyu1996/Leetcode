package Tree._297;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return "";

        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            if(node.val == -1001){
                builder.append("null,");
            }else{
                builder.append(node.val);
                builder.append(",");

                if(node.left != null)
                    queue.add(node.left);
                else
                    queue.add(new TreeNode(-1001));

                if(node.right != null)
                    queue.add(node.right);
                else
                    queue.add(new TreeNode(-1001));
            }
        }

        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == "")
            return null;

        String[] terms = data.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(terms[0]));
        List<TreeNode> list = new ArrayList<>();
        list.add(root);

        int i = 1;

        while(i < terms.length){
            List<TreeNode> temp = new ArrayList<>();

            for(TreeNode parent: list){
                if(parent == null)
                    continue;

                TreeNode child = string2Node(terms[i]);
                parent.left = child;
                i ++;
                temp.add(child);
                child = string2Node(terms[i]);
                parent.right = child;
                i ++;
                temp.add(child);
            }

            list = temp;
        }
        return root;
    }

    public TreeNode string2Node(String data){
        if(data.equals("null"))
            return null;

        return new TreeNode(Integer.parseInt(data));
    }
}