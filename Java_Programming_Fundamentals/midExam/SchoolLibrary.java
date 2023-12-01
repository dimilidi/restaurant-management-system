package midExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolLibrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> shelf = new ArrayList<>();

        String[] initialShelf = scanner.nextLine().split("&");
        shelf.addAll(List.of(initialShelf));

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Done")) {
                break;
            }

            String[] command = input.split(" \\| ");
            String action = command[0];

            switch (action) {
                case "Add Book":
                    String bookToAdd = command[1];
                    if (!shelf.contains(bookToAdd)) {
                        shelf.add(0, bookToAdd);
                    }
                    break;
                case "Take Book":
                    String bookToTake = command[1];
                    shelf.remove(bookToTake);
                    break;
                case "Swap Books":
                    String book1 = command[1];
                    String book2 = command[2];
                    int index1 = shelf.indexOf(book1);
                    int index2 = shelf.indexOf(book2);

                    if (index1 != -1 && index2 != -1) {
                        shelf.set(index1, book2);
                        shelf.set(index2, book1);
                    }
                    break;
                case "Insert Book":
                    String bookToInsert = command[1];
                    if (!shelf.contains(bookToInsert)) {
                        shelf.add(bookToInsert);
                    }
                    break;
                case "Check Book":
                    int index = Integer.parseInt(command[1]);
                    if (isValidIndex(index, shelf)) {
                        System.out.println(shelf.get(index));
                    }
                    break;
            }
        }

        System.out.println(String.join(", ", shelf));
    }

    private static boolean isValidIndex(int index, List<String> list) {
        return index >= 0 && index < list.size();
    }
}
