package com.thoughtworks.parking_lot.model;

import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Order add(@RequestBody Order order) {

        return orderService.openOrder(order);

    }

    @DeleteMapping("/orders/{orderId}")
    public Order delete(@PathVariable int orderId) {
        return orderService.closeOrder(orderId);
    }
}
