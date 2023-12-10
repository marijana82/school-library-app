package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //---try for relation many-to-many
    //List<Reservation> findReservationsByAccountsId(Long accountId);
    List<Reservation> findAllReservationsByReservationDate(LocalDate reservationDate);

}
