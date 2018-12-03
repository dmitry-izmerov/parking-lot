package ru.demi.parkinglot.service;

import java.util.Date;

public interface ParkingSlotService {
    int occupyNextFreeParkingSlot();
    Date freeParkingSlot(int slotNumber);
}
