package com.initialize.one.graph;

import java.util.*;

/**
 * 拓扑排序算法：
 * 适用范围：要求有向图，且有入度为0的节点，且没有环
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Code04_TopologySort {

    //directed graph and no loop
    public static List<Node> sortedTopology(Graph graph){
        //key: 某个node
        //value: 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        //入度为0的点，才能进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        //将节点对应的入度存入inMap
        for(Node node : graph.nodes.values()){
            inMap.put(node, node.in);
            if(node.in == 0){
                zeroInQueue.add(node);
            }
        }
        //拓扑排序的结果，依次加入result
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);    //入度节点为0，它的依赖节点已经添加到了result
            for(Node next : cur.nexts){ //遍历cur指向的节点
                inMap.put(next, inMap.get(next) - 1);   //cur指向的节点入度-1
                if(inMap.get(next) == 0){
                    //如果被指向的节点的入度节点为0，添加到zeroInQueue
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
