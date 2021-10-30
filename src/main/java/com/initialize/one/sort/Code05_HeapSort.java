package com.initialize.one.sort;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * 堆排序
 * 做不到稳定性
 * @author initialize liu
 * @date 2021/10/6
 * @apiNote
 */
public class Code05_HeapSort {

    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        //将数组调整为大顶堆
        for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }

        System.out.println(Arrays.toString(arr));

        //for(int i = arr.length; i >= 0; i--){
        //    heapify(arr, i, arr.length);
        //}

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while(heapSize > 0){
            System.out.println(Arrays.toString(arr));
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //某个数现在处于index的位置，往上继续移动
    public static void heapInsert(int[] arr, int index){
        //如果子节点数据大于父节点数据，进行数据交换
        while(arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //某个数在index的位置，能否往下移动
    public static void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1;   //左节点的下标
        while(left < heapSize){//判断index是否存在左节点
            //左右两个子节点，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1]? left + 1 : left;

            //父节点和子节点比较，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;

            if(largest == index){
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }

    }

    public static void swap(int[] arr, int p1, int p2){
        int tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{9, 23, 33, 2, 6, 8, 12, 1};

        //heapSort(arr);
        heapSort1(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort1(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        //1. 将数组调整为最大堆
        for(int i = 0; i < arr.length; i++){
            heapInsert1(arr, i, arr.length);
        }
        //for(int i = arr.length - 1; i >= 0; i--){
        //    heapify1(arr, i, arr.length);
        //}

        //2. 排序
        swap(arr, 0, arr.length - 1);
        int size = arr.length - 1;
        while(size > 1) {
            //调整为最大堆
            heapify1(arr, 0, size);
            swap(arr, 0, --size);
        }

    }

    /**
     *
     * @param arr
     * @param index:当前正在处理的节点
     * @param size:需要处理的数组长度
     */
    public static void heapify1(int[] arr, int index, int size){
        int left = index * 2 + 1;   //左子节点

        while(left < size){//判断是否存在左子节点
            //左右子节点中的最大值
            int largest = left + 1 < size && arr[left] < arr[left + 1]? left + 1 : left;

            //子节点和父节点中的最大值
            largest = arr[index] < arr[largest]? largest : index;

            if(largest == index){
                return;
            }
            //子节点中存在大于父节点的值
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }



    }

    public static void heapInsert1(int[] arr, int index, int length){
        while(arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

}
