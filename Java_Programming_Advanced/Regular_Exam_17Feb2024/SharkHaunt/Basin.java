package Regular_Exam_17Feb2024.SharkHaunt;

import java.util.*;

public class Basin {
    private String name;
    private int capacity;
    private List<Shark> sharks;

    public Basin(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.sharks = new LinkedList<>();
    }

    public void addShark(Shark shark) {
        if(capacity > sharks.size()) {
            sharks.add(shark);
        } else {
            System.out.println("This basin is at full capacity!");
        }
    }

    public boolean removeShark(String kind) {
        return sharks.removeIf(shark -> shark.getKind().equals(kind));
    }

    public Shark getLargestShark() {
        return sharks.stream().max(Comparator.comparing(Shark::getLength)).get();
    }

    public Shark getShark(String kind) {
        return sharks.stream().filter(shark -> kind.equals(shark.getKind())).findFirst().orElse(null);
    }

    public int getCount() {
        return sharks.size();
    }

    public int getAverageLength() {
        double average = sharks.stream()
                .mapToDouble(Shark::getLength)
                .average()
                .orElse(0.0);

        return (int) average;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sharks in %s:", name)).append(System.lineSeparator());

        for (Shark shark : sharks) {
            sb.append(shark.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

}
