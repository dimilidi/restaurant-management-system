package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class SummerOutfit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int temperature = Integer.parseInt(scanner.nextLine());
        String partOfTheDay = scanner.nextLine().toLowerCase();
        String outfit = "" ;
        String shoes = "" ;

        switch(partOfTheDay) {
            case "morning":
                if(temperature >= 10 && temperature <= 18) {
                    outfit = "Sweatshirt";
                    shoes = "Sneakers";
                } else if (temperature > 18 && temperature <= 24) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                } else if (temperature >= 25) {
                    outfit = "T-Shirt";
                    shoes = "Sandals";
                }
                break;
            case "afternoon":
                if(temperature >= 10 && temperature <= 18) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                } else if (temperature > 18 && temperature <= 24) {
                    outfit = "T-Shirt";
                    shoes = "Sandals";
                } else if (temperature >= 25) {
                    outfit = "Swim Suit";
                    shoes = "Barefoot";
                }
                break;
            case "evening":
                if(temperature >= 10 && temperature <= 18) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                } else if (temperature > 18 && temperature <= 24) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                } else  {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                }
                break;
            default:
                System.out.println("Error");
        }

        System.out.printf("It's %d degrees, get your %s and %s.", temperature, outfit, shoes);

    }
}
