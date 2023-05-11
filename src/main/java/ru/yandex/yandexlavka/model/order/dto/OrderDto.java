package ru.yandex.yandexlavka.model.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderDto {
    @NotNull
    Long order_id;
    @NotNull
    Double weight;
    @NotNull
    Integer regions;
    @NotEmpty
    List<@NotNull String> delivery_hours;
    @NotNull
    Integer cost;
    OffsetDateTime completed_time;
}