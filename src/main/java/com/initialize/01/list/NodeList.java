package com.initialize.list;

/**
 * 判断链表是否为回文结构？
 * 不使用额外的栈存储空间
 * @author initialize liu
 * @date 2021/10/14
 * @apiNote
 */
public class NodeList {
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

        //Node node5 = new Node(4);
        //tail.next = node5;
        //tail = node5;



        //Boolean result3 = isPalindrome3(head);

        Boolean result2 = isPalindrome3_(head);

        System.out.println("[" + head + "] 是回文结构吗？"+ result2);
    }

    public static boolean isPalindrome3_(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        //1. 通过快慢指针找到中间位置
        while(n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        Node n3 = null;
        n2 = n1.next; //现在n1就是中间位置
        n1.next = null;
        //2. 右半部分指针逆转
        while(n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        boolean res = true;
        n3 = n1;    //最右端节点，用作后续指针逆转
        n2 = n1;    //最右端节点
        n1 = head;  //最左端节点
        //3. 验证是否为回文结构
        while(n1 != null && n2 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n2 = n3.next;
        n3.next = null;
        //4. 将右半部分再次逆转指针
        while(n2 != null){
            n1 = n2.next;

            n2.next = n3;

            n3 = n2;
            n2 = n1;
        }

        return res;
    }


    // need O(1) extra space
    public static boolean isPalindrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        //find mid node
        while(n2.next != null && n2.next.next != null){
            n1 = n1.next;   // n1 -> mid
            n2 = n2.next.next;  // n2 -> end
        }
        n2 = n1.next;   // n2 -> right part first node
        n1.next = null;
        Node n3 = null;
        // right part convert
        while(n2 != null){
            n3 = n2.next;   // n3 -> save next node
            n2.next = n1;   // next of right node convert
            n1 = n2;    // n1 move
            n2 = n3;    // n2 move
        }
        n3 = n1;    // n3 -> save last node
        n2 = head;  // n2 -> left first node
        boolean res = true;
        // check palindrome
        while(n1 != null && n2 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        // recover list
        while(n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
