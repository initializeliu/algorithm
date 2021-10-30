package com.initialize.one.graph;

/**
 * 将其它的图结构，转换成我们熟悉的图结构(Graph+Node+Edge)
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Code01_GraphGenerator {

    //将矩阵结构图转换为Graph结构图
    //matrix所有的边
    //N * 3 的矩阵
    //[from节点上面的值，to节点上面的值, weight]
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
        for(int i = 0; i < matrix.length; i++){
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            if(!graph.nodes.containsKey(from)){ //from节点在图中不存在
                graph.nodes.put(from, new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);    //有向图指向发出节点添加边界
            graph.edges.add(newEdge);   //图中添加边界
        }
        return graph;
    }


}
