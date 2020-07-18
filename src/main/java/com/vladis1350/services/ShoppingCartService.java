package com.vladis1350.services;

import com.vladis1350.auth.bean.User;
import com.vladis1350.bean.ShoppingCarts;
import com.vladis1350.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCarts saveShoppingCart(ShoppingCarts shoppingCarts) {
        shoppingCarts = shoppingCartRepository.save(shoppingCarts);
        return shoppingCarts;
    }

    public ShoppingCarts findShoppingCartByUser(User user) {
        return shoppingCartRepository.findShoppingCartByUser(user);
    }

    public boolean findShoppingCartByIdUserBool(User user) {
        return shoppingCartRepository.findShoppingCartByUser(user) == null;
    }

    public ShoppingCarts findShoppingCartByUserAndIsActiveTrue(User user) {
        return shoppingCartRepository.findShoppingCartByUserAndIsActiveTrue(user);
    }
}
