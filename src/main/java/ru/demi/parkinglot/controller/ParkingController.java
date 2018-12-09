package ru.demi.parkinglot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.demi.parkinglot.dto.TicketDto;
import ru.demi.parkinglot.service.ParkingService;

@Api(description = "Actions with parking.")
@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @ApiOperation(value = "Occupy a slot at the parking.")
    @PostMapping("/occupy-slot")
    public TicketDto occupyParkingSlot() {
        return parkingService.occupyParkingSlotAndGetTicket();
    }

    @ApiOperation(value = "Make available the slot at the parking.")
    @PostMapping("/free-slot")
    @ResponseStatus(HttpStatus.OK)
    public void freeParkingSlot(@RequestParam long ticketId) {
        parkingService.freeParkingSlot(ticketId);
    }
}
