public class Main {
    public static void main(String[] args) {
        String hotelName = "Orchid";
        String hotelAddress = "123 Main Street, City";
        String[] itemsOrdered = {"Coffee", "Cake", "Omelette"};
        double[] itemPrices = {250, 500, 400};
        int[] itemQuantities = {2, 1, 2};
        double totalBill = 0;
        double gst = 0.18; // 18% GST
        double tax = 0.05; // 5% tax
        int orderId = 12345;

        System.out.println("***************************");
        System.out.println("     Hotel " + hotelName);
        System.out.println(" "+ hotelAddress);
        System.out.println("***************************");
        System.out.println("Order ID: " + orderId);
        System.out.println("***************************");
        System.out.println("Items Ordered:");
        System.out.println("----------------------------------");
        System.out.printf("%-10s %10s %10s\n", "Item", "Price", "Quantity");
        System.out.println("----------------------------------");

        for (int i = 0; i < itemsOrdered.length; i++) {
            System.out.printf("%-10s %10.2f %10d\n", itemsOrdered[i], itemPrices[i], itemQuantities[i]);
            totalBill += itemPrices[i] * itemQuantities[i];
        }

        System.out.println("----------------------------------");
        System.out.printf("%-10s %10.2f\n", "Subtotal", totalBill);

        double gstAmount = totalBill * gst;
        System.out.printf("%-10s %10.2f\n", "GST (18%)", gstAmount);

        double taxAmount = totalBill * tax;
        System.out.printf("%-10s %10.2f\n", "Tax (5%)", taxAmount);

        double finalAmount = totalBill + gstAmount + taxAmount;
        System.out.println("----------------------------------");
        System.out.printf("%-10s %10.2f\n", "Total", finalAmount);
        System.out.println("***************************");
        System.out.println("Thank you for your visit!");
    }
}
