package org.arunsiddharth.Practice.Codejam;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static boolean canMake(int[] requireds, int a, int b, int start){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        int count=0;
        int n = requireds.length;
        int[] required = Arrays.copyOfRange(requireds, 0, n);
        for(int i:required)count+=i;
        while(!dq.isEmpty() && count!=0){
            int element = dq.poll();
            if(element-1<n && element-1>=0 && required[element-1]>0){
                count--;
                required[element-1]--;
                continue;
            }
            if(a>0 && element-a>0)dq.offer(element-a);
            if(b>0 && element-b>0)dq.offer(element-b);
        }
        return count==0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int ct=1;ct<=t;ct++){
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int[] required = new int[n];
            for(int i=0;i<n;i++)required[i] = sc.nextInt();
            int low = n+1;
            int high = n+b;
            int result = -1;
            while(low<=high){
                int mid = (low + high)/2;
                if(canMake(required, a, b, mid)){
                    result = mid;
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
            if(result==-1){
                System.out.println("Case #"+ct+": IMPOSSIBLE");
            }else{
                System.out.println("Case #"+ct+": "+result);
            }
        }

    }
}
