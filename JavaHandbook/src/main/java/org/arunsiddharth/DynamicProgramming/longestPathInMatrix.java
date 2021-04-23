package org.arunsiddharth.DynamicProgramming;

import java.util.*;

public class longestPathInMatrix {
    public static int solve(int[][] mat, int[][] maxDistance, int i, int j){
        if(maxDistance[i][j]!=-1)return maxDistance[i][j];
        int n = mat.length;
        int result = 0;
        if(i-1>=0 && mat[i-1][j]==mat[i][j]+1)result = Math.max(result, solve(mat, maxDistance, i-1, j));
        if(i+1<n && mat[i+1][j]==mat[i][j]+1)result = Math.max(result, solve(mat, maxDistance, i+1, j));
        if(j-1>=0 && mat[i][j-1]==mat[i][j]+1)result = Math.max(result, solve(mat, maxDistance, i, j-1));
        if(j+1<n && mat[i][j+1]==mat[i][j]+1)result = Math.max(result, solve(mat, maxDistance, i, j+1));
        maxDistance[i][j] = 1+result;
        return maxDistance[i][j];
    }
    public static int lpim(int[][] mat){
        int n = mat.length;
        int[][] maxDistance = new int[n][n];
        for(int i=0;i<n;i++)Arrays.fill(maxDistance[i], -1);
        int result = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(maxDistance[i][j]==-1){
                    solve(mat, maxDistance, i, j);
                }
                result = Math.max(result, maxDistance[i][j]);
            }
        }
        return result;
    }
    public static void main(String[] args){
        int mat[][] = { { 1, 2, 9 }, 
                        { 5, 3, 8 }, 
                        { 4, 6, 7 } };
        System.out.println(lpim(mat));
    }
}
