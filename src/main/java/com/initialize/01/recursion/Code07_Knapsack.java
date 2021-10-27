package com.initialize.recursion;

/**
 * 给定两个长度都为N的数组weights和values, weights[i]和values[i]分别代表
 * i号物品的重量和价值。给定一个正数bag,表示一个载重bag的袋子，你装的物品不能超过这个重量，返回你能装下最多的价值是多少？
 * @author initialize liu
 * @date 2021/10/26
 * @apiNote
 */
public class Code07_Knapsack {

    // i... 的货物自由选择，形成的最大价值返回
    //重要永远不要超过bag
    //之前做的决定，所达到的重量，alreadWeight
    public static int process1(int[] weights, int[] values,
                               int i, int alreadyWeight, int bag){
        if(alreadyWeight > bag){
            return 0;
        }
        if(i == weights.length){
            return 0;
        }
        return Math.max(
                process1(weights, values, i + 1, alreadyWeight, bag),
                values[i] + process1(weights, values, i + 1,
                        alreadyWeight + weights[i], bag));
    }

    public static int process2(int[] weights, int[] values,
                               int i, int alreadyWeight, int alreadyValue, int bag){
        if(alreadyWeight > bag){
            return 0;
        }
        if(i == values.length){
            return alreadyValue;
        }
        return Math.max(process2(weights, values, i + 1, alreadyWeight, alreadyValue, bag),
                process2(weights, values, i + 1, alreadyWeight + weights[i],
                        alreadyValue + values[i], bag));
    }
}
