package Final_Exam_Preparation;

import java.util.*;

public class Pianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int piecesCount = Integer.parseInt(scanner.nextLine());

        Map<String, String> composerByPiece = new LinkedHashMap<>();
        Map<String, String> keyByPiece = new LinkedHashMap<>();


        for (int i = 0; i < piecesCount; i++) {
            String input = scanner.nextLine();
            String[] infoParts = input.split("\\|");

            String piece = infoParts[0];
            String composer = infoParts[1];
            String key = infoParts[2];

            composerByPiece.put(piece, composer);
            keyByPiece.put(piece, key);
        }

        while(true){
            String input = scanner.nextLine();
            if(input.equals("Stop")){
                break;
            }

            String[] commandParts = input.split("\\|");
            String command = commandParts[0];



            switch(command) {
                case "Add" :
                    String piece = commandParts[1];
                    String composer = commandParts[2];
                    String key = commandParts[3];
                    if(composerByPiece.containsKey(piece)){
                       System.out.printf("%s is already in the collection!%n",piece);
                    } else {
                        composerByPiece.put(piece, composer);
                        keyByPiece.put(piece, key);
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                    }
                    break;
                case "Remove" :
                    String pieceToRemove = commandParts[1];
                    if(!composerByPiece.containsKey(pieceToRemove)){
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", pieceToRemove);
                    } else {
                        composerByPiece.remove(pieceToRemove);
                        keyByPiece.remove(pieceToRemove);
                        System.out.printf("Successfully removed %s!%n", pieceToRemove);
                    }
                    break;
                case "ChangeKey" :
                    String pieceToChange = commandParts[1];
                    String newKey = commandParts[2];
                    if (!composerByPiece.containsKey(pieceToChange)) {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", pieceToChange);
                    } else {
                        String oldKey = keyByPiece.get(pieceToChange);
                        keyByPiece.put(pieceToChange, newKey);
                        System.out.printf("Changed the key of %s to %s!%n", pieceToChange, newKey);
                    }
                    break;
            }
        }

       /* composerByPiece
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                  int pieceNameSortResult =   e1.getKey().compareTo(e2.getKey());
                  if(pieceNameSortResult != 0){
                        return pieceNameSortResult;
                  }

                  return e1.getValue().compareTo(e2.getValue());
                })
                .forEach((e) -> {
                    String piece = e.getKey();
                    String composer = e.getValue();
                    String key = keyByPiece.get(piece);
                 System.out.printf("%s -> Composer: %s, Key: %s%n", piece, composer, key);
              });*/

        for(Map.Entry<String, String> e : composerByPiece.entrySet()) {
            String piece = e.getKey();
            String composer = e.getValue();
            String key = keyByPiece.get(piece);
            System.out.printf("%s -> Composer: %s, Key: %s%n", piece, composer, key);
        }

    }

}
