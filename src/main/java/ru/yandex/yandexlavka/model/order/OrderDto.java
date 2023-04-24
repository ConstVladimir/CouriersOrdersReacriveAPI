package ru.yandex.yandexlavka.model.order;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Data
//@Entity
//@Table(name = "orders")
// @SecondaryTable(name = "completeOrders",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "order_id"))
public class OrderDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;
    Double weight;
    Integer regions;
    ArrayList<String> delivery_hours;
    Integer cost;
//    @Column(name="complete_time", table="completeOrders")
    OffsetDateTime completed_time;
}

/*
"OrderDto": {
        "required": [
        "cost",
        "delivery_hours",
        "order_id",
        "regions",
        "weight"
        ]
        }*/
