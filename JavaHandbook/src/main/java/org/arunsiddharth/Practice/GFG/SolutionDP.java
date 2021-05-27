package org.arunsiddharth.Practice.GFG;

import java.io.*;
import java.util.*;

public class SolutionDP {

    public static int minJumps(int[] arr, int src, int dest){
        if(src>=dest)return 0;
        if(arr[src]==0)return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=arr[src];i++){
            int jumps = minJumps(arr, src+i, dest);
            if(jumps!=Integer.MAX_VALUE && jumps+1<min){
                min = 1+jumps;
            }
        }
        return min;
    }

    public static int editDistance(String a, String b, int ia, int ib){
        int m = a.length();
        int n = b.length();
        if(ia==m)return n-ib;//insert char
        if(ib==n)return m-ia;//remove char
        if(a.charAt(ia)==b.charAt(ib)){
            return editDistance(a, b, ia+1, ib+1);
        }else{
            int add = editDistance(a, b, ia, ib+1);//add
            int remove = editDistance(a, b, ia+1, ib);//remove
            int replace = editDistance(a, b, ia+1, ib+1);//replace
            return 1+Math.min(add, Math.min(remove, replace));
        }
    }


    public static int coinChange(int coins[], int sum, int idx){
        if(sum<0 || idx==-1)return 0;
        if(sum==0)return 1;
        int ways = 0;
        ways += coinChange(coins, sum, idx-1);
        //ways += coinChange(coins, sum-coins[idx], idx-1);
        ways += coinChange(coins, sum-coins[idx], idx);
        return ways;
    }

    public static boolean equalPartition(int[] arr, int idx, int sum){
        if(sum==0)return true;
        if(idx<0 || sum < 0)return false;
        return equalPartition(arr, idx-1, sum-arr[idx])||equalPartition(arr, idx-1, sum);
    }

    public static int eggDrops(int eggs, int floors){
        if(eggs<=0 || floors<=0)return 0;
        if(floors==1)return 1;
        if(eggs==1)return floors;
        int ways = Integer.MAX_VALUE;
        for(int k=1;k<=floors;k++){
            ways = Math.min(ways, 1+Math.max(eggDrops(eggs-1, k-1), eggDrops(eggs, floors-k)));
        }
        return ways;
    }

    public static int maximumAmount(int[] arr, int si, int ei, boolean turn){
        if(si>=ei)return 0;
        if(turn){
            return Math.max(arr[si]+maximumAmount(arr, si+1, ei, !turn),arr[ei]+maximumAmount(arr, si, ei-1, !turn));
        }else{
            return Math.min(maximumAmount(arr, si+1, ei, !turn),maximumAmount(arr, si, ei-1, !turn));
        }
    }
    
    public static void main(String[] args){
        int myarr[] = {8, 15, 3, 7};
        System.out.println(maximumAmount(myarr, 0, myarr.length-1, true));
    }
}


class Reader {
    private static BufferedReader br;
    private static StringTokenizer stk;
    public static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer("");
    }
    public static String next() throws IOException {
        while(!stk.hasMoreTokens()){
            stk = new StringTokenizer(br.readLine());
        }
        return stk.nextToken();
    }
    public static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    public static void close() throws IOException {
        br.close();
    }
}