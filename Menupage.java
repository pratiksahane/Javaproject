import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Menupage {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";

    public void printMenu() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load Oracle JDBC driver");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM mainCourse")) {

            try (ResultSet resultSet = statement.executeQuery()) {
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
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the code of the food ordered:");
            String codeOrdered = scanner.nextLine();
            System.out.println("Enter the quantity required:");
            int qty = scanner.nextInt();
            scanner.close();

            // Query the details of the food item ordered by the customer
            try (PreparedStatement orderStatement = connection.prepareStatement("SELECT * FROM mainCourse WHERE code = ?")) {
                orderStatement.setString(1, codeOrdered);
                try (ResultSet orderResultSet = orderStatement.executeQuery()) {
                    if (orderResultSet.next()) {
                        // Display the details of the ordered food item
                        String itemName = orderResultSet.getString("name");
                        double price = orderResultSet.getDouble("price");
                        System.out.println("Item ordered by customer is:");
                        System.out.println("Code: " + codeOrdered + ", Name: " + itemName + "Price: $" + price);
                        System.out.println("Quantity ordered is: " + qty);
                        printBill(codeOrdered, itemName, qty, price);
                    } else {
                        System.out.println("No item found with code: " + codeOrdered);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception occurred");
            e.printStackTrace();
        }
    }

    public void printBill(String codeOrdered, String itemName, int qty, double price) {
        String hotelName = "Orchid";
        String hotelAddress = "123 Main Street, City";

        double totalBill = qty * price;

        double gst = 18;
        double tax = 5;

        System.out.println("***************************");
        System.out.println("     Restaurant    " + hotelName);
        System.out.println("***************************");
        System.out.println(" " + hotelAddress);
        System.out.println("***************************");
        System.out.println("Items Ordered:");
        System.out.println("Code: " + codeOrdered);
        System.out.println("Name: "+itemName);
        System.out.println("Price: " +price+" Quantity: "+ qty);
        System.out.println("----------------------------------");

        // Calculate and print total, GST, tax, and final amount
        double gstAmount = totalBill * gst / 100;
        System.out.printf("%-10s %10.2f\n", "GST (18%)", gstAmount);

        double taxAmount = totalBill * tax / 100;
        System.out.printf("%-10s %10.2f\n", "Tax (5%)", taxAmount);

        double finalAmount = totalBill + gstAmount + taxAmount;
        System.out.println("----------------------------------");
        System.out.printf("%-5s %7.2f\n", "Total", finalAmount);
        System.out.println("***************************");
        System.out.println("Thank you for your visit!");
    }
}
