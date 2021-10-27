package com.initialize.tree;

/**
 * 二叉树节点
 * @author initialize liu
 * @date 2021/10/15
 * @apiNote
 */
public class Node<V> {
    V value;
    Node<V> left;
    Node<V> right;
    public Node(){}
    public Node(V v){
        this.value = v;
    }
}
