import java.util.Objects;

public class Cashier {
    private String id;
    private String name;
    private double salary;
    private CashRegister currentRegister;

    public Cashier(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public CashRegister getCurrentRegister() {
        return currentRegister;
    }

    public void setCurrentRegister(CashRegister currentRegister) {
        this.currentRegister = currentRegister;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashier cashier = (Cashier) o;
        return Double.compare(cashier.salary, salary) == 0 &&
                Objects.equals(id, cashier.id) &&
                Objects.equals(name, cashier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary);
    }
}
