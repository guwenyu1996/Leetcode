package String._012;

class Solution {
    public String intToRoman(int num) {
        String[] strArr = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] intArr = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        int cur = 0;
        StringBuilder builder = new StringBuilder();
        while(num != 0){
            if(num / intArr[cur] > 0){
                builder.append(strArr[cur]);
                num -= intArr[cur];
            }else
                cur ++;
        }

        return builder.toString();
    }
}
