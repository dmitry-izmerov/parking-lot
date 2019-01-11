package ru.demi.parkinglot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demi.parkinglot.entity.ParkingSlot;
import ru.demi.parkinglot.exception.AlreadyFreeParkingException;
import ru.demi.parkinglot.exception.NoFreeParkingSlotsException;
import ru.demi.parkinglot.repository.ParkingSlotRepository;

import java.util.Date;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ParkingSlotServiceImpl implements ParkingSlotService {
    private final ParkingSlotRepository parkingSlotRepository;

    @Override
    public int occupyNextFreeParkingSlot() {
        Optional<ParkingSlot> optional = parkingSlotRepository.findFirstByIsAvailableTrueOrderByNumberAsc();
        ParkingSlot slot = optional.orElseThrow(NoFreeParkingSlotsException::new);
        int slotNumber = slot.getNumber();
        parkingSlotRepository.save(slot.setAvailable(false));
        return slotNumber;
    }

    @Override
    public Date freeParkingSlot(int slotNumber) {
        ParkingSlot parkingSlot = parkingSlotRepository.findByNumber(slotNumber);
        if (parkingSlot.isAvailable()) {
            throw new AlreadyFreeParkingException(slotNumber);
        }
        parkingSlotRepository.save(parkingSlot.setAvailable(true));
        return new Date();
    }
}
