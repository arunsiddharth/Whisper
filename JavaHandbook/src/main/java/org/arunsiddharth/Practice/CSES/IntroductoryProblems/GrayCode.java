package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;


public class GrayCode {
    static int getGrayCode(int number){
        return number^(number>>1);
    }
    static String getBinaryString(int num, int length){
        String binString = Integer.toBinaryString(num);
        int repetition = length-binString.length();
        return new String("0").repeat(repetition)+binString;
    }
    static void solve(int n) throws Exception{
        for(int i=0;i<(1<<n);i++){
            Printer.println(getBinaryString(getGrayCode(i), n));
        }
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
