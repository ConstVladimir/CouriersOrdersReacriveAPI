package ru.yandex.yandexlavka.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

public interface MustNotDeliveryRepositoryInterface extends ReactiveCrudRepository<OrderDB, Long> {
}
