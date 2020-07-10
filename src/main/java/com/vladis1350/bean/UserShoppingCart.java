package com.vladis1350.bean;

import com.vladis1350.bean.embedded.UserShoppingCartId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_shopping_cart")
@AssociationOverride(name = "pk.product",
                    joinColumns = @JoinColumn(name = "product_id"))
@AssociationOverride(name = "pk.shoppingCarts",
                    joinColumns = @JoinColumn(name = "id_shoppingCart"))
public class UserShoppingCart  implements Serializable {

    @EmbeddedId
    private UserShoppingCartId pk;

    @Column(name = "quantity_product")
    private Integer quantityProduct;

    @Column(name = "summ_order")
    private BigDecimal summOrder;

}
