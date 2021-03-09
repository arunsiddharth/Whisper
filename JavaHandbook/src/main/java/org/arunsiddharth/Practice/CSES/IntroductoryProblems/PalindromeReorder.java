package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;

public class PalindromeReorder {
    static void solve(String str) throws Exception{
        char[] carr = str.toCharArray();
        int n = str.length();
        int[] count = new int[26];
        for(int i=0;i<n;i++)count[carr[i]-'A']++;
        int oddCount = 0;
        for(int c:count)if(c%2==1)oddCount++;
        if((n%2==0 && oddCount>0)||(n%2==1 && oddCount>1)){
            Printer.println("NO SOLUTION");
            return;
        }
        char[] result = new char[n];
        int k=0;
        for(int i=0;i<26;i++){
            int sub = 0;
            for(int c=0;c<count[i]/2;c++){
                result[k] = result[n-1-k] = (char)('A'+i);
                k++;
                sub-=2;
            }
            count[i]+=sub;
        }
        int oddIndex = -1;
        for(int i=0;i<26;i++)if(count[i]==1){
            oddIndex = i;
            break;
        }
        if(oddIndex!=-1)result[k]=(char)('A'+oddIndex);
        Printer.println(new String(result));
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        String str = Reader.next();
        solve(str);
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
