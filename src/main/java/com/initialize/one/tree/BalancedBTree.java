package com.initialize.one.tree;

/**
 * 平衡二叉树
 * 如何判断一个二叉树是平衡二叉树？
 * 一个节点的两个子节点高度查小于2，满足为平衡二叉树
 * @author initialize liu
 * @date 2021/10/17
 * @apiNote
 */
public class BalancedBTree {
    public static void main(String[] args) {

        Node haed = null;
        System.out.println("是否是平衡二叉树？" + isBalanced(haed));
    }
    //返回值类型
    public static class ReturnType{
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int height){
            this.isBalanced = isB;
            this.height = height;
        }
    }

    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    public static ReturnType process(Node x){
        if(x == null){
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        //该节点的层高为左右子节点层高的最大值+1
        int height = Math.max(leftData.height, rightData.height) + 1;
        //左节点为平衡二叉树
        //右节点为平衡二叉树
        //左右层高相差小于2
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

}
