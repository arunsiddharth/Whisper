package org.arunsiddharth.DynamicProgramming;

import java.util.*;

public class WordBreak {
    static Set<String> dict;
    static int[][] memo;
    static int canBreak(String str, int start, int end){
        if(start>end)return 1;
        if(memo[start][end]!=-1)return memo[start][end];
        if(dict.contains(str.substring(start, end+1))){
            memo[start][end]= 1;
        }else{
            int contains = 0;
            for(int i=start;i<end;i++){
                contains|=canBreak(str, start, i)&canBreak(str, i+1, end);
            }
            memo[start][end]= contains;
        }
        return memo[start][end];
    }
    static boolean canBreak(String str){
        int n=str.length();
        boolean[][] dp = new boolean[n][n];
        for(int len=0;len<n;len++){
            for(int i=0;i<n-len;i++){
                dp[i][i+len]=false;
                if(dict.contains(str.substring(i, i+len+1)))dp[i][i+len]=true;
                else{
                    for(int j=0;j<len;j++){
                        dp[i][i+len]|=dp[i][i+j]&dp[i+j+1][i+len];
                    }
                }
            }
        }
        return dp[0][n-1];
    }
    public static void main(String[] args){
        String dictionary[] = {"mobile","samsung","sam","sung",
                            "man","mango","icecream","and",
                             "go","i","like","ice","cream"};
        String str = "ilikesamsung";
        int n = str.length();
        memo = new int[n][n];
        for(int i=0;i<n;i++)Arrays.fill(memo[i], -1);
        dict = new HashSet<>(dictionary.length);
        for(String word:dictionary)dict.add(word);
        System.out.println(canBreak(str));
    }
}











