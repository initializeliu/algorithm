package com.initialize.one.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法
 * 在某个标准下，优先考虑最满足标准的样本，最后考虑最不满足标准的样本，最终得到一个答案的算法，叫做贪心算法
 * 也就是说，不从整体最优上加以考虑，所做出的是在某种意义上的局部最优解
 * 局部最优 -？-> 整体最优
 *
 *
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间（给你一个数组，里面是一个具体的项目），
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回这个最多的宣讲场次。
 *
 * 1.按照会议结束最早的时间排序
 * 2.遍历数组，找到上一次会议结束时间之后的第一个会议，添加到选中的会议。result++
 * @author initialize liu
 * @date 2021/10/21
 * @apiNote
 */
public class Code01_BestArrange {
    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bastArrange(Program[] programs, int timePoint){
        Arrays.sort(programs, new ProgramComparator());     //按照会议的结束时间排序
        int result = 0;
        for(int i = 0; i < programs.length; i++){
            if(timePoint <= programs[i].start){     //timePoint上一次会议的结束时间
                result++;       //
                timePoint = programs[i].end;
            }
        }
        return result;  //数量
    }
}
