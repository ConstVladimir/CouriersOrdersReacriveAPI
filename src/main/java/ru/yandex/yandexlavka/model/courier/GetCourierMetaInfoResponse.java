package ru.yandex.yandexlavka.model.courier;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.model.courier.dto.CourierDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetCourierMetaInfoResponse {
    Long courier_id;
    String courier_type;
    ArrayList<Integer> regions;
    ArrayList<String> working_hours;
    Integer rating;
    Integer earnings;

    public void setRatingAndEarnings (List<OrderDB> orderDBList, LocalDate startDate, LocalDate endDate){
        earnings = 0;
        rating = 0;
        for (OrderDB orderDB : orderDBList) {
            earnings += orderDB.getCost();
        }
        if (courier_type.equals(CourierType.FOOT.toString())) {
            earnings *= 2;
            rating = (endDate.getDayOfYear() - startDate.getDayOfYear()) * 24 * 3 / orderDBList.size();
        }
        if (courier_type.equals(CourierType.BIKE.toString())) {
            earnings *= 3;
            rating = (endDate.getDayOfYear() - startDate.getDayOfYear()) * 24 * 2 / orderDBList.size();
        }
        if (courier_type.equals(CourierType.AUTO.toString())) {
            earnings *= 4;
            rating = (endDate.getDayOfYear() - startDate.getDayOfYear()) * 24 / orderDBList.size();
        }
    }
    public GetCourierMetaInfoResponse (CourierDto courierDto){
        courier_id = courierDto.getCourier_id();
        courier_type = courierDto.getCourier_type();
        regions = new ArrayList<Integer> (courierDto.getRegions());
        working_hours = new ArrayList<>(courierDto.getWorking_hours());
    }
}

/*"GetCourierMetaInfoResponse": {
        "required": [
        "courier_id",
        "courier_type",
        "regions",
        "working_hours"
        ]
        }*/
