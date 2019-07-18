package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
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
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    public void should_saving(){

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("myParkingLot");
        parkingLot.setCapacity(50);
        parkingLot.setAddress("ZHA");

        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);

        assertThat(parkingLot1).isEqualTo(parkingLot);
    }
}
