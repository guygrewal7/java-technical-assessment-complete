package kata.supermarket.promotion;

import kata.supermarket.*;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.math.BigDecimal;

public class PromotionServiceTest extends TestNG {
    PromotionService promotionService;

    @BeforeSuite
    public void setup() {
        promotionService = PromotionService.getPromotionServiceInstance();
    }

    @Test
    public void discountAppliedSingleItemByWeight() {
        // Create promotion
        Promotion promotion = promotionService.createPromotion("Yorkshire Tea", "Autumn20 Buy 1 get 1 free", PromotionTypes.BUY_ONE_GET_ONE_FREE);

        // Create item
        IItem item = new ItemByWeight(new WeighedProduct("Yorkshire Tea", BigDecimal.valueOf(3.99),
                BigDecimal.valueOf(1.5), "kg" ), 2);

        // Add to basket
        Basket basket = new Basket();
        basket.add(item);

        // Calculate discount
        BigDecimal discount = basket.discount();

        Assert.assertEquals(discount, BigDecimal.valueOf(3.99));
    }

    @Test
    public void noDiscountAppliedSingleItemByWeight() {
        // Create promotion
        Promotion promotion = promotionService.createPromotion("Yorkshire Tea", "Autumn20 Buy 1 get 1 free", PromotionTypes.BUY_ONE_GET_ONE_FREE);

        // Create item
        IItem item = new ItemByWeight(new WeighedProduct("Half a dozen bananas", BigDecimal.valueOf(3.99),
                BigDecimal.valueOf(1.5), "kg" ), 2);

        // Add to basket
        Basket basket = new Basket();
        basket.add(item);

        // Calculate discount
        BigDecimal discount = basket.discount();

        Assert.assertEquals(discount, BigDecimal.valueOf(0));
    }

    @Test
    public void discountAppliedMultipleItems() {
        // Create promotion
        Promotion promotion = promotionService.createPromotion("Yorkshire Tea", "Autumn20 Buy 1 get 1 free", PromotionTypes.BUY_ONE_GET_ONE_FREE);

        // Create items
        IItem item1 = new ItemByWeight(new WeighedProduct("Half a dozen bananas", BigDecimal.valueOf(1.79),
                BigDecimal.valueOf(1.5), "kg" ), 2);

        IItem item2 = new Item(new Product("Yorkshire Tea", BigDecimal.valueOf(3.99)), 2);

        // Add to basket
        Basket basket = new Basket();
        basket.add(item1);
        basket.add(item2);

        // Calculate discount
        BigDecimal discount = basket.discount();

        Assert.assertEquals(discount, BigDecimal.valueOf(3.99));
    }
}
