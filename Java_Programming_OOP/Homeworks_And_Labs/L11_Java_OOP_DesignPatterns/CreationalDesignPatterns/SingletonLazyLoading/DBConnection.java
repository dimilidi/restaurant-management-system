package Homeworks_And_Labs.L11_Java_OOP_DesignPatterns.CreationalDesignPatterns.SingletonLazyLoading;

public class DBConnection {
    private static DBConnection INSTANCE;

    private DBConnection() {
    }

    // instantiate INSTANCE only once when the method getInstance() is called for the first time
    public static DBConnection getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DBConnection();
        }
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
