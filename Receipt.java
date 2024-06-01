package Shop;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Receipt {
    private static final AtomicInteger count = new AtomicInteger(0); 
    private final int receiptNumber;
    private final Cashier cashier;
    private final LocalDateTime dateTime;
    private final List<Product> products;
    private final double totalPrice;

    public Receipt(Cashier cashier, List<Product> products, double totalPrice) {
        this.receiptNumber = count.incrementAndGet();
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt No: ").append(receiptNumber).append("\n");
        receipt.append("Cashier: ").append(cashier.getName()).append("\n");
        receipt.append("Date/Time: ").append(dateTime).append("\n");
        receipt.append("Products:\n");
        for (Product product : products) {
            receipt.append(product.getName()).append(" - Price: ").append(product.getPrice())
                    .append(", Quantity: ").append(product.getQuantity()).append("\n");
        }
        receipt.append("Total Price: ").append(totalPrice).append("\n");
        return receipt.toString();
    }
}
