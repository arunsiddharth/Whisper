package org.arunsiddharth.DynamicProgramming;

import java.util.*;

public class Knapsack {

    static int[][] memo;

    public static int knapsack(int[] weight, int[] value, int itemsLeft, int knapsackSize){
        if(itemsLeft==0 || knapsackSize==0)return 0;
        if(memo[knapsackSize][itemsLeft]!=-1)return memo[knapsackSize][itemsLeft];
        memo[knapsackSize][itemsLeft] = Math.max(knapsack(weight, value, itemsLeft-1, knapsackSize),knapsackSize>=weight[itemsLeft-1]?
        value[itemsLeft-1]+knapsack(weight, value, itemsLeft-1, knapsackSize-weight[itemsLeft-1]):0);
        return memo[knapsackSize][itemsLeft];
    }

    public static int knapsackDP(int[] weight, int[] value, int knapsackSize){
        int n = weight.length;
        int[][] dp = new int[knapsackSize+1][n+1];
        for(int i=0;i<=knapsackSize;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0)dp[i][j]=0;
                else{
                    dp[i][j] = Math.max(dp[i][j-1], weight[j-1]<=i?dp[i-weight[j-1]][j-1]+value[j-1]:0);
                }
            }
        }
        return dp[knapsackSize][n];
    }

    public static int unboundedKnapsack(int[] weight, int[] value, int itemsLeft, int knapsackSize){
        if(itemsLeft==0 || knapsackSize==0)return 0;
        if(memo[knapsackSize][itemsLeft]!=-1)return memo[knapsackSize][itemsLeft];
        memo[knapsackSize][itemsLeft] = Math.max(knapsack(weight, value, itemsLeft-1, knapsackSize),knapsackSize>=weight[itemsLeft-1]?
        value[itemsLeft-1]+knapsack(weight, value, itemsLeft, knapsackSize-weight[itemsLeft-1]):0);
        return memo[knapsackSize][itemsLeft];
    }

    public static int unboundedKnapsackDP(int[] weight, int[] value, int knapsackSize){
        int n = weight.length;
        int[] dp = new int[knapsackSize+1];
        dp[0]=0;
        for(int i=1;i<=knapsackSize;i++){
            for(int j=0;j<n;j++){
                if(weight[j]<=i){
                    dp[i] = Math.max(dp[i], dp[i-weight[j]]+value[j]);
                }
            }
        }
        return dp[knapsackSize];
    }
    public static void main(String[] args){
        int[] value = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int knapsackSize = 50;
        memo = new int[knapsackSize+1][weight.length+1];
        for(int i=0;i<=knapsackSize;i++)Arrays.fill(memo[i], -1);
        //System.out.println(knapsack(weight, value, weight.length, knapsackSize)); 
        System.out.println(knapsackDP(weight, value, knapsackSize));
    }
}
