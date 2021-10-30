package com.initialize.one.recursion;

import java.util.Stack;

/**
 * 给一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数，如何实现？
 * @author initialize liu
 * @date 2021/10/25
 * @apiNote
 */
public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        //获取栈中的栈底元素
        int i = f(stack);
        //如果栈中还有元素，继续递归
        reverse(stack);
        //因为每次取出的都是栈底元素，配合先出栈的后进栈就可以实现栈元素逆序
        stack.push(i);
    }
    //通过递归返回当前栈中的栈底元素
    public static int f(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
