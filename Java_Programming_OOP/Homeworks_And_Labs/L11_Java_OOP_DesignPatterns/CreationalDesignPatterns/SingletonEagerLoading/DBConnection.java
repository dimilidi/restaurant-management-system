package Homeworks_And_Labs.L11_Java_OOP_DesignPatterns.CreationalDesignPatterns.SingletonEagerLoading;

public class DBConnection {

    // Instance of DBConnection exists before DBConnection is called
    private static final DBConnection INSTANCE = new DBConnection();

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        return INSTANCE;
    }

    // CRUD
    public void create(String data) {
        System.out.println("Created " + data);
    }
    public void read() {
        System.out.println("Data");
    }
    public void update(String data) {
        System.out.println("Updated " + data);
    }
    public void delete(String data) {
        System.out.println("Deleted " + data);
    }
}
