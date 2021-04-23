package org.arunsiddharth.Practice.CSES.SortingAndSearching;

import java.util.*;
import java.io.*;

public class Apartments {
    public static int solve(long[] applicants, long[] apartments, long k){
        int result = 0;
        int n = applicants.length;
        int m = apartments.length;
        sort(applicants);
        sort(apartments);
        int i,j;
        i=j=0;
        while(i<n && j<m){
            if(apartments[j]>=applicants[i]-k && apartments[j]<=applicants[i]+k){
                result++;
                i++;
                j++;
            }else if(apartments[j]<applicants[i]-k){
                j++;
            }else{
                i++;
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        long k = Reader.nextLong();
        long[] applicants = new long[n];
        long[] apartments = new long[m];
        for(int i=0;i<n;i++)applicants[i] = Reader.nextLong();
        for(int i=0;i<m;i++)apartments[i] = Reader.nextLong();
        Printer.println(solve(applicants, apartments, k));
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
