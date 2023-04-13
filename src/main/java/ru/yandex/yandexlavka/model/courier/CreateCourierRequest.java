package ru.yandex.yandexlavka.model.courier;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateCourierRequest {
    ArrayList<CreateCourierDto> couriers;
}

/*"CreateCourierRequest": {
        "required": [
        "couriers"
        ]
        }*/
