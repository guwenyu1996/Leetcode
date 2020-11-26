package Test;

public class T2 {
    // 3, 1
    // 6, 2
    public static void main(String[] args){
        int k = 4;
        System.out.println(helper(k));
    }

    public static int helper(int n){
        int ans = 0;
        while(true){
            int root = ans * ans;
            if(root == n)
                return ans;
            if(root > n)
                break;

            ans ++;
        }
        return ans - 1;
    }
}
