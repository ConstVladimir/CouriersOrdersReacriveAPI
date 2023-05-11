package ru.yandex.yandexlavka.model.courier;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.yandexlavka.model.courier.dto.CreateCourierDto;

import java.util.ArrayList;

@Data
public class CreateCourierRequest {
    @NotEmpty
    ArrayList<@NotNull CreateCourierDto> couriers;
}