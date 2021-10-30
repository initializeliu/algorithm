package com.initialize.one.recursion;

import java.util.List;

/**
 * 打印一个字符串的所有子串，包括空字符串
 * @author initialize liu
 * @date 2021/10/25
 * @apiNote
 */
public class Code02_PrintAllSubsquences {

    //当前来到i位置，要和不要，走两条路
    //之前的选择，所形成的结果，是str
    public static void process(char[] str, int i){
        if(i == str.length){
            System.out.println(String.valueOf(str));
            return;
        }
        process(str, i + 1);    //str[i]中的数据没有发生变化，代表要走
        //利用临时变量存储str[i],当前流程处理完毕后，从新赋值给str[i]
        char tmp = str[i];
        str[i] = 0;     //将str[i]值为空，最后打印的时候不打印
        process(str, i + 1);
        str[i] = tmp;   //在将临时变量的值赋值给str[i],方便后序遍历
    }

    //res:在str中[0,i-1]字符，存储需要打印的所有字符
    public static void process(char[] str, int i, List<Character> res){
        if(i == str.length){
            printList(res); //打印res
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);    //将str[i]添加到resKeep,表示要走str[i]字符
        process(str, i + 1, resKeep);
        List<Character> resNoInclude = copyList(res);   //str[i]不需要打印，
        process(str, i + 1, resNoInclude);
    }
    public static void printList(List<Character> res){
        // print res
    }
    public static List<Character> copyList(List<Character> list){
        return null;
    }
}
