package com.initialize.one.tree;

/**
 * 二叉树中找到一个节点的后继节点?
 * 分两种情况？
 * 1.存在右子树，直接找到右子树的最左节点就是后继节点
 * 2.不存在右子树，不断找当前节点的父节点，如果当前节点是父节点的左孩子，那么这个父节点就是后继节点。
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class SuccessorNode {

    public static class  Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int val){
            value = val;
        }
    }

    public static void main(String[] args) {

    }
    //
    public static Node getSuccessorNode(Node node){
        if(node == null){
            return node;
        }
        if(node.right != null){//有右子树
            return getLeftMost(node.right);//获取右子树的最左节点
        }else{//无右子树
            Node parent = node.parent;
            while(parent != null && parent.left != node){//当前节点是其父节点的右孩子
                node = parent;
                parent = node.parent;
            }
            return parent; //设置根节点的父节点为null，可以确保最后一个节点的后继节点为null;
        }
    }
    public static Node getLeftMost(Node node){
        if(node == null){
            return node;
        }
        while(node.left != null){//找node树的最左节点
            node = node.left;
        }
        return node;
    }


}
