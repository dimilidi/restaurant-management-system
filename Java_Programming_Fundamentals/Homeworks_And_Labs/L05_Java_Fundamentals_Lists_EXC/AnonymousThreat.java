package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> inputList = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("3:1")) {
            if (command.contains("merge")) {
                String[] commandsParts = command.split(" ");
                int startIndex = Integer.parseInt((commandsParts[1]));
                int endIndex = Integer.parseInt((commandsParts[2]));

                if (startIndex < 0) {
                    startIndex = 0;
                }

                if (endIndex > inputList.size() - 1) {
                    endIndex = inputList.size() - 1;
                }

                String mergedResult = "";
                if (startIndex >= 0 && startIndex <= inputList.size() - 1 && endIndex >= 0 && endIndex <= inputList.size() - 1) {
                    for (int i = startIndex; i <= endIndex; i++) {
                        mergedResult += inputList.get(i);
                    }

                    for (int index = startIndex; index <= endIndex; index++) {
                        inputList.remove(startIndex);
                    }
                    inputList.add(startIndex, mergedResult);
                }
            } else if ((command.contains("divide"))) {
                String[] commandsParts = command.split(" ");
                int index = Integer.parseInt((commandsParts[1]));
                int partitions = Integer.parseInt((commandsParts[2]));

                if (index >= 0 && index <= inputList.size() - 1) {
                    String targetElement = inputList.get(index);
                    inputList.remove(index);

                    int lengthOfSubstring = targetElement.length() / partitions;
                    int startIndex = 0;

                    for (int i = 1; i < partitions; i++) {
                        String substr = targetElement.substring(startIndex, startIndex + lengthOfSubstring);
                        inputList.add(index, substr);
                        index++;
                        startIndex += lengthOfSubstring;
                    }
                    String textLastParts = targetElement.substring(startIndex);
                    inputList.add(index, textLastParts);
                }
            }
            command = scanner.nextLine();
        }

        System.out.println(String.join(" ", inputList));
    }
}
