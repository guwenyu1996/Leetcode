package Array._240;

public class Solution {
    // My solution
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int row = 0; row < matrix.length; row ++){
            for(int col = 0; col < matrix[0].length; col ++){
                if(matrix[row][col] == target)
                    return true;
                else if(matrix[row][col] > target)
                    break;
            }
        }
        return false;
    }

    // Solution 2: binary search
    public boolean searchMatrix2(int[][] matrix, int target) {
        for(int row = 0; row < matrix.length; row ++){
            if(binarySeach(matrix[row], target))
                return true;
        }

        return false;
    }

    public boolean binarySeach(int[] matrix, int target){
        int left = 0, right = matrix.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(matrix[mid] == target)
                return true;
            else if(matrix[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        return false;
    }

    // Space reduction search
    public boolean searchMatrix4(int[][] matrix, int target) {
        int row = 0, col = 0;

        while(row < matrix.length){
            while(col < matrix[0].length - 1 && matrix[row][col] < target)
                col ++;

            while(col > 0 && matrix[row][col] > target)
                col --;

            if(matrix[row][col] == target)
                return true;

            row ++;
        }

        return false;
    }

    public boolean searchMatrix5(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;

        while(row >= 0 && col < matrix[0].length){
            if(matrix[row][col] == target)
                return true;
            else if(matrix[row][col] < target)
                col ++;
            else
                row --;
        }
        return false;
    }
}
