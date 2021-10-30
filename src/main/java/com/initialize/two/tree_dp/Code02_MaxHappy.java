package com.initialize.two.tree_dp;

import java.util.List;

/**
 * 派对的最大快乐值
 *
 * 员工信息的定义如下：
 *     public static class Employee{
 *         public int happy;   //这名员工可以带来的快乐值
 *         public List<Employee> nexts;    //这名员工有哪些直接下级
 *     }
 * 公司的每个员工都符合Employee类的描述。整个公司的人员结构可以看作一个标准的没有环的多叉树。
 * 树的节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。叶节点是没有任何下属的基层员工(subordinates列表为空),
 * 除基层员工外每个员工都有一个或多个直接下级。
 *
 * 这个公司下载要办part，你可以决定哪些员工来，哪些员工不来，但是要遵循如下规则。
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 *
 * 给定一颗多叉树的头节点boss,请返回派对的最大快乐值。
 * @author initialize liu
 * @date 2021/10/30
 * @apiNote
 */
public class Code02_MaxHappy {

    public static class Employee{
        public int happy;   //这名员工可以带来的快乐值
        public List<Employee> nexts;    //这名员工有哪些直接下级
    }

    public static int maxHappy(Employee boss){
        Info headInfo = process(boss);
        return Math.max(headInfo.laiMaxHappy, headInfo.buMaxHappy);
    }

    public static class Info{
        public int laiMaxHappy;
        public int buMaxHappy;
        public Info(int lai, int bu){
            laiMaxHappy = lai;
            buMaxHappy = bu;
        }
    }

    public static Info process(Employee x){
        if(x.nexts.isEmpty()){//x是基层员工的时候
            return new Info(x.happy, 0);
        }
        int lai = x.happy;  //x来的情况下，整棵树最大收益值
        int bu = 0; //x不来的情况下，整棵树最大收益
        for(Employee next : x.nexts){
            Info nextInfo = process(next);
            //x来，x下的直系下属不能来
            lai += nextInfo.buMaxHappy;
            //x不来，x的直系下属可以来，可以不来
            bu += Math.max(nextInfo.laiMaxHappy, nextInfo.buMaxHappy);
        }
        //汇总x来与不来，下面的最大快乐值。
        return new Info(lai, bu);
    }
}
