package org.arunsiddharth.DynamicProgramming;

import java.util.Arrays;

public class MultiplierCardSelection {
    static int[][] dp;
    public static int solve(int[] cards, int[] multiplier, int start, int end){
        if(start>end)return 0;
        int m = multiplier.length;
        int n = cards.length;
        int multiplierIndex = start+n-end-1;
        if(multiplierIndex>=m)return 0;
        if(dp[start][end]!=-1)return dp[start][end];
        dp[start][end] = Math.max(
            cards[start]*multiplier[multiplierIndex]+solve(cards, multiplier, start+1, end),
            cards[end]*multiplier[multiplierIndex]+solve(cards, multiplier, start, end-1)
            );
        return dp[start][end];
    }
    public static void main(String[] args){
        //int[] cards = {2,13,7,15};
        //int[] multiplier = {2,3};
        int[] cards2 = {-2,8,1,15,-6};
        int[] multiplier2 = {3,2,5};
        int n = cards2.length;
        dp = new int[n][n];
        for(int i=0;i<n;i++)Arrays.fill(dp[i], -1);
        System.out.println(solve(cards2, multiplier2, 0, n-1));
    }
}
