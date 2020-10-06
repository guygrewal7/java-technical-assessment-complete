package kata.supermarket;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, int qty, String expectedPrice) {
        final IItem weighedItem = xPortionsOfAmericanSweets(qty);
        assertEquals(new BigDecimal(expectedPrice), weighedItem.getPrice().multiply(BigDecimal.valueOf(weighedItem.getQuantity())));
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("0.99", "1.00", 4, "3.96"),
                Arguments.of("0.99", "0.25", 1, "0.99"),
                Arguments.of("0.99", "0.5", 2, "1.98"),
                Arguments.of("0.99", "0", 0, "0.00")
        );
    }

    private static WeighedProduct aPortionOfAmericanSweets() {
        WeighedProduct product = new WeighedProduct("American Sweets", new BigDecimal("0.99"), new BigDecimal(0.25),"kg");
        return product;
    }

    private static IItem xPortionsOfAmericanSweets(int qty) {
        return new ItemByWeight(aPortionOfAmericanSweets(), qty);
    }
}