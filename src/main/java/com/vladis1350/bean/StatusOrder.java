package com.vladis1350.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "statuses")
public class StatusOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @Column(name = "status_name")
    @NotEmpty(message = "Пожалуйста, введите название статуса заказа.")
    private String statusName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "statusOrder", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;
}
