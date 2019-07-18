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
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(jsonPath("$.name",is("myParkingLot")))
                .andExpect(jsonPath("$.capacity",is(50)))
                .andExpect(jsonPath("$.address",is("ZHA")));
    }

}
