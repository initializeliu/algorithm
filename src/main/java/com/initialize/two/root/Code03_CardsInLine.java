package com.initialize.two.root;

/**
 * @author initialize liu
 * @date 2021/11/1
 * @apiNote
 */
public class Code03_CardsInLine {

    public static int win1(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(
                f(arr, 0, arr.length - 1),
                s(arr, 0 , arr.length - 1));
    }

    //先手函数
    //当前该你拿，arr[i...j]
    //返回你的最好分数
    public static int f(int[] arr, int i, int j){
        if(i == j){
            return arr[i];
        }
        //i...j
        //两种情况，一个是从左边开始拿，一个从右边开始拿。
        //返回两种情况的最好结果
        return Math.max(
                arr[i] + s(arr, i + 1, j),  //拿左边 + 后续情况
                arr[j] + s(arr, i, j - 1)   //拿右边 + 后续情况
        );
    }

    //后手函数
    //当前不该你拿，是对方在arr[i...j]范围上拿
    //返回你的最好分数
    public static int s(int[] arr, int i, int j){
        if(i == j){
            return 0;
        }
        //i .. j
        //后手情况，从左边拿，或从右边拿
        //站在后手的角度，目前数组中所剩的元素是固定的，
        //下次先手取到最小值，说明本次取到的是最大值。
        return Math.min(
                f(arr, i + 1, j),
                f(arr, i, j - 1));
    }

    /*
            arr = [2, 7, 9, 3, 5]

            f   0   1   2   3   4   j
            -------------------------
            0 | 2   7   14  23  35
            1 | x   7   9   16  21
            2 | x   x   9   9   12
            3 | x   x   x   3   5
            4 | x   x   x   x   5
            i

            s   0   1   2   3   4   j
            -------------------------
            0 | 0   2   7   14  21
            1 | x   0   7   9   12
            2 | x   x   0   3   5
            3 | x   x   x   0   3
            4 | x   x   x   x   0
            i
     */

    //动态规划
    public static int dp(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++){
            f[i][i] = arr[i];//basecase
        }
        int row = 0;
        int col = 1;
        //对角线开始位置row行col列
        while(col < arr.length){
            int i = row;
            int j = col;
            while(i < arr.length && j < arr.length){
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
                i++;
                j++;
            }
            col++;
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }
}
