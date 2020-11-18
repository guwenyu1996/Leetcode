package Test;

public class CR {
    public static void main(String[] args){
        int[] cr = new int[]{1,2,3,4,7};
        System.out.println(findIndex(cr));
    }

    public static int findIndex(int[] cr){
        return binarySearch(cr, 0, cr.length - 1);
    }

    public static int binarySearch(int[] cr, int left, int right){
        if(cr.length == 0)
            return -1;

        while(left <= right){
            int middle = left + (right - left) / 2;

            if(right - middle + 1 >= cr[middle])
                return cr[middle];
            else if(right - middle + 1 < cr[middle])
                right = middle - 1;
            else
                left = middle + 1;
        }

        return -1;
    }
}
