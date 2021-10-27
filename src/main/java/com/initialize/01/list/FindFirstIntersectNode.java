package com.initialize.list;

/**
 * 两个链表相交的一系列问题
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，
 * 请返回相交的第一个节点。如果不相交，返回null
 *
 * 要求：如果两个链表长度之和为N，时间复杂度请达到O(N),额外空间复杂度请达到O(1)。
 * @author initialize liu
 * @date 2021/10/15
 * @apiNote
 */
public class FindFirstIntersectNode {
    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);

        n1.next = n2;
        n2.next = n3;
        n3.next = n7;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;
        n10.next = null;

        Node head1 = n1;
        Node head2 = n4;

        //Node result = getIntersectNode(head1, head2);
        Node result = getIntersectNode_(head1, head2);

        System.out.println(">>>>>" + result.value);

    }
    //返回相交的节点
    public static Node getIntersectNode_(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        //获取两链表的入环节点
        Node loop1 = getLoopNode_(head1);
        Node loop2 = getLoopNode_(head2);

        if(loop1 == null && loop2 == null){
            //两个非环形结构
            //loop1,loop2都为空，都是线性结构，如果存在相交点，只肯能是"Y"型结构着一种。
            return noLoop_(head1, head2);
        }else if(loop1 != null && loop2 != null){
            //两个环形结构
            return bothLoop_(head1, loop1, head2, loop2);
        }
        //一个环形结构和一个非环形结构不可能存在相交情况。
        return null;
    }
    //两个环形结构的相交
    public static Node bothLoop_(Node head1, Node loop1, Node head2, Node loop2){
        //1.相交点不在环上（极端情况，相交点和入环切入点相同，看作相交点不在环上）
        if(loop1 == loop2){ //两个链表入环节点相等，说明相交点不在环上，将这类情况按照"Y"型结构处理
            Node n1 = head1;
            Node n2 = head2;
            int count = 0;//head1 -> loop1 与 head2 -> loop2 节点数差值
            //a.统计差值
            while(n1 != loop1){
                count++;
                n1 = n1.next;
            }
            while(n2 != loop2){
                count--;
                n2 = n2.next;
            }
            n1 = count > 0? head1 : head2;   // n1中存放较大的链表
            n2 = n1 == head1? head2 : head1;    //n2存放较小的链表
            //b.调整大链表指针，为了两个链表指针到末尾的偏移量相同
            while(count > 0){
                count--;
                n1 = n1.next;
            }
            //c.比较n1,n2找到相交点
            while(n1 != n2){
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        }else {//2.相交点在环上
            //head1从入环节点开始遍历，存在某个节点==loop2,则相交
            Node n3 = loop1.next;
            while(n3 != loop1){
                if(n3 == loop2){
                    return loop2;//loop1,loop2都是相交点
                }
                n3 = n3.next;
            }
        }
        //两个环形结构没有存在相交点
        return null;
    }

    //两个非环形结构相交，"Y"型结构
    public static Node noLoop_(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int count = 0;
        //1.统计head1,head2数量的差值
        while(cur1.next != null){
            cur1 = cur1.next;
            count++;
        }
        while(cur2.next != null){
            cur2 = cur2.next;
            count--;
        }
        //2.两个链表末尾节点不相同，一定不相交
        if(cur1 != cur2){
            return null;
        }

        cur1 = count > 0 ? head1 : head2; //cur1中存放的是较大的链表
        cur2 = cur1 == head1 ? head2 : head1;   //cur2中存放的是较小的链表
        //3.将较大链表指针的向后移动count个位置("Y"型结构导致相交点必然在head1的后head2.size个节点内)
        count = Math.abs(count);
        while(count > 0){
            cur1 = cur1.next;
        }
        //4.比较cur1,cur2,找到相交点
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //获取链表入环节点(链表中可能存在入环节点，可能不存在入环节点)
    //在不使用辅助数据结构的情况下，通过快慢指针完成。
    public static Node getLoopNode_(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        /*
        通过数学公示推导出：满指针n1和快指针n2，遍历节点：
        如果n1==n2不存在,则链表为线性结构，不存在闭环；
        如果n1==n2存在，则为链表存在循环结构，模型为"6"，
        将n2=heap后遍历n1,n2,当再次出现n1==n2,此时n1为入环节点
         */
        Node n1 = head.next;     //慢指针
        Node n2 = head.next.next;    //快指针

        while(n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;    //不存在环形结构
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //证明存在环形结构
        n2 = head;//将n2=head转换成慢指针进行遍历
        while(n1 != n2){    // 再次n1 == n2循环结束，n1,n2为入环节点
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }


    public static Node getIntersectNode(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        //两个链表都是无环结构
        if(loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
        //两个链表都是有环结构
        if(loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        //一个有环一个无环结构，两个链表不可能相交
        return null;
    }

    //两个有环链表，返回第一个相交节点，如果不相交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        //两个链表有一个相同的入环节点
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0? head1 : head2;        // cur1存放大链表
            cur2 = cur1 == head1 ? head2 : head1;   //cur2存放较小链表
            n = Math.abs(n);
            while(n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            //两个链表有不同的入环点
            cur1 = loop1.next;
            while(cur1 != loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    //如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while(cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }
        cur1 = n > 0 ? head1 : head2;       //谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1;   //谁短，谁的头变成cur2
        n = Math.abs(n);
        while(n != 0){
            n--;
            cur1 = cur1.next;
        }
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node n1 = head.next;    // n1 -> slow
        Node n2 = head.next.next;   // n2 -> fast
        while(n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;  // n2 -> walk again from head
        while(n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
}
