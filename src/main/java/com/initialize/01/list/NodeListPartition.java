package com.initialize.list;

/**
 * 对NodeList链表按照指定的值进行分区[<pivot], [==pivot], [pivot<]
 * 要求：1.不使用额外空间。2.保证Node顺序
 * @author initialize liu
 * @date 2021/10/15
 * @apiNote
 */
public class NodeListPartition {
    public static void main(String[] args) {
        Node head = new Node(3);
        Node tail = head;

        Node node1 = new Node(4);
        tail.next = node1;
        tail = node1;

        Node node2 = new Node(5);
        tail.next = node2;
        tail = node2;

        Node node3 = new Node(4);
        tail.next = node3;
        tail = node3;

        Node node4 = new Node(3);
        tail.next = node4;
        tail = node4;

        Node node5 = new Node(3);
        tail.next = node5;
        tail = node5;

        //head = listPartition2(head, 4);
        head = listPartition2_(head, 4);

        System.out.println("[" + head + "] 完成分区");

    }
    public static Node listPartition2_(Node head, int pivot){
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;
        //分区
        while(head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        //分区合并
        //如果有小于区域
        if(sT != null){
            sT.next = eH;
            eT = eT == null? sT : eT;
        }
        //如果有小于或等于区域
        if(eT != null){
            eT.next = mH;
        }

        return sH != null? sH : (eH != null? eH : mH);
    }

    public static Node listPartition2(Node head, int pivot){
        Node sH = null;     // small head
        Node sT = null;     // small tail
        Node eH = null;     // equal head
        Node eT = null;     // equal tail
        Node mH = null;     // big head
        Node mT = null;     // big tail
        Node next = null;   // save next node

        // every node distributed to three lists
        while(head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // small and equal reconnect
        if(sT != null){ //如果有小于区域
            sT.next = eH;
            eT = eT == null? sT : eT;
        }
        //上面的if,不管跑了没有，et
        if(eT != null){ //如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null? sH : (eH != null ? eH : mH);
    }

}
