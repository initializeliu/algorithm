package com.initialize.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树CBT
 * 如何判断一个二叉树是完全二叉树？
 * 通过广度优先遍历
 *
 * @author initialize liu
 * @date 2021/10/16
 * @apiNote
 */
public class CBTree {
    public static void main(String[] args) {

    }
    //通过广度优先遍历判断一个二叉树是否是完全二叉树
    public static boolean isCBT(Node<Integer> head){
        if(head != null) {
            Queue<Node<Integer>> queue = new LinkedList<>();
            queue.add(head);
            boolean flag = false;//如果某个节点不存在两个子节点，设置flag=true,后面所有节点必须是叶节点
            Node<Integer> l = null;
            Node<Integer> r = null;
            while(!queue.isEmpty()){
                head = queue.poll();
                l = head.left;
                r = head.right;
                //1.如果一个节点有右节点，无左节点，一定不是完全二叉树
                if(l == null && r != null){
                    return false;
                }
                //2.在上面条件不成立前提下，如果出现一个节点没有两个子节点，则后面所有节点必须是叶子节点。
                if(flag && (l != null || r != null)){//flag==true说明出现了不含有两个节点的子节点
                    return false;
                }
                //3.可以将1，2合并
                //if((flag && !(l == null && r == null)) || (l == null && r != null)){
                //    return false;
                //}
                if(l != null){
                    queue.add(l);
                }
                if(r != null){
                    queue.add(r);
                }
                if(l != null || r != null){
                    flag = true;
                }
            }
        }
        return true;
    }
}
