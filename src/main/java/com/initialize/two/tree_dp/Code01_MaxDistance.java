package com.initialize.two.tree_dp;

import com.initialize.one.tree.Node;

/**
 * 二叉树节点的最大距离问题
 *
 * 从二叉树的节点a出发，可以向上或向下走，但沿途的节点只能经过一次，
 * 到达节点b时路径上的节点个数叫作a到b的距离，那么二叉树任何两个节点之间都有距离。
 * 求整棵树上最大距离。
 *
 * 树形dp套路第一步：
 * 以某个节点X为节点的子树中，分析答案有哪些可能性，并且这种分析是以X的左子树，
 * X的右子树和X整棵树的角度来考虑可能性的
 *
 * 树型dp套路第二步：
 * 根据第一步的可能性分析，列出所有需要的信息
 *
 * 树型dp套路第三步：
 * 合并第二步的信息，对左树和右树提出同样的要求，并写出信息结构
 *
 * 树型dp套路第四步：
 * 设计递归函数，递归函数是处理以X为头节点的情况下的答案。
 * 包括设计递归的basecase,默认直接得到左树和右树的所有信息，以及把可能性做整合，
 * 并且要返回第三步的信息结构。
 * @author initialize liu
 * @date 2021/10/30
 * @apiNote
 */
public class Code01_MaxDistance {

    public static class Node<V> {
        V value;
        Node<V> left;
        Node<V> right;
        public Node(){}
        public Node(V v){
            this.value = v;
        }
    }

    //求二叉树两节点之间的最大距离
    public static int maxDistance(Node<Integer> head){
        return process(head).maxDistance;
    }

    //返回值结构
    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int dis, int h){
            maxDistance = dis;
            height = h;
        }
    }

    //返回以x为头的整棵树，两个信息
    public static Info process(Node<Integer> x){
        if(x == null){
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //info
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + 1 + rightInfo.height;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height)+ 1;
        return new Info(maxDistance, height);
    }

    public static void main(String[] args) {

        Node<Integer> head = new Node<>(3);

        //....

        //二叉树中两节点之间的最大值
        System.out.println(maxDistance(head));

    }
}
