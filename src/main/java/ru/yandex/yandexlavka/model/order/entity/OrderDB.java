package ru.yandex.yandexlavka.model.order.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import ru.yandex.yandexlavka.assign.HoursInterval;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "orders2")
public class OrderDB  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;
    Double weight;
    Integer regions;
    List<String> delivery_hours;
    @Nullable
    OffsetDateTime completed_time;
    Integer cost;
    @Nullable
    OffsetDateTime complete_time;
    @Nullable
    Long courier_id;

    @JoinColumn(name = "courier_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = CourierDto.class, fetch = FetchType.LAZY)
    private CourierDto courierDto;

    @Transient
    List<HoursInterval> delivery_time_intervals;

    void setDeliveryTimeIntervals(){
        delivery_time_intervals = delivery_hours.stream().map(HoursInterval::new).collect(Collectors.toList());
    }
}
