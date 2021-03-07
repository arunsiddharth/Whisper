package org.arunsiddharth.Domain;

public class WeightNode implements Comparable<WeightNode>{
    int vertex;
    int distance;
    public static WeightNode of(int node, int distance){
        WeightNode n = new WeightNode();
        n.vertex = node;
        n.distance = distance;
        return n;
    }

    public String toString(){
        return "["+distance+", "+vertex+"]";
    }

    public int hashCode(){
        return this.vertex*31+this.distance;
    }
    public boolean equals(Object obj){
        WeightNode node = (WeightNode)obj;
        return ((this.vertex==node.vertex) && (this.distance==node.distance));
    }

    public int compareTo(WeightNode node){
        if((this.vertex==node.vertex) && (this.distance==node.distance))return 0;
        else return this.distance<node.distance?-1:1;
    }
}