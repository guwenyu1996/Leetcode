package Tree._297;

import Tree.TreeNode;

import java.util.LinkedList;

public class Codec1 {

    String NULL = "#";
    String SEP = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    public void serialize(TreeNode node, StringBuilder builder){
        if(node == null){
            builder.append(NULL).append(SEP);
            return;
        }

        builder.append(node.val).append(SEP);
        serialize(node.left, builder);
        serialize(node.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for(String i: data.split(SEP))
            nodes.add(i);

        return deserialize(nodes);
    }

    public TreeNode deserialize(LinkedList<String> nodes){
        String temp = nodes.removeFirst();
        if(temp.equals(NULL))
            return null;

        TreeNode node = new TreeNode(Integer.parseInt(temp));
        node.left = deserialize(nodes);
        node.right = deserialize(nodes);

        return node;
    }
}

