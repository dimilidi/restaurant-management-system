package L03_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class PersonalTitles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double age = Double.parseDouble(scanner.nextLine());
        String gender = scanner.nextLine();
        String personalTitle = "";

        if(gender.equals("m")){
            if(age >= 16){
                personalTitle = "Mr.";
            } else {
                personalTitle = "Master";
            }
        } else if (gender.equals("f")){
            if(age >= 16){
                personalTitle = "Ms.";
            } else {
                personalTitle = "Miss";
            }
        }

        System.out.println(personalTitle);
    }
}
