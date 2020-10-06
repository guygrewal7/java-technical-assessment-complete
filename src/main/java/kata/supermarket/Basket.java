package kata.supermarket;

import kata.supermarket.promotion.PromotionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<IItem> items;

    private PromotionService promotionService;

    public Basket() {

        this.items = new ArrayList<>();
        this.promotionService = PromotionService.getPromotionServiceInstance();
    }

    public void add(final IItem item) {

        this.items.add(item);
    }

    List<IItem> items() {

        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {

        return new TotalCalculator().calculate();
    }

    public BigDecimal discount() {
        return new TotalCalculator().discount();
    }

    private class TotalCalculator {
        private final List<IItem> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(IItem::getPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discount());
        }

        private BigDecimal discount() {
            return promotionService.calculateDiscount(items);
        }
    }
}
