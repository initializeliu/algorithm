package com.initialize.two.tree_dp;

/**
 * Morris遍历
 *
 * 一种遍历二叉树的方式，并且时间复杂度O(N),额外空间复杂度O(1)
 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的.
 *
 * 如果方法需要做第三次会总信息的强整合才能得到答案，使用递归二叉树套路为最优解，
 * 不需要第三次信息强整合，使用Morris遍历为最优解。
 *
 *
 * Morris遍历细节
 * 假设来到当前节点cur,开始时cur来到节点位置
 * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
 * 2)如果cur有左孩子，找到左子树上最右的节点mostRight:
 *      a.如果mostRight的右指针指向空，让其指向cur,然后cur向左移动(cur=cur.left)
 *      b.如果mostRight的右指针指向cur,让其指向null，然后cur向右移动(cur=cur.right)
 * 3)cur为空时遍历停止
 *
 * @author initialize liu
 * @date 2021/10/30
 * @apiNote
 */
public class Code03_MorrisTraversal {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int val){
            value = val;
        }
    }

    public static void morris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){     //过流程
            mostRight = cur.left;   //mostRight是cur左孩子
            if(mostRight != null){  //有左子树
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //mostRight变成了cur左子树上，最右的节点
                if(mostRight.right == null){    //这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{  //mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    //先序遍历
    //只经过一次，直接处理
    //经过两次，第一次打印处理
    public static void morrisPre(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){     //过流程
            mostRight = cur.left;   //mostRight是cur左孩子
            if(mostRight != null){  //有左子树
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //mostRight变成了cur左子树上，最右的节点
                if(mostRight.right == null){    //这是第一次来到cur
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{  //mostRight.right == cur
                    mostRight.right = null;
                }
            }else {     //没有左子树的情况
                System.out.println(cur.value);//处理
            }
            cur = cur.right;
        }
    }

    //中序遍历
    //只经过一次，直接处理
    //经过两次，第二次打印处理
    public static void morrisIn(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){     //过流程
            mostRight = cur.left;   //mostRight是cur左孩子
            if(mostRight != null){  //有左子树
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //mostRight变成了cur左子树上，最右的节点
                if(mostRight.right == null){    //这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{  //mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    //后续遍历
    //某节点经过两次，第二次，逆序打印左子树的右边界。
    public static void morrisPos(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){     //过流程
            mostRight = cur.left;   //mostRight是cur左孩子
            if(mostRight != null){  //有左子树
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //mostRight变成了cur左子树上，最右的节点
                if(mostRight.right == null){    //这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{  //mostRight.right == cur
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);//整个循环完成后，打印树的最右边界
        System.out.println();
    }

    //以X为头的树，逆序打印这棵树的右边界
    public static void printEdge(Node X){
        Node tail = reverseEdge(X);
        Node cur = tail;
        while(cur != null){
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }
    public static Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while(from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }


    //判读是否是搜索二叉树
    public static boolean isBST(Node head){
        if(head == null){
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        int preValue = Integer.MIN_VALUE;
        while(cur != null){     //过流程
            mostRight = cur.left;   //mostRight是cur左孩子
            if(mostRight != null){  //有左子树
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //mostRight变成了cur左子树上，最右的节点
                if(mostRight.right == null){    //这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{  //mostRight.right == cur
                    mostRight.right = null;
                }
            }
            if(cur.value <= preValue){
                return false;
            }
            preValue = cur.value;
            cur = cur.right;
        }
        return true;
    }
}
