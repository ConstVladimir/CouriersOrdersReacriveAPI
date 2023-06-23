package ru.yandex.yandexlavka.controllers.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.courier.CreateCourierRequest;
import ru.yandex.yandexlavka.model.courier.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.time.LocalDate;

public interface CourierControllerInt {

    public Flux<CourierDto> createCouriers( CreateCourierRequest createCourierRequest);

    public Mono<CourierDto> getCourier( long courier_id);

    public Flux<CourierDto> getCouriers (int limit, int offset);

    public Mono<GetCourierMetaInfoResponse> getCourierMetaInfo (long courier_id,LocalDate startDate, LocalDate endDate);
}
