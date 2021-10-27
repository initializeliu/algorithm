package com.initialize.graph;

import java.util.ArrayList;



/**
 * 图中的节点
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Node {
    public int value;       //节点值
    public int in;          //输入节点数量
    public int out;         //输出节点数量
    public ArrayList<Node> nexts;    //本节点指向的节点（有向图），相邻节点（无向图）
    public ArrayList<Edge> edges;   //本节点指向的边界（有向图），相连边界（无向图）

    public Node(){}
    public Node(int value){
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
