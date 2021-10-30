package com.initialize.one.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 获取一个节点到各个节点最小距离
 * Dijkstra算法
 * 适用范围：环形边界权重值相加不能小于0。
 *
 * 1.distanceMap找距离head节点,最近的节点（这个节点没有被处理过）
 * 2.找到目标节点后，遍历该节点的所有边界，因为这个节点的加入可能会影响其它相邻节点到head的距离。
 * 3.通过循环查找符合条件的最小距离，完成所有节点都处理。
 * @author initialize liu
 * @date 2021/10/19
 * @apiNote
 */
public class Code07_Dijkstra {

    //通过自定义堆来处理
    //改进后的dijkstra算法
    //从head出发，所有head能达到的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size); //自定义小根堆
        nodeHeap.addOrUpdateOrIgnore(head, 0);  //节点没有进过堆，添加；节点在当前堆中，修改；节点已经出堆（当前节点到head节点距离最小值已经确定），忽略
        HashMap<Node, Integer> result = new HashMap<>();    //节点到head的距离最小值
        while(!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop(); //弹出堆顶元素
            Node cur = record.node;
            int distance = record.distance;
            for(Edge edge : cur.edges){ //遍历节点相邻边界
                //当前节点到head的距离+边界weight
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);  //确定节点最短距离
        }
        return result;
    }

    public static class NodeHeap{
        private Node[] nodes;   //堆的底层结构
        private HashMap<Node, Integer> heapIndexMap;    //Node在数组中的索引
        private HashMap<Node, Integer> distanceMap;     //Node到head的最短距离
        private int size;   //当前堆大小

        public NodeHeap(int size){
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance){
            //在堆上， update
            if(inHeap(node)){
                //之前Node节点距离head节点的距离与现在Node节点距离head节点的最小值。
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                //调整堆结构
                insertHeapify(node, heapIndexMap.get(node));
            }
            //节点没有进来过，add
            if(!isEntered(node)){
                nodes[size] = node;     //数组添加一个节点
                heapIndexMap.put(node, size);     //节点对应的索引
                distanceMap.put(node, distance);    //新节点与head的距离，没有添加前默认无穷大
                insertHeapify(node, size++);    //新加入节点可能破坏小根堆结构，再次调整堆为小根堆。
            }
            //之前进来过，现在已经不在堆上，忽略
        }
        //弹出堆顶元素
        public NodeRecord pop(){
            //堆顶元素和堆顶元素对应的距离封装成NodeRecord
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);      //将堆顶元素与最后一个元素交换
            heapIndexMap.put(nodes[size - 1], -1);  //-1：标记曾添加到堆上
            distanceMap.remove(nodes[size - 1]);    //节点对应距离移除
            nodes[size - 1] = null; //数组释放空间
            heapify(0, --size);     //在这里已经调整了堆的大小
            return nodeRecord;
        }
        //因为有节点变化，需要调整小根堆
        private void insertHeapify(Node node, int index){
            //将本节点（索引index)与父节点（索引index-1/2）想比较，如果本节点距离小于父节点距离，交换
            while(distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])){
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
        //index所在Node可能不满足小根堆，需要调整，size为堆大小
        private void heapify(int index, int size){
            int left = index * 2 + 1;   //左子节点索引
            while(left < size){
                //取出左右子节点中距离小的节点索引赋值给smallest
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])? left + 1 : left;
                //左右子节点中较小距离与index节点的距离较小的索引赋值给smallest,形成小根堆
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index])? smallest : index;
                if(smallest == index){  //当前节点是当前节点，左子节点，右子节点中的最小值，终止
                    break;
                }
                swap(smallest, index);//交换
                index = smallest;   //将smallest值为当前节点
                left = index * 2 + 1;
            }
        }
        //节点没有进来过堆（可能已经出去了，可能还在堆上）
        private boolean isEntered(Node node){
            return heapIndexMap.containsKey(node);
        }
        //节点有没有在堆上
        private boolean inHeap(Node node){
            //-1：说明不在堆上
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }
        //索引对应节点交换
        private void swap(int index1, int index2){
            //heapIndexMap上Node对应的索引要交换
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            //数组上Node要交换
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static HashMap<Node, Integer> dijkstra1(Node head) {
        //从head出发到所有点的最小距离
        //key: 从head出发到达key
        //valeu: 从head出发到达key的最小距离
        //如果在表中，没有T的记录，含义是从head出发到这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0); //头节点到头节点距离为0
        //已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        //排除已经求过距离的节点，获取距离head最进的节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while(minNode != null){
            int distance = distanceMap.get(minNode);    //获取本节点到head的距离
            for(Edge edge : minNode.edges){ //遍历本节点的边界
                Node toNode = edge.to;
                if(!distanceMap.containsKey(toNode)){//新节点到head距离为无穷大
                    distanceMap.put(toNode, distance + edge.weight); //更新新节点到head距离
                }else{
                    //因为通过minNode找到的toNode,可能会改变toNode到head的距离
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);//minNode处理完毕
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }
    public static Node getMinDistanceAndUnselectedNode(
            HashMap<Node, Integer> distanceMap,
            HashSet<Node> touchedNodes){
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for(Map.Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            //排除已经处理过的节点
            if(!touchedNodes.contains(node) && distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
