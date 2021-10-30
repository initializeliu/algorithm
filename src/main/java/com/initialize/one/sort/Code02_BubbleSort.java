package com.initialize.one.sort;

import java.util.Arrays;

/**
 * 冒泡排序法
 * 有稳定性
 * @author initialize liu
 * @date 2021/10/5
 * @apiNote
 */
public class Code02_BubbleSort {

    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        //e = (n-1) -> 1 :每次循环数据交换后，e索引位置的值为最大值
        for(int e = arr.length - 1; e > 0; e--){
            // i = 0 -> (e-1) : 每次比较i和i+1索引位置的数据大小，
            for(int i = 0; i < e; i++){
                if(arr[i] > arr[i + 1]){
                    swap(arr, i, i+1);
                }
            }

        }

    }

    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {

        int[] arr = new int[]{5, 32, 9, 48, 3, 6};

        bubbleSort(arr);

        System.out.println(Arrays.toString(arr));

    }
}
