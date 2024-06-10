package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeArrayList {
    public static void main(String[] args) {
        String path = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\list.ser";

        // Create an ArrayList of doubles
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(3.14);
        doubleList.add(2.718);
        doubleList.add(1.618);

        // Save the ArrayList to a file
        saveArrayList(doubleList, path);

        // Load the ArrayList from the file
        ArrayList<Double> loadedList = loadArrayList(path);

        // Display the loaded ArrayList
        System.out.println("Loaded ArrayList: " + loadedList);
    }

    // Method to save an ArrayList of doubles to a file
    private static void saveArrayList(List<Double> list, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(list);
            System.out.println("ArrayList saved successfully to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load an ArrayList of doubles from a file
    private static ArrayList<Double> loadArrayList(String fileName) {
        ArrayList<Double> loadedList = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedList = (ArrayList<Double>) objectInputStream.readObject();
            System.out.println("ArrayList loaded successfully from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedList;
    }
}
