package org.arunsiddharth.Practice.CSES.SortingAndSearching;

import java.util.*;
import java.io.*;

public class FerrisWheel {
    public static int solve(long[] weights, long limit){
        int result = 0;
        int n = weights.length;
        int i=0,j=n-1;
        sort(weights);
        while(i<=j){
            long sum = weights[i]+weights[j];
            result++;
            if(sum<=limit){
                i++;
                j--;
            }else{
                j--;
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int n = Reader.nextInt();
        int k = Reader.nextInt();
        long[] weights = new long[n];
        for(int i=0;i<n;i++)weights[i] = Reader.nextLong();
        Printer.println(solve(weights, k));
        Reader.close();
        Printer.close();
    }

    public static void sort(long[] arr){
        int n = arr.length;
        Random r = new Random();
        for(int i=0;i<n;i++){
            int j = r.nextInt(n);
            long tmp = arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
        Arrays.sort(arr);
    }
    static class Reader {
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
        public static long nextLong() throws IOException {
            return Long.parseLong(next());
        }
        public static void close() throws IOException {
            br.close();
        }
    }

    static class Printer {
        private static BufferedWriter bw;
        public static void init() {
            bw=new BufferedWriter(new OutputStreamWriter(System.out));
        }
        public static void print(Object object) throws IOException{
            bw.append(""+object+" ");
        }
        public static void println(Object object)throws IOException {
            bw.append(""+object+"\n");
        }
        public static void close()throws IOException{
            bw.close();
        }
    }
}
