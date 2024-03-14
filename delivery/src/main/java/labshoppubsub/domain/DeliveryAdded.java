package labshoppubsub.domain;

import java.time.LocalDate;
import java.util.*;
import labshoppubsub.domain.*;
import labshoppubsub.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryAdded extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String productId;
    private Integer qty;

    public DeliveryAdded(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryAdded() {
        super();
    }
}
//>>> DDD / Domain Event
