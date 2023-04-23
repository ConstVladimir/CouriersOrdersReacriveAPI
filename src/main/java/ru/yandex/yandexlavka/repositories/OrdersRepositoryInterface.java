package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.model.order.OrderDto;

import java.time.OffsetDateTime;
import java.util.List;

public interface OrdersRepositoryInterface extends JpaRepository<OrderDto, Long> {
    OrderDto findById (long order_id);
    @Query(value = "SELECT o.order_id, o.cost, o.delivery_hours, o.regions, o.weight, co.completed_time FROM orders o left join complete_orders co ON o.order_id=co.order_id  offset :offset rows fetch first :limit rows only;", nativeQuery = true)
    List<OrderDto> findAllOffsetLimit(
            @Param("offset") int offset,
            @Param("limit") int limit);

    //List<OrderDto> updateCompletedTimeById (long order_id, OffsetDateTime completed_time);
}
