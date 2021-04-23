package org.arunsiddharth.Practice.CSES.SortingAndSearching;

import java.util.*;
import java.io.*;

class DistinctNumbers {

    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int n = Reader.nextInt();
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++)set.add(Reader.nextInt());
        Printer.println(set.size());
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
