package ru.yandex.yandexlavka.services;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.repositories.CouriersRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourierService {
    CouriersRepositoryInterface couriersRepository;
    CourierService(CouriersRepositoryInterface couriersRepository){this.couriersRepository=couriersRepository;}
    public CreateCouriersResponse createCouriers (CreateCourierRequest createCourierRequest){
        List<CourierDto> courierDtoList = createCourierRequest.getCouriers().stream().map(CourierDto::getCourierDto).toList();
        couriersRepository.saveAll(courierDtoList);

        CreateCouriersResponse createCouriersResponse = new CreateCouriersResponse();
        createCouriersResponse.setCouriers(courierDtoList);
        return  createCouriersResponse;
    }
    public GetCouriersResponse getCouriersResponse (int limit, int offset){

        ArrayList<CourierDto> courierDtoArrayList = new ArrayList<>(couriersRepository.findAllOffsetLimit(offset, limit));

        GetCouriersResponse getCouriersResponse = new GetCouriersResponse();
        getCouriersResponse.setCouriers(courierDtoArrayList);
        getCouriersResponse.setOffset(offset);
        getCouriersResponse.setLimit(limit);
        return  getCouriersResponse;
    }
    public CourierDto getCourierById(long id){
        return couriersRepository.findById(id).get();
    }
}
