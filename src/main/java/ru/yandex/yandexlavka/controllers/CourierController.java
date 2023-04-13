package ru.yandex.yandexlavka.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.courier.CreateCourierRequest;
import ru.yandex.yandexlavka.services.CourierService;

@RestController
public class CourierController {
    CourierService courierService;

    CourierController(CourierService courierService){ this.courierService=courierService;}

    @PostMapping("/couriers")
    public ResponseEntity<?> createCourier(@RequestBody CreateCourierRequest createCourierRequest) {
        courierService.createCourier(createCourierRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createCourierRequest);
    }
    @GetMapping("/couriers/{courier_id}")
    public ResponseEntity<?> getCouriers(@PathVariable long courier_id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Courier #"+ courier_id);
    }
    @GetMapping("/couriers")
    public ResponseEntity<?> getCouriers(@RequestParam(required = false, defaultValue = "1") Integer limit, @RequestParam(required = false, defaultValue = "0") Integer offset){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("List of couriers limit "+ limit + " offset "+ offset);
    }
}
