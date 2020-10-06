package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight extends Item {

    private BigDecimal weight;
    private WeighedProduct weighedProduct;

    public ItemByWeight(final Product product, int qty) {
        super(product, qty);
        this.weighedProduct = (WeighedProduct) product;
        this.weight = weighedProduct.getWeightPerUnit().multiply(BigDecimal.valueOf(qty));
    }

    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public void addItem(int qty) {
        super.addItem(qty);
        weight.add(weighedProduct.getWeightPerUnit());
    }

    @Override
    public void subtractItem(int qty) {
        super.subtractItem(qty);
        if (getQuantity() - qty >= 0) {
            weight.subtract(weighedProduct.getWeightPerUnit());
        }
    }
}
