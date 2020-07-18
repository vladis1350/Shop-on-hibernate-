package com.vladis1350.auth.bean;

import com.vladis1350.bean.Order;
import com.vladis1350.bean.ShoppingCarts;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    @NotEmpty(message = "Пожалуйста, введите имя пользователя")
    @Length(min = 5, max = 25, message = "Имя пользователя должно быть не меньше 5 и не болеее 25 символов.")
    private String userName;

    @Column(name = "first_name")
    @NotEmpty(message = "Пожалуйста, введите Имя")
    @Length(min = 3, max = 55, message = "Имя должно быть не менее 3 и не более 55 символов.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Пожалуйста, введите фамилию")
    @Length(min = 3, max = 55, message = "Фамилия долна быть не менее 3 и не более 55 символов.")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "Пожалуйста, введите адрес электронной почты.")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "Пожалуйста, выберите пароль длиннее 5 символов")
    @NotEmpty(message = "Пожалуйста, введите пароль")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private ShoppingCarts shoppingCarts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;

    public String getAllRoles() {
        return roles.stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.joining(", ", "{", "}"));
    }

}
