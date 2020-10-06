package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<IItem> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                zeroItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments zeroItems() {
        return Arguments.of("Zero items", "0.00", Collections.emptyList());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "3.96", Collections.singleton(aKilogramOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "6.46",
                Arrays.asList(aKilogramOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static IItem aPintOfMilk() {
        return new Item(new Product("A Pint of Milk", new BigDecimal("0.49")), 1);
    }

    private static IItem aPackOfDigestives() {
        return new Item(new Product("A Pack of Digestives", new BigDecimal("1.55")), 1);
    }

    private static WeighedProduct aPortionOfAmericanSweets() {
        WeighedProduct product = new WeighedProduct("American Sweets", new BigDecimal("0.99"), new BigDecimal(0.25),"kg");
        return product;
    }

    private static IItem aKilogramOfAmericanSweets() {
        return new ItemByWeight(aPortionOfAmericanSweets(), 4);
    }

    private static WeighedProduct aportionOfMixedSweets() {
        return new WeighedProduct("Mixed Sweets", new BigDecimal("0.50"), new BigDecimal(0.2), "kg");
    }

    private static IItem twoHundredGramsOfPickAndMix() {
        return new ItemByWeight(aportionOfMixedSweets(), 5);
    }
}