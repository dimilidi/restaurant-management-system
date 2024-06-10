package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_EXC.CardsWithPower;

/*(CLUBS - 0, DIAMONDS - 13, HEARTS - 26, SPADES - 39).*/
public enum Suit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private final int power;

    public int getPower() {
        return power;
    }

    Suit(int power) {
        this.power = power;
    }
}
