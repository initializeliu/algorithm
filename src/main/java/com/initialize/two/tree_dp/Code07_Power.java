package com.initialize.two.tree_dp;

/**
 * 判断一个int类型整数是不是2的n次幂和4的n次幂
 * @author initialize liu
 * @date 2021/10/31
 * @apiNote
 */
public class Code07_Power {

    public static boolean is2Power(int n){
        return (n & (n - 1)) == 0;
    }

    //是4的n次幂一定是2的n次幂
    public static boolean is4Power(int n){
        return (n & (n - 1)) == 0 & ((n & 0X5555555) != 0);
    }
}
