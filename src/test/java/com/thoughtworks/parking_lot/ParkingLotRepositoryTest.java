package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;


    @Test
    public void should_saving() {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);

        assertThat(parkingLot1).isEqualTo(parkingLot);
    }

    @Test
    public void should_delete() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);
        parkingLotRepository.deleteById(parkingLot1.getId());

        assertNull(parkingLotRepository.findById(parkingLot1.getId()).orElse(null));
    }

    @Test
    public void should_find_By_id() {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);
        ParkingLot parkingLot2 = parkingLotRepository.findById(parkingLot1.getId()).orElse(null);

        assertThat(parkingLot2).isEqualTo(parkingLot1);
    }


    @Test
    public void should_update_capacity() {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");


        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);
        parkingLot.setCapacity(100);
        parkingLot1 = parkingLotRepository.saveAndFlush(parkingLot1);

        assertThat(parkingLot1.getCapacity()).isEqualTo(100);
    }
}
