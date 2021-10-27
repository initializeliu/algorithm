package com.initialize.sort;

import java.util.Arrays;

/**
 * 快速排序2.0
 * 做不到稳定性
 * @author initialize liu
 * @date 2021/10/6
 * @apiNote
 */
public class Code04_QuickSort {

    public static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // arr[L...R]排好序
    public static void quickSort(int[] arr, int L, int R){
        if(L < R){
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1); // < 区
            quickSort(arr, p[1] + 1, R); // > 区
        }
    }

    //这是一个处理arr[l...r]的函数
    //默认以arr[r]做划分，arr[r] -> p    <p   ==p   >p
    //返回等于区域（左边界，右边界），所以返回一个长度为2的数组res, res[0] res[1]
    public static int[] partition(int[] arr, int L, int R){
        int less = L - 1;   // 区右边界
        int more = R;   //区左边界
        while(L < more){    //L表示当前数的位置 arr[R]  -> 划分值
            if(arr[L] < arr[R]){    // 当前数 < 划分值
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]){    // 当前数 > 划分值
                swap(arr, --more, L);
            }else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }
    //交换
    public static void swap(int[] arr, int p1, int p2){
        int tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 4, 332, 17, 2, 41, 7};

        //quickSort(arr);
        quickSort1(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort1(int[] arr){
        if(arr == null && arr.length < 2){
            return;
        }
        quickSort1(arr, 0, arr.length - 1);
    }

    public static void quickSort1(int[] arr, int L, int R){
        if(L < R){
            //在[L,R]中任意选择一个数据与R索引数据交换
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);

            int[] mid = partition1(arr, L, R);

            quickSort1(arr, L, mid[0]); // < 区

            quickSort1(arr, mid[1], R); // > 区
        }
    }

    /**
     *
     * @param arr
     * @param L 进行遍历的索引
     * @param R 划分数组的中间值：存放在arr数组[L,R]范围的R索引位置上的数据，
     * @return
     */
    public static int[] partition1(int[] arr, int L, int R){
        int before = L - 1; // before :上一个比中间值小的数据在数组中的索引位置。
        int after = R;  // after :上一个比中间值大的数据在数组中的索引位置。

        while(L <  after){
            if(arr[L] > arr[R]){        // > 中间值
                swap(arr, L, --after);
            } else if (arr[L] < arr[R]) {   // < 中间值
                swap(arr, L++, ++before);
            } else{
                L++;
            }
        }
        swap(arr, after, R);

        return new int[]{before, after + 1};
    }

}
