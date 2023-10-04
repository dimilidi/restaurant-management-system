package Homeworks_And_Labs.L06_Java_Fundamentals_Mid_Exam_Preparation_EXC;

import java.util.Scanner;

public class GuineaPig {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double food = Double.parseDouble(scanner.nextLine()) * 1000;
        double hay = Double.parseDouble(scanner.nextLine()) * 1000;
        double cover = Double.parseDouble(scanner.nextLine()) *1000;
        double pigsWeight = Double.parseDouble(scanner.nextLine()) * 1000;
        int days = 1;
        boolean  isOutOfProducts = false;

        while(days < 31) {

            food -= 300;

            if(days % 2 == 0){
                double givenHay = food * 0.05;
                hay -= givenHay;
            }

            if(days % 3 == 0) {
                double usedCover = pigsWeight / 3.0;
                cover -= usedCover;
            }

            if(food <= 0 || hay <= 0 || cover <=0){
                isOutOfProducts = true;
                break;
            }

            days++;
        }

        if(isOutOfProducts) {
            System.out.println("Merry must go to the pet store!");
        } else {
            System.out.printf("Everything is fine! Puppy is happy! Food: %.2f, Hay: %.2f, Cover: %.2f.",food / 1000, hay / 1000, cover / 1000);
        }

    }
}
