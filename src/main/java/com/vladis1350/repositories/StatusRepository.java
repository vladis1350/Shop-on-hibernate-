package com.vladis1350.repositories;

import com.vladis1350.bean.OrderStatuses;
import com.vladis1350.bean.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusOrder, Long> {
    StatusOrder findByStatusName(OrderStatuses orderStatuses);
}
