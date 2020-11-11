package Test;

import java.util.Scanner;

public class SAPString {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        String[] str = scan.nextLine().split(",");
        if(str.length == 2){
            String[] firstPerson = str[0].split(" ");
            String[] secondPerson = str[1].split(" ");
            if(firstPerson.length == 2 && secondPerson.length == 2){
                System.out.println(secondPerson[1] + " " + new StringBuilder(firstPerson[0]).reverse().toString());
            }
        }
    }
}
