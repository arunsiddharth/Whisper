package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=1;i<n;i++){
            int num = sc.nextInt();
            arr[num-1]++;
        }
        for(int i=0;i<n;i++){
            if(arr[i]==0){
                System.out.println(i+1);
                break;
            }
        }
        sc.close();
    }
}
