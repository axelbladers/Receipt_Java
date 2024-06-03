import java.util.List;

public class Customer {
    private String id;
    private String name;
    private double balance;

    public Customer(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void purchaseProducts(List<Product> products, List<Integer> quantities) throws InsufficientQuantityException {
        double totalCost = 0;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            if (product.getQuantity() < quantity) {
                throw new InsufficientQuantityException("Not enough quantity for product: " + product.getName());
            }
            totalCost += product.getSellingPrice() * quantity;
        }

        if (balance < totalCost) {
            throw new InsufficientQuantityException("Insufficient balance.");
        }

        for (int i = 0; i < products.size(); i++) {
            products.get(i).decreaseQuantity(quantities.get(i));
        }

        balance -= totalCost;
    }
}
