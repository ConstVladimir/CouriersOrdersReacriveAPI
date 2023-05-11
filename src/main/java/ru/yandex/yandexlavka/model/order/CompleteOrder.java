package ru.yandex.yandexlavka.model.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class CompleteOrder {
    @NotNull
    Long order_id;
    @NotNull
    Long courier_id;
    @NotNull
    OffsetDateTime complete_time;
}