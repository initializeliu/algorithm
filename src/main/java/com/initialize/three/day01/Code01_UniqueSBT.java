package com.initialize.three.day01;

/**
 * 有非负整数N个节点，能形成多少二叉树结构？
 *
 * 解答：
 * 左子树有i个节点，右子树有N - i - 1个节点
 *
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code01_UniqueSBT {

    public static int process(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }

        int res = 0;
        for(int leftNum = 0; leftNum <= n - 1; leftNum++){
            int leftWays = process(leftNum);    // 左子树有多少种方式
            int rightWays = process(n - 1 - leftNum);   //右子树有多少种方式
            res += leftWays * rightWays;   //总的方式，一共有多少种
        }
        return res;
    }

    //动态规划
    public static int numTrees(int n){
        if(n < 2){
            return 1;
        }
        int[] dp = new int[n + 1];  //n个节点和对应的组合方式种类
        dp[0] = 1;
        for(int i = 1; i < n + 1; i++){
            for(int j = 0; j <= i - 1; j++){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
