package com.gavin.basicLearning.DataStuctureLearning.GraphLearning;

import java.util.ArrayList;
/**
*          1                 1 2 3 4 5
 *       /   \             1 0 1 1 1 0
 *      2     3    ->      2 1 0 0 0 0
 *      \      \           3 1 0 0 1 1
 *        4 -- 5           4 0 0 0 0 1
 *                         5 0 0 1 0 0
*/
public class Graph {
    private ArrayList<Object> vertexList;//存储顶点的集合
    private int[][] edges;//存储对应的邻接矩阵
    private int numofEdges;//表示边的数目
    /**
     * 传入顶点个数
    */
    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
    }

    /**
     *
     * @param vertex 插入顶点值
     */
    public void insertVertex(Object vertex){
        vertexList.add(vertex);
    }
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;
    }

}
