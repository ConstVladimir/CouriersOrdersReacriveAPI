package ru.yandex.yandexlavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.repositories.CouriersRepositoryInterface;
import ru.yandex.yandexlavka.services.interfaces.CourierServiceInt;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("courier-Service-1")
public class CourierService implements CourierServiceInt {
    @Autowired
    CouriersRepositoryInterface couriersRepository;
    @Autowired
    MappingUtils mappingUtils;
    public Flux<CourierDto> createCouriers (CreateCourierRequest createCourierRequest){
        List <CourierDto> courierDtoList = new ArrayList<>();
        createCourierRequest.getCouriers()
                .forEach(t->courierDtoList.add(mappingUtils.mappingToCourierDto(t)));

        return  couriersRepository.saveAll(courierDtoList);
    }
    public Flux<CourierDto> getCouriers(int limit, int offset){
        return couriersRepository.findAllOffsetLimit(offset, limit);
    }
    public Mono<CourierDto> getCourierById(long id){
        return couriersRepository.findById(id);
    }
}
