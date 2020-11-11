package Test;

public class SAPNames {

    public static void main(String[] args){
        String[] names = new String[]{"kylab charles", "raymond strickland", "andrea meza", "destiney alvarado"};
        int maxCount = 0, maxIndex = 0;

        for(int i = 0; i < names.length; i ++){
            int[] count = new int[26];
            String name = names[i].replaceAll("\\s+","").toLowerCase();
            for(int j = 0; j < name.length(); j ++){
                count[(int)name.charAt(j)-97] = 1;
            }

            int temp = 0;
            for(int j = 0; j < 26; j ++){
                if(count[j] == 1)
                    temp ++;
            }

            if(temp > maxCount){
                maxCount = temp;
                maxIndex = i;
            }
        }

        System.out.println(maxIndex);

    }




}
