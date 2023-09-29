package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();

        getMiddleCharacters(string);
    }

    public static void getMiddleCharacters(String string) {
        char[]  stringToCharArr = string.toCharArray();
        if(stringToCharArr.length % 2 == 0){
            int middleIndex2 = stringToCharArr.length / 2;
            int middleIndex1 = middleIndex2 - 1;
            System.out.printf("%c%c",stringToCharArr[middleIndex1], stringToCharArr[middleIndex2]);
        } else {
            System.out.println(stringToCharArr[stringToCharArr.length / 2]);
        }
    }
}
