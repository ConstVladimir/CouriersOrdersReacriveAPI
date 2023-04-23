package ru.yandex.yandexlavka.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.model.order.CompleteOrder;

import java.time.OffsetDateTime;

public interface CompleteOrdersRepositoryInterface extends JpaRepository<CompleteOrder, Long> {
    @Modifying
    @Transactional
    @Query (value = "UPDATE complete_orders SET complete_time= :complete_time WHERE order_id= :order_id ;",  nativeQuery = true)
    void completeOrder (
            @Param("complete_time")OffsetDateTime complete_time,
            @Param("order_id") long order_id
            );
}
