package com.initialize.one.tree;

/**
 * 折纸：折n次，列出凹凸顺序
 * 折1次：凹
 * 折2次：凹，凹，凸
 * 折3次：凹，凹，凸，凹，凹，凸，凸
 *
 * 结论：二叉树处理，
 * 1.根节点为凹，二叉树的高度与折的次数相同
 * 2.保证每个节点的左子节点为凹，右子节点为凸。
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class PaperFolding {

    public static void printAllFolds(int N){
        printProcess(1, N, true);
    }
    //i 是节点的层数，N一共的层数，down == true 凹 down == false 凸
    public static void printProcess(int i, int N, boolean down){
        if(i > N){
            return;
        }
        printProcess(i + 1, N, true);
        System.out.print(down ? "凹 " : "凸 ");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFolds(5);
    }
}
