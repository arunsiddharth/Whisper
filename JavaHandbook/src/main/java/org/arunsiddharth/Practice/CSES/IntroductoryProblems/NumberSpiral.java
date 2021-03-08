package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.io.*;
import java.util.*;


public class NumberSpiral {

    static long solve(long x, long y){
        long xVal, yVal;
        boolean isXPerfectSquare, isYPerfectSquare;
        isXPerfectSquare = (x%2==0);
        isYPerfectSquare = (y%2==1);
        xVal = isXPerfectSquare?x*x:(x-1)*(x-1)+1;
        yVal = isYPerfectSquare?y*y:(y-1)*(y-1)+1;
        long pivotValue, length;
        boolean shouldDecrement;
        if(xVal>=yVal){
            pivotValue = xVal;
            length = y;
            shouldDecrement = isXPerfectSquare;
        }else{
            pivotValue = yVal;
            length = x;
            shouldDecrement = isYPerfectSquare;
        }
        length--;
        pivotValue+=(shouldDecrement)?-length:length;
        return pivotValue;
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int t = Reader.nextInt();
        while(t-- >0){
            int x = Reader.nextInt();
            int y = Reader.nextInt();
            Printer.println(solve(x, y));
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
