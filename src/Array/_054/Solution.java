package Array._054;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // Solution 1: simulation
    public List<Integer> spiralOrder(int[][] matrix) {
        int rNum = matrix.length, cNum = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        boolean[][] seen = new boolean[rNum][cNum];
        int[] dirX = {0, 1, 0, -1};
        int[] dirY = {1, 0, -1, 0};

        int row = 0, col = 0;
        int dir = 0;
        for(int i = 0; i < rNum * cNum; i ++){
            result.add(matrix[row][col]);
            seen[row][col] = true;

            int newX = row + dirX[dir];
            int newY = col + dirY[dir];
            if(newX < rNum && newX >= 0 && newY < cNum && newY >= 0 && !seen[newX][newY]){
                row = newX;
                col = newY;
            }else{
                dir = (dir + 1) % 4;
                row += dirX[dir];
                col += dirY[dir];
            }
        }

        return result;
    }

    // Solution 2: layer-by-layer my solution
    public List<Integer> spiralOrder1(int[][] matrix) {
        int r1 = 0, c1 = 0;
        int r2 = matrix.length - 1, c2 = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();
        int row = 0, col = 0;

        while(r1 <= r2 && c1 <= c2){
            row = r1;
            col = c1;
            result.add(matrix[row][col]);

            while(col < c2){
                col ++;
                result.add(matrix[row][col]);
            }

            while(row < r2){
                row ++;
                result.add(matrix[row][col]);
            }

            if(r1 < r2 && c1 < c2){
                while(col > c1){
                    col --;
                    result.add(matrix[row][col]);
                }

                while(row > r1 + 1){
                    row --;
                    result.add(matrix[row][col]);
                }
            }

            r1 ++;
            c1 ++;
            r2 --;
            c2 --;
        }
        return result;
    }

    // Solution 2: layer by layer
    public List<Integer> spiralOrder3(int[][] matrix) {
        int r1 = 0, c1 = 0;
        int r2 = matrix.length - 1, c2 = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();
        int row = 0, col = 0;

        while(r1 <= r2 && c1 <= c2){
            for(int c = c1; c <= c2; c ++)
                result.add(matrix[r1][c]);

            for(int r = r1 + 1; r <= r2; r ++)
                result.add(matrix[r][c2]);

            if(r1 < r2 && c1 < c2){
                for(int c = c2 - 1; c >= c1; c --)
                    result.add(matrix[r2][c]);

                for(int r = r2 - 1; r >= r1 + 1; r --)
                    result.add(matrix[r][c1]);
            }

            r1 ++;
            c1 ++;
            r2 --;
            c2 --;
        }
        return result;
    }
}
