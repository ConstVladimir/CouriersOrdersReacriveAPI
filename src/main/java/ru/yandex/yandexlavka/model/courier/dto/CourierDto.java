package ru.yandex.yandexlavka.model.courier.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

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
    List<@NotNull Integer> regions;
    @NotEmpty
    List<@NotNull String> working_hours;
}