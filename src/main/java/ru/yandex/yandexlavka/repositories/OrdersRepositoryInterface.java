package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.model.order.OrderDto;

import java.util.List;

public interface OrdersRepositoryInterface extends JpaRepository<OrderDto, Long> {
    OrderDto findById (long order_id);
    @Query(value = "SELECT * FROM orders offset :offset rows fetch first :limit rows only;", nativeQuery = true)
    List<OrderDto> findAllOffsetLimit(
            @Param("offset") int offset,
            @Param("limit") int limit);
}
