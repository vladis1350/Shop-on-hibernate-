package com.vladis1350.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UserShoppingCartTest {

    private UserShoppingCart userShoppingCartOne;
    private UserShoppingCart userShoppingCartTwo;
    private UserShoppingCart userShoppingCartThree;
    private UserShoppingCart userShoppingCartFour;

    @BeforeEach
    void setUp() {
        userShoppingCartOne = UserShoppingCart.builder()
                .quantityProduct(12)
                .summOrder(BigDecimal.valueOf(22))
                .build();

        userShoppingCartTwo = UserShoppingCart.builder()
                .quantityProduct(12)
                .summOrder(BigDecimal.valueOf(22))
                .build();

        userShoppingCartThree = new UserShoppingCart();

        userShoppingCartFour = new UserShoppingCart();
    }

    @Test
    void testEquals() {
        Assert.assertEquals(userShoppingCartOne, userShoppingCartTwo);

        Assert.assertEquals(userShoppingCartThree, userShoppingCartFour);
    }

    @Test
    void testNotEquals() {
        userShoppingCartTwo.setQuantityProduct(16);
        Assert.assertNotEquals(userShoppingCartOne, userShoppingCartTwo);

        userShoppingCartThree.setSummOrder(BigDecimal.valueOf(616));
        userShoppingCartFour.setSummOrder(BigDecimal.valueOf(1313));
        Assert.assertNotEquals(userShoppingCartThree, userShoppingCartFour);
    }

    @Test
    void testHashCode() {
        Assert.assertNotEquals(userShoppingCartOne.hashCode(), userShoppingCartThree.hashCode());

        Assert.assertEquals(userShoppingCartOne.hashCode(), userShoppingCartTwo.hashCode());

        Assert.assertEquals(userShoppingCartThree.hashCode(), userShoppingCartFour.hashCode());

        userShoppingCartThree.setSummOrder(BigDecimal.valueOf(616));
        userShoppingCartFour.setSummOrder(BigDecimal.valueOf(1313));
        Assert.assertNotEquals(userShoppingCartThree.hashCode(), userShoppingCartFour.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertNotEquals(userShoppingCartOne.toString(), userShoppingCartThree.toString());

        Assert.assertEquals(userShoppingCartOne.toString(), userShoppingCartTwo.toString());

        Assert.assertEquals(userShoppingCartThree.toString(), userShoppingCartFour.toString());

        userShoppingCartThree.setSummOrder(BigDecimal.valueOf(616));
        userShoppingCartFour.setSummOrder(BigDecimal.valueOf(1313));
        Assert.assertNotEquals(userShoppingCartThree.toString(), userShoppingCartFour.toString());
    }
}