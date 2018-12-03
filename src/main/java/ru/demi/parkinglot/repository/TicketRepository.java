package ru.demi.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.demi.parkinglot.entity.Ticket;

import java.util.Date;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByEntryDateBetween(Date from, Date to);

    @Query(
        value = "select avg(summary.total) from (" +
                "   select count(t.entry_date) total from ticket t" +
                "   where t.entry_date > ?1 and t.entry_date < ?2" +
                "   group by cast(t.entry_date as DATE)" +
                ") summary",
        nativeQuery = true
    )
    double getAverageForPeriodPerDay(Date from, Date to);
}
