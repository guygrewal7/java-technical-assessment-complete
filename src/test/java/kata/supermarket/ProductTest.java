package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void productHasExpectedNameAndPrice() {
        final BigDecimal price = new BigDecimal("2.49");
        final String name = "Pack of Heinz baked Beans";
        Product product = new Product(name, price);
        assertEquals(price, product.getprice());
        assertEquals(name, product.getName());
    }
}