package kata.supermarket.promotion;

import kata.supermarket.IItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In real world the Promotions functionality could be implemented as a separate microservice to
 * the shopping cart functionality using REST and Spring Boot.
 */
public class PromotionService {
    private static PromotionService promotionService;
    private static Map<String, Promotion> promotionMap;

    public static synchronized PromotionService getPromotionServiceInstance() {
        new PromotionService();
        if (promotionService == null) {
            promotionService = new PromotionService();
        }
        if (promotionMap == null) {
            promotionMap = new HashMap<>();
        }
        return promotionService;
    }
    private PromotionService() {

    }

    public Promotion createPromotion(String productName, String promotionName, PromotionTypes promotionType) {
        Promotion promotion = new Promotion(productName, promotionName, promotionType);

        promotionMap.put(productName, promotion);

        return promotion;
    }

    public BigDecimal calculateDiscount(List<IItem> items) {
        BigDecimal totaldiscount = BigDecimal.valueOf(0);
        for (IItem item : items) {
            if (promotionMap.containsKey(item.getproduct().getName())) {
                Promotion promotion = promotionMap.get(item.getproduct().getName());
                if (promotion.getPromotionType() == PromotionTypes.BUY_ONE_GET_ONE_FREE &&
                        item.getQuantity() > 1 && item.getQuantity() % 2 == 0) {
                    BigDecimal originalPrice = item.getproduct().getprice();
                    BigDecimal newPrice = originalPrice.divide(new BigDecimal(2));

                    totaldiscount = item.getPrice().divide(BigDecimal.valueOf(2));
                }
            }
        }
        return totaldiscount;
    }
}
