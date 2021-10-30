package com.initialize.one.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法的在笔试时的解题套路
 * 1.实现一个不依赖贪心策略的解法X，可以用最暴力的尝试
 * 2.脑补出贪心策略A，贪心策略B，贪心策略C
 * 3.用解法X和对数器，去验证每一个贪心策略，用实验的方式得知哪一个贪心策略正确
 * 4.不要去纠结贪心策略的证明
 *
 * 根据字符数组，得到字符数组拼接后的最小字典序？
 * 先对字符数组进行排序，字典序考前的放在数组的前面。
 *
 * a.b <= b.a
 * b.c <= c.b
 * ==>
 * a.c <= c.a
 * 排序结果要满足，可传递性。
 * [.... a, b, c, ....]
 * b长度 = b.length   => N(b) = N的b次方
 * N为进制
 * a * N(b) + b <= b * N(a) + a    ----> a * N(b) <= b * N(a) + a - b  ---> N(b) <= b * (N(a) - 1) / a + 1
 * b * N(c) + c <= c * N(b) + b    ----> b * N(c) + c - b <= c * N(b) ---> b * (N(c) - 1) / c + 1 <= N(b)
 * b * (N(c) - 1) / c + 1 <= b * (N(a) - 1) / a + 1  --->   (N(c) - 1) / c <= (N(a) - 1) / a   ---> a * N(c) - a <= c * N(a) - c
 * a * N(c) + c <= c * N(a) + a
 * @author initialize liu
 * @date 2021/10/21
 * @apiNote
 */
public class Code02_LowestLexicography {
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);    //比较a+b和b+a的字典序
        }
    }
    public static String lowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        //直接按照strs进行拼接
        for(int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }
}
