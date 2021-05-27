package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.Scanner;

class ChessboardAndQueens {
    public static boolean canPlace(char[][] board, int x, int y){
        if(board[x][y]=='*')return false;
        for(int i=1;i<=x;i++){
            if(y-i>=0 && board[x-i][y-i]=='Q')return false;
            if(y+i<8 && board[x-i][y+i]=='Q')return false;
            if(board[x-i][y]=='Q')return false;
        }
        return true;
    }

    public static int solve(char[][] board, int row){
        if(row==8){
            return 1;
        }
        int count = 0;
        for(int i=0;i<8;i++){
            if(canPlace(board, row, i)){
                board[row][i]='Q';
                count+=solve(board, row+1);
                board[row][i]='.';
            }
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[8][];
        for(int i=0;i<8;i++){
            String line = sc.next();
            board[i] = line.toCharArray();
        }
        System.out.println(solve(board, 0));
        sc.close();
    }
}
