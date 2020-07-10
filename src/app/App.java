package app;

public class App {
    public static void main(String[] args) throws Exception {
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASSWORD");
        String database = System.getenv("DB_DATABASE");

        Database db = new Database(database, user, pass);
        db.initDatabase();

    }
}
