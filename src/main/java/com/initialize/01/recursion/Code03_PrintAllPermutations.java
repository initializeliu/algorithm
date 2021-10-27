package com.initialize.recursion;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * 打印一个字符串的全部排列
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 * @author initialize liu
 * @date 2021/10/25
 * @apiNote
 */
public class Code03_PrintAllPermutations {

    //str[i<]范围上，所有的字符，都可以在i位置上，后续都去尝试
    //str[i>]范围上，是之前做的选择
    //请把所有的字符串形成的全排列，加入到res里去
    public static void process(char[] str, int i, ArrayList<String> res){
        if(i == str.length){
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26];  //默认false
        //从i开始及后面所有字符都可以放到i索引处
        for(int j = i; j < str.length; j++){
            if(!visit[str[j] - 'a']){//通过分支限定，i索引处的字符，不能重复
                visit[str[j] - 'a'] = true;     //true:表示字符已经出现过
                swap(str, i, j);    //交换i，j处的元素，形成新的排列顺序
                process(str, i + 1, res);
                swap(str, i , j);   //利用栈的临时变量记录i,j,回复到之前的顺序
            }
        }
    }
    public static void swap(char[] chs, int i, int j){
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }


}
