package com.initialize.tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定两个二叉树的节点node1和node2,找到它们的最低公共祖先节点？
 * @author initialize liu
 * @date 2021/10/17
 * @apiNote
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {

    }
    //通过递归找到它们的最低公共祖先节点，不容易理解
    public static Node<Integer> lca2(Node<Integer> head, Node<Integer> o1, Node<Integer> o2){
        return process(head, o1, o2);
    }

    /**
     * o1,o2关系的两种情况：
     * 1.o1,o2互为最低公共祖先节点
     * 2.o1,o2存在一个最低公共祖先节点（非o1,o2)
     */
    public static Node<Integer> process(Node<Integer> head, Node<Integer> o1, Node<Integer> o2){
        if(head == null || head == o1 || head == o2){
            return head;
        }
        Node<Integer> leftNode = process(head.left, o1, o2);
        Node<Integer> rightNode = process(head.right, o1, o2);

        if(leftNode != null && rightNode != null){  //左右两个子树中个存在一个o1或o2,当前的head就是最低祖先节点
            //o1,o2存在一个非o1,o2的最低公共祖先节点
            return head;
        }
        //if(leftNode == null && rightNode == null){  //本节点树不存在o1和o2，用下面可直条件接代替
        //    return null;
        //}
        return leftNode != null ? leftNode : rightNode;     //第二种情况从这里找到最低公共祖先节点
    }

    //通过HashMap和HashSet, 这种方法比较容易理解
    public static Node<Integer> lca(Node<Integer> head, Node<Integer> o1, Node<Integer> o2){
        if(head != null && o1 != null && o2 != null){//保证o1和o2是树中的节点
            return null;
        }
        //1.通过遍历二叉树将，每个节点和父节点的对应关系暂存在HashMap
        HashMap<Node, Node> nodeMap = new HashMap();
        nodeMap.put(head, head);
        getAncestorMap(head, nodeMap);
        //2.根据o1在HashMap中找到所有祖先节点，后存放入HashSet
        HashSet<Node<Integer>> nodeSet = new HashSet<>();       //o1所有祖先节点
        do{
            nodeSet.add(o1);    //o1可能是最低公共祖先节点
        }while((o1 = nodeMap.get(o1)) != o1);    //只有跟节点的父节点是它自己
        nodeSet.add(o1); //将跟节点添加
        //3.根据o2在HashMap中找到父节点，如果父节点在HashSet中存在返回，这个节点就是最低祖先节点。
        //如果父节点不在HashSet中存在，就继续寻找父节点的父节点，以此类推。
        while(!nodeSet.contains(o2)){
            o2 = nodeMap.get(o2);
        }
        return o2;
    }
    //获取子父节点对应关系
    public static void getAncestorMap(Node<Integer> head, HashMap<Node,Node> nodeMap){
        if(head == null){
            return;
        }
        if(head.left != null){
            nodeMap.put(head.left, head);
            getAncestorMap(head.left, nodeMap);
        }
        if(head.right != null){
            nodeMap.put(head.right, head);
            getAncestorMap(head.right, nodeMap);
        }
    }
}
