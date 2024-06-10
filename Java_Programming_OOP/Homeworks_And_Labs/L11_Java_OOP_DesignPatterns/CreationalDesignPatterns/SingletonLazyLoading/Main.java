package Homeworks_And_Labs.L11_Java_OOP_DesignPatterns.CreationalDesignPatterns.SingletonLazyLoading;

public class Main {
    public static void main(String[] args) {
        // initialized 3 objects of DBConnection.
        // These 3 objects share 1 instance, they have the same reference
        // that is being registered in the virtual machine
        DBConnection dbConnection1 = DBConnection.getInstance();
        DBConnection dbConnection2 = DBConnection.getInstance();
        DBConnection dbConnection3 = DBConnection.getInstance();

        dbConnection2.create("test");
        dbConnection3.delete("test");
        dbConnection1.read();

    }
}
