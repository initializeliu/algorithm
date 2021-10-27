package com.initialize.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author initialize liu
 * @date 2021/10/11
 * @apiNote
 */
public class HashAndTree {
    public static void main(String[] args) {

        //Hash   ： 默认时间复杂度为常数级别
        HashSet<Integer> hashSet1 = new HashSet<>();
        hashSet1.add(3);
        System.out.println(hashSet1.contains(3));
        hashSet1.remove(3);
        System.out.println(hashSet1.contains(3));

        HashMap<Integer, String> mapTest = new HashMap<>();
        mapTest.put(1, "zuo");
        mapTest.put(1, "cheng");//覆盖
        mapTest.put(2, "2");

        System.out.println(mapTest.containsKey(1));
        System.out.println(mapTest.get(1));
        System.out.println(mapTest.get(4));

        mapTest.remove(2);
        System.out.println(mapTest.get(2));

        //Node node1 = new Node(1);



        //Tree
        TreeSet<String> treeSet = new TreeSet<>();

        System.out.println("==========================>");

        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        treeMap1.put(7, "I'm 7");
        treeMap1.put(5, "I'm 5");
        treeMap1.put(4, "I'm 4");
        treeMap1.put(3, "I'm 3");
        treeMap1.put(9, "I'm 9");
        treeMap1.put(2, "I'm 2");

        System.out.println(treeMap1.containsKey(5));;
        System.out.println(treeMap1.get(5));
        System.out.println(treeMap1.firstKey() + ", min");
        System.out.println(treeMap1.lastKey() + ", max");
        System.out.println(treeMap1.floorKey(8) + " <= 8 最接近8");
        System.out.println(treeMap1.ceilingKey(8) + " >= 8 最接近8");



    }
}
