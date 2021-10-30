package com.initialize.two.kmp;

/**
 * 岛问题
 * 题目
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上，下，左，右四个位置相连，
 * 如果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中有多少个岛？
 *
 * 例如
 * 0 1 0 1 0 1
 * 1 1 1 1 0 1
 * 1 0 0 0 1 1
 * 0 0 1 0 0 0
 *
 * 有3个岛
 *
 * 进阶
 * 如何设计一个并行算法解决这个问题
 * @author initialize liu
 * @date 2021/10/28
 * @apiNote
 */
public class Code01_Islands {

    public static int landSum(int[][] m){
        if(m == null || m.length == 0){
            return 0;
        }
        int N = m.length;
        int M = m[0].length;

        int res = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(m[i][j] == 1){
                    res ++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    //感染： 将相邻的1变为2
    public static void infect(int[][] m, int i, int j, int N, int M){
        if(i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1){
            return;
        }
        //i,j没越界，并且当前位置值是1
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);//下
        infect(m, i - 1, j, N, M);//上
        infect(m, i, j + 1, N, M);//右
        infect(m, i, j - 1, N, M);//左
    }

    public static void main(String[] args) {

        int[][] m = new int[][]{
                {0, 0, 1, 0, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 1},
                {1, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 1, 0}
        };
        System.out.println(landSum(m));
    }
}
