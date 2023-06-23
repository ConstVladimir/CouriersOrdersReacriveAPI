package ru.yandex.yandexlavka.model.courier.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class CourierDto{
    @Id
    Long courier_id;
    String courier_type;
    List<Integer> regions;
    List<String> working_hours;
}