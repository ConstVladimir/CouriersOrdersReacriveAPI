package ru.yandex.yandexlavka.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.model.courier.CourierDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CouriersRepositoryInterface extends JpaRepository<CourierDto, Long> {
    Page<CourierDto> findAll(Pageable pageable);
    CourierDto findById (long courier_id);
    @Query(value = "SELECT * FROM couriers offset :offset rows fetch first :limit rows only;", nativeQuery = true)
    List<CourierDto> findAllOffsetLimit(
            @Param("offset") int offset,
            @Param("limit") int limit);
}
