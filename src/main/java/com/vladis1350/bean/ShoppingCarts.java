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
@Data
@Table(name = "shopping_carts")
public class ShoppingCarts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shoppingCart")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "active")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCarts", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.shoppingCarts", cascade=CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UserShoppingCart> userShoppingCart;
}
