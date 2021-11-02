package com.initialize.three.day01;

import java.util.HashMap;

/**
 * 给定一个字符串类型的数组arr,求其中出现次数最多的前K个
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code13_TopKTimes {

    public static class Node{
        public String str;
        public int times;
        public  Node(String s, int t){
            str = s;
            times = t;
        }
    }

    public static class TopKRecord{
        private HashMap<String, Node> strNodeMap;
        private Node[] heap;
        private HashMap<Node, Integer> nodeIndexMap;
        private int index;

        public TopKRecord(int K){
            heap = new Node[K];
            index = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }

        public void add(String str){
            //当前str对应的节点对象
            Node curNode = null;
            //当前str对应的节点对象是否在堆上
            int preIndex = -1;
            if(!strNodeMap.containsKey(str)){//str第一次出现
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);//添加词频表
                nodeIndexMap.put(curNode, -1);//添加堆位置
            }else{//并非第一次出现
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);   //
            }

            if(preIndex == -1){//当前str对应的节点对象，词频增加之后，不在堆上
                if(index == heap.length){
                    //堆满
                    //当前节点>堆顶节点相比较, 交换
                    if(heap[0].times < curNode.times){
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, index);
                    }
                }else{
                    nodeIndexMap.put(curNode, index);
                    heap[index] = curNode;
                    heapInsert(index++);
                }
            }else{
                heapify(preIndex, index);
            }
        }

        private void heapInsert(int index){
            while(index != 0){
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                }else{
                    break;
                }
            }
        }
        private void heapify(int index, int heapSize){
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while(l < heapSize){
                if(heap[l].times < heap[index].times){
                    smallest = l;
                }
                if(r < heapSize && heap[r].times < heap[smallest].times){
                    smallest = r;
                }
                if(smallest != index){
                    swap(smallest, index);
                }else{
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }
        private void swap(int index1, int index2){
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

        public void printTopK(){
            System.out.println("Top: ");
            for(int i = 0; i != heap.length; i++){
                if(heap[i] == null){
                    break;
                }
                System.out.println("Str: " + heap[i].str);
                System.out.println("Times: " + heap[i].times);
            }
        }
    }




}
