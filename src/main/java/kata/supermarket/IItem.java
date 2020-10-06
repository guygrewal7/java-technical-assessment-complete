package kata.supermarket;

import java.math.BigDecimal;

public interface IItem {
    Product getproduct();

    BigDecimal getPrice();

    int getQuantity();

    void addItem(int qty);

    void subtractItem(int qty);
}
