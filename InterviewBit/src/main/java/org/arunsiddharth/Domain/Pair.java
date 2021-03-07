package org.arunsiddharth.Domain;

public class Pair{
    public int first,second;
    public static Pair of(int a, int b){
        Pair p = new Pair();
        p.first = a;
        p.second = b;
        return p;
    }
    public boolean equals(Object obj){
        Pair p = (Pair)obj;
        if(this==p)return true;
        else if(this.first==p.first && this.second==p.second)return true;
        return false;
    }
    public int hashCode(){
        return 31*this.first+this.second;
    }
}