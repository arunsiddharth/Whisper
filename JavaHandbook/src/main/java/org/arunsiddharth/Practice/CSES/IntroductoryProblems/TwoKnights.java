package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;

public class TwoKnights {
    static long solve(long k){
        long k2 = k*k;
        return (k2*(k2-1))/2-4*(k-1)*(k-2);
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int n = Reader.nextInt();
        for(int i=1;i<=n;i++){
            Printer.println(solve(i));
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
