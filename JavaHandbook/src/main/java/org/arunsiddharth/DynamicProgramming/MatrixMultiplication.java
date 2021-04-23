package org.arunsiddharth.DynamicProgramming;

import java.lang.reflect.Member;
import java.util.Arrays;

public class MatrixMultiplication {

    static int[][] memo;

    public static int matrixMultiplication(int[] matArr, int start, int end){
        if(memo[start][end]!=-1)return memo[start][end];
        if(start>end-2)return 0;
        if(start==end-2){
            memo[start][end] =  matArr[start]*matArr[start+1]*matArr[end];
        }else{
            memo[start][end] = Integer.MAX_VALUE;
            for(int i=start+1;i<end;i++){
                memo[start][end] = Math.min(memo[start][end], 
                matrixMultiplication(matArr, start, i)+matArr[start]*matArr[i]*matArr[end]+matrixMultiplication(matArr, i, end)
                );
            }
        }
        return memo[start][end];
    }
    public static void main(String[] args){
        int[] matArr2 = {40, 20, 30, 10, 30};
        int[] matArr = {2, 40, 2, 40, 5};
        int n = matArr.length;
        memo = new int[n][n];
        for(int i=0;i<n;i++)Arrays.fill(memo[i], -1);
        System.out.println(matrixMultiplication(matArr, 0, matArr.length-1));
    }
}
