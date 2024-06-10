package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.UnitTesting.src.main.java.magicGame;

public class Magic {
    private String name;
    private int bullets;

    public Magic(String name, int bullets) {
        this.setName(name);
        this.setBullets(bullets);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getBullets() {
        return this.bullets;
    }

    private void setBullets(int bullets) {
        this.bullets = bullets;
    }
}
