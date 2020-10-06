package kata.supermarket;

import java.math.BigDecimal;

public class Item implements IItem {

    private Product product;

    private int quantity;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getproduct() {
        return product;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void addItem(int qty) {
        this.quantity += quantity;
    }

    @Override
    public void subtractItem(int qty) {
        if (this.quantity - qty >= 0)
            this.quantity -= qty;
    }

    @Override
    public BigDecimal getPrice() {
        return product.getprice().multiply(BigDecimal.valueOf(quantity));
    }
}
