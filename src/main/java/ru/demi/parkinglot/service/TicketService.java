package ru.demi.parkinglot.service;

import ru.demi.parkinglot.entity.Ticket;

public interface TicketService {
    Ticket getNewTicket(int slotNumber);
    Ticket getById(long id);
    Ticket saveTicket(Ticket ticket);
}
