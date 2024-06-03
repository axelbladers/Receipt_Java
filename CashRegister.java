import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CashRegister {
    private String id;
    private Cashier cashier;

    public CashRegister(String id, Cashier cashier) {
        this.id = id;
        this.cashier = cashier;
    }

    public Receipt createReceipt(List<Product> products, List<Integer> quantities, LocalDateTime dateTime) throws InsufficientQuantityException {
        // Check if there is enough quantity for each product
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            if (product.getQuantity() < quantity) {
                throw new InsufficientQuantityException("Insufficient quantity of " + product.getName() + " - available: " + product.getQuantity());
            }
        }

        // Calculate total price and update product quantities
        double totalPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            double sellingPrice = product.getSellingPrice();
            
            // Check if the product is a FoodProduct and close to expiration
            if (product instanceof FoodProduct && ((FoodProduct) product).isCloseToExpiration()) {
                double discount = sellingPrice * ((FoodProduct) product).getMarkupPercentage();
                sellingPrice -= discount;
            }
            
            totalPrice += sellingPrice * quantity;
            product.decreaseQuantity(quantity);
        }

        // Create receipt
        Receipt receipt = new Receipt(cashier, dateTime, products, quantities, totalPrice);

        // Save receipt to file
        String fileName = "Receipt_" + LocalDateTime.now() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(receipt.toString());
        } catch (IOException e) {
            System.err.println("Error writing receipt to file: " + e.getMessage());
        }

        return receipt;
    }
}
