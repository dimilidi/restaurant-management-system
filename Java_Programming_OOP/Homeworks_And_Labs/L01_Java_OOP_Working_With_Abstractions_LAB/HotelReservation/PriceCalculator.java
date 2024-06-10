package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_LAB.HotelReservation;

public class PriceCalculator {

    public static double calculatePrice(double pricePerDay, int numberOfDays, Season season, DiscountType discountType) {
        return pricePerDay * numberOfDays * season.getFactor() * discountType.getDiscount();
    }
}
