package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/parkingLots")
    public ResponseEntity<ParkingLot> add(@RequestBody ParkingLot parkingLot){
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingLotService.save(parkingLot));
    }

}
