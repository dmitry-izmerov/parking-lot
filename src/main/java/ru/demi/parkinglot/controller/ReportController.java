package ru.demi.parkinglot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.demi.parkinglot.dto.ReportDto;
import ru.demi.parkinglot.service.ReportService;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    // TODO add swagger

    @GetMapping("/num-of-parks")
    public ReportDto getTotalNumberOfCarsParkedForPeriod(@RequestParam String from, @RequestParam String to) {
        long res = reportService.getTotalNumberOfCarsParkedForPeriod(from, to);
        return new ReportDto(res);
    }

    @GetMapping("/avg-num-of-parks")
    public ReportDto getAverageNumberOfCarsParkedForPeriod(@RequestParam String from, @RequestParam String to) {
        double res = reportService.getAverageNumberOfCarsParkedForPeriodPerDay(from, to);
        return new ReportDto(res);
    }
}
