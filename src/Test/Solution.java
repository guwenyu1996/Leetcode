package Test;

import java.io.*;
import java.util.*;


class Solution {
    public static void main(String[] args) {
        String input = "job1: 2017-01-01 ~ present \n job2: 2015-01-01 ~ 2017-01-01 \n job3: 2016-01-01 ~ 2017-01-01";
        System.out.println(calculateMonth(input));

    }

    // input_array =
    public static int calculateMonth(String input){
        // process input
        List<String[]> list = new ArrayList<>();
        String[] strList = input.split("\n ");
        for(String str: strList){
            String[] months = str.split(" ");
            String[] temp = new String[2];
            temp[0] = (months[1]);
            if(months[3].equals("present"))
                temp[1] = "2020-12-03";
            else
                temp[1] = months[3];
            list.add(temp);
        }

        // sort
        Collections.sort(list, (String[] s1, String[] s2) -> Integer.parseInt(s1[0].substring(0,4)) - Integer.parseInt(s2[0].substring(0,4)));

        // loop and calculate
        int[] checkedStart = new int[2];
        int[] checkedEnd = new int[2];
        int sum = 0;
        for(int i = 0; i < list.size(); i ++){
            String[] temp = list.get(i);

            int[] newStart = parseDate(temp[0]);
            int[] newEnd = parseDate(temp[1]);

            // check if intersect
            if(isIntersect(checkedStart, checkedEnd, newStart) || isIntersect(checkedStart, checkedEnd, newEnd)){
                if(dateToInt(checkedStart) > dateToInt(newStart))
                    checkedStart = newStart;

                if(dateToInt(checkedEnd) < dateToInt(newEnd))
                    checkedEnd = newEnd;
            }else{
                sum += (checkedEnd[0] - checkedStart[0]) * 12 + checkedEnd[1] - checkedStart[1];
                checkedStart = newStart;
                checkedEnd = newEnd;
            }
        }
        sum += (checkedEnd[0] - checkedStart[0]) * 12 + checkedEnd[1] - checkedStart[1];
        return sum;
    }

    // 2015-01-01  [2015, 01]
    public static int[] parseDate(String date){
        int[] result = new int[2];
        String[] temp = date.split("-");
        result[0] = Integer.parseInt((temp[0]));
        if(temp[1].startsWith("0"))
            result[1] = Integer.parseInt(temp[1].substring(1));
        else
            result[1] = Integer.parseInt((temp[1]));

        return result;
    }

    public static boolean isIntersect(int[] checkedStart, int[] checkedEnd,
                                      int[] newDate){
        if(newDate[0] >= checkedStart[0] && newDate[0] <= checkedEnd[0] &&
                newDate[1] >= checkedStart[1])
            return true;
        else
            return false;
    }

    public static int dateToInt(int[] date){
        return date[0] * 10 + date[1];
    }

}