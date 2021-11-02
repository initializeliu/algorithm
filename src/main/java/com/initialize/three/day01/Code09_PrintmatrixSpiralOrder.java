package com.initialize.three.day01;

/**
 * 用螺旋的方式打印矩阵，比如如下的矩阵
 * 0    1   2   3
 * 4    5   6   7
 * 8    9   10  11
 * 打印顺序为：0 1 2 3 7 11 10 9 8 4 5 6
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code09_PrintmatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR <= dR && tC <= dC){
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }
    public static void printEdge(int[][] m, int a, int b, int c, int d){
        if(a == c){
            for(int i = b; i <= d; i++){
                System.out.println(m[a][i] + " ");
            }
        }else if(b == d){
            for(int i = a; i <= c; i++){
                System.out.println(m[i][b] + " ");
            }
        }else {
            int curC = b;
            int curR = a;
            while (curC != d){
                System.out.println(m[a][curC] + " ");
                curC++;
            }
            while(curR != c){
                System.out.println(m[curR][d] + " ");
                curR++;
            }
            while(curC != b){
                System.out.println(m[c][curC] + " ");
                curC--;
            }
            while (curR != a){
                System.out.println(m[curR][b] + " ");
            }
        }
    }

}
