package org.arunsiddharth.Arrays;

import java.util.Arrays;

public class DutchFlagProblem {

    public static void solve(int[] array) {
        int n = array.length;
        int start=0, end=n-1;
        while(start<n && array[start]==0)start++;
        while(end>=0 && array[end]==2)end--;
        for(int i=start;i<=end;){
            if(array[i]==0){
                swap(array, i++, start++);
            }else if(array[i]==2){
                swap(array, i, end--);
            }else{
                i++;
            }
        }
    }

    public static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 0, 2, 0, 1, 2, 0, 0, 1};
        solve(array);
        System.out.println(Arrays.toString(array));
    }
    
}
