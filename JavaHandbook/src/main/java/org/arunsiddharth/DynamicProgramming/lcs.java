package org.arunsiddharth.DynamicProgramming;

import java.util.Arrays;

public class lcs {

    public static int lcs(String a, String b, int m, int n){
        if(m<=0 || n<=0)return 0;
        if(a.charAt(m-1)==b.charAt(n-1)){
            return 1+lcs(a,b,m-1,n-1);
        }else{
            return Math.max(lcs(a,b,m-1,n), lcs(a,b,m,n-1));
        }
    }

    public static String lcs(String a, String b){
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0)dp[i][j] = 0;
                else if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        for(int i=0;i<=m;i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        int index = dp[m][n];
        char[] result = new char[index];
        int i=m, j=n;
        while(i>0 && j>0){
            if(a.charAt(i-1)==b.charAt(j-1)){
                result[index-1]=a.charAt(i-1);
                index--;
                i--;
                j--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        return new String(result);
    }

    public static int lcsSpaceOptimised(String a, String b){
        int bi=0,m = a.length();
        int n = b.length();
        int[][] dp = new int[2][n+1];
        for(int i=0;i<=m;i++){
            bi = 1&i;
            for(int j=0;j<=n;j++){
                if(i==0 || j==0)dp[bi][j]=0;
                else if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[bi][j] = 1+dp[1-bi][j-1];
                }else{
                    dp[bi][j] = Math.max(dp[1-bi][j], dp[bi][j-1]);
                }
            }
        }
        return dp[bi][n];
    }


    public static void main(String[] args){
        String a = "abcdefghi";
        String b = "dbdcedgfhi";
        System.out.println(lcs(a, b));
    }
    
}
