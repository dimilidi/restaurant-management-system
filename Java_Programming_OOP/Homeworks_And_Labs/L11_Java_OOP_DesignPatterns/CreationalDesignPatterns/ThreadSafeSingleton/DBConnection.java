package Homeworks_And_Labs.L11_Java_OOP_DesignPatterns.CreationalDesignPatterns.ThreadSafeSingleton;

public class DBConnection {

    // volatile - the object is shared and visible for all threads
    private static volatile DBConnection INSTANCE;

    private DBConnection() {
    }
    //synchronized key word guarantees that the method getInstance is going to be called only by one thread at a time
    public static synchronized DBConnection getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DBConnection();
        }
        return INSTANCE;
    }

    //synchronized key word guarantees that the synchronized block is going to be called only by one thread at a time
    // getInstance method can be called from many threads
/*    public static  DBConnection getInstance() {
        if(INSTANCE == null) {
            synchronized(DBConnection.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBConnection();
                }
            }
        }
        return INSTANCE;
    }*/

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
