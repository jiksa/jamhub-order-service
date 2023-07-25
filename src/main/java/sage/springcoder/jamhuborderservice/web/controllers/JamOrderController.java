package sage.springcoder.jamhuborderservice.web.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sage.springcoder.jamhuborderservice.services.JamOrderService;
import sage.springcoder.jamhuborderservice.web.model.JamOrderDto;
import sage.springcoder.jamhuborderservice.web.model.JamOrderPagedList;

import java.util.UUID;

@RequestMapping("(api/v1/customers/{customerId}/")
@RestController
public class JamOrderController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final JamOrderService jamOrderService;

    public JamOrderController(JamOrderService jamOrderService) {
        this.jamOrderService = jamOrderService;
    }

    @GetMapping("orders")
    public JamOrderPagedList listOrders(@PathVariable("customerId") UUID customerId,
                                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize){

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return jamOrderService.listOrders(customerId, PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public JamOrderDto placeOrder(@PathVariable("customerId") UUID customerId, @RequestBody JamOrderDto jamOrderDto){
        return jamOrderService.placeOrder(customerId, jamOrderDto);
    }

    @GetMapping("orders/{orderId}")
    public JamOrderDto getOrder(@PathVariable("customerId") UUID customerId, @PathVariable("orderId") UUID orderId){
        return jamOrderService.getOrderById(customerId, orderId);
    }

    @PutMapping("/orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickupOrder(@PathVariable("customerId") UUID customerId, @PathVariable("orderId") UUID orderId){
        jamOrderService.pickupOrder(customerId, orderId);
    }
}
