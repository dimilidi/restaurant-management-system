package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.Magic;

import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician{

    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    protected MagicianImpl(String username, int health, int protection, Magic magic) {
        if(username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;

        if(health < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
        updateIsAlive();

        if(protection < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;

        if(magic == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;


    }

    private void updateIsAlive() {
        this.isAlive =  this.health > 0;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public Magic getMagic() {
        return magic;
    }


    @Override
    public void takeDamage(int points) {
        if (points > protection) {
            int damageTaken = points - protection;
            health -= damageTaken;
            protection = 0;

            updateIsAlive();
        } else {
            this.protection -= points;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getUsername() + System.lineSeparator() +
                "Health: " + getHealth() + System.lineSeparator() +
                "Protection: " + getProtection() + System.lineSeparator() +
                "Magic: " + getMagic().getName();
    }
}
