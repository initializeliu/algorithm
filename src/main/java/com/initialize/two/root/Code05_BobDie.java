package com.initialize.two.root;

/**
 * @author initialize liu
 * @date 2021/11/1
 * @apiNote
 */
public class Code05_BobDie {

    //N*M的区域，Bob从（row,col)位置出发，走rest步之后，获得的生存方法数
    public static String bob1(int N, int M, int i, int j, int K){
        long all = (long)Math.pow(4, K);
        long live = process(N, M, i, j, K);
        long gcd = gcd(all, live);
        return String.valueOf((live / gcd) + "/" + (all / gcd));
    }

    //N * M的区域，Bob从(row,col)位置出发，走rest步之后，获得的生存方法树
    public static long process(int N, int M, int row, int col, int rest){
        if(row < 0 || row == N || col < 0 || col == M){
            return 0;
        }
        //row,col没越界
        if(rest == 0){
            return 1;
        }
        //还没有走完，row,col也没越界
        long live = process(N, M, row - 1, col, rest - 1);
        live += process(N, M, row + 1, col, rest - 1);
        live += process(N, M, row, col - 1, rest - 1);
        live += process(N, M, row, col + 1, rest - 1);
        return live;
    }

    public static long gcd(long m, long n){
        return n == 0? m : gcd(n, m % n);
    }

}
