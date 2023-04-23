package ru.yandex.yandexlavka.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.NotFoundResponse;
import ru.yandex.yandexlavka.model.courier.CourierDto;
import ru.yandex.yandexlavka.model.courier.CreateCourierRequest;
import ru.yandex.yandexlavka.model.courier.CreateCouriersResponse;
import ru.yandex.yandexlavka.model.courier.GetCouriersResponse;
import ru.yandex.yandexlavka.services.CourierService;

@RestController
@RequestMapping(value = "/couriers")
public class CourierController {
    CourierService courierService;
    CourierController(CourierService courierService){ this.courierService=courierService;}
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
}
