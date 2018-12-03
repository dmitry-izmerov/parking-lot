package ru.demi.parkinglot.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TicketDto {
    private final long id;
    private final int slotNumber;
}
