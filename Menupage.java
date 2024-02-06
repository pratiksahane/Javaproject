import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        try (
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            // Prepare SQL statement to select menu items
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM mainCourse");
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                System.out.println("Menu Present in Our Restaurant:");
                System.out.println("------------------------------");
                do {
                    // Fetch and display menu item details
                    String code = resultSet.getString("code");
                    String itemName = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    System.out.println("Code: " + code + ", Name: " + itemName + ", Price: $" + price);
                } while (resultSet.next());
            } else {
                System.out.println("No items found in the menu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
