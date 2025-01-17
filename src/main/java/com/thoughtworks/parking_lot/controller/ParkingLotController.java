package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/parkingLots")
    public ResponseEntity<ParkingLot> add(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingLotService.save(parkingLot));
    }

    @DeleteMapping("/parkingLots/{parkingLotId}")
    public ResponseEntity<ParkingLot> delete(@PathVariable int parkingLotId) {
        parkingLotService.delete(parkingLotId);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping(path = "/parkingLots")
    public List<ParkingLot> findByPage(int page) {
        return parkingLotService.findByPage(page);
    }

    @GetMapping("/parkingLots/{parkingLotId}")
    public ParkingLot findById(@PathVariable int parkingLotId) {
        return parkingLotService.findById(parkingLotId);
    }

    @PutMapping(value = "/parkingLots/{parkingLotId}", params = {"capacity"})
    public ParkingLot update(@PathVariable int parkingLotId, int capacity) {
        return parkingLotService.updateCapacity(parkingLotId, capacity);
    }
}
