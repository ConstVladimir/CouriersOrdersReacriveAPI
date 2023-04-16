package ru.yandex.yandexlavka.services;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.repositories.CouriersRepositoryInterface;

import java.util.ArrayList;

@Service
public class CourierService {
    CouriersRepositoryInterface couriersRepository;
    CourierService(CouriersRepositoryInterface couriersRepository){this.couriersRepository=couriersRepository;}

    public CreateCouriersResponse createCourier (CreateCourierRequest createCourierRequest){
        ArrayList<CourierDto> courierDtoArrayList = new ArrayList<>();
        for (CreateCourierDto courier : createCourierRequest.getCouriers()){
            CourierDto courierDto = new CourierDto();
            courierDto.setCourier_type(courier.getCourier_type().toString());
            courierDto.setRegions(courier.getRegions());
            courierDto.setWorking_hours(courier.getWorking_hours());
            couriersRepository.save(courierDto);
            courierDtoArrayList.add(courierDto);
        }
        CreateCouriersResponse createCouriersResponse = new CreateCouriersResponse();
        createCouriersResponse.setCouriers(courierDtoArrayList);
        return  createCouriersResponse;
    }
    public GetCouriersResponse getCouriersResponse (int limit, int offset){
        //Pageable pageable = PageRequest.of(offset, limit);;
        //Page<CourierDto> courierDtoPage = couriersRepository.findAll(pageable);

        ArrayList<CourierDto> courierDtoArrayList = new ArrayList<>();
        //courierDtoArrayList.addAll(courierDtoPage.toList());

        courierDtoArrayList.addAll(couriersRepository.findAllOffsetLimit(offset,limit));

        GetCouriersResponse getCouriersResponse = new GetCouriersResponse();
        getCouriersResponse.setCouriers(courierDtoArrayList);
        getCouriersResponse.setOffset(offset);
        getCouriersResponse.setLimit(limit);
        return  getCouriersResponse;
    }
    public CourierDto GetCourierById (long id){
        CourierDto needCourier = couriersRepository.findById(id);
        return  needCourier;
    }
}
