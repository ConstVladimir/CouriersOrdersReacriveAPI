package ru.yandex.yandexlavka.model.order;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "orders")
public class OrderDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long order_id;
    double weight;
    int regions;
    ArrayList<String> delivery_hours;
    int cost;
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
