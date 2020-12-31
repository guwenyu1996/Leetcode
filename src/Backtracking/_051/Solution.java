package Backtracking._051;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];
        for(int i = 0; i < n; i ++)
            Arrays.fill(board[i], '.');

        helper(result, board, 0);
        return result;
    }

    public void helper(List<List<String>> result, char[][] board, int n){
        if(n == board.length){
            addResult(result, board);
            return;
        }

        for(int col = 0; col < board.length; col ++){
            board[n][col] = 'Q';
            if(check(board, n, col))
                helper(result, board, n + 1);
            board[n][col] = '.';
        }
    }

    public boolean check(char[][] board, int row, int col){
        // col
        for(int i = 0; i < row; i ++){
            if(board[i][col] == 'Q')
                return false;
        }

        // diagonal
        for(int i = 0; i < row; i ++){
            if((row+col-i) < board.length && board[i][row+col-i] == 'Q')
                return false;
        }

        // anti diagonal
        for(int i = 0; i < row; i ++){
            if((i+col-row) >= 0 && board[i][i+col-row] == 'Q')
                return false;
        }
        return true;
    }

    public void addResult(List<List<String>> result, char[][] board){
        List<String> temp = new ArrayList<>();
        for(char[] line: board)
            temp.add(new String(line));

        result.add(temp);
    }
}
