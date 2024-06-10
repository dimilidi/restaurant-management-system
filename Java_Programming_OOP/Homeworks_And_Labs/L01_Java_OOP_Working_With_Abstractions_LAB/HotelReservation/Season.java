package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_LAB.HotelReservation;

public enum Season {
    SPRING(2),
    SUMMER(4),
    AUTUMN(1),
    WINTER(3);

    private final int factor;

    Season(int factor) {
        this.factor = factor;
    }

    public int getFactor() {
        return factor;
    }
}
