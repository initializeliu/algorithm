package com.initialize.three.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个数组arr,求差值为K的去重数字对。
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code03_SubvalueEqualk {

    //arr数组
    //k=4
    //{{1,5}, {4, 8}, {7, 4}}
    public static List<List<Integer>> allPair(int[]arr, int k){
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < arr.length; i++){
            set.add(arr[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(Integer cur : set){
            if(set.contains(cur + k)){
                res.add(Arrays.asList(cur, cur + k));
            }
        }
        return res;
    }
}
