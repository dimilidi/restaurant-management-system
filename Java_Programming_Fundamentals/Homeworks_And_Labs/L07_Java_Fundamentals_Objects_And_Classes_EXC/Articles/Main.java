package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.Articles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] article = scanner.nextLine().split(", ");
        String title = article[0];
        String content = article[1];
        String author = article[2];

        int countCommands = Integer.parseInt(scanner.nextLine());
        Article newArticle = new Article(title, content, author);


        for (int i = 0; i < countCommands; i++) {
            String[] command = scanner.nextLine().split(": ");
            String commandKey = command[0];
            String commandValue = command[1];
            switch (commandKey){
                case "Edit":
                    newArticle.edit(commandValue);
                    break;
                case "ChangeAuthor":
                    newArticle.changeAuthor(commandValue);
                    break;
                case "Rename":
                    newArticle.rename(commandValue);
                    break;
            }
        }

        System.out.println(newArticle.toString());
    }
}
