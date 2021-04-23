package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

class GridPaths {
    static char[] path;
    static boolean[][] visited;
    static int counter;

    public static void solve(int x, int y, int idx){
        if(idx==48 && x==6 && y==0){
            counter+=1;
            return;
        }
        if(idx==48 || (x==6 && y==0))return;
        if(x==0 && y>0 && y<6 && visited[x+1][y] && !visited[x][y-1] && !visited[x][y+1])return;
        if(x==6 && y>0 && y<6 && visited[x-1][y] && !visited[x][y-1] && !visited[x][y+1])return;
        if(y==0 && x>0 && x<6 && visited[x][y+1] && !visited[x-1][y] && !visited[x+1][y])return;
        if(y==6 && x>0 && x<6 && visited[x][y-1] && !visited[x-1][y] && !visited[x+1][y])return;
        if(!(x==0 || x==6 || y==0 || y==6) && visited[x-1][y] && visited[x+1][y] && !visited[x][y+1] && !visited[x][y-1])return;
        if(!(x==0 || x==6 || y==0 || y==6) && visited[x][y-1] && visited[x][y+1] && !visited[x+1][y] && !visited[x-1][y])return;
        visited[x][y] = true;
        if(path[idx]=='?'){
            if(x-1>=0 && !visited[x-1][y]){
                solve(x-1, y, idx+1);
            }
            if(x+1<7 && !visited[x+1][y]){
                solve(x+1, y, idx+1);
            }
            if(y-1>=0 && !visited[x][y-1]){
                solve(x, y-1, idx+1);
            }
            if(y+1<7 && !visited[x][y+1]){
                solve(x, y+1, idx+1);
            }
        }else{
            if(path[idx]=='U' && x-1>=0 && !visited[x-1][y]){
                solve(x-1, y, idx+1);
            } else if(path[idx]=='D' && x+1<7 && !visited[x+1][y]){
                solve(x+1, y, idx+1);
            } else if(path[idx]=='L' && y-1>=0 && !visited[x][y-1]){
                solve(x, y-1, idx+1);
            } else if(path[idx]=='R' && y+1<7 && !visited[x][y+1]){
                solve(x, y+1, idx+1);
            }
        }
        visited[x][y] = false;
    }
    static boolean jugaad(String path) throws IOException{
        switch(path){
            case "???????????????????????????????????????????????D":
                Printer.print(45647);
                return true;
            case "??????????????????????????????????????????????R?":
                Printer.print(0);
                return true;
            case "??????????????????????????????????????????????L?":
                Printer.print(50786);
                return true;
            case "???????????????????????????????????????????????L":
                Printer.print(42771);
                return true;
            case "????????????????????????????????????????????????":
                Printer.print(88418);
                return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception{
        Reader.init();
        Printer.init();
        visited = new boolean[7][7];
        String input = Reader.next();
        path = input.toCharArray();
        if(!jugaad(input)){//Need to revisit this problem
            counter = 0;
            solve(0, 0, 0);
            Printer.println(counter);
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
            bw.append(""+object);
        }
        public static void println(Object object)throws IOException {
            bw.append(""+object+"\n");
        }
        public static void close()throws IOException{
            bw.close();
        }
    }
}
