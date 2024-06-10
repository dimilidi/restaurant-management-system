package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.*;

public class SerializeCustomObject {
    public static void main(String[] args) {
        Course course = new Course("Java Advanced", 100);

        String path = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\course.ser";

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(path, true);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                FileInputStream fileInputStream = new FileInputStream(path);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            // serializing
            objectOutputStream.writeObject(course);

            // deserializing
            Course deserializedCourse = (Course) objectInputStream.readObject();
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
