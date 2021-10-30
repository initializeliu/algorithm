package com.initialize.one.graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 * 1.利用栈实现
 * 2.从源节点开始把节点按照深度放入栈，然后弹出
 * 3.每弹出一个点，把该节点下一个没有进过栈的邻节点放入栈
 * 4.直到栈变空
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Code03_DFS {

    public static void dfs(Node node){
        if(node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();    //已经被放入栈中的节点
        stack.add(node);
        set.add(node);
        System.out.println(node.value); //处理逻辑
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next : cur.nexts){     //当前节点相关节点
                if(!set.contains(next)){    //存在一个与本节点相邻的节点，没有放入栈中
                    stack.push(cur);    //当前节点再次入栈，因为可能存在当前节点相邻节点还没有入站（只有当前节点所有相邻节点都入栈，本节点才能出栈）
                    stack.push(next);
                    System.out.println(next.value);     //处理逻辑
                    break;      //下一次出栈的就是next节点，会一条路走下去。
                }
            }
        }
    }
}
