package ru.demi.parkinglot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.demi.parkinglot.dto.TicketDto;
import ru.demi.parkinglot.service.ParkingService;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/occupy-slot")
    public TicketDto occupyParkingSlot() {
        return parkingService.occupyParkingSlotAndGetTicket();
    }

    @PostMapping("/free-slot")
    @ResponseStatus(HttpStatus.OK)
    public void freeParkingSlot(@RequestParam long ticketId) {
        parkingService.freeParkingSlot(ticketId);
    }
}
