package ru.yandex.yandexlavka.model.order;

import jakarta.persistence.*;
import lombok.Data;
import ru.yandex.yandexlavka.model.courier.CourierDto;

import java.time.OffsetDateTime;
@Data
//@Entity
//@Table(name =  "completeOrders")
public class CompleteOrder {
//    @Id
    Long order_id;
    Long courier_id;
    OffsetDateTime complete_time;

//    @JoinColumn(name = "courier_id", insertable = false, updatable = false)
//    @ManyToOne(targetEntity = CourierDto.class, fetch = FetchType.LAZY)
//    private CourierDto courierDto;

//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
//    @OneToOne(targetEntity = OrderDto.class, fetch = FetchType.EAGER)
//    private OrderDto orderDto;
}

/*"CompleteOrder": {
        "required": [
        "complete_time",
        "courier_id",
        "order_id"
        ]
        }*/
