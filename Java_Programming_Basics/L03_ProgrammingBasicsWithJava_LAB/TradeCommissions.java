package L03_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class TradeCommissions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine().toLowerCase();
        double sales = Double.parseDouble(scanner.nextLine());
        double commission = 0;

        switch(city) {
            case "sofia":
                if(sales >= 0 && sales <= 500) {
                    commission = 0.05;
                } else if (sales > 500 && sales <=1000) {
                    commission = 0.07;
                } else if (sales > 1000 && sales <=10000) {
                    commission = 0.08;
                } else if (sales > 10000) {
                    commission = 0.12;
                }
                break;
            case "varna":
                if(sales >= 0 && sales <= 500) {
                    commission = 0.045;
                } else if (sales > 500 && sales <=1000) {
                    commission = 0.075;
                } else if (sales > 1000 && sales <=10000) {
                    commission = 0.1;
                } else if (sales > 10000) {
                    commission = 0.13;
                }
                break;
            case "plovdiv":
                if(sales >= 0 && sales <= 500) {
                    commission = 0.055;
                } else if (sales > 500 && sales <=1000) {
                    commission = 0.08;
                } else if (sales > 1000 && sales <=10000) {
                    commission = 0.12;
                } else if (sales > 10000) {
                    commission = 0.145;
                }
                break;
            default:
                System.out.println("");
        }

        if(sales < 0 || !(city.equals("sofia") || city.equals("varna") ||city.equals(("plovdiv")))) {
            System.out.println("error");
        } else {
            System.out.printf("%.2f", sales * commission);
        }

    }
}
