import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    void testAddProduct() {
        Store store = new Store(5);
        Product apple = new FoodProduct("F001", "Apple", 0.5, 100, LocalDate.now().plusDays(10));
        Product chicken = new FoodProduct("F002", "Chicken", 5.0, 20, LocalDate.now().plusDays(5));
        store.addProduct(apple);
        store.addProduct(chicken);
        assertEquals(2, store.getProducts().size());
        assertEquals(150.0, store.getExpenses(), 0.01);
    }

    @Test
    void testAddCashier() {
        Store store = new Store(5);
        Cashier cashier = new Cashier("C001", "Alice", 1200);
        store.addCashier(cashier);
        assertEquals(1, store.getCashiers().size());
        assertEquals(1200.0, store.getExpenses(), 0.01);
    }

    @Test
    void testAddReceipt() {
        Store store = new Store(5);
        Cashier cashier = new Cashier("C001", "Alice", 1200);
        store.addCashier(cashier);
        Product apple = new FoodProduct("F001", "Apple", 0.5, 100, LocalDate.now().plusDays(10));
        Product shampoo = new NonFoodProduct("NF001", "Shampoo", 3.0, 50);
        Product chicken = new FoodProduct("F002", "Chicken", 5.0, 20, LocalDate.now().plusDays(5));
        Product rice = new FoodProduct("F003", "Rice", 1.2, 80, LocalDate.now().plusMonths(6));
        store.addProduct(apple);
        store.addProduct(shampoo);
        store.addProduct(chicken);
        store.addProduct(rice);
        CashRegister register = new CashRegister("CR001", cashier);

        List<Product> productsToBuy = new ArrayList<>();
        productsToBuy.add(apple);
        productsToBuy.add(shampoo);
        productsToBuy.add(chicken);
        productsToBuy.add(rice);
        List<Integer> quantities = List.of(2, 1, 3, 5);
        Receipt receipt = register.createReceipt(productsToBuy, quantities, LocalDateTime.now());
        store.addReceipt(receipt);
        assertEquals(1, store.getReceipts().size());
        assertTrue(receipt.toString().contains("Apple"));
        assertTrue(receipt.toString().contains("Shampoo"));
        assertTrue(receipt.toString().contains("Chicken"));
        assertTrue(receipt.toString().contains("Rice"));
    }

    @Test
    void testCalculateTotalTurnover() {
        Store store = new Store(5);
        Cashier cashier = new Cashier("C001", "Alice", 1200);
        store.addCashier(cashier);
        Product apple = new FoodProduct("F001", "Apple", 0.5, 100, LocalDate.now().plusDays(10));
        Product shampoo = new NonFoodProduct("NF001", "Shampoo", 3.0, 50);
        Product chicken = new FoodProduct("F002", "Chicken", 5.0, 20, LocalDate.now().plusDays(5));
        Product rice = new FoodProduct("F003", "Rice", 1.2, 80, LocalDate.now().plusMonths(6));
        store.addProduct(apple);
        store.addProduct(shampoo);
        store.addProduct(chicken);
        store.addProduct(rice);
        CashRegister register = new CashRegister("CR001", cashier);

        List<Product> productsToBuy = new ArrayList<>();
        productsToBuy.add(apple);
        productsToBuy.add(shampoo);
        productsToBuy.add(chicken);
        productsToBuy.add(rice);
        List<Integer> quantities = List.of(2, 1, 3, 5);
        Receipt receipt = register.createReceipt(productsToBuy, quantities, LocalDateTime.now());
        store.addReceipt(receipt);
        assertEquals(receipt.calculateTotal(), store.getTurnover(), 0.01);
    }

    @Test
    void testProfitCalculation() {
        Store store = new Store(5);
        Cashier cashier = new Cashier("C001", "Alice", 1200);
        store.addCashier(cashier);
        Product apple = new FoodProduct("F001", "Apple", 0.5, 100, LocalDate.now().plusDays(10));
        Product shampoo = new NonFoodProduct("NF001", "Shampoo", 3.0, 50);
        Product chicken = new FoodProduct("F002", "Chicken", 5.0, 20, LocalDate.now().plusDays(5));
        Product rice = new FoodProduct("F003", "Rice", 1.2, 80, LocalDate.now().plusMonths(6));
        store.addProduct(apple);
        store.addProduct(shampoo);
        store.addProduct(chicken);
        store.addProduct(rice);
        CashRegister register = new CashRegister("CR001", cashier);

        List<Product> productsToBuy = new ArrayList<>();
        productsToBuy.add(apple);
        productsToBuy.add(shampoo);
        productsToBuy.add(chicken);
        productsToBuy.add(rice);
        List<Integer> quantities = List.of(2, 1, 3, 5);
        Receipt receipt = register.createReceipt(productsToBuy, quantities, LocalDateTime.now());
        store.addReceipt(receipt);
        double turnover = store.getTurnover();
        double expenses = store.getExpenses();
        double profit = turnover - expenses;
        assertEquals(profit, store.getProfit(), 0.01);
    }

    @Test
    void testPurchaseProducts() {
        Store store = new Store(5);
        Cashier cashier = new Cashier("C001", "Alice", 1200);
        store.addCashier(cashier);
        Product apple = new FoodProduct("F001", "Apple", 0.5, 100, LocalDate.now().plusDays(10));
        Product shampoo = new NonFoodProduct("NF001", "Shampoo", 3.0, 50);
        Product chicken = new FoodProduct("F002", "Chicken", 5.0, 20, LocalDate.now().plusDays(5));
        Product rice = new FoodProduct("F003", "Rice", 1.2, 80, LocalDate.now().plusMonths(6));
        store.addProduct(apple);
        store.addProduct(shampoo);
        store.addProduct(chicken);
        store.addProduct(rice);
        CashRegister register = new CashRegister("CR001", cashier);
    
        List<Product> productsToBuy = new ArrayList<>();
        productsToBuy.add(apple);
        productsToBuy.add(shampoo);
        productsToBuy.add(chicken);
        productsToBuy.add(rice);
        List<Integer> quantities = List.of(2, 1, 3, 5);
        Receipt receipt = register.createReceipt(productsToBuy, quantities, LocalDateTime.now());
        store.addReceipt(receipt);
    
        try {
            Customer customer = new Customer("CU001", "Bob", 50);
            customer.purchaseProducts(productsToBuy, quantities);
            assertEquals(98, apple.getQuantity());
            assertEquals(49, shampoo.getQuantity());
            assertEquals(17, chicken.getQuantity());
            assertEquals(75, rice.getQuantity());
        } catch (InsufficientQuantityException e) {
            fail("Exception should not have been thrown");
        }
    }
}
    
