package com.initialize.greedy;

import java.util.PriorityQueue;

/**
 * 一个数据流中，随时可以取得中位数
 *
 * @author initialize liu
 * @date 2021/10/21
 * @apiNote
 */
public class Code05_Median {

    public static PriorityQueue<Integer> maxQueue = new PriorityQueue<>();
    public static PriorityQueue<Integer> minQueue = new PriorityQueue<>();

    public static int getInteger(int addInt){

        //1.获取验证值
        Integer cur = maxQueue.peek();
        cur = cur == null? addInt : cur;

        //2.addInt <= cur 添加到大根堆
        if(addInt <= cur){
            maxQueue.add(addInt);
        }else{
            //3.cur < addInt 添加到小根堆
            minQueue.add(addInt);
        }
        //4.平衡大根堆与小根堆(数量相差为2)
        while(maxQueue.size() > minQueue.size() + 1){
            //将大根堆取出最大值放入到小根堆中
            minQueue.add(maxQueue.poll());
        }
        while(maxQueue.size() + 1 < minQueue.size()){
            //将小根堆取出最小值，放入大根堆中
            maxQueue.add(minQueue.poll());
        }

        //5.取出中位数
        if(maxQueue.size() == minQueue.size()){
            return (maxQueue.peek() + minQueue.peek()) / 2;
        }else if(maxQueue.size() < minQueue.size()){
            return minQueue.peek();
        }else {
            return maxQueue.peek();
        }
    }

    public static void main(String[] args) {



    }
}
