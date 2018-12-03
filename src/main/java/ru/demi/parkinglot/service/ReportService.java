package ru.demi.parkinglot.service;

public interface ReportService {
    long getTotalNumberOfCarsParkedForPeriod(String from, String to);
    double getAverageNumberOfCarsParkedForPeriodPerDay(String from, String to);
}
