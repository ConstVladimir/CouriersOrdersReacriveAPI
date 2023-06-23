package ru.yandex.yandexlavka.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

public interface CouriersRepositoryInterface extends ReactiveCrudRepository<CourierDto, Long> {
    @Query(value = "SELECT * FROM couriers offset :offset rows fetch first :limit rows only;")
    Flux<CourierDto> findAllOffsetLimit( @Param("offset") int offset,
            @Param("limit") int limit );
}