package Homeworks_And_Labs.L09_Java_Advanced_IteratorsAndComparators_LAB.BookComparator;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    //comparator ->
    // functional interface
    // that provides a way to define custom comparison logic between objects,
    // separate from their class definition.
    @Override
    public int compare(Book firstBook, Book secondBook) {
        int result = firstBook.getTitle().compareTo(secondBook.getTitle());
        if (result == 0) {
            //двете книги имат еднакво заглавие
            return Integer.compare(firstBook.getYear(), secondBook.getYear());
            //< 0 -> first < second
            //== 0 -> first == second
            // > 0 -> first > second
        }
        return result;
    }

}
