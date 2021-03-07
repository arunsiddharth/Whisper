package org.arunsiddharth.Domain;

public class Edge implements Comparable<Edge>{
    public int source;
    public int dest;
    public int weight;
    public int id;

    public static Edge of(int s, int d, int w){
        Edge e = new Edge();
        e.source = s;
        e.dest = d;
        e.weight = w;
        e.id = 0;
        return e;
    }

    public static Edge of(int src, int dst, int wt, int id){
        Edge edge = new Edge();
        edge.source = src;
        edge.dest = dst;
        edge.weight = wt;
        edge.id = id;
        return edge;
    }

    public int compareTo(Edge e){
        return this.weight-e.weight;
    }

    public int hashCode(){
        return this.weight*31+this.dest;
    }

    public boolean equals(Object ob){
        Edge e = (Edge)ob;
        if(this==e)return true;
        return this.weight==e.weight && this.source==e.source && this.dest==e.dest;
    }

    public String toString(){
        return "["+this.source+" -> "+this.dest+" : "+ this.weight+"]";
    }
}