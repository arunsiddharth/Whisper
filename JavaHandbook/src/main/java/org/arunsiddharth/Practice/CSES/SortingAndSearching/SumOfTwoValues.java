package org.arunsiddharth.Practice.CSES.SortingAndSearching;

import java.util.*;
import java.io.*;

public class SumOfTwoValues {
    public static void main(String[] args) throws Exception{
        Reader r = new Reader();
        Printer.init();
        int n = r.nextInt();
        int k = r.nextInt();
        int[][] nums = new int[n][2];
        for(int i=0;i<n;i++){
            nums[i][0] = r.nextInt();
            nums[i][1] = i;
        }
        int i = 0;
        int j = n-1;
        boolean found = false;
        sort(nums);
        while(i<j){
            long sum = (long)nums[i][0]+nums[j][0];
            if(sum==k){
                Printer.println(nums[i][1]+1+" "+(nums[j][1]+1));
                found = true;
                break;
            }else if(sum<k)i++;
            else j--;
        }
        if(!found)Printer.println("IMPOSSIBLE");
        Printer.close();
    }

    public static void sort(int[][] arr){
        int n = arr.length;
        Random r = new Random();
        for(int i=0;i<n;i++){
            int j = r.nextInt(n);
            int[] tmp = arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
        Arrays.sort(arr, (a,b)->{
            return (a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        });
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
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
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
}
