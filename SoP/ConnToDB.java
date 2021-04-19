package SoP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnToDB {

    private static final String DB_NAME = "sop";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "coderslab";

    private static ConnToDB INSTANCE;
    private Connection conn;
    private ConnToDB(){ };

    public static ConnToDB getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnToDB();
        }
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }

}
