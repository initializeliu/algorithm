package com.initialize.graph;

/**
 * 边界
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Edge {
    public int weight;  //权重
    public Node from;   //发出点
    public Node to;     //到达点

    public Edge(int weight, Node from, Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
