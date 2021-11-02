package com.initialize.three.day01;

/**
 *
 * 一个合法的括号匹配序列有以下定义：
 * 1.空串""是一个合法的括号匹配序列
 * 2.如果"X"和"Y"都是合法的括号匹配序列，"XY"也是一个合法的括号匹配序列
 * 3.如果"X"是一个合法的括号匹配序列，那么"(X)"也是一个合法的括号匹配序列
 * 4.每个合法的括号序列都可以由以上规则生成。
 *
 * 例如："", "()","()()","((()))"都是合法的括号序列
 * 对于一个合法的括号序列我们又有以下定义它的深度：
 * 1.空串""的深度是0
 * 2.如果字符串"X"的深度是小，字符串"Y"的深度是y,那么字符串"XY"的深度为max(x,y)
 * 3.如果"X"的深度是x，那么字符串"(X)"的深度是x+1
 * 例如："()()()"的深度是1，"((()))"的深度是3，
 * 现在给一个合法的括号序列，需要你计算出其深度。
 *
 *
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code05_ParenthesesDeep {

    public static int maxLenth(String s){
        if(s == null || s.equals("")){
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int pre = 0;
        int res = 0;
        for(int i = 1; i < str.length; i++){
            if(str[i] == ')'){//当前位置只有是右括号才能计算最大有效长度
                //i-1可能也是一个右括号，有自己匹配的有效长度
                pre = i - dp[i - 1] - 1;    //  与str[i]匹配的左括号的位置pre
                if(pre >= 0 && str[pre] == '('){
                    //str[i]匹配的位置如果是左括号则dp[i] = dp[i - 1] + 2
                    //如果str[i]匹配的位置str[pre]是左括号,且str[pre-1]是有效长度。
                    dp[i] = dp[i - 1] + 2 + (pre > 0? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
