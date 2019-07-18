package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot save(ParkingLot parkingLot){
        return parkingLotRepository.saveAndFlush(parkingLot);
    }

    public void delete(int parkingLotId){
        parkingLotRepository.deleteById(parkingLotId);
    }

    public List<ParkingLot> findAll(){
        return parkingLotRepository.findAll();
    }

    public ParkingLot findById(int parkingLotId){
        return parkingLotRepository.findById(parkingLotId).orElse(null);
    }

}
