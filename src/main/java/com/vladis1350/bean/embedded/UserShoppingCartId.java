package com.vladis1350.bean.embedded;

import com.vladis1350.bean.Product;
import com.vladis1350.bean.ShoppingCarts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
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
