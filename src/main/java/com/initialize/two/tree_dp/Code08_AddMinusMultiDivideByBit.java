package com.initialize.two.tree_dp;

/**
 * 位运算处理加减乘除
 * @author initialize liu
 * @date 2021/10/31
 * @apiNote
 */
public class Code08_AddMinusMultiDivideByBit {


    /*
        1 0 1 0 1 1
      + 0 1 0 1 1 1
      --------------
      ^ 1 1 1 1 0 0
  &<<1  0 0 0 1 1 0
  ------------------
      ^ 1 1 1 0 1 0
 &<<1   0 0 1 0 0 0
 -------------------
      ^ 1 1 0 0 1 0
 &<<1   0 1 0 0 0 0
 -------------------
      ^ 1 0 0 0 1 0
 &<<1   1 0 0 0 0 0
 -------------------
    ^   0 0 0 0 1 0
 &<<1 1 0 0 0 0 0 0
 -------------------
   ^  1 0 0 0 0 1 0
 &<<1 0 0 0 0 0 0 0    ==进位信息为0，终止
     */
    //如果，用户传入的参数，a+b就是溢出的，用户活该
    //加
    public static int add(int a, int b){
        int sum = a;
        while(b != 0){
            sum = a ^ b;        //无进位相加的结果
            b = (a & b) << 1;   //进位信息
            a = sum;
        }
        return sum;
    }

    public static int negNum(int n){
        return add(~n, 1);
    }
    //减
    public static int minus(int a, int b){
        return add(a, negNum(b));
    }

    //用户传入数据不能a*b溢出
    //乘
    public static int multi(int a, int b){
        int res = 0;
        while(b != 0){
            if((b & 1) != 0){//取当前b的最后一位
                res = add(res, a);
            }
            a <<= 1;    //a左移
            b >>>= 1;   //b无符号右移
        }
        return res;
    }


}
