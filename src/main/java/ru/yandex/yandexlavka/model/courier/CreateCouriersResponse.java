package ru.yandex.yandexlavka.model.courier;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;

import java.util.List;

@Data
public class CreateCouriersResponse {
    @NotEmpty(message = "Mustn't be empty")
    List<@NotNull CourierDto> couriers;
}