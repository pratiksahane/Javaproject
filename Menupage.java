import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class Menupage {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";
    public static double totalSales = 0;

    public void printMenu() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load Oracle JDBC driver");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM mainCourse")) {
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
            }

            try (Scanner scanner = new Scanner(System.in)) {
                ArrayList<String> orderedCodes = new ArrayList<>();
                ArrayList<String> namelist = new ArrayList<>();
                ArrayList<Double> pricelist = new ArrayList<>();
                boolean addMore = true;
                double totalBill = 0;
                while (addMore) {
                    System.out.println("Enter the code of the food ordered:");
                    String codeOrdered = scanner.nextLine();
                    orderedCodes.add(codeOrdered);
                    System.out.println("Enter the quantity required:");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    try (PreparedStatement orderStatement = connection.prepareStatement("SELECT * FROM mainCourse WHERE code = ?")) {
                        orderStatement.setString(1, codeOrdered);
                        try (ResultSet orderResultSet = orderStatement.executeQuery()) {
                            if (orderResultSet.next()) {
                                // Display the details of the ordered food item
                                String itemName = orderResultSet.getString("name");
                                namelist.add(itemName);
                                double price = orderResultSet.getDouble("price");
                                pricelist.add(price);
                                System.out.println("Item ordered by customer is:");
                                System.out.println("Code: " + codeOrdered + ", Name: " + itemName + ", Price: $" + price);
                                // Assuming qty is defined somewhere in your code
                                System.out.println("Quantity ordered is: " + qty);
                                totalBill += price * qty;
                            } else {
                                System.out.println("No item found with code: " + codeOrdered);
                            }
                        }
                    }

                    System.out.println("Do you want to add more food codes? (yes/no)");
                    String response = scanner.nextLine();
                    if (!response.equalsIgnoreCase("yes")) {
                        addMore = false;
                    }
                }

                printBill(totalBill,orderedCodes,namelist,pricelist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void printBill(double totalBill, ArrayList<String> orderedCodes, ArrayList<String> nameList, ArrayList<Double> priceList) {
        String hotelName = "Orchid";
        String hotelAddress = "123 Main Street, City";

        double gst = 18;
        double tax = 5;

        System.out.println("***************************");
        System.out.println("     Restaurant    " + hotelName);
        System.out.println("***************************");
        System.out.println(" " + hotelAddress);
        System.out.println("***************************");
        System.out.println("Items Ordered:");
        System.out.println("----------------------------------");
        for (int i = 0; i < orderedCodes.size(); i++) {
            String code = orderedCodes.get(i);
            String name = nameList.get(i);
            Double price = priceList.get(i);
            
            System.out.println("Code: " + code + ", Name: " + name + ", Price: " + price);
            System.out.println("\n");
        }
        System.out.println("----------------------------------");
        // Calculate and print total, GST, tax, and final amount
        double gstAmount = totalBill * gst / 100;
        System.out.printf("%-10s %10.2f\n", "GST (18%)", gstAmount);

        double taxAmount = totalBill * tax / 100;
        System.out.printf("%-10s %10.2f\n", "Tax (5%)", taxAmount);

        double finalAmount = totalBill + gstAmount + taxAmount;
        System.out.println("----------------------------------");
        System.out.printf("%-5s %7.2f\n", "Total", finalAmount);
        Menupage.addTotalSales(finalAmount);
        System.out.println("***************************");
        System.out.println("Thank you for your visit!");
    }
    
    public static void addTotalSales(double finalAmount){
        totalSales += finalAmount;
    }
    
    public static double getTotalSales(){
        return totalSales; 
    }
    public static void setTotalSales(double newTotalSales) {
        totalSales = newTotalSales;
    }
    
}
