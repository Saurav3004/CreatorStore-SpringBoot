package in.mesauravjha.creatorstore.controllers;

import in.mesauravjha.creatorstore.dto.OrderRequest;
import in.mesauravjha.creatorstore.entities.Order;
import in.mesauravjha.creatorstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
}
