package Shop;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Store store = new Store();

            // Create products
            Product apple = new Product("Apple", 0.5, 10);
            Product bread = new Product("Bread", 1.5, 5);

            // Add products to store (delivery)
            store.addDeliveredProduct(apple, 10);
            store.addDeliveredProduct(bread, 5);

            // Create a customer
            Customer customer = new Customer("John Doe", 10.0);

            // Create a cashier and add to store
            Cashier cashier = new Cashier("Jane Smith", 1, 1500);
            store.addCashier(cashier);

            // Create a cash register and add products
            CashRegister register = new CashRegister();
            register.addProduct(apple, 3);
            register.addProduct(bread, 2);

            // Cashier sells products to the customer
            boolean success = cashier.sellProducts(customer, register, store);

            if (success) {
                System.out.println("Purchase successful.");
            } else {
                System.out.println("Purchase failed.");
            }

            // Check total receipts and revenue
            System.out.println("Total Receipts: " + store.getTotalReceipts());
            System.out.println("Total Revenue: " + store.getTotalRevenue());
            System.out.println("Total Expenses: " + store.getTotalExpenses());
            System.out.println("Profit: " + store.calculateProfit());

            // Read a specific receipt from file
            String receiptContent = Store.readReceiptFromFile(1);
            System.out.println("Receipt 1 Content:\n" + receiptContent);

        } catch (InsufficientQuantityException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
