package labshoppubsub.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshoppubsub.DeliveryApplication;
import labshoppubsub.domain.DeliveryAdded;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String productId;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        DeliveryAdded deliveryAdded = new DeliveryAdded(this);
        deliveryAdded.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void addDelivery(OrderPlaced orderPlaced) {
        
        Delivery delivery = new Delivery();

        delivery.setOrderId(orderPlaced.getId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setQty(orderPlaced.getQty());
        repository().save(delivery);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
