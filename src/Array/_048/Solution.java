package Array._048;

public class Solution {

    // Solution 1: tranpose and then reverse
    public void rotate(int[][] matrix) {
        // Transpose
        int temp = 0;
        for(int row = 0; row < matrix.length; row ++){
            for(int col = 0; col < row; col ++){
                temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }

        int middle = (matrix.length + 1) / 2;
        for(int row = 0; row < matrix.length; row ++){
            for(int col = 0; col < middle; col ++){
                temp = matrix[row][col];
                matrix[row][col] = matrix[row][matrix.length-1-col];
                matrix[row][matrix.length-1-col] = temp;
            }
        }
    }

    // Solution 2: rotate four rectangles
    public void rotate1(int[][] matrix) {
        int p1 = 0, p2 = matrix.length - 1;
        int temp;
        while(p1 < p2){
            for(int add = 0; add < (p2 - p1); add ++){
                temp = matrix[p2-add][p1];
                matrix[p2-add][p1] = matrix[p2][p2-add];
                matrix[p2][p2-add] = matrix[p1+add][p2];
                matrix[p1+add][p2] = matrix[p1][p1+add];
                matrix[p1][p1+add] = temp;
            }

            p1 ++;
            p2 --;
        }
    }

}
