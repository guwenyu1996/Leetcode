package Array._079;

public class Solution {
    // My solution: backtracking
    public boolean exist(char[][] board, String word) {
        boolean[][] isVisited = new boolean[board.length][board[0].length];

        char c = word.charAt(0);
        for(int row = 0; row < board.length; row ++){
            for(int col = 0; col < board[0].length; col ++){
                if(board[row][col] == c){
                    isVisited[row][col] = true;
                    if(helper(board, word, isVisited, row, col, 1))
                        return true;
                    isVisited[row][col] = false;
                }
            }
        }
        return false;
    }

    public boolean helper(char[][] board, String word, boolean[][] isVisited, int row, int col, int index){
        if(index == word.length())
            return true;

        if(row - 1 >= 0 && !isVisited[row - 1][col] && (board[row-1][col]) == word.charAt(index)){
            isVisited[row-1][col] = true;
            if(helper(board, word, isVisited, row - 1, col, index + 1))
                return true;
            isVisited[row-1][col] = false;
        }

        if(row + 1 < board.length && !isVisited[row+1][col] && (board[row+1][col]) == word.charAt(index)){
            isVisited[row+1][col] = true;
            if(helper(board, word, isVisited, row + 1, col, index + 1))
                return true;
            isVisited[row+1][col] = false;
        }

        if(col + 1 < board[0].length && !isVisited[row][col+1] && (board[row][col+1]) == word.charAt(index)){
            isVisited[row][col+1] = true;
            if(helper(board, word, isVisited, row, col + 1, index + 1))
                return true;
            isVisited[row][col+1] = false;
        }

        if(col - 1 >= 0 && !isVisited[row][col-1] && (board[row][col-1]) == word.charAt(index)){
            isVisited[row][col-1] = true;
            if(helper(board, word, isVisited, row, col - 1, index + 1))
                return true;
            isVisited[row][col-1] = false;
        }

        return false;
    }
}
