package Homeworks_And_Labs.L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class WeatherForecastPart2 {
    static void weatherForecast(String weather) {
        System.out.println(weather);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double degree  = Double.parseDouble(scanner.nextLine());
        String weatherString = "unknown";

        if(degree >= 26.00 && degree <= 35.00){
            weatherString = "Hot";
        } else if (degree >= 20.1 && degree <= 25.9){
            weatherString = "Warm";
        } else if (degree >= 15.00 && degree <= 20.00){
            weatherString = "Mild";
        } else if (degree >= 12.00 && degree <= 14.9){
        weatherString = "Cool";
        } else if (degree >= 5.00 && degree <= 11.9){
            weatherString = "Cold";
        }

        weatherForecast(weatherString);
    }
}
