package com.initialize.one.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 * 这是一种图的表示方式，不为一，这是一个具有一般性的结构。
 * 接下来的一些问题都是根据这种结构来处理。（对于其它的图结构处理流程也基本相同）
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Graph {
    public HashMap<Integer, Node> nodes;    //key -> value: 节点值 -> 节点
    public HashSet<Edge> edges; //边界

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
