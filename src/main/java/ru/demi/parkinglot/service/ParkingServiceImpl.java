package ru.demi.parkinglot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demi.parkinglot.dto.TicketDto;
import ru.demi.parkinglot.entity.Ticket;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ParkingSlotService parkingSlotService;
    private final TicketService ticketService;

    public TicketDto occupyParkingSlotAndGetTicket() {
        int freeSlotNumber = parkingSlotService.occupyNextFreeParkingSlot();
        Ticket ticket = ticketService.getNewTicket(freeSlotNumber);
        return new TicketDto(ticket.getId(), ticket.getSlotNumber());
    }

    public void freeParkingSlot(long ticketId) {
        Ticket ticket = ticketService.getById(ticketId);
        Date exitDate = parkingSlotService.freeParkingSlot(ticket.getSlotNumber());
        ticket.setExitDate(exitDate);
        ticketService.saveTicket(ticket);
    }
}
