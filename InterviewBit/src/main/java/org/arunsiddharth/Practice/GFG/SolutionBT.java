package org.arunsiddharth.Practice.GFG;

import java.util.*;
import java.io.*;
import org.arunsiddharth.Domain.Pair;
import org.arunsiddharth.Advanced.Trie;

public class SolutionBT {
    static List<List<Integer>> nQueenResult;
    
    /**
     * nQueen Solution
     */
    public static boolean noClash(List<Integer> queen, int r,int c, int size){
        for(int i=0;i<r;i++){
            if(queen.get(i).equals(c))return false;
            else if((c-1-i>=0 && queen.get(r-1-i).equals(c-1-i)) ||(c+1+i<=size && queen.get(r-1-i).equals(c+1+i)))return false;
        }
        return true;
    }
    
    public static void nQueen(List<Integer> queen, int r,int size){
        if(r==size){
            List<Integer> dup = new ArrayList<>();
            for(int i:queen)dup.add(i);
            nQueenResult.add(dup);
        } else {
            for(int j=1;j<=size;j++){
                if(noClash(queen, r, j, size)){
                    queen.add(j);
                    nQueen(queen, r+1, size);
                    queen.remove(r);
                }
            }
        }
    }
    
    public static void nQueenInit(int n){
        nQueenResult = new ArrayList<List<Integer>>();
		List<Integer> result = new ArrayList<Integer>();
		nQueen(result,0,n);
		for(List<Integer> tmp:nQueenResult){
		    System.out.print("[");
		    for(Integer i:tmp)System.out.print(i+" ");
		    System.out.println("]");
		}
    }
    
    /**
     * Sudoku Solve
     */
    public static boolean isPossible(int[][] mat, int r, int c, int val){
        for(int i=0;i<9;i++){
            if((mat[r][i]==val) || (mat[i][c]==val))return false;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int row = 3*(r/3)+i;
                int col = 3*(c/3)+j;
                if(mat[row][col]==val)return false;
            }
        }
        return true;
    }
    public static boolean sudokuSolver(int[][] mat){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(mat[i][j]==0){
                    for(int k=1;k<=9;k++){
                        if(isPossible(mat,i,j, k)){
                           mat[i][j]=k;
                           if(sudokuSolver(mat))return true;  
                           mat[i][j]=0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Rat in maze with 1 as valid and go from 0,0 to n-1,n-1 
     */
    static ArrayList<String> ratPaths;
    public static void solve(int[][] mat, int n, String tmp, Set<Pair> visited, int r, int c){
        if(r==n-1 && c==n-1){
            ratPaths.add(tmp);
        } else {
            if(r-1>=0 && mat[r-1][c]==1 && !visited.contains(Pair.of(r-1, c))){
                Pair node = Pair.of(r-1,c);
                visited.add(node);
                tmp+='U';
                solve(mat,n,tmp,visited,r-1,c);
                tmp = tmp.substring(0, tmp.length()-1);
                visited.remove(node);
            }
            if(r+1<n && mat[r+1][c]==1 && !visited.contains(Pair.of(r+1, c))){
                Pair node = Pair.of(r+1,c);
                visited.add(node);
                tmp+='D';
                solve(mat,n,tmp,visited,r+1,c);
                tmp = tmp.substring(0, tmp.length()-1);
                visited.remove(node);
            }
            if(c-1>=0 && mat[r][c-1]==1 && !visited.contains(Pair.of(r, c-1))){
                Pair node = Pair.of(r,c-1);
                visited.add(node);
                tmp+='L';
                solve(mat,n,tmp,visited,r,c-1);
                tmp = tmp.substring(0, tmp.length()-1);
                visited.remove(node);
            }
            if(c+1<n && mat[r][c+1]==1 && !visited.contains(Pair.of(r, c+1))){
                Pair node = Pair.of(r,c+1);
                visited.add(node);
                tmp+='R';
                solve(mat,n,tmp,visited,r,c+1);
                tmp = tmp.substring(0, tmp.length()-1);
                visited.remove(node);
            }
        }
    }
    public static ArrayList<String> printPath(int[][] m, int n) {
        // Your code here
        ratPaths = new ArrayList<String>();
        String tmp = "";
        Set<Pair> visited = new HashSet<Pair>();
        if(m[0][0]==0)return ratPaths;
        visited.add(Pair.of(0,0));
        solve(m, n, tmp, visited, 0, 0);
        Collections.sort(ratPaths);
        return ratPaths;
    }

    /**
     * Word Boggle
     */
    static List<String> result;
    
    static void boggle(Trie dictionary , char[][] mat, int r, int c, Set<Pair> visited, String tmp){
        if(dictionary.contains(tmp)){
            result.add(tmp);
        } 
        if(dictionary.isPrefix(tmp)){
            int m = mat.length, n = mat[0].length;
            if(r-1>=0 && c-1>=0 && !visited.contains(Pair.of(r-1,c-1))){
                tmp+=mat[r-1][c-1];
                Pair node = Pair.of(r-1,c-1);
                visited.add(node);
                boggle(dictionary, mat, r-1, c-1, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            if(r-1>=0 && !visited.contains(Pair.of(r-1,c))){
                tmp+=mat[r-1][c];
                Pair node = Pair.of(r-1,c);
                visited.add(node);
                boggle(dictionary, mat, r-1, c, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            if(r-1>=0 && c+1<n && !visited.contains(Pair.of(r-1,c+1))){
                tmp+=mat[r-1][c+1];
                Pair node = Pair.of(r-1,c+1);
                visited.add(node);
                boggle(dictionary, mat, r-1, c+1, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            
            if(c-1>=0 && !visited.contains(Pair.of(r,c-1))){
                tmp+=mat[r][c-1];
                Pair node = Pair.of(r,c-1);
                visited.add(node);
                boggle(dictionary, mat, r, c-1, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            if(c+1<n && !visited.contains(Pair.of(r,c+1))){
                tmp+=mat[r][c+1];
                Pair node = Pair.of(r,c+1);
                visited.add(node);
                boggle(dictionary, mat, r, c+1, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            
            if(r+1<m && c-1>=0 && !visited.contains(Pair.of(r+1,c-1))){
                tmp+=mat[r+1][c-1];
                Pair node = Pair.of(r+1,c-1);
                visited.add(node);
                boggle(dictionary, mat, r+1, c-1, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            if(r+1<m && !visited.contains(Pair.of(r+1,c))){
                tmp+=mat[r+1][c];
                Pair node = Pair.of(r+1,c);
                visited.add(node);
                boggle(dictionary, mat, r+1, c, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
            if(r+1<m && c+1<n && !visited.contains(Pair.of(r+1,c+1))){
                tmp+=mat[r+1][c+1];
                Pair node = Pair.of(r+1,c+1);
                visited.add(node);
                boggle(dictionary, mat, r+1, c+1, visited, tmp);
                visited.remove(node);
                tmp=tmp.substring(0,tmp.length()-1);
            }
        }
    }
    
    static List<String> wordBoggle(Trie dictionary , char[][] mat){
        int m = mat.length, n = mat[0].length;
        String tmp = "";
        result = new ArrayList<String>();
        Set<Pair> visited = new HashSet<Pair>();
        for(int i=0;i<m;i++)for(int j=0;j<n;j++){
            tmp=String.valueOf(mat[i][j]);
            Pair node = Pair.of(i,j);
            visited.add(node);
            boggle(dictionary, mat, i, j, visited, tmp);
            visited.remove(node);
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Generate all possible IP's using all characters from given string
     */
    ArrayList<String> resultIP;
    public boolean isValid(String ipBlock){
        Integer i = Integer.parseInt(ipBlock);
        if(ipBlock.charAt(0)=='0'){
            int j=0;
            while(j<ipBlock.length() && ipBlock.charAt(j)=='0')j++;
            if(j!=ipBlock.length() || ipBlock.length()>1)return false;
        }
        return (i>=0 && i<=255);
    }
    public void ipMaker(String s, int idx, List<String> ip){
        if(ip.size()==4 && s.length()==idx){
            String tmp=ip.get(0)+"."+ip.get(1)+"."+ip.get(2)+"."+ip.get(3);
            resultIP.add(tmp);
        } else {
            String tmp = "";
            for(int i=idx;i<s.length();i++){
                tmp+=s.charAt(i);
                if(!isValid(tmp))return;
                ip.add(tmp);
                ipMaker(s,i+1,ip);
                ip.remove(ip.size()-1);
            }
        }
    }
    public ArrayList<String> genIp(String s) {
        resultIP = new ArrayList<String>();
        List<String> ip = new ArrayList<String>();
        ipMaker(s,0,ip);
        Collections.sort(resultIP);
        return resultIP;
    }

	public static void main (String[] args) throws IOException {

	}
}