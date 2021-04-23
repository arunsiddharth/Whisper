package org.arunsiddharth.Practice.KickStart;
import java.util.*;
import java.io.*;

class Solution{

    /**
     * 
1
6 4
1 0 0 0
1 0 0 1
1 1 1 1
1 0 1 0
1 0 1 0
1 1 1 0
     * 
     * 
     * 
     * @param a
     * @param b
     * @return
     */
    public static long find(long[][] a, long[][] b){
        int m = a.length;
        int n = a[0].length;
        long result = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(a[i][j]>=b[i][j] && a[i][j]>1 && b[i][j]>=2){
                    result+=Math.min(a[i][j]/2, b[i][j])-1;
                }
            }
        }
        return result;
    }
    public static void pm(long[][] a) throws Exception{
        for(int i=0;i<a.length;i++){
            Printer.println(Arrays.toString(a[i]));
        }
        Printer.println("");
    }
    public static long solve(int[][] mat) throws Exception{
        int m = mat.length;
        int n = mat[0].length;
        long result = 0;
        long[][] up = new long[m][n];
        long[][] down = new long[m][n];
        long[][] left = new long[m][n];
        long[][] right = new long[m][n];
        for(int i=m-1;i>=0;i--){
            for(int j=0;j<n;j++){
                if(mat[i][j]==1)up[i][j] = right[i][j] = 1;
                if(i!=m-1 && mat[i][j]==1){
                    up[i][j]+=up[i+1][j];
                }
                if(j!=0 && mat[i][j]==1){
                    right[i][j]+=right[i][j-1];
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=n-1;j>=0;j--){
                if(mat[i][j]==1)down[i][j] = left[i][j] = 1;
                if(i!=0 && mat[i][j]==1){
                    down[i][j]+=down[i-1][j];
                }
                if(j!=n-1 && mat[i][j]==1){
                    left[i][j]+=left[i][j+1];
                }
            }
        }
        long x;
        x=find(down, left);
        //Printer.println(x);
        result+=x;
        x=find(left, down);
        //Printer.println(x);
        result+=x;
        x=find(down, right);
        //Printer.println(x);
        result+=x;
        x=find(right, down);
        //Printer.println(x);
        result+=x;
        x=find(up, left);
        //Printer.println(x);
        result+=x;
        x=find(left, up);
        //Printer.println(x);
        result+=x;
        x=find(up, right);
        //Printer.println(x);
        result+=x;
        x=find(right, up);
        //Printer.println(x);
        result+=x;

        return result;
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        int t = Reader.nextInt();
        for(int cs = 1;cs<=t;cs++){
            int m = Reader.nextInt();
            int n = Reader.nextInt();
            int[][] mat = new int[m][n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    mat[i][j] = Reader.nextInt();
                }
            }
            Printer.println("Case #"+cs+": "+solve(mat));
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