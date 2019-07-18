package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findOrderByIdEqualsAndStatusEqualsTest() {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        Order order = new Order();
        order.setCarNumber("x0923");
        order.setParkingLotName("myParkingLot");
        order.setStatus(false);
        order.setParkingLot(parkingLot);
        order = orderRepository.save(order);
        Order fetchOrder = orderRepository.findOrderByIdEqualsAndStatusEquals(order.getId(), order.isStatus());

        assertThat(fetchOrder).isEqualTo(order);
    }

}
