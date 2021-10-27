package com.initialize.tree;

import java.util.*;

/**
 * 搜索二叉树 BST
 * 如何判断一个二叉树是搜索二叉树？
 * 一个节点的左节点比自己小，右节点比自己大。
 * 中序遍历的到的结果一定是递增的。
 * @author initialize liu
 * @date 2021/10/16
 * @apiNote
 */
public class SBTree {

    public static void main(String[] args) {

    }
    public static int preValue = Integer.MIN_VALUE;
    //判断二叉树是不是搜索二叉树(递归)
    public static boolean checkBST(Node<Integer> head){
        if(head == null){
            return true;
        }
        boolean isLeftBst = checkBST(head.left);
        if(!isLeftBst){
            return false;
        }
        if (head.value <= preValue) { //此时preValue代表的是左子树中的最大值
            return false;
        }else{
            //此时preValue代表的是左子树和中间节点中的最大值
            preValue = head.value;  //如果左子树中的最大值都小与中间节点的值
        }
        return checkBST(head.right);
    }
    //递归判断搜索二叉树
    public static boolean checkBST2(Node head){
        List<Node> inOrderList = new ArrayList<>();
        process2(head, inOrderList);
        for (Node node : inOrderList) {

        }
        return true;
    }
    //二叉树通过中序遍历存入到List
    public static void process2(Node head, List<Node> inOrderList){
        if(head == null){
            return;
        }
        process2(head.left, inOrderList);
        inOrderList.add(head);
        process2(head.right, inOrderList);
    }
    //判断一个二叉树是否是搜索二叉树（非递归）
    public static boolean checkBST3(Node<Integer> head){
        if(head != null){
            int beforeMax = Integer.MIN_VALUE;
            Stack<Node<Integer>> stack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);//一直将"当前节点"head的左子节点压栈
                    head = head.left;
                }else{
                    head = stack.pop();
                    if (beforeMax < head.value){
                        beforeMax = head.value;
                    }else{
                        return false;   //左子树中的最大值大于该节点的值
                    }
                    head = head.right;
                }
            }
        }
        return true;
    }

    //通过递归套路判断一个树是否是搜索二叉树
    //树型DB处理套路
    public static boolean checkBST4(Node<Integer> head){
        return process(head).isBST;
    }

    public static class ReturnData{
        public boolean isBST;
        public int min;
        public int max;
        public ReturnData(boolean is, int mi, int ma){
            isBST = is;
            min = mi;
            max = ma;
        }
    }

    public static ReturnData process(Node<Integer> x){
        if(x == null){
            return null;
        }
        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);

        int min = x.value;
        int max = x.value;
        if(leftData != null){
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if(rightData != null){
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        //违规情况
        //boolean isBST = true;
        //if(leftData != null && (!leftData.isBST || leftData.max >= x.value)){
        //    isBST = false;
        //}
        //if(rightData != null && (!rightData.isBST || x.value >= rightData.min)){
        //    isBST = false;
        //}

        //正常情况
        boolean isBST = false;
        if(
                //左子节点符合要求：为null 或 左子节点最大值小于当前节点值
                (leftData != null? (leftData.isBST && leftData.max < x.value) : true)
                        //右子节点符合要求：为null 或 右子节点最小值大于当前节点值
                && (rightData == null || (rightData.isBST && x.value < rightData.min))
        ){
            isBST = true;
        }
        return new ReturnData(isBST, min, max);
    }

}
