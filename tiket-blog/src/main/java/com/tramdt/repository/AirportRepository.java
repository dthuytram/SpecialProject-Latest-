package com.tramdt.repository;

import com.tramdt.model.Airport;
import com.tramdt.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT airport_id, airport_city, airport_code, airport_name  FROM ticket.airports " ,
            nativeQuery = true)
    List<Airport> findAllAir();
}
