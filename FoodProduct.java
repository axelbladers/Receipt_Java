package Shop;

import java.time.LocalDate;

public class FoodProduct extends Product {
    private static final double MARKUP_PERCENTAGE = 0.2;

    public FoodProduct(String id, String name, double purchasePrice, LocalDate expirationDate, int quantity) {
        super(id, purchasePrice, quantity);
    }

    @Override
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