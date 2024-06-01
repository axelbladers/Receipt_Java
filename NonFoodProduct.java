package Shop;

import java.time.LocalDate;

public class NonFoodProduct extends Product {
    private static final double MARKUP_PERCENTAGE = 0.3;

    public NonFoodProduct(String id, String name, double purchasePrice, LocalDate expirationDate, int quantity) {
        super(id, purchasePrice, quantity);
    }

    public double calculateSellingPrice(int daysToExpire, double discountPercentage) {
        double price = getPrice() * (1 + MARKUP_PERCENTAGE);
        if (isCloseToExpiration(daysToExpire)) {
            price *= (1 - discountPercentage / 100);
        }
        return price;
    }

	private boolean isCloseToExpiration(int daysToExpire) {
		// TODO Auto-generated method stub
		return false;
	}
}