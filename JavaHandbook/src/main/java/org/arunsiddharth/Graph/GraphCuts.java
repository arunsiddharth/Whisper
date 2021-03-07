package org.arunsiddharth.Graph;

import java.util.*;
import org.arunsiddharth.Domain.Edge;

public class GraphCuts {
    ArrayList<Edge>[] adj;
    int N,M,cnt;
    ArrayList<Integer> bridges;
    boolean[] articulationPoints;
    boolean[] usedEdges;
    int[] preOrder;
    int[] lowLink;
    int[] stack;
    ArrayList<ArrayList<Integer>> biConnectedComponents;

    void addUnDirectedEdge(int u, int v, int wt){
        adj[u].add(Edge.of(u, v, wt, M));
        adj[v].add(Edge.of(v, u, wt, M++));
    }

    void addUnDirectedEdge(int u, int v){
        addUnDirectedEdge(u, v, 1);
    }

    int dfsBreaks(int node, int parent, int edgeId){
        if(!usedEdges[edgeId]){
            usedEdges[edgeId] = true;
            stack[++stack[0]] = edgeId;
        }

        if(preOrder[node]!=-1){
            lowLink[parent] = Math.min(lowLink[parent], preOrder[node]);
            return lowLink[parent];
        }
        
        preOrder[node]=cnt;
        lowLink[node]=cnt++;
        boolean hasFwd = false;
        for(Edge e:adj[node]){
            //No need to go back from where we came from
            if(e.id==edgeId)continue;
            if(dfsBreaks(e.dest, node, e.id)<0){
                lowLink[node] = Math.min(lowLink[e.dest], lowLink[node]);
                if(lowLink[e.dest]==preOrder[e.dest]){
                    bridges.add(e.id);
                }
                if(node!=parent?lowLink[e.dest]>=preOrder[node]:hasFwd){
                    articulationPoints[node]=true;
                    makeComponent(e.id).add(stack[stack[0]--]+1);
                }
                hasFwd = true;
            }
        }
        return -1;
    }

    //edgeId is root of biconnected components
    ArrayList<Integer> makeComponent(int edgeId){
        ArrayList<Integer> component = new ArrayList<>();
        while(stack[stack[0]]!=edgeId){
            component.add(stack[stack[0]--]+1);
        }
        biConnectedComponents.add(component);
        return component;
    }

    void findBreaks(){
        cnt=0;
        stack = new int[M+2];
        stack[0] = 0;
        usedEdges = new boolean[M+1];
        for(int i=0;i<N;i++){
            if(preOrder[i]==-1){
                usedEdges[M]=false;
                dfsBreaks(i, i, M);
                if(stack[0]>1){
                    makeComponent(M);
                }
                stack[0]=0;
            }
            
        }
    }

    GraphCuts(int V){
        adj = new ArrayList[N=V];
        for(int i=0;i<V;i++){
            adj[i]= new ArrayList<Edge>();
        }
        bridges = new ArrayList<>();
        biConnectedComponents= new ArrayList<>();
        articulationPoints = new boolean[V];
        preOrder = new int[V];
        lowLink = new int[V];        
        Arrays.fill(preOrder, -1);
    }

    void printArticulationPoints(){
        System.out.println("Articulation Points are: ");
        for(int i=0;i<N;i++){
            if(articulationPoints[i])
                System.out.print(i+" ");
        }
        System.out.println();
    }

    
    void printBridges(){
        System.out.println("Bridges are: ");
        System.out.println(bridges);
    }

    void printBiConnectedComponents(){
        System.out.println("BiConnected Components are:");
        for(ArrayList<Integer> component:biConnectedComponents)
            System.out.println(component);
        System.out.println();
    }

    void printPreOrderLowLink(){
        System.out.println("PreOrder is: ");
        System.out.println(Arrays.toString(preOrder));
        System.out.println("LowLink is: ");
        System.out.println(Arrays.toString(lowLink));
    }

    public static void main(String[] args){
        int N, M, u, v;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        GraphCuts g = new GraphCuts(N);
        while(M-->0){
            u = sc.nextInt();
            v = sc.nextInt();
            g.addUnDirectedEdge(u, v);
        }
        g.findBreaks();
        g.printArticulationPoints();
        g.printBridges();
        g.printBiConnectedComponents();
        g.printPreOrderLowLink();
        sc.close();
    }
}


