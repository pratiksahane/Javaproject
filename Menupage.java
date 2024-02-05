import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Menupage {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";

    public void printMenu() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            if (connection != null) {
                System.out.println("Connected to the database");
                System.out.println("Printing menu list:");
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
