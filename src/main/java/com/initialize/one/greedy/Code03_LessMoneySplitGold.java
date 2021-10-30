package com.initialize.one.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
 *
 * 一群人想整分整块金条，怎么分最省铜板？
 * 例如，给定数组{10, 20, 30},代表一共三个人，整块金条长度为10+20+30=60。
 * 金条要分成10，20，30三个部分。如果先把长度60的金条分成10和50，花费60，
 * 再把长度50的金条分成20和30，花费50；一共花费110铜板。
 * 但是如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，
 * 花费30；一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 *
 * 反向思考，一整块金条一定会切成给定数组的小块。两块合并成一块需要的铜板为n+m
 * 怎么样合并最省钱？每次拿两个最小块的进行合并。
 * @author initialize liu
 * @date 2021/10/21
 * @apiNote
 */
public class Code03_LessMoneySplitGold {

    public static class MinheapComarator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++){
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while(pQ.size() > 1){   //最后合并成一个大块金条
            //每次取出两个最小的合并，合并后的块大小放入队列中。
            cur = pQ.poll() + pQ.poll();
            sum += cur;       // 花费铜板数
            pQ.add(cur);
        }
        return sum;
    }


    public static void main(String[] args) {

    }
}
