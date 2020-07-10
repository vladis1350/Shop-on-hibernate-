package com.vladis1350.services;

import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.repositories.UserShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserShoppingCartService {

    @Autowired
    private UserShoppingCartRepository cartRepository;

    public UserShoppingCart save(UserShoppingCart userShoppingCart) {
        userShoppingCart = this.cartRepository.save(userShoppingCart);
        return userShoppingCart;

    }

    public UserShoppingCart update(UserShoppingCart userShoppingCart) {
        userShoppingCart = this.cartRepository.save(userShoppingCart);
        return userShoppingCart;

    }

    public UserShoppingCart getById(Long id) {
        Optional<UserShoppingCart> userShoppingCart = cartRepository.findById(id);
        return userShoppingCart.orElse(null);
    }

    public UserShoppingCart getByProductId(Long idProduct, Long idCart) {
        return cartRepository.getByProductId(idProduct, idCart);
    }

    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    public void remove(Long idCart, Long idProduct) {
        cartRepository.deleteProductFromCart(idCart, idProduct);
    }


    public List<UserShoppingCart> findAll() {
        return cartRepository.findAll();
    }

    public List<UserShoppingCart> findAllById(Long id) {
        return cartRepository.findAllById(id);
    }

    public int getQuantityProductsInUserShoppingCart(Long idProduct, Long idCart) {
        return getByProductId(idProduct, idCart).getQuantityProduct();
    }
}
