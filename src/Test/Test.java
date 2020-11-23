package Test;
//示例 1:
//
//        输入: [ 1 , 4 , 6 , 7 , 9 ] 8
//        输出: Not Exist
//        示例 2：
//
//        输入: [ 1 , 4 , 6 , 7 , 9 ] 7
//        输出: 4

public class Test {

    public static void main(String[] args){
        int[] arr = new int[]{1,4,6,7,9};
        int k = 7;
        System.out.println(search(arr, 7));
        System.out.println(search(arr, 8));
    }

    public static String search(int[] arr, int k){
        int result = helper(arr, k,0, arr.length);
        if(result == -1)
            return "Not exist";
        else
            return result + "";
    }

    public static int helper(int[] arr, int k, int left, int right){
        while(left <= right){
            int middle = left + (right - left)/2;

            if(arr[middle] == k)
                return middle + 1;
            else if(arr[middle] < k)
                left = middle + 1;
            else
                right = middle - 1;
        }

        return -1;
    }
}
