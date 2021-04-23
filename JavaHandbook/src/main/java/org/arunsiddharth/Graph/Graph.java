package org.arunsiddharth.Graph;

import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.*;
import org.arunsiddharth.Domain.Edge;
import org.arunsiddharth.Domain.GenericTreeNode;
import org.arunsiddharth.Advanced.DisjointSetUnion;
import org.arunsiddharth.Domain.GenericTreeNode;

public class Graph {
    ArrayList<ArrayList<Edge>> adjList;
    int[][] adjMat;
    ArrayList<Edge> edgeList;
    int V;

    Graph(int v){
        V = v;
        adjList = new ArrayList<>(v);
        edgeList = new ArrayList<>();
        adjMat = new int[V][V];
        for(int i=0;i<v;i++){
            adjList.add(new ArrayList<>());
        }
    }

    GenericTreeNode rootTree(int rootId){
        if(isCyclicUnDirected())return null;
        return buildTree(rootId, null);
    }

    GenericTreeNode buildTree(int rootId, GenericTreeNode parent){
        GenericTreeNode root = GenericTreeNode.of(rootId, parent);
        for(Edge child:adjList.get(root.id)){
            if(root.parent!=null && child.dest==root.parent.id)continue;
            root.children.add(buildTree(child.dest, root));
        }
        return root;
    }

    List<Integer> treeCenters(){
        int[] degree = new int[V];
        List<Integer> leaves = new ArrayList<>();
        for(int i=0;i<V;i++){
            degree[i] = adjList.get(i).size();
            if(degree[i]<=1){
                leaves.add(i);
                degree[i] = 0;
            }
        }
        int count = leaves.size();
        while(count<V){
            List<Integer> newLeaves = new ArrayList<>();
            for(Integer leave:leaves){
                for(Edge neighbor:adjList.get(leave)){
                    degree[neighbor.dest]-=1;
                    if(degree[neighbor.dest]==1){
                        newLeaves.add(neighbor.dest);
                    }
                }
                degree[leave]=0;
            }
            count+=newLeaves.size();
            leaves=newLeaves;
        }
        return leaves;
    }

    String encode(GenericTreeNode root){
        if(root==null)return "";
        ArrayList<String> labels =  new ArrayList<String>();
        for(GenericTreeNode child:root.children){
            labels.add(encode(child));
        }
        Collections.sort(labels);
        String result = "";
        for(String label:labels){
            result+="("+label+")";
        }
        return result;
    }


    boolean areTreeIsomorphic(Graph g2){
        if(this.isCyclicUnDirected() || g2.isCyclicUnDirected())return false;
        List<Integer> center1 = this.treeCenters();
        List<Integer> center2 = g2.treeCenters();
        GenericTreeNode tree1 = this.rootTree(center1.get(0));
        String encoding1 = encode(tree1);
        for(Integer center:center2){
            GenericTreeNode tree2 = g2.rootTree(center);
            String encoding2 = encode(tree2);
            if(encoding1.equals(encoding2))return true;
        }
        return false;
    }

    void addUndirectedEdge(int u, int v, int wt){
        adjList.get(u).add(Edge.of(u,v,wt));
        adjList.get(v).add(Edge.of(v,u,wt));
        adjMat[u][v]=adjMat[v][u]=wt;
        edgeList.add(Edge.of(u,v,wt));
        edgeList.add(Edge.of(v,u,wt));
    }

    void addDirectedEdge(int u, int v, int wt){
        adjList.get(u).add(Edge.of(u,v,wt));
        adjMat[u][v]=wt;
        edgeList.add(Edge.of(u,v,wt));
    }

    void printGraph(){
        for(int i=0;i<V;i++){
            System.out.println("For source "+i+" : "+adjList.get(i));
        }
    }

    void dfs(int src){
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(src);
        visited[src]=true;
        while(!stack.isEmpty()){
            int node = stack.pop();
            System.out.print(node+"->");
            for(Edge child:adjList.get(node)){
                if(!visited[child.dest]){
                    visited[child.dest]=true;
                    stack.push(child.dest);
                }
            }
        }
        System.out.println("null");
    }

    void dfsRecursiveUtil(int src, boolean[] visited){
        visited[src]=true;
        System.out.print(src+"->");
        for(Edge child:adjList.get(src)){
            if(!visited[child.dest]){
                dfsRecursiveUtil(child.dest, visited);
            }
        }
    }


    void dfsRecursive(int src){
        boolean[] visited = new boolean[V];
        dfsRecursiveUtil(src, visited);
        System.out.println("null");
    }


    void bfs(int src){
        boolean[] visited = new boolean[V];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(src);
        visited[src]=true;
        while(!q.isEmpty()){
            int node = q.poll();
            System.out.print(node+"->");
            for(Edge child:adjList.get(node)){
                if(!visited[child.dest]){
                    q.add(child.dest);
                    visited[child.dest]=true;
                }
            }
        }
        System.out.println("null");
    }

    boolean isCyclicUnDirectedUtil(int node, boolean[] visited, int parent){
        visited[node]=true;
        for(Edge child:adjList.get(node)){
            if(child.dest!=parent){
                if(visited[child.dest] || isCyclicUnDirectedUtil(child.dest, visited, node))return true;
            }
        }
        return false;
    }

    boolean isCyclicUnDirected(){
        boolean[] visited = new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i] && isCyclicUnDirectedUtil(i, visited, -1))return true;
        }
        return false;
    }

    boolean isCyclicDirectedUtil(int src, boolean[] recStack, boolean[] visited){
        visited[src]=true;
        recStack[src]=true;
        for(Edge child:adjList.get(src)){
            if(!visited[child.dest]){
                if(isCyclicDirectedUtil(child.dest, recStack, visited))return true;
            }else if(recStack[child.dest])return true;//for cross edge
        }
        recStack[src]=false;
        return false;
    }

    boolean isCyclicDirected(){
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i] && isCyclicDirectedUtil(i, recStack, visited))return true;
        }
        return false;
    }

    void topologicalSortBfs(){
        Deque<Integer> q = new ArrayDeque<>();
        int[] indegree = new int[V];
        int vertexCount = 0;
        for(int i=0;i<V;i++){
            for(Edge child:adjList.get(i))indegree[child.dest]++;
        }
        for(int i=0;i<V;i++){
            if(indegree[i]==0)q.offer(i);
        }
        while(!q.isEmpty()){
            int node = q.poll();
            System.out.println(node);
            vertexCount++;
            for(Edge child:adjList.get(node)){
                if(--indegree[child.dest]==0)q.offer(node);
            }
        }
        if(vertexCount!=V)System.out.println("Not Exists");
        System.out.println();
    }



    void topologicalSortDfsUtil(int src, boolean[] visited, Deque<Integer> stack){
        visited[src]=true;
        for(Edge child:adjList.get(src)){
            if(!visited[child.dest])topologicalSortDfsUtil(child.dest, visited, stack);
        }
        stack.offer(src);
    }

    void topologicalSortDfs(){
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0;i<V;i++){
            if(!visited[i])topologicalSortDfsUtil(i, visited, stack);
        }
        while(!stack.isEmpty()){
            System.out.println(stack.poll()+" ");
        }
    }


    ArrayList<ArrayList<Integer>> topologicalSortStore;

    void getAllTopologicalSortUtil(int index, boolean[] visited, int[] indegree, ArrayList<Integer> tmp){
        if(index==V){
            topologicalSortStore.add(new ArrayList<>(tmp));
            return;
        }
        for(int i=0;i<V;i++){
            if(indegree[i]==0 && !visited[i]){
                visited[i]=true;
                tmp.add(i);
                for(Edge child:adjList.get(i))indegree[child.dest]--;
                getAllTopologicalSortUtil(index+1, visited, indegree, tmp);
                for(Edge child:adjList.get(i))indegree[child.dest]++;
                tmp.remove(tmp.size()-1);
                visited[i]=false;
            }
        }
    }

    void getAllTopologicalSort(){
        boolean[] visited = new boolean[V];
        int[] indegree = new int[V];
        topologicalSortStore = new ArrayList<>();
        for(int i=0;i<V;i++){
            for(Edge child:adjList.get(i))indegree[child.dest]++;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        getAllTopologicalSortUtil(0, visited, indegree, result);
        for(ArrayList<Integer> t:topologicalSortStore){
            System.out.println(t);
        }
    }

    int getMinDistanceNode(int[] distance, boolean[] sptSet){
        int minDistance=Integer.MAX_VALUE, minNode=-1;
        for(int i=0;i<V;i++){
            if(!sptSet[i] && distance[i]<minDistance){
                minDistance = distance[i];
                minNode = i;
            }
        }
        return minNode;
    }

    void dijkstraAM(int src){
        int[] distance = new int[V];
        int[] parent = new int[V];
        boolean[] sptSet = new boolean[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[src]=0;
        for(int i=0;i<V-1;i++){//don't need to do this step for last vertex
            int u = getMinDistanceNode(distance, sptSet);
            if(u==-1)break;//graph is disconnected
            sptSet[u]=true;
            for(int j=0;j<V;j++){
                if(!sptSet[j] && adjMat[u][j]!=0 && distance[u]+adjMat[u][j]<distance[j]){
                    distance[j]=distance[u]+adjMat[u][j];
                    parent[j]=i;
                }
            }
        }
        System.out.println(Arrays.toString(distance));
    }

    void dijkstraAL(int src){
        int[] distance = new int[V];
        int[] parent = new int[V];
        Set<Integer> settledNodes = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(V, (a,b)->{
            return a.weight-b.weight;
        });
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src]=0;
        Arrays.fill(parent, -1);
        pq.add(Edge.of(src, -1, 0));
        while(settledNodes.size()!=V){
            Edge minWtNode = pq.poll();
            if(settledNodes.contains(minWtNode.source))continue;
            settledNodes.add(minWtNode.source);
            for(Edge child:adjList.get(minWtNode.source)){
                if(!settledNodes.contains(child.dest) &&
                 distance[child.dest]>child.weight+distance[minWtNode.source]){
                    distance[child.dest]=child.weight+distance[minWtNode.source];
                    parent[child.dest]=minWtNode.source;
                    pq.offer(Edge.of(child.dest, -1, distance[child.dest]));
                }
            }
        }
        System.out.println(Arrays.toString(distance));
    }


    void dijkstraALSet(int src){
        int[] distance = new int[V];
        int[] parent = new int[V];
        TreeSet<Edge> set = new TreeSet<>((a,b)->{
            return a.weight-b.weight;
        });
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        set.add(Edge.of(0, -1, 0));
        distance[src]=0;
        while(!set.isEmpty()){
            Edge minWtNode = set.pollFirst();
            if(distance[minWtNode.source]<minWtNode.weight)continue;
            distance[minWtNode.source]=minWtNode.weight;
            for(Edge child:adjList.get(minWtNode.source)){
                if(distance[child.dest]>distance[minWtNode.source]+child.weight){
                    if(distance[child.dest]!=Integer.MAX_VALUE)
                        set.remove(Edge.of(child.dest, -1, distance[child.dest]));
                    distance[child.dest]=distance[minWtNode.source]+child.weight;
                    set.add(Edge.of(child.dest, -1, distance[child.dest]));
                    parent[child.dest] = minWtNode.source;
                }
            }
        }
        System.out.println(Arrays.toString(distance));
    }

    boolean updateDistance(int[] distance){
        boolean distanceUpdated = false;
        for(Edge edge:edgeList){
            if(distance[edge.source]!=Integer.MAX_VALUE && distance[edge.dest]>distance[edge.source]+edge.weight){
                distance[edge.dest]=distance[edge.source]+edge.weight;
                distanceUpdated = true;
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
        if(updateDistance(distance))System.out.println("Graph contains negative cycle");
        else{
            System.out.println(Arrays.toString(distance));
        }
    }

    void floydWarshall(){
        int[][] distance = new int[V][V];
        for(int i=0;i<V;i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i]=0;
        }
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(adjMat[i][j]!=0)
                    distance[i][j]=adjMat[i][j];
            }
        }
        for(int k=0;k<V;k++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    if(i==j)continue;
                    if(distance[i][k]!=Integer.MAX_VALUE && distance[k][j]!=Integer.MAX_VALUE && distance[i][j]>distance[i][k]+distance[k][j]){
                        distance[i][j] = distance[i][k]+distance[k][j];
                    }
                }
            }
        }
        for(int i=0;i<V;i++)System.out.println(Arrays.toString(distance[i]));   
    }


    void primAM(){
        int[] keyValue = new int[V];
        boolean[] mstSet = new boolean[V];
        int[] parent = new int[V];

        Arrays.fill(keyValue, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        
        keyValue[0]=0;
        int wt = 0;
        for(int i=0;i<V;i++){
            int u = getMinDistanceNode(keyValue, mstSet);
            if(u==-1)break;//disconnected graph
            mstSet[u]=true;
            wt+=keyValue[u];
            for(int j=0;j<V;j++){
                if(!mstSet[j] && adjMat[u][j]!=0 && adjMat[u][j]<keyValue[j]){
                    keyValue[j]=adjMat[u][j];
                    parent[j]=u;
                }
            }
        }
        System.out.println(wt);
        for(int i=1;i<V;i++){
            System.out.println(i+"-"+parent[i]);
        }
    }

    void primAL(){
        int[] keyValue = new int[V];
        int[] parent = new int[V];
        Set<Integer> settledNodes = new HashSet<>();
        Arrays.fill(keyValue, Integer.MAX_VALUE);
        keyValue[0]=0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(V, (a,b)->{
            return a.weight-b.weight;
        });
        pq.offer(Edge.of(0, -1, 0));
        int wt = 0;
        while(settledNodes.size()!=V){
            Edge minWtNode = pq.poll();
            if(settledNodes.contains(minWtNode.source))continue;
            settledNodes.add(minWtNode.source);
            wt+=minWtNode.weight;
            for(Edge child:adjList.get(minWtNode.source)){
                if(!settledNodes.contains(child.dest) && keyValue[child.dest]>child.weight){
                    keyValue[child.dest] = child.weight;   
                    parent[child.dest] = minWtNode.source;
                    pq.offer(Edge.of(child.dest, -1, keyValue[child.dest]));
                }
            }
        }
        System.out.println(wt);
        for(int i=1;i<V;i++){
            System.out.println(i+"-"+parent[i]);
        }
    }

    void kruskal(){
        DisjointSetUnion dsu = new DisjointSetUnion(V);
        Collections.sort(edgeList);
        ArrayList<Edge> edges = new ArrayList<>();
        int wt = 0;
        for(Edge e:edgeList){
            if(!dsu.isInSameSet(e.source, e.dest)){
                dsu.unionByRank(e.source, e.dest);
                edges.add(e);
                wt+=e.weight;
            }
            if(edges.size()==V-1)break;
        }
        if(edges.size()<V-1)System.out.println("MST Not possible");
        else{
            System.out.println(wt);
            for(Edge edge:edges){
                System.out.println(edge);
            }
        }
    }

}
