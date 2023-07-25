package sage.springcoder.jamhuborderservice.services;

import org.springframework.data.domain.Pageable;
import sage.springcoder.jamhuborderservice.web.model.JamOrderDto;
import sage.springcoder.jamhuborderservice.web.model.JamOrderPagedList;

import java.util.UUID;

public interface JamOrderService {

    JamOrderPagedList listOrders(UUID customerId, Pageable pageable);

    JamOrderDto placeOrder(UUID customerId, JamOrderDto jamOrderDto);

    JamOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickupOrder(UUID customerId, UUID orderId);
}
