package ru.demi.parkinglot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demi.parkinglot.entity.Ticket;
import ru.demi.parkinglot.exception.NotFoundException;
import ru.demi.parkinglot.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Ticket getNewTicket(int slotNumber) {
        Ticket ticket = new Ticket();
        ticket.setSlotNumber(slotNumber);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getById(long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Ticket with id = %d was not found", id)));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
