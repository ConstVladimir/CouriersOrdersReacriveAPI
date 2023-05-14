package ru.yandex.yandexlavka.model.courier.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCourierDto {
    @NotNull
    String courier_type;
    @NotEmpty
    List<@NotNull Integer> regions;
    @NotEmpty
    List<@NotNull String> working_hours;
}