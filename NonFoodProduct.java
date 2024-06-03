public class NonFoodProduct extends Product {
    private static final double MARKUP = 0.2;

    public NonFoodProduct(String id, String name, double deliveryPrice, int quantity) {
        super(id, name, deliveryPrice, "Non-Food", quantity);
    }

    @Override
    public double getSellingPrice() {
        return getDeliveryPrice() * (1 + MARKUP);
    }

    public double getMarkupPercentage() {
        return MARKUP;
    }

    @Override
    public String toString() {
        return "NonFoodProduct{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", deliveryPrice=" + getDeliveryPrice() +
                ", category='" + getCategory() + '\'' +
                ", quantity=" + getQuantity() +
                '}';
    }
}
