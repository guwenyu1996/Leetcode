package Test;

public class Square {

//    给定一个n*m的二维格点地图, 每个位置要么是字符’.’表示空地, 要么是’@’表示有敌人在这里.
//            规定给定一个d(1 <= d <= min(m, n)), 如果一个d*d的区域内没有任何敌人, 则认为这片区域是安全的. 问给定的地图中有多少个这样安全的区域.
//  . @ . .
//            . . . .
//            . . . .
//
//            1,0,1,1
//            1,1,1,2
//            1,2,2,2
//
//            if . dp[i,j] = min(dp[i-1,j], dp[i,j-1], dp[i-1,j-1]) + 1
//            if @ dp[i,j] = 0


    public int findSafeArea(char[][] grid, int d){
        if(grid.length == 0)
            return 0;

        int[][] dp = new int[grid.length][grid[0].length];
        int count = 0;

        // initial
        for(int i = 0; i < grid[0].length; i ++){
            if(grid[0][i] == '.'){
                dp[0][i] = 1;
                if(d == 1)
                    count ++;
            }
            else
                dp[0][i] = 0;
        }

        for(int i = 0; i < grid.length; i ++){
            if(grid[i][0] == '.'){
                dp[i][0] = 1;
                if(d == 1)
                    count ++;
            }
            else
                dp[i][0] = 0;
        }

        for(int row = 1; row < grid.length; row ++){
            for(int col = 1; col < grid[0].length; col ++){
                if(grid[row][col] == '@')
                    dp[row][col] = 0;
                else{
                    dp[row][col] = Math.min(Math.min(dp[row-1][col-1], dp[row][col-1]), dp[row-1][col]) + 1;
                    if(dp[row][col] >= d)
                        count ++;
                }
            }

        }

        return count;
    }




//        0 way
//1 barrier
//
//
//0 1 0 0 0
//        0 1 0 1 0
//        0 1 0 1 0
//        0 1 0 1 0
//        0 0 0 1 0
//
//        0 0 1
//        1 1 1
//        0 0 0
//
//        if barrier dp[i,j] = 1 + min(dp[i-1, j], dp[i, j-1])
//if way,    dp[i,j] = min(dp[i-1, j], dp[i, j-1])


}
