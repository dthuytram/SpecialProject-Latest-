package com.tramdt.repository;

import com.tramdt.model.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeatTypeRepository extends JpaRepository<SeatType,Long> {
}
