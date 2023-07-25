package sage.springcoder.jamhuborderservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sage.springcoder.jamhuborderservice.domain.Customer;
import sage.springcoder.jamhuborderservice.domain.JamOrder;
import sage.springcoder.jamhuborderservice.domain.JamOrderStatusEnum;
import sage.springcoder.jamhuborderservice.repositories.CustomerRepository;
import sage.springcoder.jamhuborderservice.repositories.JamOrderRepository;
import sage.springcoder.jamhuborderservice.web.mappers.JamOrderMapper;
import sage.springcoder.jamhuborderservice.web.model.JamOrderDto;
import sage.springcoder.jamhuborderservice.web.model.JamOrderPagedList;


import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JamOrderServiceImpl implements JamOrderService{
    private final JamOrderRepository jamOrderRepository;
    private final CustomerRepository customerRepository;
    private final JamOrderMapper jamOrderMapper;
    private final ApplicationEventPublisher publisher;

    public JamOrderServiceImpl(JamOrderRepository jamOrderRepository,
                                CustomerRepository customerRepository,
                                JamOrderMapper jamOrderMapper, ApplicationEventPublisher publisher) {
        this.jamOrderRepository = jamOrderRepository;
        this.customerRepository = customerRepository;
        this.jamOrderMapper = jamOrderMapper;
        this.publisher = publisher;
    }
    @Override
    public JamOrderPagedList listOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Page<JamOrder> jamOrderPage =
                    jamOrderRepository.findAllByCustomer(customerOptional.get(), pageable);

            return new JamOrderPagedList(jamOrderPage
                    .stream()
                    .map(jamOrderMapper::jamOrderToDto)
                    .collect(Collectors.toList()), PageRequest.of(
                    jamOrderPage.getPageable().getPageNumber(),
                    jamOrderPage.getPageable().getPageSize()),
                    jamOrderPage.getTotalElements());
        } else {
            return null;
        }
    }
    @Transactional
    @Override
    public JamOrderDto placeOrder(UUID customerId, JamOrderDto jamOrderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            JamOrder jamOrder = jamOrderMapper.dtoToJamOrder(jamOrderDto);
            jamOrder.setId(null); //should not be set by outside client
            jamOrder.setCustomer(customerOptional.get());
            jamOrder.setOrderStatus(JamOrderStatusEnum.NEW);

            jamOrder.getJamOrderLines().forEach(line -> line.setJamOrder(jamOrder));

            JamOrder savedJamOrder = jamOrderRepository.saveAndFlush(jamOrder);

            log.debug("Saved Jam Order: " + jamOrder.getId());

            //todo impl
            //  publisher.publishEvent(new NewJamOrderEvent(savedJamOrder));

            return jamOrderMapper.jamOrderToDto(savedJamOrder);
        }
        //todo add exception type
        throw new RuntimeException("Customer Not Found");
    }

    @Override
    public JamOrderDto getOrderById(UUID customerId, UUID orderId) {
        return jamOrderMapper.jamOrderToDto(getOrder(customerId, orderId));
    }

    @Override
    public void pickupOrder(UUID customerId, UUID orderId) {
        JamOrder jamOrder = getOrder(customerId, orderId);
        jamOrder.setOrderStatus(JamOrderStatusEnum.PICKED_UP);

        jamOrderRepository.save(jamOrder);
    }

    private JamOrder getOrder(UUID customerId, UUID orderId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Optional<JamOrder> jamOrderOptional = jamOrderRepository.findById(orderId);

            if(jamOrderOptional.isPresent()){
                JamOrder jamOrder = jamOrderOptional.get();

                // fall to exception if customer id's do not match - order not for customer
                if(jamOrder.getCustomer().getId().equals(customerId)){
                    return jamOrder;
                }
            }
            throw new RuntimeException("Jam Order Not Found");
        }
        throw new RuntimeException("Jam Not Found");
    }
}
