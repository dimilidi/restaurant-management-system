package Final_Exam_Preparation;

import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stops = scanner.nextLine();

        while(true){
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("travel")) {
                break;
            }
            String[] commandParts = input.split(":");
            String command = commandParts[0];

            switch(command){
                case "Add Stop" :
                    int index = Integer.parseInt(commandParts[1]);
                    String stop = commandParts[2];
                    if(isValidIndex(index, stops)){
                        String left = stops.substring(0,index);
                        String right = stops.substring(index);
                        stops = left + stop + right;
                    }

                    break;
                case "Remove Stop" :
                    int indexStart = Integer.parseInt(commandParts[1]);
                    int indexEnd = Integer.parseInt(commandParts[2]);
                    if(isValidIndex(indexStart, stops) && isValidIndex(indexEnd+1, stops)){
                        String left = stops.substring(0, indexStart);
                        String right = stops.substring(indexEnd+1);
                        stops = left + right;
                    }
                    break;
                case "Switch" :
                    String oldString = commandParts[1];
                    String newString = commandParts[2];
                    stops = stops.replace(oldString,newString);
                    break;
            }

            System.out.println(stops);
        }

        System.out.printf("Ready for world tour! Planned stops: %s", stops);
    }

    private static boolean isValidIndex(int index, String string) {
        return index >= 0 && index <= string.length();
    }
}
