package com.initialize.tree;

/**
 * 满二叉树
 * 如何判读一个二叉树为满二叉树？
 * 节点数n = 2的层高树次方 - 1
 * @author initialize liu
 * @date 2021/10/17
 * @apiNote
 */
public class BSTree {
    public static void main(String[] args) {

    }

    public static class Info{
        public int height;
        public int nodes;
        public Info(int h, int n){
            height = h;
            nodes = n;
        }
    }

    //通过树型DB判断一个二叉树是否为满二叉树
    public static boolean isF(Node<Integer> head){
        if(head == null){
            return true;
        }
        Info info = f(head);
        //节点数n = 2的层高树次方 - 1
        return info.nodes == (1 << info.height -1);
    }

    public static Info f(Node<Integer> x){
        if(x == null){
            return new Info(0, 0);
        }
        Info leftData = f(x.left);
        Info rightData = f(x.right);
        //当前层高 = 子节点最大层高 + 1
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Info(height, nodes);
    }


}
