package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.Telephony;

import java.util.List;

public class Smartphone implements  Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder calledNumbers = new StringBuilder();
        for (String number : this.numbers) {
            if (!containsLetter(number)) {
                calledNumbers.append("Calling... ").append(number).append(System.lineSeparator());
            } else {
                calledNumbers.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return calledNumbers.toString().trim();
    }

    @Override
    public String browse() {
        StringBuilder browsedURLs = new StringBuilder();
        for (String url : this.urls) {
            if (!containsDigit(url)) {
                browsedURLs
                        .append("Browsing: ")
                        .append(url)
                        .append("!")
                        .append(System.lineSeparator());
            } else {
                browsedURLs.append("Invalid URL!").append(System.lineSeparator());
            }
        }
        return browsedURLs.toString().trim();
    }

    private boolean containsDigit(String url) {
        for (char c : url.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLetter(String number) {
        for (char c : number.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return true;
            }
        }
        return false;
    }

}
