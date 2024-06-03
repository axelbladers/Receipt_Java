import java.time.LocalDate;
import java.util.Objects;

public class FoodProduct extends Product {
    private LocalDate expirationDate;
    private static final double MARKUP = 0.3;
    private static final double DISCOUNT = 0.5;

    public FoodProduct(String id, String name, double deliveryPrice, int quantity, LocalDate expirationDate) {
        super(id, name, deliveryPrice, "Food", quantity);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void updateExpirationDate(int daysToAdd) {
        if (expirationDate != null) {
            this.expirationDate = expirationDate.plusDays(daysToAdd);
        }
    }

    @Override
    public double getSellingPrice() {
        double price = getDeliveryPrice() * (1 + MARKUP);
        if (isCloseToExpiration()) {
            price *= (1 - DISCOUNT);
        }
        return price;
    }

    public int getExpirationDays() {
        if (expirationDate != null) {
            return (int) LocalDate.now().until(expirationDate).getDays();
        }
        return -1; // Return -1 if expiration date is not set
    }

    public double getMarkupPercentage() {
        return MARKUP;
    }

    protected boolean isCloseToExpiration() {
        return expirationDate != null && expirationDate.isBefore(LocalDate.now().plusDays(Store.getDaysThreshold()));
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "expirationDate=" + expirationDate +
                ", id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", deliveryPrice=" + getDeliveryPrice() +
                ", category='" + getCategory() + '\'' +
                ", quantity=" + getQuantity() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoodProduct that = (FoodProduct) o;
        return Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate);
    }
}
