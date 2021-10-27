package com.initialize.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树遍历可以根据是否使用递归分为：递归遍历和非递归遍历
 * 遍历顺序：
 * 先序遍历（当前节点->左节点->右节点）
 * 中序遍历（左节点->当前节点->右节点）
 * 后续遍历（左节点->右节点->当前节点）
 * 以上三种遍历都是深度优先遍历
 * 广度优先遍历（宽度优先遍历）是结合队列来完成的。
 * @author initialize liu
 * @date 2021/10/15
 * @apiNote
 */
public class OrderTree {

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
        Node n11 = new Node(11);
        Node n12 = new Node(12);
        Node n13 = new Node(13);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        n4.right = n9;
        n5.left = n10;
        n5.right = n11;
        n6.left = n12;
        n6.right = n13;
        n7.left = new Node(14);
        n7.right = new Node(15);
        n8.left = new Node(16);
        n8.right = new Node(17);
        n9.left = new Node(18);

        Node head = n1;

        //recursive
        System.out.println("==========recursive==========");
        System.out.println("pre-order:");
        //preOrderRecur(head);//递归先序
        preOrderRecur_(head);//递归先序
        System.out.println();
        System.out.println("in-order:");
        //inOrderRecur(head);//递归中序
        inOrderRecur_(head);//递归中序
        System.out.println();
        System.out.println("pos-order");
        //posOrderRecur(head);//递归后序
        posOrderRecur_(head);//递归后序
        System.out.println();

        //unrecursive
        System.out.println("==========unrecursive==========");
        //preOrderUnRecur(head);//非递归先序
        preOrderUnRecur_(head);//非递归先序
        System.out.println();
        //inOrderUnRecur(head);//非递归中序
        inOrderUnRecur_(head);//非递归中序
        System.out.println();
        //posOrderUnRecur(head);//非递归后序
        posOrderUnRecur_(head);//非递归后序
        System.out.println();

        System.out.println("===========广度优先遍历=============");
        System.out.println("广度优先遍历：");
        //iterQueue(head);
        iterQueue_(head);
        System.out.println();
        //int max = maxLevelSum(head);
        //int max = maxLevelSum_(head);
        int max = maxLevelSum2(head);
        System.out.println("最大的层节点数：" + max);


    }

    //通过广度优先遍历+HashMap
    public static int maxLevelSum_(Node head){
        if(head == null){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Queue<Node> queue = new LinkedList();
        HashMap<Node, Integer> map = new HashMap();     //每个节点对应的层数

        //1.通过广度优先遍历，且记录节点对应的层数
        int level = 1;  //上一个节点所在层数
        int levelNodeSum = 0;   //上一个节点所在层已有节点数
        int curLevel = 0;   //当前节点所在层
        queue.add(head);
        map.put(head, curLevel);
        Node curNode = null;
        while(!queue.isEmpty()){
            curNode = queue.poll();
            curLevel = map.get(curNode);   //获取节点对应层数
            //2.如果节点所在层数与之前节点所在层数相同，本层节点数+1，否则重置level和levelNodeSum
            if(level == curLevel){
                levelNodeSum++;
            }else{
                //说明该节点与之前一个节点不在同一层。
                max = levelNodeSum > max? levelNodeSum : max;//取max与levelNodeSum中的较大值
                level = curLevel;
                levelNodeSum = 1;   //因为本节点是新层第一个节点，设置为1
            }

            if(curNode.left != null){
                queue.add(curNode.left); //放入队列
                map.put(curNode.left, curLevel + 1);    //存放队列对应层数
            }
            if(curNode.right != null){
                queue.add(curNode.right); //放入队列
                map.put(curNode.right, curLevel + 1);    //存放队列对应层数
            }
        }
        max = levelNodeSum > max? levelNodeSum : max;   //最后一层节点数可没有与max比较
        return max;
    }

    public static void preOrderUnRecur_(Node head){
        System.out.println("pre-order:");
        //1.获取一个栈
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        //2.获取队列中的节点进行操作
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.print(cur.value + " ");//出栈后随后执行节点逻辑
            //3.如果有右节点，右节点先压栈，后出栈
            if(cur.right != null){  //
                stack.push(cur.right);
            }
            //4.如果有左节点，左节点后压栈，先出栈
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }
    //中序（左中右）
    //逻辑非常简单，但是很不好理解
    public static void inOrderUnRecur_(Node head){
        System.out.println("in-order");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){   //对于当前节点保证所有左子节点优先压栈
                    //1.当前节点不为空，将当前节点压栈
                    stack.push(head);
                    //2.将左子节点设置为当前节点
                    head = head.left;
                }else{
                    //2.弹栈：从栈中弹出的节点，不需要考虑该节点是否存在左子节点的问题（通过之前的条件保证所有左子节点已经压栈）。
                    head = stack.pop();
                    System.out.print(head.value + " ");     //父节点逻辑执行
                    //3.如果弹出的节点存在右子节点：将右子节点当成"当前节点"处理。
                    head = head.right;
                }
            }
        }
    }
    //后序（左右后）
    public static void posOrderUnRecur_(Node head){
        System.out.println("pos-order:");
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while(!s1.isEmpty()){
            Node cur = s1.pop();//弹栈
            s2.push(cur);   //父节点在s1中先弹栈，s2中先与子节点压栈，s2后弹栈：这个父节点逻辑后执行
            if(cur.left != null){
                s1.push(cur.left); //s1先压栈，s1后弹栈，s2后压栈，s2先弹栈：这个子节点优先执行逻辑
            }
            if(cur.right != null){
                s1.push(cur.right);  //s1后压栈，s1先弹栈，s2先压栈，s2后弹栈：这个子节点后执行逻辑
            }
        }
        while(!s2.isEmpty()){
            System.out.print(s2.pop().value + " ");
        }
    }
    public static void preOrderRecur_(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur_(head.left);
        preOrderRecur_(head.right);
    }
    public static void inOrderRecur_(Node head){
        if(head == null){
            return;
        }
        inOrderRecur_(head.left);
        System.out.print(head.value + " ");
        inOrderRecur_(head.right);
    }
    public static void posOrderRecur_(Node head){
        if(head == null){
            return;
        }
        posOrderRecur_(head.left);
        posOrderRecur_(head.right);
        System.out.print(head.value + " ");
    }
    //先序递归
    public static void preOrderRecur(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    //中序递归
    public static void inOrderRecur(Node head){
        if(head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }
    //后序递归(左右后)
    public static void posOrderRecur(Node head){
        if(head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    //先序非递归
    public static void preOrderUnRecur(Node head){
        System.out.println("pre-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value + " ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //中序非递归
    public static void inOrderUnRecur(Node head){
        System.out.println("in-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    //后序非递归:利用两个栈结构完成(右左后)
    public static void posOrderUnRecur(Node head){
        System.out.println("pos-order: ");
        if(head != null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);//压栈
            while(!s1.isEmpty()){
                head = s1.pop();//弹栈
                s2.push(head);
                if(head.right != null){
                    s1.push(head.right);
                }
                if(head.left != null){
                    s1.push(head.left);
                }
            }
            while(!s2.isEmpty()){
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    public static void iterQueue_(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            if(cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    //广度优先遍历
    public static void iterQueue(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    //广度优先遍历，查找出节点最多的那一层有多少节点？
    //利用HashMap存储每个节点对应的层数
    public static int maxLevelSum(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();//每个节点对应的层数
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(curNodeLevel == curLevel){
                curLevelNodes++;
            }else{
                max = Math.max(max, curLevelNodes );
                curLevel++;
                curLevelNodes = 1;
            }
            //System.out.println(cur.value);
            if(cur.left != null){
                levelMap.put(cur.left, curLevel + 1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                levelMap.put(cur.right, curLevel + 1);
                queue.add(cur.right);
            }
        }
        //求那层的节点最多，是多少个？
         return curLevelNodes > max ? curLevelNodes : max;
    }

    //广度优先遍历，查找出节点最多的那一层有多少节点？
    //不使用HashMap存储每个节点对应的层数
    public static int maxLevelSum2(Node head){
        if(head == null){
            return 0;
        }

        int max = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        int curLevelNodeSum = 0;

        Node curLevelLastNode = head;   //当前层最后一个节点
        Node nextLevelLastNode = null;  //下一层最后一个节点

        Node curNode = null;

        while(!queue.isEmpty()){
            curNode = queue.poll();
            curLevelNodeSum++;      //当前层节点数+1

            //1.如果存在子节点，新添加到列的节点为下一层最后一个节点
            if(curNode.left != null){
                queue.add(curNode.left);
                nextLevelLastNode = curNode.left;   //队列中新添加的子节点就是下一层最后一个节点
            }
            if(curNode.right != null){
                queue.add(curNode.right);
                nextLevelLastNode = curNode.right;  //队列中新添加的子节点就是下一层最后一个节点
            }

            //2.当前节点为当前层内最后一个节点
            if(curLevelLastNode == curNode){
                max = Math.max(max, curLevelNodeSum);   //前层节点数与max取大值
                curLevelNodeSum = 0;        //重置下一层节点数量为0
                curLevelLastNode = nextLevelLastNode;
                nextLevelLastNode = null;
            }
        }
        return max;
    }
}
