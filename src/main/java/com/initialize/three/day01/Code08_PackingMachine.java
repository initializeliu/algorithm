package com.initialize.three.day01;

/**
 * 有n个打包机从左到右一字排开，上方有一个自动装置会抓取一批物品放到打包机上，放到每个机器上的这些物品数量有多有少，
 * 由于物品数量不相同，需要工人将每个机器上的物品进行移动从而达到物品数量相等才能打包。每个物品重量太大，
 * 每次只能般一个物品进行移动，为了省力，只在相邻的机器上移动。
 * 请计算在搬动最小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，返回-1
 *
 * @author initialize liu
 * @date 2021/11/2
 * @apiNote
 */
public class Code08_PackingMachine {

    public static int MinOps(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for(int i = 0; i < size; i++){
            sum += arr[i];
        }
        if(sum % size != 0){
            return -1;//物品总数量不能均分到机器上
        }

        int avg = sum / size;
        int leftSum = 0;//左边总数量
        int ans = 0;
        for(int i = 0; i < arr.length; i++){// i
            //负需要输入 正需要输出
            int leftRest = leftSum - i * avg;       //i的左边数量与平均值差值
            //负需要输入     正需要输出
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if(leftRest < 0 && rightRest < 0){
                //左右两边都需要输入
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            }else{
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
