package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.model.OrderController;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;


    @Test
    public void should_open_order() throws Exception {

        Order order = new Order();
        order.setId(1);
        order.setParkingLotName("myParkingLot");
        order.setCarNumber("x9031");

        when(orderService.openOrder(any(Order.class))).thenReturn(order);

        ResultActions resultActions = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"parkingLotName\":\"myParkingLot\",\n" +
                        "\t\"carNumber\":\"x9031\"\n" +
                        "}"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.parkingLotName", is("myParkingLot")))
                .andExpect(jsonPath("$.carNumber", is("x9031")));
    }


    @Test
    public void shoule_closeOrder() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setParkingLotName("myParkingLot");
        order.setCarNumber("x9031");

        when(orderService.closeOrder(anyInt())).thenReturn(order);

        ResultActions resultActions = mockMvc.perform(delete("/orders/{orderId}", anyInt()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.parkingLotName", is("myParkingLot")))
                .andExpect(jsonPath("$.carNumber", is("x9031")))
                .andExpect(jsonPath("$.status", is(false)));
    }
}
