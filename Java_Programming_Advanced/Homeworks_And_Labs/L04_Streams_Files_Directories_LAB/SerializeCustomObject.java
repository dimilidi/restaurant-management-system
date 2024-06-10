package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.*;

public class SerializeCustomObject {
    static class Cube implements Serializable {
        String color;
        double width;
        double height;
        double depth;

        public Cube(String color, double width, double height, double depth) {
            this.color = color;
            this.width = width;
            this.height = height;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cube cube = new Cube("black", 22, 22, 22);

        // Serialize
        FileOutputStream fileOutputStream = new FileOutputStream("cube-data.ser");
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
        objOutputStream.writeObject(cube);
        objOutputStream.close();

        // Deserialize
        FileInputStream inputStream = new FileInputStream("cube-data.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Cube cube2 = (Cube) objectInputStream.readObject();

    }
}
