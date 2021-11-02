package com.initialize.three.day01;

/**
 * 假设s和m初始化，s="a";m=s
 * 再定义两种操作，第一种操作：
 * m = s
 * s = s + s
 *
 * 第二种操作：
 * s = s + m;
 *
 * 求最小的操作步骤树，可以将s拼接到长度等于n
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code12_SplitbySM {

    //请保证n不是质数
    //返回：
    //1.所有因子的和，当时因子不包括1
    //2.所有因子的个数，但是因子不包括1
    public static int[] divsSumAndCount(int n){
        int sum = 0;
        int count = 0;
        for(int i = 2; i <= Math.pow(n, 0.5); i++){
            while(n % i == 0){
                sum += i;
                count++;
                n /= i;
            }
        }
        return new int[]{sum, count};
    }
    public static int minOps(int n){
        if(n < 2){
            return 0;
        }
        if(isPirm(n)){
            return n - 1;
        }
        //n不是质数
        int[] divSumAndCount = divsSumAndCount(n);
        return divSumAndCount[0] - divSumAndCount[1];
    }

    public static boolean isPirm(int n){
        for(int i = 2; i <= Math.pow(n, 0.5); i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
