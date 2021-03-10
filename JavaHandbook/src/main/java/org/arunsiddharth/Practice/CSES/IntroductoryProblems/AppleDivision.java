package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;

class AppleDivision{
    static List<Long> weightList;
    static void solve(int[] apples, int i, long currentWeight) throws IOException{
        if(i==apples.length){
            weightList.add(currentWeight);
            return;
        }
        currentWeight+=apples[i];
        solve(apples, i+1, currentWeight);
        currentWeight-=apples[i];
        solve(apples, i+1, currentWeight);
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int n = Reader.nextInt();
        int[] apples = new int[n];
        for(int i=0;i<n;i++)apples[i] = Reader.nextInt();
        long sumWeight = 0L;
        for(int wt:apples)sumWeight+=wt;
        weightList = new ArrayList<>();
        solve(apples, 0, 0L);
        long diff = Long.MAX_VALUE;
        for(long wt:weightList){
            diff = Math.min(diff, Math.abs(2*wt-sumWeight));
        }
        Printer.println(diff);
        Reader.close();
        Printer.close();
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