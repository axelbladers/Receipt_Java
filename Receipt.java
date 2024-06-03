import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Receipt {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private int id;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> products;
    private List<Integer> quantities;
    private double total;

    public Receipt(Cashier cashier, LocalDateTime dateTime, List<Product> products, List<Integer> quantities, double total) {
        this.id = counter.getAndIncrement();
        this.cashier = cashier;
        this.dateTime = dateTime;
        this.products = products;
        this.quantities = quantities;
        this.total = total;
    }

    public double calculateTotal() {
        return total;
    }

    public void saveToFile() {
        String fileName = "receipt_" + id + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(toString());
        } catch (IOException e) {
            System.err.println("Error writing receipt to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt ID: ").append(id).append("\n")
                .append("Cashier: ").append(cashier.getName()).append("\n")
                .append("Date: ").append(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n")
                .append("Products:\n");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            sb.append("  - ").append(product.getName()).append(", ID: ").append(product.getId())
                    .append(", Price: ").append(product.getSellingPrice()).append(", Quantity: ").append(quantities.get(i));
            if (product instanceof FoodProduct) {
                sb.append(", Expiration: ").append(((FoodProduct) product).getExpirationDate());
            }
            sb.append("\n");
        }

        sb.append("Total: ").append(total).append("\n");
        return sb.toString();
    }
}
