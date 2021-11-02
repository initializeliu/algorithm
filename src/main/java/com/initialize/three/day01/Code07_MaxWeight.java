package com.initialize.three.day01;

/**
 * 二叉树每个节点都有一个int型权值，给定一颗二叉树，要求计算出发从根节点到叶节点的所有路径中，权值和最大的值是多少？
 *
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code07_MaxWeight {

    public static class Node{
        public int value;
        public Node right;
        public Node left;
        public Node(int value){
            this.value = value;
        }
    }

    //全局变量，只在到达叶节点的时候，有可能更新
    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPath(Node head){
        p(head, 0);
        return maxSum;
    }

    //从跟节点出发到当前节点上的节点，获得的路径和
    public static void p(Node x, int pre){
        if(x.left == null && x.right == null){//当前x是叶子节点
            maxSum = Math.max(maxSum, pre + x.value);
        }
        if(x.left != null){
            p(x.left, pre + x.value);
        }
        if(x.right != null){
            p(x.right, pre + x.value);
        }
    }

    public static int maxDis(Node head){
        if(head == null){
            return 0;
        }
        return process2(head);
    }

    //x为头的整棵树上，最大路径和是多少，返回。
    //路径要求，一定从x出发，到叶节点，算作一个路径
    public static int process2(Node x){
        if(x.left == null && x.right == null){
            return x.value; //返回叶节点的值
        }
        int next = Integer.MIN_VALUE;
        if(x.left != null){
            next = process2(x.left);
        }
        if(x.right != null){
            next = Math.max(next, process2(x.right));
        }
        return x.value + next;
    }
}
