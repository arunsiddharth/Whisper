package org.arunsiddharth.Advanced;

import java.util.*;
import org.arunsiddharth.Domain.TrieNode;

public class WordBoggle {
    TrieNode root;
    static String[] dict;
	static List<String> result;
	static int[] xd = {-1,-1,-1,0,0,1,1,1};
	static int[] yd = {-1,0,1,-1,1,-1,0,1};
	public static boolean containsWord(String word){
        System.out.println(word);
		for(String d:dict){
			if(d.equals(word))return true;
		}
		return false;
	}

    public static boolean shouldContinue(String word){
        for(String d:dict){
			if(d.contains(word))return true;
		}
		return false;
    }
	
	public static void boggle(int i, int j, boolean[][] visited, char[][] board, StringBuilder stb){
		visited[i][j]=true;
		stb.append(board[i][j]);
		if(containsWord(stb.toString()))result.add(stb.toString());
        if(!shouldContinue(stb.toString()))return;
		int m = board.length;
		int n = board[0].length;
		for(int k=0;k<8;k++){
			int ii = i+xd[k];
			int jj = j+yd[k];
			if(ii>=0 && ii<m && jj>=0 && jj<n && !visited[ii][jj]){
				boggle(ii,jj,visited,board,stb);
			}
		}
		stb.deleteCharAt(stb.length()-1);
		visited[i][j]=false;
	}
    public static List<String> boggleBoard(char[][] board, String[] words) {
        dict = words;
        result = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        StringBuilder stb = new StringBuilder("");
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                boggle(i, j, visited, board, stb);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'t', 'h', 'i', 's', 'i', 's', 'a'},
            {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
            {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
            {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
            {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
            {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
            {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
            {'N', 'O', 'T', 'R', 'E', '-', 'P'},
            {'x', 'x', 'D', 'E', 'T', 'A', 'E'}
        };
        String[] words =  {"this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"};
        /*
        char board[][] = { { 'G', 'I', 'Z' },
                            { 'U', 'E', 'K' },
                            { 'Q', 'S', 'E' } };
        String[] words = { "GEEKS", "FOR", "QUIZ", "GUQ", "EE"};*/
        System.out.println(boggleBoard(board, words));
    }
}
