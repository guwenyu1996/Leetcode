package DFS._130;

public class Solution {
    // Solution 1: dfs
    public void solve(char[][] board) {
        if(board.length == 0)
            return;

        for(int i = 0; i < board[0].length; i ++){
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        for(int i = 1; i < board.length - 1; i ++){
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }

        for(int row = 0; row < board.length; row ++){
            for(int col = 0; col < board[0].length; col ++){
                switch(board[row][col]){
                    case 'O':{
                        board[row][col] = 'X';
                        break;
                    }
                    case 'E':{
                        board[row][col] = 'O';
                        break;
                    }
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O')
            return ;

        board[x][y] = 'E';

        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
}
