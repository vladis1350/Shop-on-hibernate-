package com.vladis1350.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "statuses")
public class StatusOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @Column(name = "status_name", unique = true)
    @Enumerated(value = EnumType.STRING)
    private OrderStatuses statusName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "statusOrder", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;

}



