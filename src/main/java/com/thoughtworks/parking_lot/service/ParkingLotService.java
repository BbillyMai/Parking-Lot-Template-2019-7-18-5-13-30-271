package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.saveAndFlush(parkingLot);
    }

    public void delete(int parkingLotId) {
        parkingLotRepository.deleteById(parkingLotId);
    }

    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot findById(int parkingLotId) {
        return parkingLotRepository.findById(parkingLotId).orElse(null);
    }

    public List<ParkingLot> findByPage(int page) {
        int start = (page - 1) * 15;
        int end = page * 15 > findAll().size() ? findAll().size() : page * 15;
        return parkingLotRepository.findAll().subList(start, end);
    }

    public ParkingLot updateCapacity(int parkingLotId, int capacity) {
        ParkingLot parkingLot = findById(parkingLotId);
        parkingLot.setCapacity(capacity);
        return parkingLotRepository.saveAndFlush(parkingLot);
    }
}
