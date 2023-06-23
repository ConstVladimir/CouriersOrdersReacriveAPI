package ru.yandex.yandexlavka.model.order.entity;

import jakarta.annotation.Nullable;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import ru.yandex.yandexlavka.model.HoursInterval;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Table(name = "orders2")
public class OrderDB  {
    @Id
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

    //@JoinColumn(name = "courier_id", insertable = false, updatable = false)
    //@ManyToOne(targetEntity = CourierDto.class, fetch = FetchType.LAZY)
    private CourierDto courierDto;

    @Transient
    List<HoursInterval> delivery_time_intervals;

    void setDeliveryTimeIntervals(){
        delivery_time_intervals = delivery_hours.stream().map(HoursInterval::new).collect(Collectors.toList());
    }
}
