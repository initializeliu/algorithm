package com.initialize.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 * 1.利用队列实现
 * 2.从源节点开始依次按照宽度进队列，然后弹出
 * 3.每弹出一个点，把该节点所有没有进过队列的邻节点放入队列
 * 4.直到队列变空
 * @author initialize liu
 * @date 2021/10/18
 * @apiNote
 */
public class Code02_BFS {



    //从node出发，进行宽度优先遍历
    public static void bfs(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();    //已经添加到队列中的节点
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);      //处理逻辑
            for(Node next : cur.nexts){     //遍历所有相邻节点
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
