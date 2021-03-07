package org.arunsiddharth.Arrays;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Min Heap
 */
class Heap{
    int arr[];
    int capacity;
    int size;
    Heap(int n){
        capacity = n;
        size = 0;
        arr = new int[n];
    }

    public boolean offer(int val){
        if(size==capacity)return false;
        int index= size++;
        arr[index]=val;
        while(index!=0){
            int parent = (index-1)/2;
            if(arr[parent]>arr[val]){
                arr[parent]+=arr[val]-(arr[val]=arr[parent]);
                index=parent;
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
        return arr[size];
    }

    public int peek(){
        if(size==0)return Integer.MIN_VALUE;
        return arr[0];
    }

    private void minHeapify(int idx){
        int l = 2*idx+1;
        int r = 2*idx+2;
        int smallest=idx;
        if(l<size && arr[l]<arr[smallest]){
            smallest=l;
        }
        if(r<size && arr[r]<arr[smallest]){
            smallest=r;
        }
        if(smallest!=idx){
            arr[smallest]+=arr[idx]-(arr[idx]=arr[smallest]);
            minHeapify(smallest);
        }
    }

}


public class HeapPractice {
    
    public static void main(String[] args){
        int[] a = {36, 27, -35, 43, -15, 36, 42, -1, -29, 12, -23, 40, 9, 13, -24, -10, -24, 22, -14, -39, 18, 17, -21, 32, -20, 12, -27, 17, -15, -21, -48, -28, 8, 19, 17, 43, 6, -39, -8, -21, 23, -29, -31, 34, -13, 48, -26, -35, 20, -37, -24, 41, 30, 6, 23, 12, 20, 46, 31, -45, -25, 34, -23, -14, -45, -4, -21, -37, 7, -26, 45, 32, -5, -36, 17, -16, 14, -7, 0, 37, -42, 26, 28};
        int[] b = {38, 34, -47, 1, 4, 49, -18, 10, 26, 18, -11, -38, -24, 36, 44, -11, 45, 20, -16, 28, 17, -49, 47, -48, -33, 42, 2, 6, -49, 30, 36, -9, 15, 39, -6, -31, -10, -21, -19, -33, 47, 21, 31, 25, -41, -23, 17, 6, 47, 3, 36, 15, -44, 33, -31, -26, -22, 21, -18, -21, -47, -31, 20, 18, -42, -35, -10, -1, 46, -27, -32, -5, -4, 1, -29, 5, 29, 38, 14, -22, -9, 0, 43};
        Arrays.sort(a);
        Collections.reverse(Arrays.asList(a));
        Arrays.sort(b);
        Collections.reverse(Arrays.asList(b));
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
    
}
