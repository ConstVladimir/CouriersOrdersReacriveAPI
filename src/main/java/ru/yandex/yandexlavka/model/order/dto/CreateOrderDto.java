package ru.yandex.yandexlavka.model.order.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOrderDto {
    @NotNull
    Double weight;
    @NotNull
    Integer regions;
    @NotEmpty
    List<@NotNull String> delivery_hours;
    @NotNull
    Integer cost;
}