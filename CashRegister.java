package Shop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashRegister {
    private Map<Product, Integer> products;

    public CashRegister() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) throws InsufficientQuantityException {
        product.reduceQuantity(quantity);
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public double getTotalPrice() {
        return products.entrySet().stream().mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
    }

    public Receipt generateReceipt(Cashier cashier) {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productList.add(new Product(entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue()));
        }
        return new Receipt(cashier, productList, getTotalPrice());
    }
}
