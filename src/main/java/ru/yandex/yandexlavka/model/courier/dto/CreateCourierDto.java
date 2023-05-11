package ru.yandex.yandexlavka.model.courier.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateCourierDto {
    @NotNull
    String courier_type;
    @NotEmpty
    ArrayList<@NotNull Integer> regions;
    @NotEmpty
    ArrayList<@NotNull String> working_hours;
}