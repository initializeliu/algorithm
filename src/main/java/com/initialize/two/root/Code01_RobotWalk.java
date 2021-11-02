package com.initialize.two.root;

/**
 * 有N(>1)个正整数，从S位置出发到E位置结束，只能走K步，
 * 请问一共有多少种走法？
 *
 * 暴力递归优化
 *
 * try(暴力递归)  -->  记忆化搜索动态优化  -->  严格表结构动态优化
 * @author initialize liu
 * @date 2021/11/1
 * @apiNote
 */
public class Code01_RobotWalk {

    public static int walkWays1(int N, int E, int S, int K){
        return f1(N, E, K, S);
    }

    //一共是1～N这么多位置，固定参数
    //最终的目标是E 固定参数
    //还剩rest步需要走
    //当前在cur位置
    //返回方法数
    //时间复杂度O(pow(2,K))
    public static int f1(int N, int E, int rest, int cur){
        if(rest == 0){//basecase
            return cur == E ? 1 : 0;
        }
        if(cur == 1){//如果当前位置是1，下一个位置只能是2
            return f1(N, E, rest - 1, 2);
        }
        if(cur == N){//如果当前位置是N,下一个位置只能是N-1
            return f1(N, E, rest - 1, N - 1);
        }
        //机器人来到中间位置
        return f1(N, E, rest - 1, cur - 1) + f1(N, E, rest - 1, cur + 1);
    }

    public static int walkWays2(int N, int E, int S, int K){
        int[][] dp = new int[K+1][N+1];
        for(int i = 0; i <= K; i++){
            for(int j = 0; j <= N; j++){
                dp[i][j] = -1;
            }
        }
        return f2(N, E, K, S, dp);
    }
    //记忆话搜索版本
    //添加缓存，解决所有可变参数的组合
    //时间复杂度O(K * N)
    public static int f2(int N, int E, int rest, int cur, int[][] dp){
       if(dp[rest][cur] != -1){
           return dp[rest][cur];    //之前算过的位置不会重复计算
       }
        if(rest == 0){//basecase
            dp[rest][cur] = cur == E ? 1 : 0;
            return dp[rest][cur];
        }
        if(cur == 1){//如果当前位置是1，下一个位置只能是2
            dp[rest][cur] =  f2(N, E, rest - 1, 2, dp);
        }else if(cur == N){//如果当前位置是N,下一个位置只能是N-1
            dp[rest][cur] =  f2(N, E, rest - 1, N - 1, dp);
        }else {
            dp[rest][cur] = f2(N, E, rest - 1, cur - 1, dp) + f2(N, E, rest - 1, cur + 1, dp);
        }
        //机器人来到中间位置
        return dp[rest][cur];
    }

    //严格表结构动态优化
    //时间复杂度O(K * N)
    /*
                    P
          | 0 1 2 3 4 5
        ---------------
        0 | x 0 0 0 1 0
        1 | x 0 0 1 0 1
        2 | x 0 1 0 2 0
        3 | x 1 0 3 0 2
        4 | x 0 4 0 5 0
        k       M
     */
    public static int dpWay(int N, int P, int M, int K){
        int[][] dp = new int[K + 1][N + 1];//dp[...][0]废了用不了
        dp[0][P] = 1;
        for(int rest = 1; rest <= K; rest++){
            for(int cur = 1; cur <= N; cur++){
                if(cur == 1){
                    dp[rest][cur] = dp[rest - 1][2];
                }else if(cur == N){
                    dp[rest][cur] = dp[rest - 1][N - 1];
                }else{
                    dp[rest][cur] = dp[rest - 1][cur - 1] + dp[rest - 1][cur + 1];
                }
            }
        }
        return dp[K][P];
    }


    public static void main(String[] args) {
        System.out.println(walkWays1(10, 6, 3, 9));
    }
}
