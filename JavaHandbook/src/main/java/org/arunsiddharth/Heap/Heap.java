package org.arunsiddharth.Heap;

/**
 * Min Heap Code
 */
public class Heap {
    int[] arr;
    int size;
    int capacity;
    Heap(int cap){
        arr = new int[cap];
        size = 0;
        capacity = cap;
    }
    public int peek(){
        if(size==0)return Integer.MIN_VALUE;
        return arr[0];
    }
    public boolean offer(int val){
        if(size==capacity)return false;
        size++;
        int idx = size-1;
        arr[idx]=val;
        while(idx!=0){
            int p=(idx-1)/2;
            if(arr[p]>arr[idx]){
                arr[p]+=arr[idx]-(arr[idx]=arr[p]);
                idx=p;
            }else{
                break;
            }
        }
        return true;
    }
    public int poll(){
        if(size==0)return Integer.MIN_VALUE;
        int idx = size-1;
        arr[0]+=arr[idx]-(arr[idx]=arr[0]);
        size--;
        minHeapify(0);
        return arr[idx];
    }
    public void minHeapify(int idx){
        int l=2*idx+1,r=2*idx+2;
        int smallest = idx;
        if(l<size && arr[l]<arr[smallest])smallest=l;
        if(r<size && arr[r]<arr[smallest])smallest=r;
        if(smallest!=idx){
            arr[smallest]+=arr[idx]-(arr[idx]=arr[smallest]);
            minHeapify(smallest);
        }
    }

    public static void main(String[] args){
        Heap h = new Heap(11);
        h.offer(3);
        h.offer(2);
        h.offer(1);
        h.offer(15);
        h.offer(5);
        h.offer(4);
        h.offer(45);
        System.out.println(h.poll());
        System.out.println(h.peek());
    }
}