package org.arunsiddharth.Arrays;

import java.util.Arrays;
import java.util.List;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Random;


public class Sorting {
    
    public static <T extends Comparable<T>> void insertionSort(T[] array){
        int n = array.length;
        for(int i=1;i<n;i++){
            T key = array[i];
            int j = i-1;
            while(j>=0 && array[j].compareTo(key)>0){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=key;
        }
    }


    public static <T extends Comparable<T>> void selectionSort(T[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex = findMinIndex(array, i);
            if(minIndex!=i){
                swap(array, i, minIndex);
            }
        }
    }


    public static <T extends Comparable<T>> void bubbleSort(T[] array){
        int n = array.length;
        for(int i=0;i<n-1;i++){
            boolean isSwapped = false;
            for(int j=0;j<n-i-1;j++){
                if(array[j].compareTo(array[j+1])>0){
                    swap(array, j, j+1);
                    isSwapped=true;
                }
            }
            if(!isSwapped){
                break;
            }
        }
    }


    public static <T extends Comparable<T>> void mergeSort(T[] array, int start, int end){
        if(start<end){
            int mid = start + (end - start)/2;
            mergeSort(array, start, mid);
            mergeSort(array, mid+1, end);
            merge(array, start, mid, end);
        }
    }


    public static <T extends Comparable<T>> void quickSort(T[] array, int start, int end){
        if(start<end){
            int pivot = partition(array, start, end);
            quickSort(array, start, pivot-1);
            quickSort(array, pivot+1, end);
        }
    }

    public static <T extends Comparable<T>> int partition(T[] array, int start, int end){
        int partitionIndex = start;
        Random rand = new Random();
        swap(array, start+rand.nextInt(end-start+1), end);
        T pivot = array[end];
        for(int i=start;i<end;i++){
            if(array[i].compareTo(pivot)<=0){
                swap(array, i, partitionIndex++);
            }
        }
        swap(array, partitionIndex, end);
        return partitionIndex;
    }

    public static <T extends Comparable<T>> void merge(T[] array, int start, int mid, int end){
        List<T> list = new ArrayList<>(end-start+1);
        int i=start;
        int j=mid+1;
        while(i<=mid && j<=end){
            if(array[i].compareTo(array[j])<=0){//make it stable by <=
                list.add(array[i++]);
            }else{
                list.add(array[j++]);
            }
        }
        while(i<=mid){
            list.add(array[i++]);
        }
        while(j<=end){
            list.add(array[j++]);
        }
        for(i=start;i<=end;i++){
            array[i]=list.get(i-start);
        }
    }


    public static <T extends Comparable<T>> int findMinIndex(T[] array, int start){
        int minIndex = start;
        while(start<array.length){
            if(array[start].compareTo(array[minIndex])<0){
                minIndex=start;
            }
            start++;
        }
        return minIndex;
    }

    public static <T extends Comparable<T>> void swap(T[] array, int i1, int i2){
        T tmp = array[i1];
        array[i1]=array[i2];
        array[i2]=tmp;
    }

    public static void main(String[] args){
        Integer[] array = {5,4,3,2,1};
        quickSort(array, 0 , array.length-1);
        System.out.println(Arrays.toString(array));
    }
}