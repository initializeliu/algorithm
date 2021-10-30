package com.initialize.one.list;

import java.util.HashMap;

/**
 * 拷贝NodeList带有随机节点
 * @author initialize liu
 * @date 2021/10/15
 * @apiNote
 */
public class CopyNodeList {
    public static void main(String[] args) {

        Node1 node1 = new Node1(8);
        Node1 node2 = new Node1(3);
        Node1 node3 = new Node1(2);
        Node1 node4 = new Node1(5);
        Node1 node5 = new Node1(9);
        Node1 node6 = new Node1(22);
        Node1 node7 = new Node1(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;

        node1.rand = node4;
        node2.rand = node7;
        node3.rand = node3;
        node4.rand = null;
        node5.rand = node2;
        node6.rand = null;
        node7.rand = node1;


        Node1 head = node1;

        //head = copyListWithRand1(head);
        //head = copyListWithRand2(head);
        //head = copyListWithRand1_(head);
        head = copyListWithRand2_(head);

        System.out.println("[" + head + "] copy successfully!");
    }
    public static Node1 copyListWithRand2_(Node1 head){
        if(head == null){
            return null;
        }
        Node1 cur = head;
        //1. 复制本节点在该节点后一个位置
        Node1 next = null;
        while(cur != null){
            next = cur.next;
            cur.next = new Node1(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        //2. 处理复制节点的rand
        Node1 copCur = null;
        while (cur != null){
            next = cur.next.next;
            copCur = cur.next;
            copCur.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }
        Node1 res = head.next;
        copCur = null;
        cur = head;
        //3. split
        while (cur != null){
            next = cur.next.next;
            if(copCur != null){
                copCur.next = cur.next;
            }
            copCur = cur.next;
            cur.next = next;
            cur = next;
        }
        return res;
    }

    public static Node1 copyListWithRand1_(Node1 head){
        HashMap<Node1, Node1> map = new HashMap<>();
        //1. 存储到HashMap
        Node1 cur = head;
        while (cur != null){
            map.put(cur, new Node1(cur.value));
            cur = cur.next;
        }
        cur = head;
        //2. 处理复制Node的next和rand
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    //使用HashMap辅助NodeList的复制
    public static Node1 copyListWithRand1(Node1 head){
        HashMap<Node1, Node1> map = new HashMap<>();
        Node1 cur = head;
        //1.将Node存储到HashMap(Node, Node`)
        while(cur != null){
            map.put(cur, new Node1(cur.value));
            cur = cur.next;
        }
        cur = head;
        //2.设置新节点的next节点和rand节点
        while(cur != null){
            //cur 老节点
            //map.get(cur) 新节点
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    //不使用HashMap复制NodeList
    public static Node1 copyListWithRand2(Node1 head){
        if(head == null){
            return null;
        }
        Node1 cur = head;
        Node1 next = null;
        //1. copy node and link to every node
        // 1 -> 2
        // 1 -> 1` -> 2
        while(cur != null){
            next = cur.next;
            cur.next = new Node1(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node1 curCopy = null;
        //2. set copy node rand
        // 1 -> 1` -> 2 -> 2`
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node1 res = head.next;
        cur = head;
        //3. split
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null? next.next : null;
            cur = next;
        }
        return res;
    }
}
