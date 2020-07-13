package com.vladis1350.services;

import com.vladis1350.auth.bean.User;
import com.vladis1350.bean.ShoppingCarts;
import com.vladis1350.repositories.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCarts shoppingCart;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = User.builder().userName("valera777").build();
        shoppingCart = ShoppingCarts.builder()
                .user(user)
                .id(1L)
                .build();
    }

    @Test
    void saveShoppingCart() {
        given(this.shoppingCartRepository.save(any()))
                .willReturn(shoppingCart);
        ShoppingCarts shoppingCartTest = shoppingCartService.saveShoppingCart(shoppingCart);
        assertThat(shoppingCartTest).isEqualTo(shoppingCart);
    }

    @Test
    void findShoppingCartByUser() {
        given(this.shoppingCartRepository.findShoppingCartByUser(any()))
                .willReturn(shoppingCart);
        ShoppingCarts shoppingCartTest = shoppingCartService.findShoppingCartByUser(user);
        assertThat(shoppingCartTest).isEqualTo(shoppingCart);
    }

}