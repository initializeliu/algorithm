package com.initialize.one.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：
 * 正数数组costs
 * 正数数组profits
 * 正数k
 * 正数m
 * 含义：
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱（利润）
 * k表示你只能串行的最多做k个项目
 * m表示你初始的资金
 * 说明：
 * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出：
 * 你最后获得的最大钱数。
 *
 * 通过小根堆和大根堆进行处理？
 * 小根堆内放所有为处理项目，按照项目的花费进行排序，
 * 取出小根堆内所有小于现有资金m的项目放入，大根堆（利润排序）内，
 * 从大根堆内取出一个顶部项目，处理，获取利润最大。
 *
 *
 * @author initialize liu
 * @date 2021/10/21
 * @apiNote
 */
public class Code04_IPO {
    public static class Node{
        public int p;
        public int c;
        public Node(int p, int c){
            this.p = p;
            this.c = c;
        }
    }
    public static class MinCostComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }
    public static class MaxProfitComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital){
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        //所有项目仍到被锁池中，花费组织的小根堆
        for(int i = 0; i < Profits.length; i++){
            minCostQ.add(new Node(Profits[i], Capital[i]));
        }
        for(int i = 0; i < k; i++){ //进行k轮
            //能力所及的项目，全解锁
            //W现有资金
            //当前剩余项目minCostQ中，花费资金 <= W现有资金
            while(!minCostQ.isEmpty() && minCostQ.peek().c <= W){
                //能够处理的项目放入大根堆
                maxProfitQ.add(minCostQ.poll());
            }
            //大根堆为空，结束
            if(maxProfitQ.isEmpty()){
                return W;
            }
            W += maxProfitQ.poll().p;//大根堆弹出利润最大的项目，处理
        }
        return W;
    }

}
