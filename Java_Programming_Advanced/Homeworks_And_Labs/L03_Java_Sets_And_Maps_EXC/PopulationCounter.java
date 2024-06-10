package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class PopulationCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        LinkedHashMap<String, LinkedHashMap<String, Long>> populationByCountries = new LinkedHashMap<>();
        Map<String, Long> totalPopulation = new LinkedHashMap<>();

        while (!input.equals("report")){

            String[] tokens = input.split("\\|");

            String city = tokens[0];
            String country = tokens[1];
            Long population = Long.parseLong(tokens[2]);
            populationByCountries.putIfAbsent(country, new LinkedHashMap<>());

            totalPopulation.putIfAbsent(country, 0L);

            Long currentPopulation = totalPopulation.get(country);
            totalPopulation.put(country, currentPopulation + population);

            populationByCountries.get(country).put(city, population);

            input = scanner.nextLine();
        }

        totalPopulation.entrySet()
                        .stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                        .forEach(entry -> {
                                            System.out.printf("%s (total population: %d)\n",
                                                    entry.getKey(),
                                                    entry.getValue());
                                            populationByCountries.get(entry.getKey())
                                                    .entrySet()
                                                    .stream()
                                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                                    .forEach((pair) -> {
                                                        System.out.printf("=>%s: %d\n", pair.getKey(), pair.getValue());
                                                    });
                                        });


    }
}
