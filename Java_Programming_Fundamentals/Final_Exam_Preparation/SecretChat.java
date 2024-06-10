package Final_Exam_Preparation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        StringBuilder messageBuilder = new StringBuilder(message);
        String input = scanner.nextLine();

        while(!input.equals("Reveal")){
            String[] commandParts = input .split(":\\|:");
            String command = commandParts[0];

            switch (command){
                case "InsertSpace" :
                    int idx = Integer.parseInt(commandParts[1]);
                    messageBuilder.insert(idx, " ");
                    System.out.println(messageBuilder);
                    break;
                case "Reverse" :
                    String substring = commandParts[1];
                    if(messageBuilder.toString().contains(substring)) {
                        int indexOfSubstring = messageBuilder.indexOf((substring));
                        messageBuilder.delete(indexOfSubstring, indexOfSubstring + substring.length() + 1);
                        String reversedString = new StringBuilder(substring).reverse().toString();
                        messageBuilder.append(reversedString);
                        System.out.println(messageBuilder);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll" :
                    String textForReplacement = commandParts[1];
                    String replacementText = commandParts[2];
                    messageBuilder = new StringBuilder(messageBuilder.toString().replaceAll(textForReplacement, replacementText));
                    System.out.println(messageBuilder);
                    break;
            }

            input = scanner.nextLine();
        }

        System.out.println("You have a new text message: " + messageBuilder);
    }

}
