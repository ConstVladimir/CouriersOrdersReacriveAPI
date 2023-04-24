package ru.yandex.yandexlavka.model.courier;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GetCourierMetaInfoResponse {
    Long courier_id;
    String courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;
    Integer rating;
    Integer earnings;
}

/*"GetCourierMetaInfoResponse": {
        "required": [
        "courier_id",
        "courier_type",
        "regions",
        "working_hours"
        ]
        }*/
