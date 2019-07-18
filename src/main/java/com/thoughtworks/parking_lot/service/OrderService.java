package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.repository.OrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderReposity;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public Order openOrder(Order order) {
        order.setStartTime(new Date());
        order.setStatus(true);
        return orderReposity.save(order);
    }

    public Order closeOrder(int orderId) {
        Order order = orderReposity.findOrderByIdEqualsAndStatusEquals(orderId, true);
        order.setEndTime(new Date());
        order.setStatus(false);
        return orderReposity.save(order);
    }

}
