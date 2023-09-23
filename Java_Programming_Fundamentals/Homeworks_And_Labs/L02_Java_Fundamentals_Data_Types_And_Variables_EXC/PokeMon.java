package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class PokeMon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long pokePower = Long.parseLong(scanner.nextLine());
        long distance = Long.parseLong(scanner.nextLine());
        long exhaustionFactor = Long.parseLong(scanner.nextLine());
        long initialPokePower = pokePower;
        long pokedTargetsCount = 0;

        while (pokePower >= distance) {
            pokePower -= distance;
            pokedTargetsCount++;

            if (pokePower == initialPokePower / 2 && exhaustionFactor != 0) {
                pokePower /= exhaustionFactor;
            }
        }

        System.out.println(pokePower);
        System.out.println(pokedTargetsCount);
    }
}
