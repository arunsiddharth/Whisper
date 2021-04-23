package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.io.*;
import java.util.*;


public class DigitQueries {

    static long binPow(long base, long exp){
        long result = 1;
        while(exp>0){
            if((exp&1)!=0)result*=base;
            base*=base;
            exp>>=1;
        }
        return result;
    }
    /**
     * Lesson learnt
     * Never use pow methods of library if range ever getting to long
     * Double check you log functions
     * @param dp
     * @param n
     * @return
     * @throws Exception
     */
    static char solve(long[] dp, long n) throws Exception{
        long numberOfDigits = 0;
        for(int i=0;i<=18;i++)if(dp[i]>=n){
            numberOfDigits=i;
            break;
        }
        long sumOfLessThanDigits = dp[(int)numberOfDigits-1];
        long actualDigitPosition = n-sumOfLessThanDigits;
        long digitIndex = ((actualDigitPosition+numberOfDigits-1)/numberOfDigits);
        long actualNumber = binPow(10, numberOfDigits-1)-1+digitIndex;
        String str = String.valueOf(actualNumber);
        long index = (numberOfDigits+(actualDigitPosition)%numberOfDigits-1)%numberOfDigits;
        return str.charAt((int)index);
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int t = Reader.nextInt();
        long[] dp = new long[19];
        for(int i=1;i<=18;i++){
            dp[i]=i*9*binPow(10, i-1);
            dp[i]+=dp[i-1];
        }
        dp[18]=Long.MAX_VALUE;
        //System.out.println(Arrays.toString(dp));
        while(t-- >0){
            long n = Reader.nextLong();
            System.out.println(solve(dp,n));
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
