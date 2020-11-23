package Test;//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
// 此外，你可以假设该网格的四条边均被水包围。
//
//        示例 1:
//
//        输入:
//        11110
//        11010
//        11000
//        00000
//        输出: 1
//        示例 2:
//
//        输入:
//        11000
//        11000
//        00100
//        00011
//        输出: 3
//        解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。

public class Island {

    public static void main(String[] args){
        int [][] a = {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
        int [][] b = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};

        System.out.println(island(a));
        System.out.println(island(b));
    }

    public static int island(int[][] arr){
        int count = 0;
        for(int i = 0; i < arr[0].length; i ++){
            for(int j = 0; j < arr.length; j ++){
                if(arr[j][i] == 1){
                    dfs(arr, j, i);
                    count ++;
                }

            }
        }
        return count;
    }

    public static void dfs(int[][] arr, int x, int y){
        arr[x][y] = 2;

        if(x+1 < arr.length && arr[x+1][y] == 1)
            dfs(arr, x+1, y);
        if(x-1 >= 0 && arr[x-1][y] == 1)
            dfs(arr, x-1, y);
        if(y+1 < arr[0].length && arr[x][y+1] == 1)
            dfs(arr, x, y+1);
        if(y-1 >= 0 && arr[x][y-1] == 1)
            dfs(arr, x, y-1);

    }
}
