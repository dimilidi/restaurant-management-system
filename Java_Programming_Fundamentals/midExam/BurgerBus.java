package midExam;

import java.util.Scanner;

public class BurgerBus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCities = Integer.parseInt(scanner.nextLine());
        double totalProfit = 0;
        String[] cityNames = new String[numberOfCities];
        double[] cityProfits = new double[numberOfCities];

        for (int cityNumber = 1; cityNumber <= numberOfCities; cityNumber++) {
            String cityName = scanner.nextLine();
            double income = Double.parseDouble(scanner.nextLine());
            double expenses = Double.parseDouble(scanner.nextLine());


            if (cityNumber % 3 == 0 && cityNumber % 5 != 0) {
                double additionalCost = expenses * 0.5;
                income -= expenses;
                income += additionalCost;
            }


            if (cityNumber % 5 == 0) {
                    income -= income * 0.1;
            }

            double profit = income - expenses;
            totalProfit += profit;
            cityNames[cityNumber - 1] = cityName;
            cityProfits[cityNumber - 1] = profit;
        }

        for (int cityNumber = 0; cityNumber < numberOfCities; cityNumber++) {
            System.out.printf("In %s Burger Bus earned %.2f leva.%n", cityNames[cityNumber], cityProfits[cityNumber]);
        }

        System.out.printf("Burger Bus total profit: %.2f leva.%n", totalProfit);
    }
}


//3
//        Sofia
//        895.67
//        213.50
//        Plovdiv
//        2563.20
//        890.26
//        Burgas
//        2360.55
//        600.00

//5
//Lili
//        2226.00
//        1200.60
//        Rennes
//        6320.60
//        5460.20
//        Reims
//        600.20
//        452.32
//        Bordeaux
//        6925.30
//        2650.40
//        Montpellier
//        680.50
//        290.20