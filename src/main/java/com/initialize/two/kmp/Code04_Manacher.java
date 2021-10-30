package com.initialize.two.kmp;

/**
 * Manacher算法解决的问题
 *
 * 字符串str中，最长回文子串的长度如何求解
 * 如何做到时间复杂度O(N)完成？
 * @author initialize liu
 * @date 2021/10/29
 * @apiNote
 */
public class Code04_Manacher {

    //伪代码
    //public static int[] manacher(String s){
    //
    //    1221 --> #1#2#2#1#
    //    s -> 处理串 str
    //    char[] str;
    //
    //    int[] pArr = new int[str.lenth];  //每个位置回文长度
    //    int R = ?;
    //    int C = ?;
    //
    //    for(int i = 0; i < str.length; i++){
    //        if(i在R外部){
    //            从i开始往两边暴力扩; R变大
    //        }else{
    //            if(i` 回文区域彻底在L...R内){
    //                pArr[i] = 某个O(1)表达式;
    //            }else if(i` 回文区域有一部分在L...R外){
    //                pArr[i] = 某个O(1)表达式;
    //            }else{  //i`回文区域和L...R的左边界压线
    //                从R之外的字符开始，往外扩张，然后确定pArr[i]的答案
    //                第一步扩失败了，R不变
    //                        否则，R变大
    //            }
    //        }
    //    }
    //    return null;
    //}

    //字符串结构处理
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i = 0; i != res.length; i++){
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
    public static int maxLcpsLength(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = manacherString(s); // 1221 -> #1#2#2#1#
        int[] pArr = new int[str.length]; //回文半径数组
        int C = -1;     //中心
        int R = -1;     //回文右边界的再往右一个位置 最右的有效区是R-1位置
        int max = Integer.MIN_VALUE;    //扩展出来的最大值
        for(int i = 0; i != str.length; i++){//每一个位置都
            //i至少的回文区域，先给pArr[i]
            //pArr[i] = pArr[2 * C - i]     i`的回文半径在C的回文范围内，str[i+pArr[i]] != str[i - pArr[i]]恒成立
            //pArr[i] = R - i   i`的回文半径在有部分在C的回文范围外，str[i+pArr[i]] != str[i - pArr[i]]恒成立
            //pArr[i] = pArr[2 * C - i] == R - i    i`的回文半径与R - i相等，i`的回文半径与C的回文范围左边境重合，
            //      如果str[i + pArr[i]] == str[i - pArr[i]]，更新pArr[i]++,  最后更新C = i; R = i + pArr[i]
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;

            while(i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }



}
