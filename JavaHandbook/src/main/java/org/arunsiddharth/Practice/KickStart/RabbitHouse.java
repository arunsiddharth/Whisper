package org.arunsiddharth.Practice.KickStart;

import java.util.*;

import java.io.*;

public class RabbitHouse {
    public static long solve(int[][] mat){
        long result = 0;
        int m = mat.length;
        int n = mat[0].length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(m*n, (a,b)->{
            return b.height-a.height;
        });
        Map<String, Integer> hm = new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]!=0){
                    pq.add(Pair.of(i,j,mat[i][j]));
                    hm.put(i+"-"+j, mat[i][j]);
                }
            }
        }
        
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int i=p.i;
            int j=p.j;
            if(hm.get(i+"-"+j)>p.height)continue;
            result+=p.height-mat[i][j];
            mat[i][j] = p.height;
            if(i-1>=0 && p.height-hm.getOrDefault(i-1+"-"+j,mat[i-1][j])>1){
                pq.offer(Pair.of(i-1,j,p.height-1));
                hm.put(i-1+"-"+j, p.height-1);
            }
            if(i+1<m && p.height-hm.getOrDefault(i+1+"-"+j,mat[i+1][j])>1){
                pq.offer(Pair.of(i+1,j,p.height-1));
                hm.put(i+1+"-"+j, p.height-1);
            }
            if(j-1>=0 && p.height-hm.getOrDefault(i+"-"+(j-1),mat[i][j-1])>1){
                pq.offer(Pair.of(i,j-1,p.height-1));
                hm.put(i+"-"+(j-1), p.height-1);
            }
            if(j+1<n && p.height-hm.getOrDefault(i+"-"+(j+1),mat[i][j+1])>1){
                pq.offer(Pair.of(i,j+1,p.height-1));
                hm.put(i+"-"+(j+1), p.height-1);
            }
        }
        return result;
    }

    static class Pair{
        public int height,i,j;
        public static Pair of(int a, int b, int c){
            Pair p = new Pair();
            p.height = c;
            p.i = a;
            p.j = b;
            return p;
        }
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















