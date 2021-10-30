package com.initialize.one.list;

/**
 * @author initialize liu
 * @date 2021/10/14
 * @apiNote
 */
public class Node {
    public Integer value;

    public Node next;

    public Node(){}
    public Node(Integer value){
        this.value = value;
    }

    @Override
    public String toString() {
        if(next != null){
            return " " + value + "," + next;
        }else {
            return " " + value;
        }

    }
}
