package org.arunsiddharth.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

class ReaderFile {
    private static BufferedReader br;
    private static StringTokenizer stk;
    public static void init(String fileName) throws Exception{
        br = new BufferedReader(new FileReader(fileName));
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
    public static void close() throws IOException{
        br.close();
    }
}

class PrinterFile {
    private static BufferedWriter bw;
    public static void init(String fileName) throws Exception{
        bw=new BufferedWriter(new FileWriter(new File(fileName)));
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

public class FileIO {
    public static void main(String[] args) throws Exception{
        String file = "test";
        String prefix = "/Users/arunsiddharth/Documents/Workspace/Deku/src/main/java/org/arunsiddharth/InterviewBit/";
        String suffix = ".txt";
        String outPrefix = "out_";
        String inputFileName = prefix+file+suffix;
        String outputFileName = prefix+outPrefix+file+suffix;        
        ReaderFile.init(inputFileName);
        PrinterFile.init(outputFileName);
    }
}
