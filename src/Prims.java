import java.io.*;
import java.lang.*;
import java.util.*;

class PrimsMST {
    public int node;
    public LinkedList<int[]>[] GraphRepresentationList;

    public PrimsMST(int node) {
        this.node = node;
        GraphRepresentationList = new LinkedList[node];
        for (int i = 0; i < node; i++) {
            GraphRepresentationList[i] = new LinkedList<>();
        }
    }

    public void addWeightedEdge(int source, int dest, int weight) {
        int[] edge = { dest, weight };
        int[] edge2 = { source, weight };
        GraphRepresentationList[source].add(edge);
        GraphRepresentationList[dest].add(edge2);
        System.out.println("Added a weighted edge between " + source + " & " + dest + " having weight: " + weight);
    }

    public List<int[]> primMST() {
        List<int[]> resultMST = new ArrayList<>();
        int[] parent = new int[node];
        int[] key = new int[node];
        Boolean[] mstSet = new Boolean[node];

        for (int i = 0; i < node; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < node - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int j = 0; j < GraphRepresentationList[u].size(); j++) {
                int[] edge = GraphRepresentationList[u].get(j);
                int v = edge[0];
                int weight = edge[1];
                if (!mstSet[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                }
            }
        }

        for (int i = 1; i < node; i++) {
            int[] edge = { parent[i], i, key[i] };
            resultMST.add(edge);
        }

        return resultMST;
    }

    int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < node; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    public static void main(String[] args) {
        PrimsMST weightedGraph = new PrimsMST(6);

        weightedGraph.addWeightedEdge(0, 2, 8);
        weightedGraph.addWeightedEdge(0, 1, 7);
        weightedGraph.addWeightedEdge(1, 3, 6);
        weightedGraph.addWeightedEdge(1, 5, 5);
        weightedGraph.addWeightedEdge(2, 3, 4);
        weightedGraph.addWeightedEdge(2, 5, 3);
        weightedGraph.addWeightedEdge(1, 2, 3);
        weightedGraph.addWeightedEdge(5, 3, 2);
        weightedGraph.addWeightedEdge(4, 5, 2);

        List<int[]> minimumSpanningTree = weightedGraph.primMST();
        int min_cost = 0;

        System.out.println("Edges in the Minimum Spanning Tree (MST):");
        for (int[] edge : minimumSpanningTree) {
            System.out.println("Edge: " + edge[0] + " - " + edge[1] + " | Weight: " + edge[2]);
            min_cost += edge[2];
        }
        System.out.println("Total Cost of MST:" + min_cost);
    }
}
