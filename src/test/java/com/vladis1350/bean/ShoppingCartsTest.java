package com.vladis1350.bean;

import com.vladis1350.auth.bean.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartsTest {

    private ShoppingCarts shoppingCartOne;
    private ShoppingCarts shoppingCartTwo;
    private ShoppingCarts shoppingCartThree;
    private ShoppingCarts shoppingCartFour;
    private User userOne;
    private User userTwo;



    @BeforeEach
    void setUp() {
        userOne = User.builder().id(1L).build();
        userTwo = User.builder().id(2L).build();
        shoppingCartOne = ShoppingCarts.builder()
                .id(1L)
                .user(userOne)
                .build();

        shoppingCartTwo = ShoppingCarts.builder()
                .id(2L)
                .user(userTwo)
                .build();

        shoppingCartThree = new ShoppingCarts();
        shoppingCartFour = new ShoppingCarts();
    }

    @Test
    void testEquals() {
        Assert.assertNotEquals(shoppingCartOne, shoppingCartTwo);

        shoppingCartOne.setId(2L);
        shoppingCartOne.setUser(userTwo);
        Assert.assertEquals(shoppingCartOne, shoppingCartTwo);

        Assert.assertEquals(shoppingCartThree, shoppingCartFour);

        Assert.assertNotEquals(shoppingCartOne, shoppingCartThree);
        userTwo.setId(166L);
        shoppingCartFour.setUser(userTwo);
        Assert.assertNotEquals(shoppingCartThree, shoppingCartFour);
    }

    @Test
    void testHashCode() {
        Assert.assertNotEquals(shoppingCartOne.hashCode(), shoppingCartTwo.hashCode());

        shoppingCartOne.setId(2L);
        shoppingCartOne.setUser(userTwo);
        Assert.assertEquals(shoppingCartOne.hashCode(), shoppingCartTwo.hashCode());

        Assert.assertNotEquals(shoppingCartOne.hashCode(), shoppingCartThree.hashCode());

        Assert.assertEquals(shoppingCartThree.hashCode(), shoppingCartFour.hashCode());

        shoppingCartFour.setId(166L);
        Assert.assertNotEquals(shoppingCartThree.hashCode(), shoppingCartFour.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertNotEquals(shoppingCartOne.toString(), shoppingCartTwo.toString());

        shoppingCartOne.setId(2L);
        shoppingCartOne.setUser(userTwo);
        Assert.assertEquals(shoppingCartOne.toString(), shoppingCartTwo.toString());

        Assert.assertNotEquals(shoppingCartOne.toString(), shoppingCartThree.toString());

        Assert.assertEquals(shoppingCartThree.toString(), shoppingCartFour.toString());
        shoppingCartFour.setId(666L);
        userTwo.setId(666L);
        shoppingCartThree.setUser(userTwo);
        Assert.assertNotEquals(shoppingCartThree.toString(), shoppingCartFour.toString());
    }
}