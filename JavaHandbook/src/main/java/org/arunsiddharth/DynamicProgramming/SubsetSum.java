package org.arunsiddharth.DynamicProgramming;

public class SubsetSum {

    public static boolean isSubsetSumPossible(int[] array, int targetSum){
        int n = array.length;
        boolean[][] dp = new boolean[targetSum+1][n+1];
        for(int i=0;i<=targetSum;i++){
            for(int j=0;j<=n;j++){
                if(i==0)dp[i][j]=true;
                else if(j==0)dp[i][j]=false;
                else{
                    dp[i][j] = ((i>=array[j-1])?dp[i-array[j-1]][j-1]:false)|dp[i][j-1];
                }
            }
        }
        return dp[targetSum][n];
    }
    public static void main(String[] args){
        int[] array = {3, 34, 4, 12, 5, 2};
        int targetSum = 9;
        System.out.println(isSubsetSumPossible(array, targetSum));
    }
}
