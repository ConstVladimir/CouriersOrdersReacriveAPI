package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

public interface MustNotDeliveryRepositoryInterface extends JpaRepository<OrderDB, Long> {
}
