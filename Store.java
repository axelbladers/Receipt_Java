package Shop;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Cashier> cashiers;
    private List<Product> deliveredProducts;
    private List<Product> soldProducts;
    private List<Receipt> receipts;
    private double totalRevenue;
    private double totalExpenses;

    public Store() {
        this.cashiers = new ArrayList<>();
        this.deliveredProducts = new ArrayList<>();
        this.soldProducts = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
        totalExpenses += cashier.getMonthlySalary();
    }

    public void addDeliveredProduct(Product product, int quantity) {
        deliveredProducts.add(new Product(product.getName(), product.getPrice(), quantity));
        totalExpenses += product.getPrice() * quantity;
    }

    public void addSoldProduct(Product product, int quantity) {
        soldProducts.add(new Product(product.getName(), product.getPrice(), quantity));
    }

    public void addReceipt(Receipt receipt) throws IOException {
        receipts.add(receipt);
        totalRevenue += receipt.getTotalPrice();
        Store.saveReceiptToFile(receipt);
    }

    public double calculateProfit() {
        return totalRevenue - totalExpenses;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public int getTotalReceipts() {
        return receipts.size();
    }

    public static void saveReceiptToFile(Receipt receipt) throws IOException {
        String filename = "receipt_" + receipt.getReceiptNumber() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(receipt.toString());
        }
    }

    public static String readReceiptFromFile(int receiptNumber) throws IOException {
        String filename = "receipt_" + receiptNumber + ".txt";
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

	public void addSoldProduct(Receipt receipt) {
		// TODO Auto-generated method stub
		
	}

}
