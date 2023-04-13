package ru.yandex.yandexlavka.model.courier;

import java.util.ArrayList;

public class GetCourierMetaInfoResponse {
    long courier_id;
    CourierType courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;
    int rating;
    int earnings;
}

/*"GetCourierMetaInfoResponse": {
        "required": [
        "courier_id",
        "courier_type",
        "regions",
        "working_hours"
        ]
        }*/
