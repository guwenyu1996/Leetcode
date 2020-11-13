package Test;

import java.util.Scanner;

public class SAPint {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input = "300";//scan.nextLine();

        char[] ch = input.toCharArray();
        int len = input.length(), i = 1;

        // 从左起开始找，找到一个右边比左边大的位置
        while(i < len && ch[i-1] < ch[i])
            i ++;

        // 从这个数到最左，满足ch[j-1] < ch
//        for(int j = i; i > 0; i --){
//            while(ch[j-1] >= ch[j])
//                ch[j-1] --;
//        }

        // 从这个数到最左，每个值-1
        while(i > 0 && ch[i-1] >= ch[i])
        {
            ch[i - 1] --;
            i --;
        }

        // 从这个数到最右，填充9
        for(int j = i + 1; j < len; j ++)
            ch[j] = '9';

        // 从最右到最左，如果
        char cur = '9';
        for(int j = len -1; j >= 0; j --){
            if(ch[j] >= cur)
                ch[j] = cur--;
        }
        System.out.println(String.valueOf(ch));
    }
}
