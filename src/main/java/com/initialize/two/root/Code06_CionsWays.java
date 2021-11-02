package com.initialize.two.root;

/**
 * @author initialize liu
 * @date 2021/11/1
 * @apiNote
 */
public class Code06_CionsWays {

    //arr里都是正数，没有重复值，每一个值代表一种货币，每一种都可以用无限张
    //最终要找零钱数是aim
    //找零方法数返回
    public static int way1(int[] arr, int aim){
        return process(arr, 0, aim);
    }

    //可以自由使用arr[index....]所有的面值
    //需要搞定的钱数是rest
    //返回找零的方法数
    public static int process(int[] arr, int index, int rest){
        if(index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        //arr[index] 0张 1张。。。不要超过rest的钱数
        int ways = 0;
        for(int zhang = 0; arr[index] * zhang <= rest; zhang++){
            ways += process(arr, index + 1, rest - arr[index] * zhang);
        }
        return ways;
    }

    public static int way2(int[] arr, int aim){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for(int index = N - 1; index >= 0; index --){
            for(int rest = 0; rest <= aim; rest++){
                int ways = 0;
                for(int zhang = 0; arr[index] * zhang <= rest; zhang++ ){
                    ways += dp[index + 1][rest - arr[index] * zhang];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    //斜率优化
    //在填表的时候是否有枚举行为
    //枚举行为是否可以被临近行数据替代
    //尝试（从左向右，范围尝试）
    //      1.单可变参数纬度，最好是一个整数
    //      2.可变参数个数，越少越好
    public static int way3(int[] arr, int aim){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for(int index = N - 1; index >= 0; index --){
            for(int rest = 0; rest <= aim; rest++){
                dp[index][rest]  = dp[index+1][rest];
                if(rest - arr[index] >= 0){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }
}
