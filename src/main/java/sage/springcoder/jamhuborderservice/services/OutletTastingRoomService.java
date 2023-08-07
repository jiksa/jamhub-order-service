package sage.springcoder.jamhuborderservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sage.springcoder.jamhuborderservice.bootstrap.JamOrderBootstrap;
import sage.springcoder.jamhuborderservice.domain.Customer;
import sage.springcoder.jamhuborderservice.repositories.CustomerRepository;
import sage.springcoder.jamhuborderservice.repositories.JamOrderRepository;
import sage.springcoder.jamhuborderservice.web.model.JamOrderDto;
import sage.springcoder.jamhuborderservice.web.model.JamOrderLineDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class OutletTastingRoomService {
    private final CustomerRepository customerRepository;
    private final JamOrderService jamOrderService;
    private final JamOrderRepository jamOrderRepository;
    private final List<String> jamUpcs = new ArrayList<>(3);

    public OutletTastingRoomService(CustomerRepository customerRepository, JamOrderService jamOrderService,
                              JamOrderRepository jamOrderRepository) {
        this.customerRepository = customerRepository;
        this.jamOrderService = jamOrderService;
        this.jamOrderRepository = jamOrderRepository;

        jamUpcs.add(JamOrderBootstrap.JAM_1_UPC);
        jamUpcs.add(JamOrderBootstrap.JAM_2_UPC);
        jamUpcs.add(JamOrderBootstrap.JAM_3_UPC);
    }

    @Transactional
    @Scheduled(fixedRate = 5000) //run every 2 seconds
    public void placeTastingRoomOrder(){

        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(JamOrderBootstrap.TASTING_ROOM);

        if (customerList.size() == 1){ //should be just one
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
        }
    }

    private void doPlaceOrder(Customer customer) {
        String jamToOrder = getRandomJamUpc();

        JamOrderLineDto jamOrderLine = JamOrderLineDto.builder()
                .upc(jamToOrder)
                .orderQuantity(new Random().nextInt(6)) //todo externalize value to property
                .build();

        List<JamOrderLineDto> jamOrderLineSet = new ArrayList<>();
        jamOrderLineSet.add(jamOrderLine);

        JamOrderDto jamOrder = JamOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .jamOrderLines(jamOrderLineSet)
                .build();

        JamOrderDto savedOrder = jamOrderService.placeOrder(customer.getId(), jamOrder);

    }

    private String getRandomJamUpc() {
        return jamUpcs.get(new Random().nextInt(jamUpcs.size() -0));
    }
}
