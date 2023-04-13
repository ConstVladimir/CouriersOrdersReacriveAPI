package ru.yandex.yandexlavka.services;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.courier.CourierDto;
import ru.yandex.yandexlavka.model.courier.CreateCourierDto;
import ru.yandex.yandexlavka.model.courier.CreateCourierRequest;
import ru.yandex.yandexlavka.repositories.CouriersRepository;
import ru.yandex.yandexlavka.repositories.CouriersRepositoryInterface;

@Service
public class CourierService {
    CouriersRepositoryInterface couriersRepository;
    CourierService(CouriersRepositoryInterface couriersRepository){this.couriersRepository=couriersRepository;}

    public void createCourier (CreateCourierRequest createCourierRequest){
        for (CreateCourierDto courier : createCourierRequest.getCouriers()){
            CourierDto courierDto = new CourierDto();
            courierDto.setCourier_type(courier.getCourier_type().toString());
            courierDto.setRegions(courier.getRegions());
            courierDto.setWorking_hours(courier.getWorking_hours());
            couriersRepository.save(courierDto);
        }
    }
}
