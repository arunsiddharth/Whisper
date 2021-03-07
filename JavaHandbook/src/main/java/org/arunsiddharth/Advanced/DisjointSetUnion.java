package org.arunsiddharth.Advanced;

import java.util.Arrays;

public class DisjointSetUnion {
    int[] parent;
    int[] rank;
    int disconnectedComponents;
    public DisjointSetUnion(int size){
        disconnectedComponents=size;
        parent = new int[size];
        rank = new int[size];
        for(int i=0;i<size;i++)parent[i]=i;
        Arrays.fill(rank, 1);
    }
    public int find(int node){
        return parent[node]==node?node:(parent[node]=find(parent[node]));
    }
    public void unionByRank(int node1, int node2){
        int p1 = find(node1);
        int p2 = find(node2);
        if(rank[p1]>=rank[p2]){
            parent[p2]=p1;
            rank[p1]+=rank[p2];
        } else {
            parent[p1]=p2;
            rank[p2]+=rank[p1];
        }
        disconnectedComponents--;
    }
    
    public void union(int parent, int child){
        int p1 = find(parent);
        int p2 = find(child);
        this.parent[p2]=p1;
        rank[p1]+=rank[p2];
        disconnectedComponents--;
    }

    public boolean isInSameSet(int node1, int node2){
        return find(node1)==find(node2);
    }
    public boolean isConnected(){
        return disconnectedComponents==0;
    }
}
