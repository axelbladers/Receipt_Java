import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;
    private double expenses;
    private static int daysThreshold;

    public Store(int daysThreshold) {
        products = new ArrayList<>();
        cashiers = new ArrayList<>();
        receipts = new ArrayList<>();
        expenses = 0;
        Store.daysThreshold = daysThreshold;
    }

    public void addProduct(Product product) {
        products.add(product);
        expenses += product.getDeliveryPrice() * product.getQuantity();
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
        expenses += cashier.getSalary();
    }

    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
        receipt.saveToFile();
    }

    public int getTotalReceipts() {
        return receipts.size();
    }

    public double getTurnover() {
        return receipts.stream().mapToDouble(Receipt::calculateTotal).sum();
    }

    public static int getDaysThreshold() {
        return daysThreshold;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public double getExpenses() {
        double productExpenses = products.stream()
                .mapToDouble(product -> product.getDeliveryPrice() * product.getQuantity())
                .sum();
        double cashierExpenses = cashiers.stream()
                .mapToDouble(Cashier::getSalary)
                .sum();
        return productExpenses + cashierExpenses;
    }

    public double getProfit() {
        return getTurnover() - getExpenses();
    }
}
