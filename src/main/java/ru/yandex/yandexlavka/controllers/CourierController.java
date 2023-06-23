package ru.yandex.yandexlavka.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.controllers.interfaces.CourierControllerInt;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.services.interfaces.CourierServiceInt;
import ru.yandex.yandexlavka.services.interfaces.OrdersServiceInt;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/couriers")
@ResponseStatus(HttpStatus.OK)
public class CourierController implements CourierControllerInt {
    @Qualifier("courier-Service-1")
    CourierServiceInt courierService;
    @Qualifier("orders-Service-1")
    OrdersServiceInt ordersService;
    @PostMapping("")

    public Flux<CourierDto> createCouriers
            (@RequestBody CreateCourierRequest createCourierRequest) {
            return courierService.createCouriers(createCourierRequest);
    }
    @GetMapping("/{courier_id}")
    public Mono<CourierDto> getCourier(@PathVariable long courier_id){
            return courierService.getCourierById(courier_id);
    }
    @GetMapping("")
    public Flux<CourierDto> getCouriers
            (@RequestParam(required = false, defaultValue = "1") int limit,
             @RequestParam(required = false, defaultValue = "0") int offset){
            return courierService.getCouriers(limit, offset);
    }
    @GetMapping("meta-info/{courier_id}")
    public Mono<GetCourierMetaInfoResponse> getCourierMetaInfo (@PathVariable long courier_id,
                                                 @RequestParam LocalDate startDate,
                                                 @RequestParam LocalDate endDate){
            /*CourierDto needCourier = courierService.getCourierById(courier_id);
            List<OrderDB> orderDBList = ordersService.getForCourierMetaInfo(courier_id, startDate, endDate);

            if (orderDBList.isEmpty()) return ResponseEntity.ok(needCourier);

            GetCourierMetaInfoResponse courierMetaInfoResponse = new GetCourierMetaInfoResponse(needCourier);
            courierMetaInfoResponse.setRatingAndEarnings(orderDBList, startDate, endDate);*/
            return Mono.empty();
    }
}
