package FinalExam;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String password = scanner.nextLine();
            validateAndEncryptPassword(password);
        }

    }

    private static void validateAndEncryptPassword(String password) {
        String regex = "([!@#$%^&*()_+={}\\[\\]:;<>,.?/~`|\\\\-]|[a-zA-Z]|[0-9]+)>(\\d{3})\\|([a-z]{3})\\|([A-Z]{3})\\|([!@#$%^&*()_+={}\\[\\]:;<>,.?/~`|\\\\-]{3}|[a-zA-Z]{3}|[0-9]{3})<(\\1)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.find()) {
            String encryptedPassword = matcher.group(2) + matcher.group(3) + matcher.group(4) + matcher.group(5);
            System.out.println("Password: " + encryptedPassword);
        } else {
            System.out.println("Try another password!");
        }



    }
}
