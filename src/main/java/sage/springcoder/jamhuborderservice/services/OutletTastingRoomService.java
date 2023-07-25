package sage.springcoder.jamhuborderservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sage.springcoder.jamhuborderservice.bootstrap.JamOrderBootstrap;
import sage.springcoder.jamhuborderservice.repositories.CustomerRepository;
import sage.springcoder.jamhuborderservice.repositories.JamOrderRepository;

import java.util.ArrayList;
import java.util.List;

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
}
