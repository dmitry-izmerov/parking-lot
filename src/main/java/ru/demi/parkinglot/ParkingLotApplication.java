package ru.demi.parkinglot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.demi.parkinglot.entity.ParkingSlot;
import ru.demi.parkinglot.repository.ParkingSlotRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class ParkingLotApplication {

    @Value("${parking.amountOfSlots}")
    private int amountOfSlots;

    @Value("${parking.init}")
    private boolean init;


    public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

    @Bean
    public CommandLineRunner loadData(ParkingSlotRepository repository) {
	    return args -> {
	        if (init) {
                List<ParkingSlot> parkingSlots = IntStream.rangeClosed(1, amountOfSlots)
                        .mapToObj(ParkingSlot::new)
                        .collect(Collectors.toList());
                repository.saveAll(parkingSlots);
            }
        };
    }
}
