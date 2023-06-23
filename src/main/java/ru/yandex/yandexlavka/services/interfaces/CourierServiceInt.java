package ru.yandex.yandexlavka.services.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.courier.CreateCourierRequest;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

public interface CourierServiceInt {
    Flux<CourierDto> createCouriers (CreateCourierRequest createCourierRequest);
    Flux<CourierDto> getCouriers(int limit, int offset);
    Mono<CourierDto> getCourierById(long id);
}
