package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;

public class TowerOfHanoi {
    static void towerOfHanoi(int from, int to, int aux, int numberOfDisc) throws IOException{
        if(numberOfDisc==0)return;
        towerOfHanoi(from, aux, to, numberOfDisc-1);
        Printer.println(from+" "+to);
        towerOfHanoi(aux, to, from, numberOfDisc-1);
    }

    static void solve(int n) throws IOException{
        Printer.println((1<<n)-1);
        towerOfHanoi(1,3,2,n);
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
