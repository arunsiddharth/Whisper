package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.Scanner;
import java.io.*;

public class WeirdAlgorithm {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        Printer.init();
        long number = sc.nextLong();
        while(number!=1){
            Printer.print(number);
            if(number%2==0){
                number/=2;
            }else{
                number=number*3+1;
            }
        }
        Printer.println(1);
        Printer.close();
        sc.close();
    }
}

class Printer {
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