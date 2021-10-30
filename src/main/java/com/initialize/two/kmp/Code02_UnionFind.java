package com.initialize.two.kmp;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * @author initialize liu
 * @date 2021/10/28
 * @apiNote
 */
public class Code02_UnionFind {

    public static class Element<V>{
        public V value;
        public Element(V value){
            this.value = value;
        }
    }
    public static class UnionFindSet<V>{
        public HashMap<V, Element<V>> elementMap;   //元素V 对应Element
        public HashMap<Element<V>, Element<V>> fatherMap;   //元素V对应的父节点，没有父节点的默认是父节点为自己
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for(V value : list){
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);//
                fatherMap.put(element, element);//初始每个元素父元素为自己
                sizeMap.put(element, 1);//默认元素所在集合元素为1
            }
        }
        //查找给定节点的头节点
        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> path = new Stack<>();
            while(element != fatherMap.get(element)){
                path.push(element);
                element = fatherMap.get(element);//查找这个节点的父节点
            }
            //将element节点到头节点中间的父节点做扁平化处理，节约性能
            while(!path.isEmpty()){
                //将每个节点的父节点设置为头节点
                fatherMap.put(path.pop(), element);
            }
            return element;
        }
        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }
        public void union(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if(aF != bF){
                    //a和b没有相同的头节点
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    //将短的节点连，接到长连
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }
    }

}
