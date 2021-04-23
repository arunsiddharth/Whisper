package org.arunsiddharth.DynamicProgramming;

import java.util.Arrays;

public class ShortestSupersequence {
    public static int shortestSupersequence(String a,String b){
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0)dp[i][j]=j;
                else if(j==0)dp[i][j]=i;
                else if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }else{
                    dp[i][j] = 1+Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        for(int i=0;i<=m;i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        int i=m,j=n;
        String result = "";
        while(i>0 && j>0){
            if(a.charAt(i-1)==b.charAt(j-1)){
                result = a.charAt(i-1)+result;
                i--;
                j--;
            }else if(dp[i-1][j]<dp[i][j-1]){
                result = a.charAt(i-1)+result;
                i--;
            }else{
                result = b.charAt(j-1)+result;
                j--;
            }
        }
        while(i>0){
            result=a.charAt(i-1)+result;
            i--;
        }
        while(j>0){
            result=b.charAt(j-1)+result;
            j--;
        }
        System.out.println(result);
        return dp[m][n];
    }
    public static void main(String[] args){
        String a = "pear";
        String b = "peach";
        System.out.println(shortestSupersequence(a, b));
    }
}
