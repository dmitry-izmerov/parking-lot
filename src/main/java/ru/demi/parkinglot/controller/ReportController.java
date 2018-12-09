package ru.demi.parkinglot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.demi.parkinglot.dto.ReportDto;
import ru.demi.parkinglot.service.ReportService;

@Api(description = "For getting reports.")
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private static final String DATE_PARAM_DESC = "value format: yyyy-MM-ddTHH:mm:ss";
    private final ReportService reportService;

    @ApiOperation(value = "Get the total number of cars parked for a period.")
    @GetMapping("/num-of-parks")
    public ReportDto getTotalNumberOfCarsParkedForPeriod(
        @ApiParam(DATE_PARAM_DESC) @RequestParam String from,
        @ApiParam(DATE_PARAM_DESC) @RequestParam String to
    ) {
        long res = reportService.getTotalNumberOfCarsParkedForPeriod(from, to);
        return new ReportDto(res);
    }

    @ApiOperation(value = "Get the average number of cars parked for a period.")
    @GetMapping("/avg-num-of-parks")
    public ReportDto getAverageNumberOfCarsParkedForPeriod(
        @ApiParam(DATE_PARAM_DESC) @RequestParam String from,
        @ApiParam(DATE_PARAM_DESC) @RequestParam String to
    ) {
        double res = reportService.getAverageNumberOfCarsParkedForPeriodPerDay(from, to);
        return new ReportDto(res);
    }
}
