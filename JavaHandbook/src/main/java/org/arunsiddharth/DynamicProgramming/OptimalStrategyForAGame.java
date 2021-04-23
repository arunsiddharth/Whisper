package org.arunsiddharth.DynamicProgramming;

import java.util.*;

public class OptimalStrategyForAGame {
    static int[][] memo;
    public static int optimalPlayRec(int[] arr, int start, int end){
        if(start>end)return 0;
        if(memo[start][end]!=-1)return memo[start][end];
        memo[start][end] =  Math.max(arr[start]+Math.min(optimalPlayRec(arr, start+2, end),optimalPlayRec(arr, start+1, end-1)),
                                    arr[end]+Math.min(optimalPlayRec(arr, start+1, end-1),optimalPlayRec(arr, start, end-2)));
        return memo[start][end];
    }

    public static int optimalPlayDP(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0;i<n-gap;i++){
                int start = i;
                int end = i+gap;
                int startAfter = start+2<=end?dp[start+2][end]:0;
                int endBefore = end-2>=start?dp[start][end-2]:0;
                int middle = start+1<=end-1?dp[start+1][end-1]:0;
                dp[start][end] = Math.max(arr[start]+Math.min(startAfter, middle),arr[end]+Math.min(middle, endBefore));
            }
        }
        return dp[0][n-1];
    }


    public static void main(String[] args){
        //int[] arr = {5,3,7,10};
        int[] arr = {8,15,3,7};
        int n = arr.length;
        memo = new int[n][n];
        for(int i=0;i<n;i++)Arrays.fill(memo[i], -1);
        //System.out.println(optimalPlayRec(arr, 0, arr.length-1));
        System.out.println(optimalPlayDP(arr));
    }
}
