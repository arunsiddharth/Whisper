package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.Scanner;
import java.io.*;


public class Permutations {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        Printer.init();
        long n = sc.nextLong();
        if(n>1 && n<=3)Printer.println("NO SOLUTION");
        else{
            for(int i=1;i<=(n+1)/2;i++){
                if(n%2==0 && (n+1)/2+i<=n)Printer.print((n+1)/2+i);
                Printer.print(i);
                if(n%2==1 && (n+1)/2+i<=n)Printer.print((n+1)/2+i);
            }
        }
        sc.close();
        Printer.close();
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


