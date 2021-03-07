package org.arunsiddharth.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;


class Reader {
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


class Printer {
    private static BufferedWriter bw;
    public static void init() {
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public static void print(Object object) throws IOException{
        bw.append(""+object+" ");
    }
    public static void println()throws IOException {
        bw.append("\n");
    }
    public static void close()throws IOException{
        bw.close();
    }
}

public class ConsoleIO {
    
}
