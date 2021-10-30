package com.initialize.two.hash;

/**
 * 布隆过滤器
 * （极大的节省内存空间，允许一定程度的失误率）
 * 黑名单，爬虫去重
 *
 * 1.样本只添加不删除
 * 2.允许一定的失误率
 * 3.单样本大小无关
 *
 * n = 样本量      p = 失误率
 * m  布隆过滤器长度       k 哈希函数的个数
 * m = -(n * ln p) / pow((ln 2), 2)
 * k = ln 2 * (m / n)  向上取整
 * p(真) = pow((1 - pow(e, -((n * k(真))/m(真))), k(真))
 * @author initialize liu
 * @date 2021/10/27
 * @apiNote
 */
public class Code03_BitMap {
    public static void main(String[] args) {
        int a = 0;

        //a 32 bit
        int[] arr = new int[10];//32 bit * 10 = 320bit
        //arr[0]  int 0 ~ 31
        //arr[1] int 32 ~ 63
        //arr[2] int 64 ~ 95

        int i = 178; //想去178个bit的状态

        int numIndex = 178 / 32;
        int bitIndex = 178 % 32;

        //拿到178位的状态
        int s = ((arr[numIndex] >> (bitIndex)) & 1);

        //请把178位的状体改成1
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));

        i = 178; //请把178位的状态改成0
        arr[numIndex] = arr[numIndex] & (~ (1 << bitIndex));

        i = 178; //请把178位的状态拿出来

        //bit 0 1
        int bit = (arr[i / 32] >> (i % 32)) & 1;
    }
}
