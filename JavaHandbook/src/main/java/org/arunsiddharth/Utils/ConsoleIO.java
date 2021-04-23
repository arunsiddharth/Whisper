package org.arunsiddharth.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;

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

class FastReader{
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastReader(){
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
    private void fillBuffer() throws IOException
    {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private byte read() throws IOException
    {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException
    {
        if (din == null)
            return;
        din.close();
    }
    public int nextInt() throws IOException
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do
        {
            ret = ret * 10 + c - '0';
        }  while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
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
