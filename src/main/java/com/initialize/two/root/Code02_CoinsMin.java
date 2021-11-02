package com.initialize.two.root;

/**
 * 有一些纸币，将它们的金额存放在数组中arr，
 * 从这些纸币中取出指定金额，但是要求纸币的张数最小？
 *
 * @author initialize liu
 * @date 2021/11/1
 * @apiNote
 */
public class Code02_CoinsMin {

    //保证arr中都是正数
    public static int minCoins1(int[] arr, int aim){
        return process(arr, 0, aim);
    }

    //暴力递归版本
    public static int process(int[] arr, int index, int rest){
        if(rest < 0){//需要处理的钱数<0,没法处理，无效解。
            return -1;
        }else if(rest == 0){//当前所选中的硬币正好满足要求
             return 0;
        }else if(index == arr.length){//rest > 0, 硬币已经使用完毕，仍然没有凑够指定钱数
            return -1;
        }

        //rest > 0 并且 也有硬币
        int p1 = process(arr, index + 1, rest);//不使用当前硬币
        int p2Next = process(arr, index + 1, rest - arr[index]);//使用当前硬币
        if(p1 == -1 && p2Next == -1){
            return -1;//两个都是无效解，有可能出现
        }else{
            if(p1 == -1){//可能性p1无效，返回可能性p2硬币数 + 1
                return p2Next + 1;
            }
            if(p2Next == -1){   //可能性p2无效，返回可能性p1
                return p1;
            }
            //选择使用硬币小的情况
            return Math.min(p1, p2Next);
        }
    }

    //记忆化搜索版本
    public static int minCoins2(int[] arr, int aim){
        int[][] dp = new int[arr.length + 1][aim + 1];
        for(int i = 0; i <= arr.length; i++){
            for(int j = 0; j <= aim; j++){
                dp[i][j] = -2;
            }
        }
        return process2(arr, 0, aim, dp);
    }
    //arr[index...]组成出rest这么多钱，最少的硬币数量返回
    public static int process2(int[] arr, int index, int rest, int[][] dp){
        if(rest < 0){//需要处理的钱数<0,没法处理，无效解。
            return -1;
        }
        if(dp[index][rest] != -2){
            return dp[index][rest];
        }
        if(rest == 0){//当前所选中的硬币正好满足要求
            dp[index][rest] = 0;
        }else if(index == arr.length){//rest > 0, 硬币已经使用完毕，仍然没有凑够指定钱数
            dp[index][rest] = -1;
        }else{
            //rest > 0 并且 也有硬币
            int p1 = process2(arr, index + 1, rest, dp);//不使用当前硬币
            int p2Next = process2(arr, index + 1, rest - arr[index], dp);//使用当前硬币
            if(p1 == -1 && p2Next == -1){
                dp[index][rest] = -1;//两个都是无效解，有可能出现
            }else{
                if(p1 == -1){//可能性p1无效，返回可能性p2硬币数 + 1
                    dp[index][rest] = p2Next + 1;
                }
                if(p2Next == -1){   //可能性p2无效，返回可能性p1
                    dp[index][rest] = p1;
                }
                //选择使用硬币小的情况
                dp[index][rest] = Math.min(p1, p2Next);
            }
        }
        return dp[index][rest];
    }

    //动态规划表
    //严格表动态规划
    //1.可变范围，一个可变参数一维表，两个参数二维表
    //2.标出要计算的终止位置
    //3.根据basecase找出，不需要计算就知道的位置
    //4.一开始就有的位置和一个普遍位置是如何依赖的
    //5.确定依次计算的顺序，
    public static int minCoins3(int[] arr, int aim){
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        for(int row = 0; row <= N; row++){
            dp[row][0] = 0;
        }
        for(int col = 1; col <= aim; col++){
            dp[N][col] = -1;
        }
        for(int index = N - 1; index >= 0; index--){
            for(int rest = 1; rest <= aim; rest++){
                int p1 = dp[index + 1][rest];
                int p2Next = -1;
                if(rest - arr[index] >= 0){
                    p2Next = dp[index + 1][rest - arr[index]];
                }
                if(p1 == -1 && p2Next == -1){
                    dp[index][rest] = -1;
                }else{
                    if(p1 == -1){
                        dp[index][rest] = p2Next + 1;
                    }
                    if(p2Next == -1){
                        dp[index][rest] = p1;
                    }
                    dp[index][rest] = Math.min(p1, p2Next + 1);
                }
            }
        }
        return dp[0][aim];
    }


    //for test
    public static int[] generateRandomArray(int len, int max){
        int[] arr = new int[(int)(Math.random() * len) + 1];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * max) + 1;

        }
        return arr;
    }


    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10000;
        for(int i = 0; i < testTime; i++){
            int[] arr = generateRandomArray(len, max);
            int aim = (int) (Math.random() * 3 * max) + max;
            if(minCoins1(arr, aim) != minCoins2(arr, aim)){
                System.out.println("ooops!");
                break;
            }
        }
    }

}
