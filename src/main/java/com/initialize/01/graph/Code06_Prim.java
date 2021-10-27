package com.initialize.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树
 * prim算法
 * 适用范围：要求无向图
 * 返回Set<Edge>是最小生成树用到的边界
 * 不同与kruskal开始节点的选择是随机的。
 *
 * 1.随机选择一个节点，将节点向连的边界添加到优先级队列中
 * 2.从队列中取出边界权重最小的边界，
 * 3.判断边界指向的to节点，是否是未解锁的节点？
 * 4.to未解锁，说明这条边界是最小生成树的一部分，把to节点相连的边界添加到队列中
 * 5.to已解锁，跳过，
 * 6.再次从队列中获取最小边界,循环执行3，4，5。直到队列为空
 *
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Code06_Prim {

    public static Set<Edge> primMST(Graph graph){

        //解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Code05_Kruskal.EdgeComparator());

        HashSet<Node> set = new HashSet<>();//已经解锁过的节点

        Set<Edge> result = new HashSet<>(); //  依次挑选的边在result

        //适用for的原因防止森林内有多个不相连的图
        for(Node node : graph.nodes.values()){  //随便挑选一个节点，
            //node是开始点
            if(!set.contains(node)){
                set.add(node);
                for(Edge edge : node.edges){//由一个点，解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while(!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();   //弹出解锁的边界中，最小的边
                    Node toNode = edge.to;      //可能是一个新的点
                    if(!set.contains(toNode)){  //set中不含有，就是新点
                        set.add(toNode);     //节点解锁
                        result.add(edge);   //发现新节点，所以这个边界是最小生成树的一部分
                        for(Edge nextEdge : toNode.edges){  //将新节点中边界添加到队列
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }
}
