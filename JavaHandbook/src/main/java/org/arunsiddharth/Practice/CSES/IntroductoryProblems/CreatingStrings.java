package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.*;
import java.io.*;

public class CreatingStrings {
    static boolean nextPermutation(char[] carr) throws IOException{
        int n = carr.length;
        int dippingPoint = n-2;
        while(dippingPoint!=-1 && carr[dippingPoint]>=carr[dippingPoint+1])dippingPoint--;
        if(dippingPoint==-1)return false;
        int start = dippingPoint+1;
        int end = n;
        for(int i=0;i<(end-start)/2;i++){
            carr[start+i]+=carr[end-1-i]-(carr[end-1-i]=carr[start+i]);
        }
        int i=dippingPoint+1;
        while(i<n && carr[dippingPoint]>=carr[i])i++;
        carr[dippingPoint]+=carr[i]-(carr[i]=carr[dippingPoint]);
        return true;
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        String str = Reader.next();
        char[] carr = str.toCharArray();
        Arrays.sort(carr);
        List<String> permutations = new ArrayList<>();
        do{
            permutations.add(new String(carr));
        }while(nextPermutation(carr));
        Printer.println(permutations.size());
        for(String permutation:permutations){
            Printer.println(permutation);
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
