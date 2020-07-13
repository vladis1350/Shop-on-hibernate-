package com.vladis1350.bean.embedded;

import com.vladis1350.bean.Product;
import com.vladis1350.bean.ShoppingCarts;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserShoppingCartId implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private ShoppingCarts shoppingCarts;


}
