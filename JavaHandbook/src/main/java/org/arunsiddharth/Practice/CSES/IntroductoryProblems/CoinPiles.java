package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;


class CoinPiles {
    static boolean solve(long a, long b) throws Exception{
        long delta = Math.abs(b-a);
        long x = Math.min(a-delta, b-delta);
        return (x>=0 && x%3==0);
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int t = Reader.nextInt();
        while(t-- >0){
            int a = Reader.nextInt();
            int b = Reader.nextInt();
            Printer.println(solve(a, b)?"YES":"NO");
        }
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