package com.vladis1350.services;

import com.vladis1350.bean.Product;
import com.vladis1350.bean.ShoppingCarts;
import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.bean.embedded.UserShoppingCartId;
import com.vladis1350.repositories.UserShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class UserShoppingCartServiceTest {

    @Mock
    private UserShoppingCartRepository userShoppingCartRepository;

    @InjectMocks
    private UserShoppingCartService userShoppingCartService;

    private UserShoppingCart userShoppingCart;
    private UserShoppingCartId userShoppingCartId;
    private Product product;
    private ShoppingCarts shoppingCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        product = Product.builder().id(1L).build();
        shoppingCart = ShoppingCarts.builder().id(6L).build();
        userShoppingCartId = UserShoppingCartId.builder()
                .shoppingCarts(shoppingCart)
                .product(product)
                .build();

        userShoppingCart = UserShoppingCart.builder()
                .pk(userShoppingCartId)
                .quantityProduct(10)
                .summOrder(BigDecimal.valueOf(132.0))
                .build();
    }

    @Test
    void save() {
        given(this.userShoppingCartRepository.save(any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = userShoppingCartService.save(userShoppingCart);
        assertThat(userShoppingCartTest).isEqualTo(userShoppingCart);
    }

    @Test
    void update() {
        given(this.userShoppingCartRepository.save(any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = userShoppingCartService.update(userShoppingCart);
        assertThat(userShoppingCartTest).isEqualTo(userShoppingCart);
    }

    @Test
    void getByProductId() {
        given(this.userShoppingCartRepository.getByProductId(any(), any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = userShoppingCartService.getByProductId(1L, 6L);
        assertThat(userShoppingCartTest).isEqualTo(userShoppingCart);
    }

    @Test
    void removeProductByIdCart() {
        given(this.userShoppingCartRepository.deleteProductFromCart(any(), any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = userShoppingCartService.remove(6L, 1L);
        assertThat(userShoppingCartTest).isEqualTo(userShoppingCart);
    }

    @Test
    void findAll() {
        List<UserShoppingCart> userShoppingCartList = new ArrayList<>();
        userShoppingCartList.add(userShoppingCart);
        given(this.userShoppingCartRepository.findAll()).willReturn(userShoppingCartList);
        List<UserShoppingCart> userShoppingCartTestList = userShoppingCartService.findAll();
        assertThat(userShoppingCartTestList.size() == userShoppingCartList.size());
    }

    @Test
    void findAllById() {
        List<UserShoppingCart> userShoppingCartList = new ArrayList<>();
        userShoppingCartList.add(userShoppingCart);
        given(this.userShoppingCartRepository.findAllByIdShoppingCart(any())).willReturn(userShoppingCartList);
        List<UserShoppingCart> userShoppingCartTestList = userShoppingCartService.findAllByIdShoppingCart(6L);
        assertThat(userShoppingCartTestList.size() == userShoppingCartList.size());
    }

    @Test
    void getQuantityProductsInUserShoppingCart() {
        given(this.userShoppingCartRepository.getByProductId(any(), any()))
                .willReturn(userShoppingCart);
        int countProduct = userShoppingCartService.getQuantityProductsInUserShoppingCart(1L, 6L);
        assertThat(countProduct).isEqualTo(userShoppingCart.getQuantityProduct());
    }
}