package ru.yandex.yandexlavka.model.courier.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.checkerframework.checker.regex.qual.Regex;
import ru.yandex.yandexlavka.model.HoursInterval;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "couriers")
public class CourierDto{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long courier_id;
    @NotNull
    String courier_type;
    @NotEmpty
    ArrayList<@NotNull Integer> regions;
    @NotEmpty
    ArrayList<@NotNull String> working_hours;
}