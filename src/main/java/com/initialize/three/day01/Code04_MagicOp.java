package com.initialize.three.day01;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给一个包含n个整数元素的集合a,一个包含
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code04_MagicOp {

    //请保证arr1无重复值，arr2中无重复值，且arr1和arr2肯定有数字
    public static int maxOps(int[] arr1, int[] arr2){
        double sum1 = 0;//数组arr1中的平均值
        for(int i = 0; i < arr1.length; i++){
            sum1 += (double) arr1[i];
        }
        double sum2 = 0;
        for(int i = 0; i < arr2.length; i++){
            sum2 += (double)arr2[i];
        }
        //平均值相等无法操作
        if(avg(sum1, arr1.length) == avg(sum2, arr2.length)){
            return 0;
        }
        //平均值不想等
        int[] arrMore = null;
        int[] arrLess = null;
        double sumMore = 0;
        double sumLess = 0;
        if(avg(sum1, arr1.length) > avg(sum2, arr2.length)){
            arrMore = arr1;
            sumMore = sum1;
            arrLess = arr2;
            sumLess = sum2;
        }else{
            arrMore = arr2;
            sumMore = sum2;
            arrLess = arr1;
            sumLess = sum1;
        }
        Arrays.sort(arrMore);//平均值较大集合排序
        HashSet<Integer> setLess = new HashSet<>();//平均值小的集合中的元素
        for(int num : arrLess){
            setLess.add(num);
        }
        int moreSize = arrMore.length;//平均值大的集合还剩几个数
        int lessSize = arrLess.length;//平均值小的集合还剩几个数
        int ops = 0;//操作了多少次
        for(int i = 0; i < arrMore.length; i++){//小 -> 大
            double cur = (double) arrMore[i];
            //小于平均值大集合的平均值，大于平均值较小集合的平均值，在平均值较小集合中不存在
            if(cur < avg(sumMore, moreSize) && cur > avg(sumLess, lessSize)
                    && !setLess.contains(cur)){
                sumMore -= cur;
                moreSize--;
                sumLess += cur;
                lessSize++;
                setLess.add(arrMore[i]);
                ops++;
            }
        }
        return ops;
    }

    public static double avg(double sum, int size){
        return sum / (double)size;
    }

}
