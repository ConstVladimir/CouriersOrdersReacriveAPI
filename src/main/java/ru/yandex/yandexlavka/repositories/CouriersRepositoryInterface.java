package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.util.List;

public interface CouriersRepositoryInterface extends JpaRepository<CourierDto, Long> {
    @Query(value = "SELECT * FROM couriers offset :offset rows fetch first :limit rows only;", nativeQuery = true)
    List<CourierDto> findAllOffsetLimit(
            @Param("offset") int offset,
            @Param("limit") int limit);
}
