package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends Product {

    private final String weightUnit;
    private final BigDecimal weightPerUnit;

    public WeighedProduct(final String name, final BigDecimal pricePerWeighedUnit, final BigDecimal weightPerUnit, final String weightUnit) {
        super(name, pricePerWeighedUnit);
        this.weightPerUnit = weightPerUnit;
        this.weightUnit = weightUnit;
    }

    public String getWeightUnit() {

        return weightUnit;
    }

    public BigDecimal getWeightPerUnit() {
        return weightPerUnit;
    }
}
