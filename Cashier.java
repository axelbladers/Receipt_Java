package Shop;
import java.io.IOException;

public class Cashier {
    private String name;
    private int idNumber;
    private double monthlySalary;

    public Cashier(String name, int idNumber, double monthlySalary) {
        this.name = name;
        this.idNumber = idNumber;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public boolean sellProducts(Customer customer, CashRegister register, Store store) {
        if (customer.getMoney() >= register.getTotalPrice()) {
            customer.setMoney(customer.getMoney() - register.getTotalPrice());
            Receipt receipt = register.generateReceipt(this);
            System.out.println(receipt);
            store.addSoldProduct(receipt);
            try {
                store.addReceipt(receipt);
            } catch (IOException e) {
                System.err.println("Failed to save receipt: " + e.getMessage());
            }
            return true;
        } else {
            System.out.println("Not enough money to buy products.");
            return false;
        }
    }
}
