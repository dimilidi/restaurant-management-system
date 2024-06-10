package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_EXC.CardsWithPower;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int calcPower() {
        return Rank.valueOf(rank).getPower() + Suit.valueOf(suit).getPower();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", rank, suit, calcPower());
    }
}
