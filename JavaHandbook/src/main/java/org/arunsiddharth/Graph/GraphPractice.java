package org.arunsiddharth.Graph;

import java.util.*;

import org.arunsiddharth.Advanced.DisjointSetUnion;
import org.arunsiddharth.Domain.*;
public class GraphPractice {
      static class Graph{
        ArrayList<ArrayList<Edge>> adjList;
        int V;
        Graph(int v){
            V = v;
            adjList = new ArrayList<>(v);
            for(int i=0;i<v;i++){
                adjList.add(new ArrayList<>());
            }
        }

        void addDirectedEdge(int src, int dest, int wt){
            adjList.get(src).add(Edge.of(src,dest,wt));
        }

        void addUndirectedEdge(int src, int dest, int wt){
            adjList.get(src).add(Edge.of(src,dest,wt));
            adjList.get(dest).add(Edge.of(dest,src,wt));
        }
        void dfsRecursiveUtil(int src, boolean[] visited){
            visited[src] = true;
            System.out.print(src+"->");
            for(Edge child:adjList.get(src)){
                if(!visited[child.dest]){
                    dfsRecursiveUtil(child.dest, visited);
                }
            }
        }
        void dfsRecursive(){
            boolean[] visited = new boolean[V];
            for(int i=0;i<V;i++){
                if(!visited[i]){
                    dfsRecursiveUtil(i, visited);
                    System.out.print("null");
                }
            }
        }
        void dfs(int src){
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] visited = new boolean[V];
            stack.push(src);
            visited[src]=true;
            while(!stack.isEmpty()){
                int node = stack.pop();
                System.out.print(node+"->");
                for(Edge child:adjList.get(node)){
                    if(!visited[child.dest]){
                        visited[child.dest] = true;
                        stack.push(child.dest);
                    }
                }
            }
            System.out.println("null");
        }
        void bfs(int src){
            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[V];
            queue.offer(src);
            visited[src] = true;
            while(!queue.isEmpty()){
                int node = queue.poll();
                System.out.print(node+"->");
                for(Edge child:adjList.get(node)){
                    if(!visited[child.dest]){
                        visited[child.dest] = true;
                        queue.offer(child.dest);
                    }
                }
            }
            System.out.println("null");
        }
        boolean isCyclicUnDirectedUtil(int src, boolean[] visited, int parent){
            visited[src]=true;
            for(Edge child:adjList.get(src)){
                if(child.dest!=parent){
                    if(visited[child.dest] || isCyclicUnDirectedUtil(child.dest, visited, src))return true;
                }
            }
            return false;
        }
        boolean isCyclicUnDirected(){
            boolean[] visited = new boolean[V];
            for(int i=0;i<V;i++){
                if(!visited[i] && isCyclicUnDirectedUtil(i, visited, -1)){
                    return true;
                }
            }
            return false;
        }
        boolean isCyclicDirectedUtil(int src, boolean[] recStack, boolean[] visited){
            visited[src] = true;
            recStack[src] = true;
            for(Edge child:adjList.get(src)){
                if(visited[child.dest] && recStack[child.dest])return true;
                if(!visited[child.dest] && isCyclicDirectedUtil(child.dest, recStack, visited))return true;
            }
            recStack[src] = false;
            return false;
        }
        boolean isCyclicDirected(){
            boolean[] recStack = new boolean[V];
            boolean[] visited = new boolean[V];
            for(int i=0;i<V;i++){
                if(!visited[i] && isCyclicDirectedUtil(i, recStack, visited))return true;
            }
            return false;
        }
        void topologicalSortDfsUtil(int src, boolean[] visited, Deque<Integer> stack){
            visited[src] = true;
            for(Edge child:adjList.get(src)){
                if(!visited[child.dest])topologicalSortDfsUtil(child.dest, visited, stack);
            }
            stack.push(src);
        }
        void topologicalSortDfs(){
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] visited = new boolean[V];
            for(int i=0;i<V;i++){
                if(!visited[i])topologicalSortDfsUtil(i, visited, stack);
            }
            while(!stack.isEmpty()){
                System.out.print(stack.pop()+"->");
            }
            System.out.println("null");
        }
        void topologicalSortBfs(){
            int[] indegree = new int[V];
            for(int i=0;i<V;i++){
                for(Edge child:adjList.get(i)){
                    indegree[child.dest]++;
                }
            }
            Deque<Integer> dq = new ArrayDeque<>();
            for(int i=0;i<V;i++)if(indegree[i]==0)dq.offer(i);
            while(!dq.isEmpty()){
                int node = dq.poll();
                System.out.print(node+"->");
                for(Edge child:adjList.get(node)){
                    if(--indegree[child.dest]==0){
                        dq.offer(child.dest);
                    }
                }
            }
            System.out.println("null");
        }
        ArrayList<ArrayList<Integer>> allTopologicalArrayLists;
        void getAllTopologicalSortUtil(int[] indegree, boolean[] visited, ArrayList<Integer> topoArray){
            if(topoArray.size()==V){
                allTopologicalArrayLists.add(new ArrayList<>(topoArray));
                return;
            }
            for(int i=0;i<V;i++){
                if(indegree[i]==0 && !visited[i]){
                    visited[i]=true;
                    topoArray.add(i);
                    for(Edge child:adjList.get(i))indegree[child.dest]--;
                    getAllTopologicalSortUtil(indegree, visited, topoArray);
                    for(Edge child:adjList.get(i))indegree[child.dest]++;
                    topoArray.remove(topoArray.size()-1);
                    visited[i]=false;
                }
            }
        }
        void getAllTopologicalSort(){
            int[] indegree = new int[V];
            boolean[] visited = new boolean[V];
            for(int i=0;i<V;i++){
                for(Edge child:adjList.get(i)){
                    indegree[child.dest]++;
                }
            }
            allTopologicalArrayLists = new ArrayList<>();
            getAllTopologicalSortUtil(indegree, visited, new ArrayList<Integer>());
            System.out.println(allTopologicalArrayLists);
        }

        void dijkstraAL(int src){
            int[] distance = new int[V];
            int[] parent = new int[V];
            Set<Integer> settledNodes = new HashSet<Integer>();
            PriorityQueue<Edge> pq = new PriorityQueue<>(V, (a,b)->{
                return a.weight-b.weight;
            });
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            distance[src]=0;
            pq.offer(Edge.of(src, -1, 0));
            while(!pq.isEmpty()){
                Edge minWtNode = pq.poll();
                if(settledNodes.contains(minWtNode.source))continue;
                distance[minWtNode.source]=minWtNode.weight;
                settledNodes.add(minWtNode.source);
                for(Edge child:adjList.get(minWtNode.source)){
                    if(!settledNodes.contains(child.dest) && distance[child.dest]>distance[minWtNode.source]+child.weight){
                        distance[child.dest]=distance[minWtNode.source]+child.weight;
                        pq.offer(Edge.of(child.dest, -1, distance[child.dest]));
                        parent[child.dest] = minWtNode.source;
                    }
                }
            }
            System.out.println(Arrays.toString(distance));
        }

        boolean updateDistance(int[] distance){
            boolean distanceUpdated = false;
            for(int i=0;i<V;i++){
                for(Edge e:adjList.get(i)){
                    if(distance[e.source]!=Integer.MAX_VALUE && distance[e.source]+e.weight<distance[e.dest]){
                        distance[e.dest] = distance[e.source]+e.weight;
                        distanceUpdated = true;
                    }
                }
            }
            return distanceUpdated;
        }

        void bellmanFord(int src){
            int[] distance = new int[V];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[src] = 0;
            for(int i=0;i<V-1;i++){
                updateDistance(distance);
            }
            if(updateDistance(distance)){
                System.out.println("Graph contains negative weight cycle");
                return;
            }
            System.out.println(Arrays.toString(distance));
        }
        void floydWarshall(){
            int[][] dist = new int[V][V];
            for(int i=0;i<V;i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][i]=0;
                for(Edge child:adjList.get(i)){
                    dist[i][child.dest]=child.weight;
                }
            }

            for(int k=0;k<V;k++){
                for(int i=0;i<V;i++){
                    for(int j=0;j<V;j++){
                        if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE && dist[i][k]+dist[k][j]<dist[i][j]){
                            dist[i][j] = dist[i][k]+dist[k][j];
                        }
                    }
                }
            }

            for(int i=0;i<V;i++)System.out.println(Arrays.toString(dist[i]));
        }
        void primAL(){
            int[] keyValue = new int[V];
            boolean[] mstSet = new boolean[V];
            int[] parent = new int[V];
            Arrays.fill(keyValue, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            PriorityQueue<Edge> pq = new PriorityQueue<>(V, (a,b)->{
                return a.weight-b.weight;
            });
            keyValue[0] = 0;
            pq.offer(Edge.of(0, -1, 0));
            int wt = 0;
            while(!pq.isEmpty()){
                Edge minWtNode = pq.poll();
                if(mstSet[minWtNode.source])continue;
                mstSet[minWtNode.source]=true;
                wt+=minWtNode.weight;
                for(Edge child:adjList.get(minWtNode.source)){
                    if(!mstSet[child.dest] && keyValue[child.dest]>child.weight){
                        keyValue[child.dest] = child.weight;
                        parent[child.dest]=minWtNode.source;
                        pq.offer(Edge.of(child.dest, -1, keyValue[child.dest]));
                    }
                }
            }
            System.out.println(wt);
        }
        void kruskal(){
            ArrayList<Edge> edgeList = new ArrayList<>();
            for(int i=0;i<V;i++){
                for(Edge e:adjList.get(i)){
                    edgeList.add(e);
                }
            }
            Collections.sort(edgeList);
            DisjointSetUnion dsu = new DisjointSetUnion(V);
            List<Edge> edges = new ArrayList<>();
            int wt=0;
            for(Edge e:edgeList){
                if(!dsu.isInSameSet(e.source, e.dest)){
                    dsu.unionByRank(e.source, e.dest);
                    edges.add(e);
                    wt+=e.weight;
                }
                if(edges.size()==V-1)break;
            }
            if(edges.size()!=V-1)System.out.println("NO MST");
        }

    }

    static class GraphCuts{
        ArrayList<Edge>[] graph;
        int[] preOrder;
        int[] lowLink;
        int cnt;
        ArrayList<Integer> bridges;
        boolean[] articulationPoints;
        int V,M;
        GraphCuts(int N){
            graph = new ArrayList[V=N];
            for(int i=0;i<N;i++){
                graph[i] = new ArrayList<Edge>();
            }
            preOrder = new int[N];
            lowLink = new int[N];
            Arrays.fill(preOrder, -1);
            bridges = new ArrayList<>();
            articulationPoints = new boolean[V];
            M=cnt=0;
        }
        int dfs(int node, int parent, int edgeNumber){
            if(preOrder[node]!=-1){//this is back edge
                lowLink[parent] = Math.min(lowLink[parent], preOrder[node]);
                return lowLink[parent];
            }
            preOrder[node]=++cnt;
            boolean hasFwd = false;
            for(Edge e:graph[node]){
                if(e.id==edgeNumber)continue;
                if(dfs(e.dest, node, e.id)<0){
                    lowLink[node] = Math.min(lowLink[node], lowLink[e.dest]);
                    if(lowLink[e.dest]==preOrder[e.dest]){
                        bridges.add(e.id);
                    }
                    if(node!=parent?lowLink[node]>=preOrder[node]:hasFwd){
                        articulationPoints[node]=true;
                    }
                    hasFwd=true;
                }
            }
            return -1;
        }
        void findBreaks(){
            for(int i=0;i<V;i++){
                if(preOrder[i]==-1){
                    dfs(i, i, M);
                }
                
            }
        }
        void addUnDirectedEdge(int src, int dest){
            graph[src].add(Edge.of(src,dest,1,M));
            graph[dest].add(Edge.of(dest,src,1,M++));
        }
    }


    public static void main(String[] args){
        Graph graph = new Graph(5);
        graph.addDirectedEdge(0, 1, 1);
        graph.addDirectedEdge(0, 2, 1);
        graph.addDirectedEdge(1, 2, 1);
        graph.addDirectedEdge(2, 0, 1);
        graph.addDirectedEdge(2, 3, 1);
        graph.addDirectedEdge(3, 3, 1);
        //graph.bfs(2);

        Graph topoGraph = new Graph(6);
        topoGraph.addDirectedEdge(5,0,1);
        topoGraph.addDirectedEdge(4,0,1);
        topoGraph.addDirectedEdge(2,3,1);
        topoGraph.addDirectedEdge(3,1,1);
        topoGraph.addDirectedEdge(5,2,1);
        topoGraph.addDirectedEdge(4,1,1);
        //topoGraph.topologicalSortBfs();
        //topoGraph.getAllTopologicalSort();


        Graph distanceGraph = new Graph(9);
        distanceGraph.addUndirectedEdge(0, 1, 4);
        distanceGraph.addUndirectedEdge(0, 7, 8);
        distanceGraph.addUndirectedEdge(1, 7, 11);
        distanceGraph.addUndirectedEdge(1, 2, 8);
        distanceGraph.addUndirectedEdge(7, 6, 1);
        distanceGraph.addUndirectedEdge(7, 8, 7);
        distanceGraph.addUndirectedEdge(2, 8, 2);
        distanceGraph.addUndirectedEdge(6, 8, 6);
        distanceGraph.addUndirectedEdge(2, 3, 7);
        distanceGraph.addUndirectedEdge(6, 5, 2);
        distanceGraph.addUndirectedEdge(2, 5, 4);
        distanceGraph.addUndirectedEdge(3, 5, 14);
        distanceGraph.addUndirectedEdge(3, 4, 9);
        distanceGraph.addUndirectedEdge(5, 4, 10);
        distanceGraph.floydWarshall();
        //distanceGraph.kruskal();

        Graph distanceGraph2 = new Graph(5);
        distanceGraph2.addDirectedEdge(0, 1, -1);
        distanceGraph2.addDirectedEdge(0, 2, 4);
        distanceGraph2.addDirectedEdge(1, 2, 3);
        distanceGraph2.addDirectedEdge(3, 2, 5);
        distanceGraph2.addDirectedEdge(4, 3, -3);
        distanceGraph2.addDirectedEdge(3, 1, 1);
        distanceGraph2.addDirectedEdge(1, 3, 2);
        distanceGraph2.addDirectedEdge(1, 4, 2);
        //distanceGraph2.bellmanFord(0);


        Graph iso1 = new Graph(7);
        iso1.addUndirectedEdge(0, 1, 1);
        iso1.addUndirectedEdge(0, 2, 1);
        iso1.addUndirectedEdge(6, 2, 1);
        iso1.addUndirectedEdge(2, 5, 1);
        iso1.addUndirectedEdge(2, 3, 1);
        iso1.addUndirectedEdge(3, 4, 1);

        Graph iso2 = new Graph(7);
        iso2.addUndirectedEdge(2, 1, 1);
        iso2.addUndirectedEdge(2, 3, 1);
        iso2.addUndirectedEdge(0, 3, 1);
        iso2.addUndirectedEdge(3, 4, 1);
        iso2.addUndirectedEdge(3, 5, 1);
        iso2.addUndirectedEdge(6, 5, 1);
    }
}
