package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.Scanner;

public class IncreasingArray {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int maxElement = 0;
        long numOfOperations = 0;
        for(int i=0;i<n;i++){
            maxElement = Math.max(maxElement, arr[i]);
            numOfOperations+=maxElement-arr[i];
        }
        System.out.println(numOfOperations);
    }
}
