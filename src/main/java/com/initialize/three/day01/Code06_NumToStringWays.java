package com.initialize.three.day01;

import jdk.nashorn.internal.ir.ReturnNode;

/**
 * 将给定的数转haunch为字符串，原则如下：1对应a,2对应b,......26对应z,
 * 12258可以转化为"abbeh","aveh","abyh","lbeh","lyh"个数为5。
 * 编写一个函数，给出可以转换的不同字符串的个数。
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code06_NumToStringWays {

    //0..index-1已经转换完毕，并且转换正确
    //str[index...]能转出多少种有效字符串表达
    public static int process(char[] str, int index){
        if(index == str.length){
            return 1;
        }
        //index及其后续是还有数字字符的
        //0.....
        if(str[index] == '0'){//开头为0
            return 0;
        }
        //index及其后续是还有数字字符的，且不以0开头，以1～9开头
        int res = process(str, index + 1);//做了一个决定，就让str[index]自己作为一部分
        if(index == str.length - 1){//除了index之外，后续没有字符串了
            return res;
        }
        //index + 1依然没越界
        //index和index + 1共同构成一个部分 < 27
        if(((str[index] - '0') * 10 + str[index + 1] - '0') < 27){
            res += process(str, index + 2);
        }
        return res;
    }

    //还能简化
    public static int dpWays(int num){
        if(num < 1){
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        //dp[i]含义：从i位置到末尾一共有多少种转换方式
        dp[N] = 1;
        dp[N - 1] = str[N - 1] == '0' ? 0 : 1;//最后一个字符可以转换的种类
        for(int i = N - 2; i >= 0; i--){
            if(str[i] == '0'){
                dp[i] = 0;
            }else{
                //将i位置的字符转换成一个字母dp[i] = dp[i + 1]
                //将i位置和i+1位置转换成一个字母dp[i] = dp[i + 2]
                dp[i] = dp[i + 1]
                        + (((str[i] - '0') * 10 + str[i + 1] - '0') < 27 ?
                        dp[i + 2] : 0);
            }
        }
        return dp[0];
    }

}
