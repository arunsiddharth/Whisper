package org.arunsiddharth.DynamicProgramming;

import java.util.*;

public class lis {

    public static int lisn2(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        int result = 1;
        for(int i=0;i<n;i++){
            dp[i] = 1;
            parent[i] = -1;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j] && 1+dp[j]>dp[i]){
                    dp[i] = 1+dp[j];
                    parent[i] = j;
                }
            }
            result = Math.max(result, dp[i]);
        }
        int i=n-1;
        while(i>=0 && dp[i]!=result)i--;
        List<Integer> list = new ArrayList<>();
        while(i!=-1){
            list.add(i);
            i=parent[i];
        }
        Collections.reverse(list);
        //System.out.println(list);
        return result;
    }

    public static int lowerBound(int[] dp, int length, int value){
        int low = 0;
        int high = length-1;
        int result = -1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(dp[mid]<value){
                result = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return result;
    }

    public static int lisnlogn(int[] arr){
        int n =arr.length;
        int length = 1;
        int[] dp = new int[n];
        dp[0] = arr[0];
        for(int i=1;i<n;i++){
            int value = arr[i];
            if(dp[length-1]<value){
                dp[length++] = value;
            }else{
                int idx = lowerBound(dp, length, value);
                dp[idx+1] = value;
            }
        }
        return length;
    }


    public static void main(String[] args){
        int[] arr = {1,2,4,3,4,6,5,6,8,7};
        System.out.println(lisnlogn(arr));
    }
}
