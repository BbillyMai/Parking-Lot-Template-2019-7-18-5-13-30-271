package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    public void should_saving() throws Exception {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        when(parkingLotService.save(any(ParkingLot.class))).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(post("/parkingLots")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"name\": \"myParkingLot\",\n" +
                        "\t\"capacity\":50,\n" +
                        "\t\"address\":\"ZHA\"\n" +
                        "\n" +
                        "}\n"));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("myParkingLot")))
                .andExpect(jsonPath("$.capacity", is(50)))
                .andExpect(jsonPath("$.address", is("ZHA")));
    }

    @Test
    public void should_delete_when_invoke() throws Exception {

        doNothing().when(parkingLotService).delete(anyInt());
        ResultActions resultActions = mockMvc.perform(delete("/parkingLots/{parkingLotId}", 1).accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isAccepted());
        verify(parkingLotService).delete(anyInt());
    }

    @Test
    public void should_find_by_paging() throws Exception {

        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLots.add(parkingLot);

        mockMvc.perform(get("/parkingLots")
                .param("page", "1"))
                .andExpect(status().isOk());

        assertThat(parkingLots.size()).isEqualTo(1);
    }

    @Test
    public void should_find_by_id() throws Exception {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        when(parkingLotService.findById(anyInt())).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(get("/parkingLots/{parkingLotId}", anyInt()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("myParkingLot")))
                .andExpect(jsonPath("$.capacity", is(50)))
                .andExpect(jsonPath("$.address", is("ZHA")));
    }

    @Test
    public void should_update_capacity() throws Exception {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        when(parkingLotService.updateCapacity(anyInt(), anyInt())).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(put("/parkingLots/{parkingLotsId}", 1)
                .param("capacity", "1"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("myParkingLot")))
                .andExpect(jsonPath("$.capacity", is(50)))
                .andExpect(jsonPath("$.address", is("ZHA")));
    }
}
