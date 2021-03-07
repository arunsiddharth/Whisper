package org.arunsiddharth.Graph;

public class GraphPractice {
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
        distanceGraph.kruskal();

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

    }
}
