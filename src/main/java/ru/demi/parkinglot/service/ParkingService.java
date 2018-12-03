package ru.demi.parkinglot.service;

import ru.demi.parkinglot.dto.TicketDto;

public interface ParkingService {
    TicketDto occupyParkingSlotAndGetTicket();
    void freeParkingSlot(long ticketId);
}
