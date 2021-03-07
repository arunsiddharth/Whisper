package org.arunsiddharth.Tree;

interface QueryInterface {
    public void increment(int i, int j, int val);
    public int minimum(int i, int j);
}

class RangeSlow implements QueryInterface{
    int[] arr;
    public RangeSlow(int n){
        arr = new int[n];
    }

    public void increment(int i, int j, int val){
        for(int k=i;k<=j;k++){
            arr[k] += val;
        }
    }
    public int minimum(int i, int j){
        int res = arr[i];
        for(int k=i+1; k<=j;k++){
            res = Math.min(res, arr[k]);
        }
        return res;
    }
}

public class SegmentTree implements QueryInterface{
    int n;
    int[] lo, hi, min, delta;

    public SegmentTree(int size){
        this.n = size;
        lo = new int[4*n+1];
        hi = new int[4*n+1];
        min = new int[4*n+1];
        delta = new int[4*n+1];
        init(1, 0, n-1);
    }

    void init(int node, int left, int right){
        lo[node] = left;
        hi[node] = right;
        if(left == right){
            //single node;
            return;
        }
        int mid = left + (right-left)/2;
        init(2*node, left, mid);
        init(2*node+1, mid+1, right);
    }

    public void increment(int left, int right, int val){
        increment(1, left, right, val);
    }

    public int minimum(int left, int right){
        return minimum(1, left, right);
    }

    /**
     * Propagates the delta at node to its children
     * @param node
     */
    void prop(int node){
        delta[2*node] += delta[node];
        delta[2*node+1] += delta[node];
        delta[node] = 0;
    }

    /**
     * Updates the node with aggregation of children values
     * @param node
     */
    void update(int node){
        min[node] = Math.min(min[2*node]+delta[2*node], min[2*node+1]+delta[2*node+1]);
    }

    void increment(int node, int left, int right, int val){
        //outside of range of node
        if(right<lo[node] || left>hi[node]){
            return;
        }
        //node completely covers interval
        if(left<=lo[node] && hi[node]<=right){
            delta[node]+=val;
            return;
        }

        //partial update
        //push change down to both
        prop(node);

        increment(2*node, left, right, val);
        increment(2*node+1, left, right, val);

        //aggregate the ranges to parent
        update(node);
    }

    int minimum(int node, int left, int right){
        if(right<lo[node] || lo[node]< left){
            return Integer.MAX_VALUE;
        }

        if(left <= lo[node] && hi[node]<= right){
            return min[node] + delta[node];
        }

        prop(node);

        int minLeft = minimum(2*node, left, right);
        int minRight = minimum(2*node+1, left, right);

        update(node);

        return Math.min(minLeft, minRight);
    }

}
