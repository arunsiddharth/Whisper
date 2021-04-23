package org.arunsiddharth.Practice.Codejam;

import java.util.Arrays;
import java.util.Scanner;

public class ReversortEngineering {
    public static boolean nextPermutation(int[] arr){
        int n = arr.length;
        int defPoint = n-2;
        while(defPoint>=0 && arr[defPoint]>arr[defPoint+1]){
            defPoint--;
        }
        if(defPoint==-1)return false; 
        reverse(arr, defPoint+1, n-1);
        if(defPoint!=-1){
            for(int i=defPoint+1;i<n;i++){
                if(arr[i]>arr[defPoint]){
                    arr[defPoint]+=arr[i]-(arr[i]=arr[defPoint]);
                    break;
                }
            }
            return true;
        }
        return false;
    }
    public static void reverse(int[] arr, int i, int j){
        while(i<j){
            arr[i]+=arr[j]-(arr[j]=arr[i]);
            i++;
            j--;
        }
    }
    public static int findMinIndex(int[] arr, int idx){
        int n = arr.length;
        int result = idx;
        for(int i=idx;i<n;i++){
            if(arr[i]<arr[result]){
                result = i;
            }
        }
        return result;
    }
    public static int reversort(int[] arr){
        int result = 0;
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            int index = findMinIndex(arr, i);
            result+=index-i+1;
            reverse(arr, i, index);
        }
        return result;
    }
    public static int[] solve(int n, int sum){
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=i+1;
        }
        do{
            int[] copy = Arrays.copyOf(arr, n);
            if(reversort(copy)==sum)return arr;
        }while(nextPermutation(arr));
        return new int[]{};
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int ct=1;ct<=t;ct++){
            int n = sc.nextInt();
            int sum = sc.nextInt();
            int[] result = solve(n, sum);
            System.out.print("Case #"+ct+": ");
            if(result.length==0)System.out.println("IMPOSSIBLE");
            else{
                for(int i:result){
                    System.out.print(i+" ");
                }
                System.out.println();
            }
        }
    }
}
