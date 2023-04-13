package ru.yandex.yandexlavka.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.yandexlavka.model.courier.CourierDto;

public interface CouriersRepositoryInterface extends CrudRepository<CourierDto, Long> {
}
