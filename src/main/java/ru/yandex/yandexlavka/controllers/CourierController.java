package ru.yandex.yandexlavka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CourierService courierService;
    @Autowired
    OrdersService ordersService;
    @PostMapping("")
    public ResponseEntity<CreateCouriersResponse> createCouriers
            (@RequestBody CreateCourierRequest createCourierRequest) {
            return ResponseEntity.ok(courierService.createCouriers(createCourierRequest));
    }
    @GetMapping("/{courier_id}")
    public ResponseEntity<CourierDto> getCourier(@PathVariable long courier_id){
            return ResponseEntity.ok(courierService.getCourierById(courier_id));
    }
    @GetMapping("")
    public ResponseEntity<GetCouriersResponse> getCouriers(@RequestParam(required = false, defaultValue = "1") Integer limit, @RequestParam(required = false, defaultValue = "0") Integer offset){
            return ResponseEntity.ok(courierService.getCouriersResponse(limit, offset));
    }
    @GetMapping("meta-info/{courier_id}")
    public ResponseEntity<?> getCourierMetaInfo (@PathVariable long courier_id,
                                                 @RequestParam LocalDate startDate,
                                                 @RequestParam LocalDate endDate){
            CourierDto needCourier = courierService.getCourierById(courier_id);
            List<OrderDB> orderDBList = ordersService.getForCourierMetaInfo(courier_id, startDate, endDate);

            if (orderDBList.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(needCourier);
            }

            GetCourierMetaInfoResponse courierMetaInfoResponse = new GetCourierMetaInfoResponse(needCourier);
            courierMetaInfoResponse.setRatingAndEarnings(orderDBList, startDate, endDate);
            return ResponseEntity.ok()
                    .body(courierMetaInfoResponse);
    }
}
