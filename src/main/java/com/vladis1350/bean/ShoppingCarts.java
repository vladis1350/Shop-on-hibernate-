package com.vladis1350.bean;

import com.vladis1350.auth.bean.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shopping_carts")
public class ShoppingCarts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shoppingCart")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", unique = true)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.shoppingCarts", cascade=CascadeType.ALL)
    private Set<UserShoppingCart> userShoppingCart;
}
