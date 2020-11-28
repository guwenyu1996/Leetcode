package String._415;

public class Solution {
    public String addStrings(String num1, String num2) {
        int l1 = num1.length(), l2 = num2.length();
        int maxLength = Math.max(l1, l2);
        StringBuilder result = new StringBuilder();
        int flag = 0;

        for(int i = 0; i < maxLength; i ++){
            int x = 0, y = 0;

            if(i < l1)
                x = Character.getNumericValue(num1.charAt(l1 - 1 - i));
            if(i < l2)
                y = Character.getNumericValue(num2.charAt(l2 - 1 - i));

            int temp = x + y + flag;
            if(temp >= 10){
                result.append(temp - 10);
                flag = 1;
            }else{
                result.append(temp);
                flag = 0;
            }
        }

        if(flag == 1)
            result.append(1);

        return result.reverse().toString();
    }
}
