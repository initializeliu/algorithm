package com.initialize.one.list;

/**
 * @author initialize liu
 * @date 2021/10/15
 * @apiNote
 */
public class Node1 {

    public Integer value;

    public Node1 next;

    public Node1 rand;

    public Node1(){}
    public Node1(Integer value){
        this.value = value;
    }

    @Override
    public String toString() {
        return " " + value + (next == null? "" : "," + next);
    }
}
