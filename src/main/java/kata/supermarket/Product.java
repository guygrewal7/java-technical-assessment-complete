package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private String name;

    private BigDecimal price;

    public Product() {
    }

    public Product(String name, BigDecimal price) {

        this.name = name;
        this.price = price;
    }

    public BigDecimal getprice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
