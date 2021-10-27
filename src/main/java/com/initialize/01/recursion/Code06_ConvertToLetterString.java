package com.initialize.recursion;

import java.util.Arrays;

/**
 * 规定1和A对应，2和B对应，3和C对应。。。
 * 那么一个数字字符串比如"111"，就可以转化为"AAA"，"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * @author initialize liu
 * @date 2021/10/26
 * @apiNote
 */
public class Code06_ConvertToLetterString {

    //i之前的位置，如何转化已经做过决定了
    //i...有多少种转化的结果
    public static int process(char[] chs, int i){
        if(i == chs.length){
            return 1;
        }
        if(chs[i] == '0'){
            return 0;
        }
        if(chs[i] == '1'){
            //i自己作为单独的部分，后序有多少种方法
            int res = process(chs, i + 1);
            if(i + 1 < chs.length){
                //(i和i+1)作为单独的部分，后续有多少种方法
                res += process(chs, i + 2);
            }
            return res;
        }
        if(chs[i] == '2'){
            //i自己作为单独的部分，后续有多少种方法
            int res = process(chs, i + 1);
            //(i和i+1)作为单独的部分并且没有超过26，后续有多少种方法
            if(i + 1 < chs.length && (chs[i + 1] >= '0' && chs[i + 1] <= '6')){
                res += process(chs, i + 2);
            }
            return res;
        }
        return process(chs, i + 1);
    }
    public static int number(String str){
        return process(str.toCharArray(), 0);
    }

    public static void main(String[] args) {
        System.out.println(number("1111"));
    }
}
