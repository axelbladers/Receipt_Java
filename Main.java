import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store(5);
        Cashier cashier1 = new Cashier("C001", "Alice", 1200);
        CashRegister cashRegister1 = new CashRegister("CR001", cashier1);
        store.addCashier(cashier1);

        Product apple = new FoodProduct("F001", "Apple", 0.5, 100, LocalDate.now().plusDays(10));
        Product shampoo = new NonFoodProduct("NF001", "Shampoo", 3.0, 50);
        Product chicken = new FoodProduct("F002", "Chicken", 5.0, 20, LocalDate.now().plusDays(5));
        Product rice = new FoodProduct("F003", "Rice", 1.2, 80, LocalDate.now().plusMonths(6));
        store.addProduct(apple);
        store.addProduct(shampoo);
        store.addProduct(chicken);
        store.addProduct(rice);

        Customer customer = new Customer("CU001", "Bob", 50);
        List<Product> productsToBuy = new ArrayList<>();
        productsToBuy.add(apple);
        productsToBuy.add(shampoo);
        productsToBuy.add(chicken);
        productsToBuy.add(rice);

        List<Integer> quantitiesToBuy = new ArrayList<>();
        quantitiesToBuy.add(2);
        quantitiesToBuy.add(1);
        quantitiesToBuy.add(3);
        quantitiesToBuy.add(5);

        try {
            customer.purchaseProducts(productsToBuy, quantitiesToBuy);
            Receipt receipt = cashRegister1.createReceipt(productsToBuy, quantitiesToBuy, LocalDateTime.now());
            store.addReceipt(receipt);
            System.out.println("Receipt generated: \n" + receipt);
        } catch (InsufficientQuantityException e) {
            System.err.println(e.getMessage());
        }

        // Display store statistics
        System.out.println("Total receipts issued: " + store.getTotalReceipts());
        System.out.println("Total turnover: " + store.getTurnover());
        System.out.println("Total expenses: " + store.getExpenses());
        System.out.println("Total profit: " + store.getProfit());
    }
}
