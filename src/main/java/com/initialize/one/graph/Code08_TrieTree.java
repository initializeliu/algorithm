package com.initialize.one.graph;

/**
 * 什么是前锥树？
 * 如何生成前缀树？
 * 例：一个字符串类型的数组arr1,另一个字符串类型的数组arr2。arr2中有哪些字符，是arr1中出现的？
 * arr2中有哪些字符，是作为arr1中某个字符串前缀出现的？打印arr2中出现次数最大的前缀？
 * 通过最左前树结构完成，Trie的root代表的是""，所有单词都是以""开头
 * @author initialize liu
 * @date 2021/10/19
 * @apiNote
 */
public class Code08_TrieTree {

    public static class TrieNode{
        public int pass;    //经过该节点的字符串数量
        public int end;     //将节点作为终止节点的字符串数量
        //public HashMap<Char, Node> nexts; //如果字符类型不能确定，可使用HashMap，数组不合适
        //public TreeMap<Char, Node> nexts; //字符之间有顺序要求
        public TrieNode[] nexts; //该节点下一个可能走向的节点//因为小写英文字符是确定的26个，所以数组比较合适
        public TrieNode(){
            pass = 0;
            end = 0;
            // next[0] == null 没有走向'a'的路
            // next[0] != null 有走向'a'的路
            // ...
            // next[25] != null 有走向'z'的路
            nexts = new TrieNode[26];
        }
    }
    public static class Trie{
        private TrieNode root;  //表示以空字符为前缀的节点
        public Trie(){
            root = new TrieNode();
        }
        //将新的单词插入前缀树中
        public void insert(String word){
            if(word == null){
                return;
            }
            char[] chs = word.toCharArray();//字符串转字节数组
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for(int i = 0; i < chs.length; i++){//从左向右遍历字符
                index = chs[i] - 'a';   //由字符，对应成走那条路
                if(node.nexts[index] == null){//需要链接的节点之间，之前没有链接
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        //删除单词
        public void delete(String word){
            if(search(word) != 0){ //确定树中确实加入过word,才删除
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for(int i = 0; i < chs.length; i++){
                    index = chs[i] - 'a';
                    if(--node.nexts[index].pass == 0){
                        //通过node.next[index]获取到chs[i]所在节点
                        //pass-- 后为0，说明该节点并没有单词经过了。
                        node.nexts[index] = null;   //chs[i]对应节点应为gc root遍历不可达到，会被垃圾回收器回收
                        //C++需要遍历到底去析构
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        //word这个单词之前加入过几次
        public int search(String word){
            if(word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            //找到word最后一个字符所在节点
            return node.end;
        }

        //所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;  //下一个节点在本节点数组中的索引
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    //pre中的下一个字符在node中不存在
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

}
