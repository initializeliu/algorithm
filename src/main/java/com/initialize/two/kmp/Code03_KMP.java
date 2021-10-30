package com.initialize.two.kmp;

/**
 * 利用KMP判断大字符串中，是否存在小字符串
 * @author initialize liu
 * @date 2021/10/28
 * @apiNote
 */
public class Code03_KMP {

    public static int getIndexOf(String s, String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0; //str1比对的位置
        int i2 = 0; //str2比对的位置
        int[] next = getNextArray(str2); //0(M)   获取str2字符串中每个字符之前的字符串，前缀和后缀相等的最大数值，(第一个字符默认-1)
        //O(N)
        while(i1 < str1.length && i2 < str2.length){
            if(str1[i1] == str2[i2]){
                i1++;
                i2++;
            }else if(next[i2] == -1) { //i == 0 str2中对比的位置已经无法往前跳
                i1++;
            }else{
                i2 = next[i2];
            }
        }
        //i1越界 或者 i2越界了
        return i2 == str2.length ? i1 - i2 : -1;
    }

    //返回指定字符出每个位置的字符之前的字符串，前缀和后缀相同的最大字符数
    public static int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;   //默认第一个位置为-1
        next[1] = 0;    //第二个位置为0
        int i = 2;  //next数组的位置
        int cn = 0; //前缀的下一个字符位置，现在使用的位置
        while(i < next.length){
            if(ms[i - 1] == ms[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){   //当前跳到cn位置
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }

}
