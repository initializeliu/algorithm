package com.initialize.one.recursion;

/**
 * 暴力递归就是尝试
 * 1.把问题转化为规模缩小了的同类问题的子问题
 * 2.有明确的不需要继续进行递归的条件（base case)
 * 3.有当得到了子问题的结果之后的决策过程
 * 4.不记录每一个子问题的解
 *
 * 汉诺塔问题
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 * @author initialize liu
 * @date 2021/10/24
 * @apiNote
 */
public class Code01_Hanoi {
    public static void hanoi(int n){
        if(n > 0){
            func(n, "左", "右", "中");
        }

    }
    public static void func(int i, String start, String end, String other){
        if(i == 1){
            System.out.println("Move 1 from " + start + " to " + end);
        }else{
            func(i - 1, start, other, end);
            System.out.println("Move " + i + " from " + start + " to " + end);
            func(i-1, other, end, start);
        }
    }
    public static void main(String[] args) {
        int n = 3;
        hanoi(n);
    }
}
