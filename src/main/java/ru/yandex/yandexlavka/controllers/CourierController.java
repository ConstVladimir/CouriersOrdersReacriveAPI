package ru.yandex.yandexlavka.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.NotFoundResponse;
import ru.yandex.yandexlavka.model.courier.*;
import ru.yandex.yandexlavka.model.order.kvv.OrderDB;
import ru.yandex.yandexlavka.services.CourierService;
import ru.yandex.yandexlavka.services.OrdersService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/couriers")
public class CourierController {
    CourierService courierService;
    OrdersService ordersService;
    CourierController(CourierService courierService, OrdersService ordersService){
        this.courierService=courierService;
        this.ordersService=ordersService;
    }
    @PostMapping("")
    public ResponseEntity<CreateCouriersResponse> createCourier(@RequestBody CreateCourierRequest createCourierRequest) {
        CreateCouriersResponse createCouriersResponse = courierService.createCourier(createCourierRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createCouriersResponse);
    }
    @GetMapping("/{courier_id}")
    public ResponseEntity<?> getCouriers(@PathVariable long courier_id){
        CourierDto needCourier = courierService.GetCourierById(courier_id);
        if (needCourier == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundResponse());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(needCourier);
    }
    @GetMapping("")
    public ResponseEntity<GetCouriersResponse> getCouriers(@RequestParam(required = false, defaultValue = "1") Integer limit, @RequestParam(required = false, defaultValue = "0") Integer offset){
        GetCouriersResponse getCouriersResponse = courierService.getCouriersResponse(limit, offset);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getCouriersResponse);
    }
    @GetMapping("meta-info/{courier_id}")
    public ResponseEntity<?> getCourierMetaInfo (@PathVariable long courier_id,
                                                 @RequestParam LocalDate startDate,
                                                 @RequestParam LocalDate endDate){
        CourierDto needCourier = courierService.GetCourierById(courier_id);
        if (needCourier == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundResponse());
        }

        List<OrderDB> orderDBList = ordersService.getForCourierMetaInfo(courier_id, startDate, endDate);

        if (orderDBList.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(needCourier);
        }
        Integer earnings = 0;
        Integer rating = 0;
        for (OrderDB orderDB : orderDBList){
            earnings+=orderDB.getCost();
        }
        if (needCourier.getCourier_type().equals(CourierType.FOOT.toString())) {
            earnings *= 2;
            rating = (endDate.getDayOfYear()-startDate.getDayOfYear())*24*3/orderDBList.size();
        }
        if (needCourier.getCourier_type().equals(CourierType.BIKE.toString())) {
            earnings *= 3;
            rating = (endDate.getDayOfYear()-startDate.getDayOfYear())*24*2/orderDBList.size();
        }
        if (needCourier.getCourier_type().equals(CourierType.AUTO.toString())) {
            earnings *= 4;
            rating = (endDate.getDayOfYear()-startDate.getDayOfYear())*24/orderDBList.size();
        }

        GetCourierMetaInfoResponse courierMetaInfoResponse = new GetCourierMetaInfoResponse();
        courierMetaInfoResponse.setCourier_id(needCourier.getCourier_id());
        courierMetaInfoResponse.setCourier_type(needCourier.getCourier_type());
        courierMetaInfoResponse.setRegions(needCourier.getRegions());
        courierMetaInfoResponse.setWorking_hours(needCourier.getWorking_hours());
        courierMetaInfoResponse.setEarnings(earnings);
        courierMetaInfoResponse.setRating(rating);
        return ResponseEntity.ok()
                .body(courierMetaInfoResponse);
    }
}
