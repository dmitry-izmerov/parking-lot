package ru.demi.parkinglot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demi.parkinglot.repository.TicketRepository;
import ru.demi.parkinglot.util.DateUtil;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final TicketRepository ticketRepository;

    @Override
    public long getTotalNumberOfCarsParkedForPeriod(String from, String to) {
        Date fromDate = DateUtil.convertToDate(from);
        Date toDate = DateUtil.convertToDate(to);
        return ticketRepository.countByEntryDateBetween(fromDate, toDate);
    }

    @Override
    public double getAverageNumberOfCarsParkedForPeriodPerDay(String from, String to) {
        Date fromDate = DateUtil.convertToDate(from);
        Date toDate = DateUtil.convertToDate(to);
        return ticketRepository.getAverageForPeriodPerDay(fromDate, toDate);
    }
}
