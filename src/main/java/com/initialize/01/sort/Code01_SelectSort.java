package com.initialize.sort;

import java.util.Arrays;

import com.sun.deploy.util.ArrayUtil;

/**
 * 选择排序
 * 做不到稳定性
 * @author initialize liu
 * @date 2021/10/5
 * @apiNote
 */
public class Code01_SelectSort {

    public static void selectSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        //从i~n-1中每次选出最小值，与i索引的值进行交换
        for(int i = 0; i < arr.length - 1; i++){
            //默认i索引为i~n-1中的最小值
            int minIndex = i;
            //比较i和i+1~n-1中的最小值，索引赋值给minIndex
            for(int j = i + 1; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex]? j : minIndex;
            }
            if(i != minIndex){
                swap(arr, i, minIndex);
            }
        }
    }
    //指定索引数据交换
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{9, 2, 3, 8, 23};

        selectSort(arr);

        System.out.println(Arrays.toString(arr));


    }
}
