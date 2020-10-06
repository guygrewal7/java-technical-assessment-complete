package kata.supermarket.promotion;

public class Promotion {
    private String productName;
    private String name;
    private PromotionTypes promotionType;

    public Promotion(String productName, String name, PromotionTypes promotionType) {
        this.productName = productName;
        this.name = name;
        this.promotionType = promotionType;
    }

    public String getProduct() {
        return productName;
    }

    public String getName() {
        return name;
    }

    public PromotionTypes getPromotionType() {
        return promotionType;
    }
}
