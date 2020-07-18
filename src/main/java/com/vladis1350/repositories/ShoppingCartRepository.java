package com.vladis1350.repositories;

import com.vladis1350.auth.bean.User;
import com.vladis1350.bean.ShoppingCarts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCarts, Long> {
    ShoppingCarts findShoppingCartById(Long id);

    ShoppingCarts findShoppingCartByUserAndIsActiveTrue(User user);
    ShoppingCarts findShoppingCartByUser(User user);

}
