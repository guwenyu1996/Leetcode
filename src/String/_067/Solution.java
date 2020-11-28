package String._067;

public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int l1 = a.length(), l2 = b.length();
        int carry = 0;

        for(int i = 0; i < Math.max(l1, l2); i ++){
            int x = 0, y = 0;
            if(i < l1)
                x = a.charAt(l1 - 1 - i) - '0';
            if(i < l2)
                y = b.charAt(l2 - 1 - i) - '0';

            result.append((x + y + carry) % 2);
            carry = (x + y + carry) / 2;
        }

        if(carry == 1)
            result.append(1);

        return result.reverse().toString();
    }
}
