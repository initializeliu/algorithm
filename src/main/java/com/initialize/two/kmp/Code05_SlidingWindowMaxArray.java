package com.initialize.two.kmp;

import java.util.LinkedList;

/**
 * 由一个代表题目，引出一种结构
 *
 * 有一个整形数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置。
 * 例如，数组为[4,3,5,4,3,3,6,7],窗口大小为3时：
 * [4 3 5] 4 3 3 6 7
 * 4 [3 5 4] 3 3 6 7
 * 4 3 [5 4 3] 3 6 7
 * 4 3 5 [4 3 3] 6 7
 * 4 3 5 4 [3 3 6] 7
 * 4 3 5 4 3 [3 6 7]
 * 窗口中最大值为5，窗口中最大值为5，窗口中最大值为5，窗口中最大值为4，窗口中最小值为6
 * 窗口中最大值为7
 *
 * 如果数组长度为n, 窗口大小为w, 则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数，输入：整形数组arr,窗口大小为w。
 * 输出：一个长度为n - w + 1的数组res,res[i]表示每一种窗口状态下的最大值。
 * 以本题为例：返回{5,5,5,4,6,7}
 * @author initialize liu
 * @date 2021/10/30
 * @apiNote
 */
public class Code05_SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w){
        if(arr == null || w < 1 || arr.length < w){
            return null;
        }
        //下标 值 大 -> 小
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){    //窗口的R（右边界）
            // i -> arr[i]
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() == i - w){  // i - w 过期的下标
                qmax.pollFirst();
            }
            if(i >= w - 1){//窗口形成了
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
