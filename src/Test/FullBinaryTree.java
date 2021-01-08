package Test;

class Node{
    Node left, right;
}

public class FullBinaryTree {
    public int getHeight(Node root, int height){
        if(root == null)
            return height;
        else
            return 0; //return geiHeight(root.left, height + 1);
    }

    public boolean ifFullBinaryTree(Node left, Node right){
        if(left == null && right == null)
            return true;
        else if(left == null || right == null)
            return false;
        return false;//return isFullBinaryTree(left.left, right.right);
    }

    public int count(Node root){
        if(root == null)
            return 0;
        int height = getHeight(root, 0);
        Node left = root.left;
        if(ifFullBinaryTree(left.left, left.right))
            return count(root.right) + (int)Math.pow(2, height -1);
        else
            return count(root.left) + (int)Math.pow(2, height - 2);
    }

}
