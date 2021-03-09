package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;

public class TwoSets {
    static void solve(long n) throws Exception{
        if((n*(n+1))%4!=0){
            Printer.println("NO");
            return;
        }
        Printer.println("YES");
        long n1,n2;
        n1 = (long)Math.ceil((double)n/2);
        n2 = n-n1;
        Printer.println(n1);
        int start = (n%2==0)?2:1;
        for(int i=start;i<=n;i+=4){
            Printer.print(i+" "+((i+1<=n)?(i+1):""));
        }
        Printer.println("");
        Printer.println(n2);
        start = (n%2==0)?1:3;
        if(start==1){
            Printer.print(1);
            start+=3;
        }
        for(int i=start;i<=n;i+=4){
            Printer.print(i+" "+((i+1<=n)?(i+1):""));
        }
        Printer.println("");
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int n = Reader.nextInt();
        solve(n);
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
