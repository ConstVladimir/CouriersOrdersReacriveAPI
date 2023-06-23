package ru.yandex.yandexlavka.repositories;


import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface OrdersRepositoryInterface2 extends ReactiveCrudRepository<OrderDB, Long> {

    @Query(value = "SELECT " +
            "o.order_id, o.cost, o.delivery_hours, o.regions, o.weight, o.completed_time, o.complete_time, o.courier_id " +
            "FROM orders2 o offset :offset rows fetch first :limit rows only;")
    Flux<OrderDB> findAllOffsetLimit(
            @Param("offset") int offset,
            @Param("limit") int limit);

    @Modifying
    @Transactional
    @Query (value = "UPDATE orders2 " +
            "SET completed_time= :completed_time " +
            "WHERE order_id= :order_id ;")
    void completedOrder (@Param("completed_time") OffsetDateTime completed_time,
            @Param("order_id") long order_id);

    @Query(value = "SELECT " +
            "o.order_id, o.cost, o.delivery_hours, o.regions, o.weight, o.completed_time, o.complete_time, o.courier_id " +
            "FROM orders2 o " +
            "WHERE courier_id = :courier_id " +
            "AND completed_time>= :startDate AND completed_time< :endDate ;")
    Flux<OrderDB> getCourierOrdersBetweenDate (@Param("courier_id") long courier_id,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);
    @Query (value = "SELECT " +
            "o.order_id, o.cost, o.delivery_hours, o.regions, o.weight, o.completed_time, o.complete_time, o.courier_id " +
            "FROM orders2 o " +
            "WHERE complete_time IS NULL;")
    Flux<OrderDB> getOrdersWhereCompleteTimeIsNULL ();
}