package com.initialize.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树序列化和重组二叉树
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class SerializeAndReconstructTree {

    //先序序列化
    public static String serialByPre(Node head){
        if(head == null){
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //反序列化
    public static Node reconByPreString(String preStr){
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i != values.length; i++){
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

}
