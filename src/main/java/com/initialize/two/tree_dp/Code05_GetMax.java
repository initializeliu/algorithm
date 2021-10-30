package com.initialize.two.tree_dp;

/**
 * 位运算的题目
 *
 * 之前介绍过一些，下面继续
 * 给定两个符号32位整数a和b,返回a和b中较大的。
 *
 * 要求：
 * 不用做任何比较判断
 * @author initialize liu
 * @date 2021/10/30
 * @apiNote
 */
public class Code05_GetMax {
    //请保证参数n,不是1就是0的情况下
    //1 -> 0
    //0 -> 1
    public static int flip(int n){
        return n ^ 1;
    }

    //n是非负数，返回1
    //n是负数，返回0
    public static int sign(int n){
        //提取符号位，先于1取与，再与1取反
        return flip( (n >> 31) & 1 );
    }

    public static int getMax1(int a, int b){
        int c = a - b;  //c>0 a>b， 存在数据溢出风险
        int scA = sign(c);  //a-b为非负，scA为1；a-b为负，scA为0
        int scB = flip(scA);    //scA为0，scB为1；scA为1，scB为0
        //只有一部分会成立
        return a * scA + b * scB;
    }

    public static int getMax2(int a, int b){
        int c = a - b; //有溢出风险
        int sa = sign(a);//a符号状态
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;// a和b符号不一样，为1，一样为0
        int sameSab = flip(difSab); // a和b的符号一样，为1；不一样为0
        int returnA = difSab * sa + sameSab * sc;   //
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

}
