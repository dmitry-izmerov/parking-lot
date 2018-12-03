package ru.demi.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demi.parkinglot.entity.ParkingSlot;

import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    Optional<ParkingSlot> findFirstByIsAvailableTrueOrderByNumberAsc();

    ParkingSlot findByNumber(int slotNumber);
}
