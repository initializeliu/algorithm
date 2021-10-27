package com.initialize.graph;

import java.util.*;

/**
 * 最小生成树
 * kruskal算法
 * 适用范围：要求无向图
 * 返回Set<Edge>是最小生成树用到的边界
 * 1.将每个节点孤立，节点中对应的集合只有它自己
 * 2.边界放入优先级队列，权重小的边界先出队列
 * 3.判断边界两边的节点是否在不同的区域（节点对应的集合不相同），
 * 如果区域不同合并区域，则边界为有效边界。
 * 如果区域相同，则边界不在最小生成树中
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Code05_Kruskal {

    public static class MySets{
        public HashMap<Node, List<Node>> setMap;

        public MySets(List<Node> nodes){
            for(Node cur : nodes){
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);   //setMap中每个节点对应的集合，只有本节点一个
            }
        }
        //判断两个节点对应的集合是否相同。
        public boolean isSameSet(Node from, Node to){
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }
        //两个节点链接，两个节点对应的集合合并，集合中的每个节点对应的集合相同。
        public void union(Node from, Node to){
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for(Node toNode : toSet){
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        //UnionFind unionFind = new UnionFind();
        //unionFind.makeSets(graph.nodes.values());

        MySets mySets = new MySets((List<Node>) graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for(Edge edge : graph.edges){ //M条边
            priorityQueue.add(edge);    //O(logM)
        }
        //Edge按照优先级进行出队列，保证weitht最小的优先出队列
        Set<Edge> result = new HashSet<>();
        while(!priorityQueue.isEmpty()){    //M条边全部出队列，循环结束
            Edge edge = priorityQueue.poll();       //O(logM)
            //if(!unionFind.isSameSet(edge.from, edge.to)){
            //    result.add(edge);
            //    unionFind.union(edge.from, edge.to);
            //}
            if(!mySets.isSameSet(edge.from, edge.to)){  //两个节点不在同一个集合中
                result.add(edge);   //添加到结果中，表示会走这条路径
                mySets.union(edge.from, edge.to);   //将from和to所在的集合合并
            }
        }
        return result;
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
